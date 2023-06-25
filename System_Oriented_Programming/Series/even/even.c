#include <stdio.h>

int	main()
{
	int	sum = 0;
	for(int i = 2; i < 22; i += 2)
	{
		printf("%d\n", i);
		sum += i;
	}
	printf("The sum is %d\n", sum);
}
