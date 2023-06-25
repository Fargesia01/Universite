#include "gps.h"

int	main()
{
	t_gps	coor[NBR_POINTS];
	t_cart	cart[NBR_POINTS];
	double sum = 0;

	if (readFile("creux-du-van.csv", coor, NBR_POINTS) == -1)
		return (1);
	for (int i = 0; i < 10; i++)
	{
		printf("%d : ", i);
		display(coor[i]);
	}
	printf("\nThe altitude difference is : %f\n", alt_diff(coor, NBR_POINTS));
	printf("\nThe total rise is : %f\n", rise(coor, NBR_POINTS));
	convert(coor, cart, NBR_POINTS);
	for (int i = 1; i < NBR_POINTS; i++)
	{
		sum += distance_t(cart[i - 1], cart[i]);
	}
	printf("\n Total distance is : %f\n", sum);
	printf("\n The maximum slope is : %d%%\n", slope(coor, cart, NBR_POINTS));
	return (0);
}

void	display(t_gps coor)
{
	printf("Latitude: %f  Longitude: %f  Altitude: %f\n",
		 coor.lat, coor.lon, coor.alt);
	return ;
}

int	readLine(char *line, t_gps *coor)
{
	char	list[3][100] = {0};
	int	c = 0;
	int	j = 0;
	
	if (check_error(line))
		return (error());
	for (size_t i = 0; i < strlen(line); i++, j++)
	{
		if (line[i] == ',')
		{
			c++;
			j = 0;
			i++;
		}
		list[c][j] =line[i];
	}
	if (!(coor->lat = atof(list[0])))
		return (error());
	if (!(coor->lon = atof(list[1])))
		return (error());
	if (!(coor->alt = atof(list[2])))
		return (error());
	return (0);
}

int readFile(char *fileName, t_gps *arrayToFill, int length)
{
    FILE * file = fopen(fileName, "r");
    int n = 0;
    char buffer[100];
    
    if (file == NULL) 
	    return (-1);
    while (fgets(buffer, 100, file) != NULL)
    {
        if (n >= length) 
		break ;
        int ok = readLine(buffer, &arrayToFill[n]);
        if (!ok) 
		n++;
    }

    fclose(file);
    return (n);
}
