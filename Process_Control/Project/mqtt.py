import paho.mqtt.client as mqtt
import json

MSG_CONNECT = r'{{"type": "connect", "payload": {{"value": {}}}}}' # value: { true | false }

VEHICLE_ID = 'ef7e50abbe16' # TODO: auto-discover, and multiple vehicles using list
TOPIC_VEHICLE_I = 'Anki/Vehicles/U/{}/I/'.format(VEHICLE_ID)
TOPIC_VEHICLE_S = 'Anki/Vehicles/U/{}/S'.format(VEHICLE_ID)

status = {
	"lights_front" : "default_value",
	"lights_back" : "default_value",
	"speed_velo" : 0,
	"speed_accel" : 0,
	"lane_offset" : 0,
	"lane_velo" : 0,
	"lane_accel" : 0
}

def on_connect(client, userdata, flags, rc):
	if (rc == 0):
		print("Success connecting to Broker!")
	elif (rc == 1):
		print("Failed to connect to Broker... Error code: %d", rc)

def on_message(client, userdata, msg):
	global status
	#print(msg.topic+" "+str(msg.payload))
	print("Message received")

	payload = json.loads(msg.payload)

	if msg.topic == TOPIC_VEHICLE_S + '/intended/lights/front':
		status["lights_front"] = payload['value']
	elif msg.topic == TOPIC_VEHICLE_S + '/intended/lights/back':
		status["lights_back"] = payload['value']
	elif msg.topic == TOPIC_VEHICLE_S + '/intended/speed':
		status["speed_velo"] = payload['velocity']
		status["speed_accel"] = payload['acceleration']
	elif msg.topic == TOPIC_VEHICLE_S + '/intended/lane':
		status["lane_offset"] = payload['offset']
		status["lane_velo"] = payload['velocity']
		status["lane_accel"] = payload['acceleration']

def on_disconnect(client, userdata, rc):
	print("Disconnected with result code %d", rc)


def connect():
	client = mqtt.Client(client_id="T490", clean_session=True)
	client.on_connect = on_connect
	client.on_message = on_message
	client.connect_async("192.168.4.1", 1883, 60)
	client.loop_start()
	client.loop_forever()
	client.subscribe(TOPIC_VEHICLE_S+'/#')
	msg = MSG_CONNECT.format("true")
	client.publish(TOPIC_VEHICLE_I, msg)
	return client

def disconnect(client):
	msg = MSG_CONNECT.format("false")
	client.publish(TOPIC_VEHICLE_I, msg)
	client.loop_stop()
	exit()
