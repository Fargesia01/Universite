#include <stdio.h>
#include <math.h>
#include <unistd.h>
#include <stdlib.h>

void	compute_star(int nbr, int i_radius, int o_radius);

int	main(int argc, char **argv)
{
	if (argc != 1 && argc != 4)
		return (write(1, "Wrong number of arguments\n", 26));
	if (argc == 1)
		compute_star(5, 50, 100);
	else
		compute_star(atoi(argv[1]), atoi(argv[2]), atoi(argv[3]));
	return (0);
}

void	compute_star(int nbr, int i_radius, int o_radius)
{
	int	c = 0;
	double	x = 0;
	double	y = 0;
	double	angle;

	for (int i = 0; i < nbr * 2; i++)
	{
		angle =  i * (360 / (nbr * 2)) * M_PI / 180.0;
		if (i % 2 == 0)
		{
			y = cos(angle) * o_radius;
			x = sin(angle) * o_radius;
		}
		else
		{
			x = sin(angle) * i_radius;
			y = cos(angle) * i_radius;
		}
		c++;
		printf("Point number %d is located at: %f %f\n", c, x, y);
	}

