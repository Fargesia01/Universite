from unifr_api_epuck import wrapper

import numpy as np

def step_three(robot):
	print("### Entered phase 3. ###")

	# disable front led (of phase 1) and light up led 4
	robot.enable_led(2)
	robot.enable_led(6)

	step = 0
	turn = 0
	meanDistanceRightSensor = 0
	meanDistanceRightSensorList = [0] * 80
	j = 0

	#open file for writing
	data = open("IRsensors.csv", "w")

	if data == None:
		print('Error opening data file!\n')
		quit

	#write header in CSV file
	data.write('step,')
	for i in range(robot.PROX_SENSORS_COUNT):
		data.write('ps'+str(i)+',')
	data.write('\n')

	# wait before starting
	robot.sleep(1)

	while (turn < 3):
		ps = robot.get_calibrate_prox()

		#write a line of data 
		data.write(str(step)+',')
		for v in ps:
			data.write(str(v)+',')
		data.write('\n')

		# get sensor value of prox2 (sensor on the right side)
		meanDistanceRightSensorList[j] = ps[2]
		#print(sum(meanDistanceRightSensorList) / 40)

		# if distance of right sensor is smaller than the mean of this right sensor, then we reached the nearest point and can save it (= meanDistanceRightSensor)
		if (meanDistanceRightSensor < (sum(meanDistanceRightSensorList) / 40)):
			meanDistanceRightSensor = (sum(meanDistanceRightSensorList) / 40)
			print("Max of right sensor: " + str(meanDistanceRightSensor))
			
		if (turn == 0 and meanDistanceRightSensor > 5):
			turn = 0.5
		
		if (turn == 0.5 and (sum(meanDistanceRightSensorList) / 40) < 0.2):
			print("First encounter and turn around.")
			meanDistanceRightSensorList.clear
			meanDistanceRightSensor = 0
			turn = 1

		if (turn == 1 and meanDistanceRightSensor > 5):
			turn = 1.5

		if (turn == 1.5 and (sum(meanDistanceRightSensorList) / 40) < 0.2):
			print("Second encounter and turn around.")
			meanDistanceRightSensorList.clear
			meanDistanceRightSensor = 0
			turn = 2

		if (turn == 2 and meanDistanceRightSensor > 5):
			print("Third encounter and halt")
			turn = 3

		robot.set_speed(-0.5,0.5)

		if (j > 79):
			j = 0
		step = step + 1

		# Print the current state and mean of right sensor
		print("Turn: " + str(turn) + ", mean dist of right sensor: " + str((sum(meanDistanceRightSensorList) / 40)))

		robot.go_on()
		
	data.close()
	robot.disable_all_led()