#include "stdio.h"
#include "stdlib.h"

const int POCET = 10;

int puleni(int cisla[], int a, int b, int hledane);

int main()
{
	int cisla[POCET];
	int i;

	for(i=0;i<POCET;i++) {
		cisla[i] = 3 * i + 1;
	}

	for (i=0;i<POCET;i++)
	{
		printf("Hledane cislo je na indexu %i\n", puleni(cisla,0,POCET-1,3 * i + 1) );
	}

}

int puleni(int cisla[], int a, int b, int hledane) {
	int polovinaIntervalu = (a + b) / 2;
	// pokud je delka pole licha, bereme vyssi index
	polovinaIntervalu += (a+b) % 2 == 0 ? 0 : 1;
	int polovina = cisla[polovinaIntervalu];

	if (polovina == hledane) {
		return polovinaIntervalu;
	}
	// pokud jsou indexy stejne a cislo se nenaslo v predchozi podmince, v poli neni
	else if (a == b ) {
		return -1;
	}
	//pokud prohledavame prvni cast, staci hledat do - 1 mimo stred
	else if (hledane < polovina) {
		return puleni(cisla, a, polovinaIntervalu-1, hledane);
	}
	else {
		return puleni(cisla, polovinaIntervalu, b, hledane);	
	}
}