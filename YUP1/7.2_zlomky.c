#include "stdio.h"

typedef struct {
	int citatel;
	int jmenovatel;
} zlomek;

zlomek zlomekSoucet(zlomek z1, zlomek z2);
int euklid(int i, int j);

int main()
{
	zlomek z1 = {2,3}, z2 = {-1,6}, soucet;
	soucet = zlomekSoucet(z1,z2);
	printf("Soucet zlomku %i/%i a %i/%i je %i/%i.\n",z1.citatel,z1.jmenovatel,z2.citatel,z2.jmenovatel,soucet.citatel,soucet.jmenovatel );

	return 0;
}

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

	while(j > 0) {
		z = i % j; 
		i = j;
		j = z;
	}

	return i;
}