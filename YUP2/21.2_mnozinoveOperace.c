#include <stdio.h>
#include <stdlib.h>

/* struktura reprezentujici mnozinu */
typedef struct {
    int *prvky;			/* pole pro bitove ulozeni prvku */
    int pocet;			/* pocet prvku v mnozine */
} mnozina;

void vypis(mnozina A);		/* vypis indexu prvku dane mnoziny na obrazovku */
mnozina prunik(mnozina A, mnozina B);	/* prunik dvou mnozin */
mnozina sjednoceni(mnozina A, mnozina B);	/* sjednoceni dvou mnozin */
mnozina rozdil(mnozina A, mnozina B);	/* rozdil prvni a druhe mnoziny */

int main()
{
    mnozina A, B, C;

    /* vytvoreni mnoziny A */
    A.prvky = (int *) malloc(3 * sizeof(int));
    A.prvky[0] = 43;
    A.prvky[1] = 55;
    A.prvky[2] = 42351;
    A.pocet = 3 * sizeof(int) * 8;

    /* vytvoreni mnoziny B */
    B.prvky = (int *) malloc(3 * sizeof(int));
    B.prvky[0] = 43;
    B.prvky[1] = 5501;
    B.prvky[2] = 42000;
    B.pocet = 3 * sizeof(int) * 8;

    /* vypis mnoziny A na obrazovku */
    printf("Mnozina A: \n");
    vypis(A);
    printf("\n");

    /* vypis mnoziny B na obrazovku */
    printf("Mnozina B: \n");
    vypis(B);
    printf("\n");

    /* vypocet pruniku a jeho vypis na obrazovku */
    C = prunik(A, B);
    printf("Prunik mnozin A a B: \n");
    vypis(C);
    printf("\n");

    /* vypocet sjednoceni a jeho vypis na obrazovku */
    free(C.prvky);
    C = sjednoceni(A, B);
    printf("Sjednoceni mnozin A a B: \n");
    vypis(C);
    printf("\n");

    /* vypocet rozdilu a jeho vypis na obrazovku */
    free(C.prvky);
    C = rozdil(A, B);
    printf("Rozdil mnozin A a B: \n");
    vypis(C);
    printf("\n");

    system("pause");
    return 0;
}

void vypis(mnozina A)
{
    long maska;
    int index;
    int i, j;
    int mez_i;

    index = 0;

    /* vypocet meze cyklu */
    mez_i = A.pocet / (sizeof(int) * 8) + 1;

    /* cyklus pres cisla typu int */
    for (i = 0; i < mez_i; i++) {

	/* vypocet meze cyklu */
	int mez_j = (i < mez_i - 1) ? sizeof(int) * 8 : (A.pocet % (sizeof(int) * 8));

	/* posun masky na zacatek */
	maska = 1;

	/* cyklus pres jednotlive bity */
	for (j = 0; j < mez_j; j++) {

	    /* pokud je prvek v mnozine - bit 1 pod maskou,
	     * tak vypiseme odpovidajici index
	     */
	    if (A.prvky[i] & maska)
		printf("%i, ", index);
	    maska *= 2;		/* posun masky */
	    index++;		/* posun indexu */
	}
    }
}

mnozina prunik(mnozina A, mnozina B)
{
    mnozina C;
    int mez = A.pocet / (sizeof(int) * 8) + 1;
    C.prvky = (int *) malloc(mez * sizeof(int));
    for (int i = 0; i < mez; i++) {
        C.prvky[i] = A.prvky[i] & B.prvky[i];
    }
    C.pocet = mez * sizeof(int) * 8;

    return C;
}

mnozina sjednoceni(mnozina A, mnozina B)
{
    mnozina C;
    int mez = A.pocet / (sizeof(int) * 8) + 1;
    C.prvky = (int *) malloc(mez * sizeof(int));
    for (int i = 0; i < mez; i++) {
        C.prvky[i] = A.prvky[i] | B.prvky[i];
    }
    C.pocet = mez * sizeof(int) * 8;

    return C;
}

mnozina rozdil(mnozina A, mnozina B)
{
    mnozina C;
    int mez = A.pocet / (sizeof(int) * 8) + 1;
    C.prvky = (int *) malloc(mez * sizeof(int));
    for (int i = 0; i < mez; i++) {
        C.prvky[i] = A.prvky[i] & (A.prvky[i] ^ B.prvky[i]);
    }
    C.pocet = mez * sizeof(int) * 8;

    return C;
}
