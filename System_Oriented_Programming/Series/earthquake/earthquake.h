#ifndef EARTHQUAKE_H
# define EARTHQUAKE_H

#define FILENAME "earthquake-multiple"

#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdbool.h>

int readFile(char *nameOfFile, double *tableToFill);
int nbrLines(char *fileName);

#endif
