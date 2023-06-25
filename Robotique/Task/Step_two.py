from unifr_api_epuck import wrapper

import numpy as np

def step_two(robot):
	print("### Entered phase 2. ###")
	done = False

	# disable front led (of phase 1) and light up led 4
	robot.disable_front_led()
	robot.enable_led(4)

	# values to recognise colored lines on the way to the green block
	firstBlackLine = False
	firstVioletLine = False
	reachedInBetween = False
	meanGroundValue = 0
	i = 0

	# state after crossing the two lines and recalib for green block
	recalibrationForGreenBlock = False
	loveGreenBlock = False

	# values for loving behaviour
	NORM_SPEED = 0.5
	MAX_PROX = 750

	# calculate mean of the last 40 center values of the green block
	meanXCenterList = [0] * 40
	i = 0
	TIME_FOR_LOVE = 0

	while (not done):
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
			if (firstBlackLine and reachedInBetween and bool(meanGroundValue < 850 and meanGroundValue > 750)):
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

			TIME_FOR_LOVE += 1

			speed = NORM_SPEED - ds
			robot.set_speed(speed)

			# after a certain time loving the green block, were done and can leave this state
			if (TIME_FOR_LOVE > 200):
				done = True

		robot.go_on()

	robot.disable_all_led()