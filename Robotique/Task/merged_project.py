############################################################################
# Task: test run for a robot, with phases 1-4
############################################################################

# Import libraries
from unifr_api_epuck import wrapper
import numpy as np
import os
import signal

# Set up IP
MY_IP = '192.168.2.203' # works
#MY_IP = '192.168.2.211'
robot = wrapper.get_robot(MY_IP)

# Starting state will be phase 1
running_phase = 1


############################# Phase 1 - Detection ##########################

# run the code to generate IR sensor data
# create directory
try: 
    os.mkdir("./img") 
except OSError as error: 
    print(error)

robot.initiate_model()
robot.init_camera("./img")

robot.calibrate_prox()
robot.init_tof()

# wait 3 seconds before starting
robot.sleep(3)

# enable front led (led 0) and speed
robot.enable_front_led()
robot.enable_led(7)
robot.set_speed(-1,1)

# phase 1: initiate lists to track detections and states
step = 0
roundNr = 0
round_black = [0, 0]
round_blue = [0, 0]
round_red = [0, 0]
round_green = [0, 0]

# phase 2: initiate lists to track detections and states

# values to recognise colored lines on the way to the green block
firstBlackLine = False
firstVioletLine = False
reachedInBetween = False
#meanGroundValue = 0
state = 0

# state after crossing the two lines and recalib for green block
recalibrationForGreenBlock = False
loveGreenBlock = False

# values for loving behaviour
NORM_SPEED = 0.5
MAX_PROX = 750

# calculate mean of the last 40 center values of the green block
meanXCenterList = [0] * 40
i = 0
timeForLove = 0


