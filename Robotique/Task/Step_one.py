from unifr_api_epuck import wrapper
import numpy as np
import time

def step_one(robot):
	print("### Entered phase 1. ###")

	# enable front led (led 0)
	robot.enable_front_led()

	done = False
	stop_next = False
	last_time = None

	data = create_file()
	
	while (not done):
		img = np.array(robot.get_camera())
		detections = robot.get_detection(img,0.2)
		robot.go_on()
		for item in detections:

			# show color of detected block
			if (item.label == "Red Block"):
				robot.enable_led(7, 100, 0, 0)
			elif (item.label == "Blue Block"):
				robot.enable_led(7, 0, 0, 100)
			elif (item.label == "Black Block"):
				robot.enable_led(7, 100, 100, 100)

			if (item.y_center < 40 or item.y_center > 50):
				continue

			data.write("{},{},{},{},{},{}\n".format(item.x_center, item.y_center, item.width, 
				item.height, item.confidence, item.label))

			if (item.label == "Green Block"):
				robot.enable_led(7, 100, 0, 0)
				last_time = time.time()
				print ("Green block detected")
			if (last_time is not None and time.time() - last_time > 2):
				stop_next = True
			if (stop_next and item.label == "Green Block"):
				print ("Stop next and green block detected")

				if (item.x_center > 79 and item.x_center < 81):
					robot.set_speed(0, 0)
					done = True
					break
				else:
					speed = 0.1				

				if (item.x_center < 80):
					robot.set_speed(-speed, speed)
				else:
					robot.set_speed(speed, -speed)
			else:
				robot.set_speed(-0.5, 0.5)
			robot.go_on()
			
	robot.disable_all_led()
	data.close()

def create_file():
	data = open("object_recog.csv", "w")

	if data == None:
	    print('Error opening data file!\n')
	    quit

	data.write('x_center,y_center,width,height,conf,label\n')
	return (data)
