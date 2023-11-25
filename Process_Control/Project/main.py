import paho.mqtt.client as mqtt
import threading
import json
import time
from mqtt import *

# Payloads
MSG_DISCOVER = r'{{"type": "discover", "payload":{{"value": {}}}}}' # value: { true | false }
MSG_SPEED = r'{{"type": "speed", "payload": {{"velocity": {}, "acceleration": {}}}}}' # speed: { -100..2000 }, accel: { 0..2000 }
MSG_LANE = r'{{"type": "lane", "payload": {{"offset": {}, "velocity": {}, "acceleration": {}}}}}' # offset: { -1000..1000 }, velocity: { 0..1000 }, accel: { 0..2000 }
MSG_LIGHTS = r'{{"type": "lights", "payload": {{"front": "{}", "back": "{}"}}}}' # front: { off | on }, back: { off | on | flicker }

# Topics
VEHICLE_ID = 'ef7e50abbe16' # TODO: auto-discover, and multiple vehicles using list
TOPIC_VEHICLE_I = 'Anki/Vehicles/U/{}/I/'.format(VEHICLE_ID)
TOPIC_VEHICLE_S = 'Anki/Vehicles/U/{}/S'.format(VEHICLE_ID)

# Global Variables

def thread_1():
	global status
	while True:
		time.sleep(1)
		if status["lights_front"] == "on":
			msg = MSG_LIGHTS.format("off", "off")
			client.publish(TOPIC_VEHICLE_I, msg)
		else:
			msg = MSG_LIGHTS.format("on", "on")
			client.publish(TOPIC_VEHICLE_I, msg)

def thread_2():
	global status
	msg = MSG_SPEED.format(500, 500)
	client.publish(TOPIC_VEHICLE_I, msg)
	while True:
		time.sleep(3)
		status["speed_velo"] = status["speed_velo"] + 250 
		if status["speed_velo"] > 1500:
			status["speed_velo"] = 0
		msg = MSG_SPEED.format(status["speed_velo"], 500)
		client.publish(TOPIC_VEHICLE_I, msg)



# Main
client = connect()

t1 = threading.Thread(target=thread_1).start()
#t2 = threading.Thread(target=thread_2).start()

user_input = input("Press enter to stop\n")
if user_input == "":
	disconnect(client)





# 1. Choose a Computer Language
# 2. ...Using an MQTT-Client-API (paho)...
# 3. Connect to the MQTT-Broker of hyperdrive
## 4. Subscribe to a host so you can see the discovered vehicles
# 4. Let the host discover the vehicles
# 5. Choose 'your' vehicles
# 6. Subscribe to 'your' vehicle so you can see its online status
# 7. Connect 'your' vehicle
# 8. go in a loop and let it blink once a second (forever)
# 
# 
# Exercise 2 (possibly in group)
# 
# While your Exercise 1 is running...
# Do the same as you have done for exercise 1 (if necessary)
# go in a loop and let your car drive with different velocities changing every 3 seconds (forever)
# 
# 
# Exercise 3 (possibly in group)
# 
# While your Exercise 1 and 2 is running...
# Do the same as you have done for exercise 1 (if necessary)
# go in a loop and let your car drive on different lanes changing every 5 seconds (forever)

