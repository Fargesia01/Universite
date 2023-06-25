#ifndef GPS_H
#define GPS_H

#define NBR_POINTS 170
#define R_EARTH 6378100

#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

typedef struct	GpsPoint
{
	double	lat;
	double	lon;
	float	alt;
}		t_gps;

typedef struct	Cartesian
{
	 double	x;
	 double	y;
	 double	z;
}		t_cart;

void	convert(t_gps *coor, t_cart *cart, int nbr);
void	fill_zero(char **list);
void	display(t_gps coor);
int	error();
int	readLine(char *line, t_gps *coor);
int	readFile(char *fileName, t_gps *arrayToFill, int length);
int	check_error(char *line);
int	ft_index(char *line, char c, int nbr);
int	slope(t_gps *coor, t_cart *cart, int nbr);
float	alt_diff(t_gps *coor, int nbr);
float	rise(t_gps *coor, int nbr);
double	radians(double deg);
double	distance_t(t_cart src, t_cart dst);
double	distance_d(t_cart src, t_cart dst);

#endif
