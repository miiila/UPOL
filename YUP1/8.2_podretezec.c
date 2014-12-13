#include "stdio.h"
#include "stdlib.h"

char *strrstr(const char *text, const char *hledany);
char *endOfString(const char *text);
int main()
{
	char text[] = "Ahoj svete!";
	char hledame[] = "svet";

	printf("Text:\"%s\"\nHledame:\"%s\"\nVraceny ukazatel:\"%s\"\n", text,hledame,strrstr(text,hledame));


	return 0;
}

char *strrstr(const char *text, const char *hledany) {
	//najdeme konce obou retezcu
	char *textEnd = endOfString(text);
	char *hledanyEnd = endOfString(hledany);

	//od konce prochazime zadany text a postupne testujeme vsechny znaky z hledaneho
	while (textEnd >= text) {
		while(hledanyEnd >= hledany) {
			if (*hledanyEnd != *textEnd) break;
			//pokud nedoslo k breaku a jsme na konci hledaneho, muzeme vratit
			if(hledanyEnd == hledany) return textEnd;
			hledanyEnd--,textEnd--;
		}
	textEnd--;
	}

	return NULL;
}

char *endOfString(const char *text) {
	while(*text != '\0') {
		text++;
	}

	return text-1;
}