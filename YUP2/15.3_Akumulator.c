#include "stdio.h"
#include "stdlib.h"

double akumulator(double (*fce)(double, double), double cisla[], int pocet);
double soucet (double a, double b);
double soucin (double a, double b);

int main()
{
	double p[10];
	int i;
   	for (i=0; i<10; i++) {
    	p[i] = i+1;
   	}

   	printf("Suma je: %g\n", akumulator(soucet,p,10));
	printf("Produkt je: %g\n", akumulator(soucin,p,10));

	return 0;
}

double soucet (double a, double b) {
	return a+b;
}

double soucin (double a, double b) {
	return a*b;
}

double akumulator(double (*fce)(double, double), double cisla[], int pocet) {
	int i;
	double result = fce(cisla[0],cisla[1]);
	for (i=2;i<pocet;i++) {
		result = fce(result,cisla[i]);
	}

	return result;
}