/* 
Please do not change the name of this file. 

Name (of all group members): Sylvain Lott
Date: 22.03.2023
Program runs (yes or no, because): 
*/

#include <stdio.h>
#include <math.h>


void star(int n, double rint, double rext) {
    for (int i = 0; i < 5; i++) {
	int pi = (M_PI * 2.0);             // Exercise 1. Observe the value of i
        double angle = pi / (n * i);    // Exercise 1. Observe the value of angle
        double x = cos(angle) * rext;		  // Exercise 2. Replace the data type double of x by int
        double y = sin(angle) * rext;
        printf("%0.1f, %0.1f\n", x, y);

        angle = M_PI * 2.0 / n * (i + 0.5);   // Exercise 1. Observe the value of angle
        x = cos(angle) * rint;
        y = sin(angle) * rint;
        printf("%0.1f, %0.1f\n", x, y);
    }
}

int main(int argc, char * argv[]) {
	star(5, 50, 100);
    return 0;
}

/*

	A :

b 15
b 20
commands 1-2
silent
printf "\ni = %d\n", i
printf "angle = %f\n", angle
printf "x = %f\n", x
printf "\n"
continue
end
run
*/

// B:

 // Le programme a crash à la ligne 16, et le signal envoyé est SIGFPE (Floating Point Exception, correspond à toutes les erreurs d'arithmétoque style division par 0)
