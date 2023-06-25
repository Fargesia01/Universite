#include "Datastructure.h"

// Allocates the memory needed for the datastructure
// and initiates the variables
Datastructure *create_Datastructure()
{
	Datastructure *data = (Datastructure *) calloc(1, sizeof(Datastructure));

	if (!data) 
		return (NULL);
	
	data->time = 0;
	data->index_jsons = 0;
	data->table_jsons = (char**) calloc(MAX_JSONS, sizeof(char*));
	if(!data->table_jsons)
	{
		destroy_Datastructure(data);
		return (NULL);
	}
	return (data);
}

// Function that frees and sets to null the whole datastructure, even if not fully filled yet
// 1. Frees every table_jsons index
// 2. Frees the table_jsons
// 3. Frees the data structure
void destroy_Datastructure(Datastructure *data)
{
	if (!data) 
		return ;
	if (!data->table_jsons)
	{
		free(data);
		return ;
	}

	for (int i = 0; i < MAX_JSONS; i++)
	{
		if (!data->table_jsons[i])
			break ;
		free(data->table_jsons[i]);
		data->table_jsons[i] = NULL;
	}
	free(data->table_jsons);
	data->table_jsons = NULL;
	free(data);
}


// Returs a string with the time and the value formatted as json
// We first get the length of the value and the time in char by getting the return value of snprintf (c.f man snprintf)
// We the calloc the length needed + 1 for the terminating 0
// Then we just format the char* as a json
char	*getJsonChar(Datastructure *data, float value)
{
	int	time_len = snprintf(NULL, 0, "%d", data->time);
	int	value_len = snprintf(NULL, 0, "%g", value);

	char	*jsonChar = (char *)calloc(1, 4 + time_len + value_len);
	if (!jsonChar)
		return (NULL);

	jsonChar[0] = '[';
	snprintf(&jsonChar[1], time_len + 1, "%d", data->time);
	jsonChar[time_len + 1] = ',';
	snprintf(&jsonChar[time_len + 2], value_len + 1, "%g", value);
	jsonChar[time_len + value_len + 2] = ']';
	return (jsonChar);
}

// Adds a value into the datastructure and then prints all the values stored in the Serial
void filling_datastructure(float value, Datastructure* data)
{
	if (data->table_jsons[data->index_jsons])
		free(data->table_jsons[data->index_jsons]);
	data->table_jsons[data->index_jsons] = getJsonChar(data, value);
	data->index_jsons += 1;
	if (data->index_jsons == MAX_JSONS)
		data->index_jsons = 0;
}
