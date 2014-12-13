#include "stdio.h"
#include "stdlib.h"
#include "string.h"

int set(char* in, char** out);

int main()
{
	char text[] = "Ahoj Svete 23.";
	char *text2;
	int count = set(text,&text2);

	printf("Puvodni text: \"%s\"\nZmeneny text: \"%s\"\nZmeneno %i znaku.\n",text,text2,count);

	return 0;
}

int set(char* in, char** out) {
	int changes = 0;
	*out = (char*)malloc(sizeof(char)*strlen(in));
	if(*out == NULL) {
		return -1;
	}
	for(int i = 0; i < strlen(in);i++) {
		if(in[i] >= 'A' && in[i] <= 'Z') {
			(*out)[i] = in[i] + ('a' - 'A');
			changes++;
		}
		else if (in[i] >= 'a' && in[i] <= 'z') {
			(*out)[i] = in[i] - ('a' - 'A');
			changes++;	
		}
		else {
			(*out)[i] = in[i];
		}
	}

	return changes;
}