#include "stdio.h"

int main() {
	int max;

	printf("Zadejte maximum:");
	scanf("%i",&max);

	int array[max];
	//inicializace pole
	for (int i = 2; i <= max; i++) {
		array[i] = 1;
	}

	//Eratosthenovo sito
	for (int i = 2 ; i <= max; i++) {
		if (array[i]) {
			printf("%i,",i);
			//dvojnasobek prave zkoumaneho cisla je nejmensi cislo, 
			//kde ma smysl zacit
			for (int j = i*2; j <= max; j++) {
				if ((array[j]) && (j % i == 0)) {
					array[j] = 0;
				}
			}
		}
	}

}