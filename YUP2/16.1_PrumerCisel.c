#include "stdio.h"
#include "stdlib.h"
#include "stdarg.h"

long double prumer(char* format, ...);

int main()
{
	long double pr = prumer("idld", 1, (double)3, (long double)2, 3.0);
	if (pr == -1) {
		printf("Nepovoleny znak v parametru format.\n");
	}
	else {
		printf("Prumer je %Lf. \n", pr);
	}

	return 0;
}

long double prumer(char* format, ...) {
	long double vysledek = 0.0;
	va_list zasobnik;
	int i = 0;
	va_start(zasobnik,format);

	while(format[i]) {
		switch (format[i]) {
			case 'i': 
				vysledek += (long double) va_arg(zasobnik, int);
				break;
			case 'd': 
				vysledek += (long double) va_arg(zasobnik, double);
				break;
			case 'l': 
				vysledek += (long double) va_arg(zasobnik, long double);
				break;
			default:
				va_end(zasobnik);
				return -1;
		}
		i++;
	}
	va_end(zasobnik);

	return vysledek/i;
}