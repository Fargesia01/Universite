#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

void swap_str(char **s1, char **s2)
{
	char tmp[strlen(*s1)];

	strcpy(tmp, *s1);
	*s1 = calloc(1, strlen(*s2) + 1);
	strcpy(*s1, *s2);
	*s2 = calloc(1, strlen(tmp) + 1);
	strcpy(*s2, tmp);
}

void swap_char(char *c1, char *c2)
{
	char tmp = *c1;

	*c1 = *c2;
	*c2 = tmp;
}

int main()
{
	char c1 = 'a', c2 = 'b';
	char *s1 = "aa";
	char *s2 = "bb";

	swap_str(&s1, &s2);
	printf("s1 : %s\n", s1);
	printf("s2 : %s\n", s2);

	swap_char(&c1, &c2);
	printf("%c\n", c1);
	printf("%c\n", c2);
}
