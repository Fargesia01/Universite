#include "gps.h"

int	slope(t_gps *coor, t_cart *cart, int nbr)
{
	double	max = 0;
	double	tmp = 0;

	for (int i = 1; i < nbr; i++)
	{
		tmp = (coor[i].alt - coor[i - 1].alt) / distance_d(cart[i - 1], cart[i]);
		if (tmp > max)
		{
			max = tmp;
		}
	}
	return ((int)(max * 100.0));
}

double	distance_t(t_cart src, t_cart dst)
{
	return (sqrt(pow(dst.x - src.x, 2) + pow(dst.z - src.z, 2) + pow(dst.y - src.y, 2)) / 1000.0);
}

double	distance_d(t_cart src, t_cart dst)
{
	return (sqrt(pow(dst.x - src.x, 2) + pow(dst.y - src.y, 2)));
}

void	convert(t_gps *coor, t_cart *cart, int nbr)
{
	for (int i = 0; i < nbr; i++)
	{
		cart[i].x = (R_EARTH + coor[i].alt) * cos(radians(coor[i].lat)) 
			* cos(radians(coor[i].lon));
		cart[i].y = (R_EARTH + coor[i].alt) * cos(radians(coor[i].lat)) 
			* sin(radians(coor[i].lon));
		cart[i].z = (R_EARTH + coor[i].alt) * sin(radians(coor[i].lat));
	}
	return ;
}

float	alt_diff(t_gps *coor, int nbr)
{
	return (coor[nbr - 1].alt - coor[0].alt);
}

float	rise(t_gps *coor, int nbr)
{
	float	rise = 0;

	for (int i = 1; i < nbr; i++)
	{
		if (coor[i - 1].alt < coor[i].alt)
			rise += coor[i].alt - coor[i - 1].alt;
	}
	return (rise);
}

double	radians(double deg)
{
	return (deg * M_PI / 180);
}
