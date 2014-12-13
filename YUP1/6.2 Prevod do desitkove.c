#include "stdio.h"
#include "string.h"

int toDecadic(char number[], int base);
int expt (int number);
int charToInt(char digit);

int main()
{
char number[] = "A1B";
int base = 12;

printf("Cislo %s v soustave o zakladu %i odpovida cislu %i v desitkove soustave.\n", number,base,toDecadic(number,base));

}

int toDecadic(char number[], int base) {
	int decadicNumber = 0;
	//parsovani retezce zprava a nasobeni jednotlivych cifer umocnenym zakladem
	for (int i = strlen(number)-1,expt = 1; i >= 0;i--,expt *= base) {
		decadicNumber += charToInt(number[i]) * expt;
	}

	return decadicNumber;
}

//funkce na prevod znaku na cislo
//nekontroluje platnost a pocita s max 16kovou soustavou pouze velkymi pismeny
int charToInt(char digit) {
	int i;
	if ('0' <= digit && digit <= '9') {
    	i = digit - '0';
	} else if ('A' <= digit && digit <= 'F') {
	    i = 10 + digit - 'A';
	}

	return i;
}