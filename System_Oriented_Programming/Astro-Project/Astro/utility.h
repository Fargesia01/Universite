#ifndef UTILITY_H
# define UTILITY_H

#include "Datastructure.h"
#include "Adafruit_VEML7700.h"
#include "M5Atom.h"

void	print_gain(Adafruit_VEML7700 veml);
void	print_IT(Adafruit_VEML7700 veml);
void	setup_veml(Adafruit_VEML7700 *veml);
void	print_data(Datastructure *data);

#endif
