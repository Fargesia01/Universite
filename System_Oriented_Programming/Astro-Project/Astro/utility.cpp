#include "utility.h"

// Simple method to print all values stored
void	print_data(Datastructure *data)
{
	int	counter = 0;
	Serial.println("Value in json table: ");
	for (int pos = data->index_jsons; counter < MAX_JSONS && data->table_jsons[pos]; pos--, counter++)
	{
		if (pos < 0)
			pos = MAX_JSONS - 1;
		Serial.println(data->table_jsons[pos]);
	}
}

// Prints the VEML gain
void	print_gain(Adafruit_VEML7700 veml)
{
	Serial.print(F("Gain: "));
	switch (veml.getGain())
	{
		case VEML7700_GAIN_1: Serial.println("1"); break;
		case VEML7700_GAIN_2: Serial.println("2"); break;
		case VEML7700_GAIN_1_4: Serial.println("1/4"); break;
		case VEML7700_GAIN_1_8: Serial.println("1/8"); break;
	}
}

// Prints the VEML IT
void print_IT(Adafruit_VEML7700 veml)
{
	Serial.print(F("Integration Time (ms): "));
	switch (veml.getIntegrationTime())
	{
		case VEML7700_IT_25MS: Serial.println("25"); break;
		case VEML7700_IT_50MS: Serial.println("50"); break;
		case VEML7700_IT_100MS: Serial.println("100"); break;
		case VEML7700_IT_200MS: Serial.println("200"); break;
		case VEML7700_IT_400MS: Serial.println("400"); break;
		case VEML7700_IT_800MS: Serial.println("800"); break;
	}
}

// Sets up the veml
void	setup_veml(Adafruit_VEML7700 *veml)
{
	// Set Gain=2, IT=800ms for dark lighting conditions
	//veml->setGain(VEML7700_GAIN_2);
	//veml->setIntegrationTime(VEML7700_IT_800MS);
	print_gain(*veml);
	print_IT(*veml);

	// Set the thresholds if lighting conditions changes
	veml->setLowThreshold(10000);
	veml->setHighThreshold(20000);
	veml->interruptEnable(true);
}
