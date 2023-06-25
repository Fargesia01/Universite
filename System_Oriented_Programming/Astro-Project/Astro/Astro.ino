#include "Datastructure.h"
#include "client.h"
#include "utility.h"
#include <pthread.h>

Adafruit_VEML7700 veml = Adafruit_VEML7700();
Datastructure* data;
pthread_t thread;
pthread_mutex_t mutex;

// Create 2 char strings with the name of the SSID and password of the hotspot network of the phone
const char* ssid = "HP Hotspot";
const char* password = "djfx8303";

// Create a client for the API on the M5 Atom Lite
// Use the Webserver/HTTP Port 80
// Define header of function that is defined at the end of this file
WiFiClient client;
WiFiServer server(80);
boolean connect_Wifi();

void setup(){ 
	// Define the wifi mode to station (STA) to connect to an existing network (here: hotspot)
	// PS: If we choose access point (AP) we create a new network
	// Disconnects -> wait 1s -> re-connect -> start server
	WiFi.mode(WIFI_STA);
	WiFi.disconnect();
	delay(1000);
	connect_Wifi(ssid, password);
	server.begin();

	M5.begin(true, true, true);
	Serial.begin(115200);
	while (!Serial)
		delay(10);
	Serial.println("Adafruit VEML7700 Test");

	while (!veml.begin())
	{
		Serial.println("Sensor not found");
		delay(1000);
	}
	Serial.println("Sensor found");
	setup_veml(&veml);
	data = create_Datastructure();
	pthread_mutex_init(&mutex, NULL);
	pthread_create(&thread, NULL, &record, (void *)data);
}

void loop()
{
	wl_status_t wifi_Status = handle_wifi(ssid, password);

	client = server.available(); 
	if (!client)
	{
		Serial.println("Client is unavailable");
		delay(1000);
		return ;
	}
	
	String currentLine = "";		
	boolean currentLineIsBlank = true;
	while (client.connected())
	{
		if (!client.available())
			return ;
		// Read the next byte of server which the client is connected to (since the last call of read)
		char c = client.read();

		// Writes binary data to serial port
		Serial.write(c);

		// If new line and current line is empty
		// PS: All different println are here to send data to server which the client is connected to
		if (c == '\n' && currentLineIsBlank)
		{
			print_client_header(client);
			pthread_mutex_lock(&mutex);
			print_client_data(client, data);
			print_client_tail(client, data->lux);
			pthread_mutex_unlock(&mutex);
			break;
		}
		if (c == '\n')
			currentLineIsBlank = true;
		else if (c != '\r') 
			currentLineIsBlank = false;
	}
	delay(500);
}

void	*record(void *arg)
{
	Datastructure *data = (Datastructure *)arg;

	while (1)
	{
		pthread_mutex_lock(&mutex);
		data->lux = veml.readLux();
		filling_datastructure(data->lux, data);
		data->time++;
		Serial.print("index of jsons: ");
		Serial.println(data->index_jsons);
		pthread_mutex_unlock(&mutex);

		// Print line, if we change the lighting conditions
		uint16_t irq = veml.interruptStatus();
		if (irq & VEML7700_INTERRUPT_LOW)
			Serial.println("** Low threshold");
		if (irq & VEML7700_INTERRUPT_HIGH)
			Serial.println("** High threshold");
		delay(1000);
	}
}
