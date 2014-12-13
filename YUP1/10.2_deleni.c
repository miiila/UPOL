#include "stdio.h"
#include "stdlib.h"

int deleni(int a, int b, int *r);

int main()
{
	int cislo1 = 13,cislo2 = 4, r;
	printf("%i : %i = %i (zbytek %i).\n",cislo1,cislo2,deleni(cislo1,cislo2,&r),r);

	return 0;
}

int deleni(int a, int b, int *r) {
	int c = 0;
	while(a>b) {
		a -= b;
		c++;
	}
	*r = a == 0 ? b : a;

	return c;
}