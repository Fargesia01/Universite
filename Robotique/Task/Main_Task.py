from unifr_api_epuck import wrapper
from Setup import *
from Step_one import *
from Step_two import *
from Step_three import *
from Step_four import *

MY_IP = '192.168.2.201'
robot = wrapper.get_robot(MY_IP)

def main():
	setup(robot)
	# step_one(robot)
	step_two(robot)
	step_three(robot)
	step_four(robot)

	robot.clean_up()

if __name__ == "__main__":
	main()
