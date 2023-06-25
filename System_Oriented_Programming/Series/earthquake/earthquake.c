#include "earthquake.h"

int	main()
{
	double	*tab = calloc(nbrLines(FILENAME) + 1,  sizeof(double));
	bool	on = false;
	int	count = 0;
	double	energy = 0;

	readFile(FILENAME, tab);
	for (int i = 0; i < nbrLines(FILENAME); i++)
	{
		if (!on && tab[i] > 20.0)
		{
			on = true;
			printf("Start: %d\n", i);
		}
		else if (on && tab[i] < 20.0)
			count++;
		else if (on && tab[i] > 20.0)
			count = 0;
		if (on == true)
			energy += pow(tab[i], 2);
		if (count >= 200)
		{
			count = 0;
			on = false;
			printf("Energy: %f\n", energy);
			printf("End: %d\n\n", i);
		}
	}
	free(tab);
}

int	nbrLines(char *fileName)
{
	FILE 	*fp = fopen(fileName, "r");
	int	nbrLines = 0;
	char	ch = 0;

	if (fp == NULL)
	{
		fprintf(stderr, "Error: unable to open the file '%s'\n", fileName);
		exit(1);
	}
	while ((ch = fgetc(fp)) != EOF)
	{
		if (ch == '\n')
			nbrLines++;
	}
	fclose(fp);
	return (nbrLines);
}

int readFile(char *nameOfFile, double *tableToFill) 
{
    FILE *file = fopen(nameOfFile, "r");
    int	n = 0;
    char buffer[100];
    
    if (file == NULL) 
    {
        fprintf(stderr, "Error: unable to open the file '%s'\n", nameOfFile);
        exit(1);
    }
    while (fgets(buffer, 100, file) != NULL) 
    {
        tableToFill[n] = atof(buffer);
        n++;
    }
    fclose(file);
    return (n);
}

