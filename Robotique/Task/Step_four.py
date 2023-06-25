from unifr_api_epuck import wrapper
import os # for log files
import signal

def handler(signum, frame):
	robot.clean_up()

signal.signal(signal.SIGINT, handler)

# general parameters
PID_MAX_DS = 1.5
NORM_SPEED = 1
PID_WALL_TARGET = 100

# changed PID sensors (weakened front sensor, weight a from 4 to 0.2, and increased b from 2 to 3)
#a = 4
a = 0.2
#b = 2
b = 3
c = 1
d = 0

FRONT_WALL_TARGET = 10

# PID parameters
K = 0.007
T_D = 0.25
T_I = 9999999999  #optional


def step_four(robot):
    print("### Entered phase 4. ###")
    # open file for writing (adding a number if already exists)
    n = 0
    while os.path.exists("logPID_{}.csv".format(n)):
        n += 1
    data = open("logPID_{}.csv".format(n), "w")

	if data == None:
		print('Error opening data file!\n')
		quit

	step = 0

	#write header in CSV file
	data.write('step,K,T_I,T_D,P,I,D,ds,left speed,right speed\n')

	# create pid instance
	pid = PID( K, T_I, T_D)

	robot.sleep(1)

	# lists to calculate the mean of left, right and front sensors (as to be able to stop after turning in corners)
	proxFront = 0
	proxR = 0
	proxL = 0
	meanDistanceRightSensorList = [0] * 80
	meanDistanceLeftSensorList = [0] * 80
	meanDistanceFrontSensorList = [0] * 80
	j = 0
	cornerReached = 0
	distTof = 0


	while(cornerReached < 3):
		ps = robot.get_calibrate_prox()

		# calculate right sensor data and save it in list to calculate mean
		proxR = ((a * ps[0] + b * ps[1] + c * ps[2] + d * ps[3]) / (a+b+c+d))
		meanDistanceRightSensorList[j] = proxR

		# calculate left sensor data and save it in list to calculate mean
		proxL = ((a * ps[7] + b * ps[6] + c * ps[5] + d * ps[4]) / (a+b+c+d))
		meanDistanceLeftSensorList[j] = proxL

		# additional prox calc for front - to recognize corner and then stop
		proxFront = ((ps[0] + ps[7]))/2
		meanDistanceFrontSensorList[j] = proxFront

        # get TOF
        distTof = robot.get_tof()
        # print("Distance (TOF): " + str(distTof))

        if (cornerReached == 0):
            robot.enable_led(2)

            # if we approach first corner, we stop and turn
            if (distTof < 100 and distTof > 45):
                robot.set_speed(NORM_SPEED,NORM_SPEED)
                # special led state for additional sensor
                robot.enable_led(0)
                robot.enable_led(2)
                robot.enable_led(6)
            elif (distTof < 45):
                # save state to turn
                cornerReached = 1

				# get last distance of right side to the wall
				LAST_MEAN_DIST_R = (sum(meanDistanceRightSensorList)/80)

                robot.set_speed(0,1)
                print("Prox front: " + str(meanDistanceFrontSensorList[j]/80))
                print("Robot halted. State reached: " + str(cornerReached))

                # clear sensor data for more robust turn
                meanDistanceFrontSensorList.clear
            else:
                ds = pid.compute(proxR,PID_WALL_TARGET);
                ds += .05
                speedR = NORM_SPEED + ds
                speedL = NORM_SPEED - ds
                robot.set_speed(speedL,speedR)

        # Turning the robot after reaching the first corner
        if (cornerReached == 1):

            robot.set_speed(-2,2)
            robot.disable_led(2)
            print("Last prox right = " + str(LAST_MEAN_DIST_R) + ", current norm left = " + str(sum(meanDistanceLeftSensorList)/80))

            # if diff between saved distance and new mean distance is under a certain treshhold, we now we turned fully and can stop the robot
            if ((abs(sum(meanDistanceLeftSensorList)/80 - LAST_MEAN_DIST_R)) < 1):
                cornerReached = 2
                print("Turn complete. State reached: " + str(cornerReached))
                robot.enable_led(6)
                # turn a little to the left to be sure to follow wall
                robot.set_speed(-1,1)
                meanDistanceFrontSensorList.clear
        
        if (cornerReached == 2):
            print("Prox front: " + str(meanDistanceFrontSensorList[j]/80))

            if (distTof < 100 and distTof > 45):
                robot.set_speed(NORM_SPEED,NORM_SPEED)
                # special led state for additional sensor
                robot.enable_led(0)
                robot.enable_led(2)
                robot.enable_led(6)

            # check if we reach second corner, then halt and we have finished all phases! :)
            elif (distTof < 45):
                # save state to turn
                cornerReached = 3
                robot.set_speed(0,0)
                print("Robot halted. State reached: " + str(cornerReached))

                # light up all Red LEDs (0/2/4/6) + body LED
                robot.enable_led(0)
                robot.enable_led(2)
                robot.enable_led(4)
                robot.enable_led(6)
                robot.enable_body_led()

            else:
                ds = pid.compute(proxL,PID_WALL_TARGET);
                ds += .05
                speedR = NORM_SPEED + ds
                speedL = NORM_SPEED - ds
                robot.set_speed(speedR,speedL)

                # write a line of data in log file
                data.write("{},{},{},{},{},{},{},{},{},{}\n".format(step, K, T_I, T_D, pid.P(), pid.I(), pid.D(), ds, speedL,speedR))

		#print("current left dist = " + str(sum(meanDistanceLeftSensorList)/80) + ", current right dist = " + str(sum(meanDistanceRightSensorList)/80))

		if (j > 79):
			j = 0	
		step += 1

        robot.go_on()

    robot.disable_all_led()    
    data.close()


class PID:
	TIME_STEP = 64

	def __init__(self, k, t_i, t_d):
		self.error = 0
		self.deriv = 0
		self.integ = 0
		self.K = k
		self.T_I = t_i
		self.T_D = t_d

	def compute(self,prox,target):	
		prev_err = self.error
		self.error = prox - target
		self.deriv = (self.error - prev_err)*1000/self.TIME_STEP
		self.integ += self.error*self.TIME_STEP/1000

		#return self.K * ( self.error + 1.0 / self.T_I * self.integ + self.T_D * self.deriv)
		return self.P() + self.I() + self.D()

	def P(self) :
		return self.K * self.error	

	def I(self) :
		return self.K * (self.integ/self.T_I)	

	def D(self) :
		return self.K * (self.T_D * self.deriv)	