while robot.go_on():

	if (running_phase == 1):
		img = np.array(robot.get_camera())
		detections = robot.get_detection(img,0)

		# Iterate through detections and save them
		for item in detections:
			#data.write("{},{},{},{},{},{},{}\n".format(step,item.x_center,item.y_center,item.width,item.height,item.confidence,item.label))

			if len(detections) > 0:
				if (roundNr < 2):
					if (item.label == "Black Block"):
						round_black[roundNr] = 1
						robot.enable_led(7, 0, 0, 0)
						#print ("Black block detected")

					elif (item.label == "Blue Block" and round_black[roundNr]):
						round_blue[roundNr] = 1
						robot.enable_led(7, 0, 0, 100)
						#print ("Blue block detected")	

					elif (item.label == "Red Block" and round_blue[roundNr]):
						round_red[roundNr] = 1
						robot.enable_led(7, 100, 0, 0)
						#print ("Green block detected")

					elif (item.label == "Green Block" and round_red[roundNr]):
						round_green[roundNr] = 1
						robot.enable_led(7, 0, 100, 0)
						#print ("Green block detected")

					if (roundNr == 0 and round_black[0] and round_blue[0] and round_red[0] and round_green[0]):
						roundNr = 1
					elif(roundNr == 1 and round_black[1] and round_blue[1] and round_red[1] and round_green[1]):
						roundNr = 2

					print("Round(0) " + str(round_black[0]) + " " + str(round_blue[0]) + " " + str(round_red[0]) + " " + str(round_green[0]))
					print("Round(1) " + str(round_black[1]) + " " + str(round_blue[1]) + " " + str(round_red[1]) + " " + str(round_green[1]))

				elif (roundNr == 2):
					print("Found green block after two turns!")
					robot.set_speed(0,0)
					robot.enable_led(7, 100, 0, 0)
					robot.disable_all_led()
					
					# disable front led (of phase 1) and light up led 4
					robot.disable_front_led()
					robot.enable_led(4)

					# hand over to next phase
					running_phase = 2

					robot.init_ground()
					robot.init_sensors()
					robot.sleep(3)
					break


	############################# Phase 2 - Forward ##########################
	
	if (running_phase == 2):
		# light up led 7 when detecting green block		
		detections = robot.get_detection(np.array(robot.get_camera()),0.2)
		
		for item in detections:
			if (item.label == "Green Block"):
				robot.enable_led(7, 0, 100, 0)
			else:
				robot.disable_led(7)

		if (not firstVioletLine):
			gs = robot.get_ground()
			# get mean of the three ground sensors
			i += 1
			if i % 4:
				meanGroundValue = round((gs[0] + gs[1] + gs[2]) / 3)
				# print(meanGroundValue)
			
			# get the three sensors (sensing a black line if value < 350)
			# and set boolean states
			if (not firstBlackLine):
				firstBlackLine  = bool(meanGroundValue < 350)
			else:
				print("Black line reached.")

			# we enter white area inbetween
			if (firstBlackLine and not bool(meanGroundValue < 350)):
				print("Reached inbetween.")
				reachedInBetween = True	

			# and finally we reach the violet line
			if (firstBlackLine and reachedInBetween and bool(meanGroundValue < 900 and meanGroundValue > 700)):
				firstVioletLine = True
				loveGreenBlock = True
				# reset i concerning the next phase of color recognition
				i = 0
				print("Reached violet line - now love the green block")

			robot.set_speed(0.5,0.5)

		# now steer to the green block to love him for a certain time
		elif(loveGreenBlock):
			prox_values = robot.get_calibrate_prox()
			prox = (prox_values[0] + prox_values[7])/2
			ds = (NORM_SPEED * prox) / MAX_PROX
			print("Reached green block and loving him. ds = " + str(ds))

			timeForLove = timeForLove + 1

			speed = NORM_SPEED - ds
			robot.set_speed(speed)

			# after a certain time loving the green block, were done and can leave this state
			if (timeForLove > 150):
				print("Sufficient calibration for green block, phase 2 over.")
				print(timeForLove)
				# hand over to next phase
				running_phase = 3
				robot.disable_all_led()
				
			# disable front led (of phase 1) and light up led 4
			robot.enable_led(2)
			robot.enable_led(6)

			# initiate state information variables
			step = 0
			turn = 0
			meanDistanceRightSensor = 0
			meanDistanceRightSensorList = [0] * 80
			j = 0

			turn = 0
				
		step += 1



	############################# Phase 3 - Turning ##########################

	if (running_phase == 3 and turn < 3):
		ps = robot.get_calibrate_prox()

		# get sensor value of prox2 (sensor on the right side)
		meanDistanceRightSensorList[j] = ps[2]
		#print(sum(meanDistanceRightSensorList) / 40)

		# if distance of right sensor is smaller than the mean of this right sensor, then we reached the nearest point 
		# and can save it (= meanDistanceRightSensor)
		if (meanDistanceRightSensor < (sum(meanDistanceRightSensorList) / 40)):
			meanDistanceRightSensor = (sum(meanDistanceRightSensorList) / 40)
			print("Max of right sensor: " + str(meanDistanceRightSensor))
			
		# first turn
		if (turn == 0 and meanDistanceRightSensor > 5):
			turn = 0.5
			meanDistanceRightSensorList.clear
			meanDistanceRightSensor = 0
			turn = 1

		if (turn == 1 and meanDistanceRightSensor > 5):
			turn = 1.5

		# second turn...
		if (turn == 1.5 and (sum(meanDistanceRightSensorList) / 40) < 0.2):
			print("Second encounter and turn around.")
			meanDistanceRightSensorList.clear
			meanDistanceRightSensor = 0
			turn = 2

		# ... and halt
		if (turn == 2 and meanDistanceRightSensor > 5):
			print("Third encounter and halt")
			turn = 3
			
			# and change running phase to go to next phase
			running_phase = 4

		robot.set_speed(-0.5,0.5)

		if (j > 79):
			j = 0
		step = step + 1

		# Print the current state and mean of right sensor
		print("Turn: " + str(turn) + ", mean dist of right sensor: " + str((sum(meanDistanceRightSensorList) / 40)))
			
	robot.disable_all_led()

############################# Phase 4 - Wall following ##########################

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


if (running_phase == 4 and cornerReached < 3):
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

			# task finished
			running_phase = 5

		else:
			ds = pid.compute(proxL,PID_WALL_TARGET);
			ds += .05
			speedR = NORM_SPEED + ds
			speedL = NORM_SPEED - ds
			robot.set_speed(speedR,speedL)

			# write a line of data in log file
			# data.write("{},{},{},{},{},{},{},{},{},{}\n".format(step, K, T_I, T_D, pid.P(), pid.I(), pid.D(), ds, speedL,speedR))

	#print("current left dist = " + str(sum(meanDistanceLeftSensorList)/80) + ", current right dist = " + str(sum(meanDistanceRightSensorList)/80))

	if (j > 79):
		j = 0    
	step += 1

	robot.go_on()

robot.disable_all_led()    


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

