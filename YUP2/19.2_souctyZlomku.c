#include "stdio.h"
#include "stdlib.h"

typedef struct {
	int citatel;
	int jmenovatel;
} zlomek;

zlomek soucet(const char *vstup);
zlomek zlomekSoucet(zlomek z1, zlomek z2);
int euklid(int i, int j);
const int LENGTH = 50; //dostatecne velke cislo, aby se nacetl cely radek

int main()
{
	zlomek vysledek = soucet("./file");
	printf("Soucet je: %d/%d\n", vysledek.citatel, vysledek.jmenovatel);

	return 0;

}

zlomek soucet(const char *vstup) {
	char row[LENGTH];
	zlomek zlomek1 = {0, 1}; //vstupni nulovy zlomek pro jednodussi soucet
	zlomek zlomek2;

	FILE *input = fopen(vstup, "rt");
  	if (!input) exit(1);

  	while (fgets(row, LENGTH, input)) {
	  	sscanf(row, "%d / %d", &zlomek2.citatel, &zlomek2.jmenovatel);
	  	zlomek1 = zlomekSoucet(zlomek1, zlomek2);
  	}

  	fclose(input);

  	return zlomek1;
}

/*
Strukturovany typ, soucet zlomku a Eukleiduv algoritmus jsou prevzaty z ukolu 7.2 - Zlomky
*/

zlomek zlomekSoucet(zlomek z1, zlomek z2) {
	zlomek vysledek;
	int nsd;

	vysledek.citatel = z1.citatel * z2.jmenovatel + z1.jmenovatel * z2.citatel;
	vysledek.jmenovatel = z1.jmenovatel * z2.jmenovatel;

	nsd = euklid(vysledek.citatel,vysledek.jmenovatel);

	vysledek.citatel = vysledek.citatel / nsd;
	vysledek.jmenovatel = vysledek.jmenovatel / nsd;

	return vysledek;	
}

int euklid(int i, int j) {
	int z;

	while(j != 0) {
		z = i % j; 
		i = j;
		j = z;
	}

	return i;
}
