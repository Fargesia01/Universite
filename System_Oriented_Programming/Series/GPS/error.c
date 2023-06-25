#include "gps.h"

int check_error(char *line)
{
	int	c = 0;

	for (size_t i = 0; i < strlen(line); i++)
	{
		if (line[i] != 46 && line[i] != 44 && line[i] != 13 && 
			 line[i] != 10 && (line[i] > 57 || line[i] < 48))
		{
			printf("%d\n", line[i]);
			return (1);
		}
		if (line [i] == 44)
			c++;
		if (c > 2)
			return (1);
	}
	return (0);
}

int	error()
{
	printf("Error: wrong coordinates format.\n");
	return (1);
}
