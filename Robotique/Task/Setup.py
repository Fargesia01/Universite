import os
import time
import signal


from unifr_api_epuck import wrapper

def setup(robot):
	
	# create directory
	try: 
	    os.mkdir("./img") 
	except OSError as error: 
	    print(error) 

	robot.initiate_model()
	robot.init_camera("./img")
	robot.init_ground()
	robot.init_sensors()
	robot.calibrate_prox()
	robot.init_tof()
	robot.sleep(1)
