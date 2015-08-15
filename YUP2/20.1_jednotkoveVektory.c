#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>

#define VELIKOST_BLOKU 50
#define PRESNOST 100
#define DIMENZE 3

/* hlavicky lokalnich funkci */
int generuj_data(char *nazev, int max, long pocet);
int precti_data(char *nazev);
double vel_vektor(double *v);
int uprav_data(char *nazev);

/* definice funkci */
int main()
{
    generuj_data("data.dat", 100, 123);
    precti_data("data.dat");
    uprav_data("data.dat");
    printf("\nNova data: \n");
    precti_data("data.dat");
    system("pause");
    return 0;
}

/* funkce vytvori binarni soubor s cisly typu double od 0 do max - 1, 
 * parametr pocet udava pocet cisel v souboru
 */
int generuj_data(char *nazev, int max, long pocet)
{
    FILE *f;
    double data[VELIKOST_BLOKU];
    long i, j;
    unsigned int zapsano;

    /* otevreni souboru */
    f = fopen(nazev, "wb");
    if (f == NULL)
	return 1;

    /* inicializace generetoru nahodnych cisel */
    srand((unsigned) time(NULL));

    /* generovani a zapis uplnych datovych bloku */
    for (i = 0; i < pocet / VELIKOST_BLOKU; i++) {

	/* generovani jednoho datoveho bloku */
	for (j = 0; j < VELIKOST_BLOKU; j++) {
	    data[j] = (rand() % (max * PRESNOST)) / (double) PRESNOST;
	}

	/* zapis jednoho datoveho bloku */
	zapsano = (unsigned) fwrite(data, sizeof(double), VELIKOST_BLOKU, f);

	/* test uspesnosti zapisu */
	if (zapsano != VELIKOST_BLOKU) {
	    fclose(f);
	    return 2;
	}
    }

    /* generovani posledniho neuplneho datoveho bloku */
    for (j = 0; j < pocet % VELIKOST_BLOKU; j++) {
	data[j] = (rand() % (max * PRESNOST)) / (double) PRESNOST;
    }

    /* zapis posledniho datoveho bloku */
    zapsano = (unsigned) fwrite(data, sizeof(double), pocet % VELIKOST_BLOKU, f);

    /* test uspesnosti zapisu */
    if (zapsano != pocet % VELIKOST_BLOKU) {
	fclose(f);
	return 2;
    }

    /* uzavreni proudu a test uspesnosti */
    if (EOF == fclose(f))
	return 3;
    return 0;
}

/* funkce cte binarni soubor s cisly typu double a vypisuje je na std. vystup */
int precti_data(char *nazev)
{
    FILE *f;
    double data[VELIKOST_BLOKU];
    unsigned int i;
    unsigned int precteno;

    /* otevreni souboru */
    f = fopen(nazev, "rb");
    if (f == NULL)
	return 1;

    /* cte se dokud to jde */
    do {
	/* cteni celeho bloku */
	precteno = (unsigned) fread(data, sizeof(double), VELIKOST_BLOKU, f);

	/* vypis bloku dat na obrazovku */
	for (i = 0; i < precteno; i++) {
	    printf("%g ", data[i]);
	}
	printf("\n");
    } while (precteno == VELIKOST_BLOKU);

    /* uzavreni proudu a test uspesnosti */
    if (EOF == fclose(f))
	return 3;
    return 0;
}

/* funkce pro vypocet delky vektoru urcite DIMENZE */
double vel_vektor(double *v)
{
    double out = 0.0;
    int i;
    for (i = 0; i < DIMENZE; i++)
	out += v[i] * v[i];
    return sqrt(out);
}

int uprav_data(char *nazev)
{
    double vektor[DIMENZE];

    FILE *input = fopen(nazev, "r+b");
    while(fread(vektor, sizeof(double), DIMENZE, input)) {
        double velikost = vel_vektor(vektor);
        for (int i = 0; i < DIMENZE; i++) {
            vektor[i] /= velikost;
            fseek(input, -DIMENZE*sizeof(double), SEEK_CUR);
            fwrite(vektor, sizeof(double), DIMENZE, input);
        }
    }

    return 0;
}
