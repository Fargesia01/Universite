#include <stdio.h>
#include <time.h>
#include <stdlib.h>

int	main()
{
	double	nbr;
	double	nbr2;

	srandom(time(NULL));
	nbr = random() / (RAND_MAX + 1.0) * 6;
	nbr2 = random() / (RAND_MAX + 1.0) * 6;
	if ((int)nbr + (int)nbr2 == 12)
		printf("Vous avez gagné!\n");
	else if ((int)nbr == (int)nbr2)
		printf("Pas mal!\n");
	else
		printf("Pas de chance!\n");
}
