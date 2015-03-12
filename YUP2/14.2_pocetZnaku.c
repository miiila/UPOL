#include "stdio.h"
#include "stdlib.h"

int vyskyty(char *texty[], int pocet, char hledany);

int main()
{
	char **texty;

	char hledany = 'e';
	const int RADKY = 3;

	texty = (char **)malloc(RADKY * sizeof(char *));
	texty[0] = "Ahoj uzivateli,";
	texty[1] = "jak se mas?";
	texty[2] = "Tohle bude snadne, ne?";

	int pocet = vyskyty(texty,RADKY,hledany);

	printf("Znak %c se v poli vyskytuje %ikrát\n", hledany, pocet);
}

int vyskyty(char *texty[], int pocet, char hledany) {
	int i,k,pocetVyskytu = 0;

	for (i=0;i<pocet;i++)
	{
		k = 0;
		while(texty[i][k] != '\0') {
			if (texty[i][k] == hledany) {
				pocetVyskytu++;
			}
			k++; 
		}
	}

	return pocetVyskytu;
}