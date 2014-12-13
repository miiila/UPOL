#include<stdio.h>

int main()
{
	float a,b,c,max;

	printf("Zadejte prvni cislo:\n");
	scanf("%g",&a);
	printf("Zadejte druhe cislo:\n");
	scanf("%g",&b);
	printf("Zadejte treti cislo:\n");
	scanf("%g",&c);

	if (a > b && a > c ) {
			max = a;
	}
	else if (b > c) {
		max = b;
	}
	else {
		max = c;
	}

	printf("Maximum je %g\n",max);
	return 0;
}