#ifndef DATASTRUCTURE_H
#define DATASTRUCTURE_H

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <pthread.h>

// Define the maximal number we store data in form of jsons, as large as possible
#define MAX_JSONS 23001

// Define the maximal time we wait to push the data on the API (Set at 3600)
#define MAX_TIME 120

typedef struct
{
	int		time;
	int		index_jsons;
	char**		table_jsons;
	float		lux;
}		Datastructure;

Datastructure	*create_Datastructure();
char		*getJsonChar(Datastructure *data, float value);
void		destroy_Datastructure(Datastructure *data);
void		filling_datastructure(float value, Datastructure* data_struct);

#endif
