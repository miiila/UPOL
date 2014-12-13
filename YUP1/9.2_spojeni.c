#include "stdio.h"
#include "stdlib.h"
#include "string.h"

char *spojeni(char *t1, char *t2);

int main()
{

	char t1[] = "Ahoj";
	char t2[] = "Svete";

	printf("Spojeni slov \"%s\" a \"%s\" je \"%s\"\n", t1,t2,spojeni(t1,t2));

 	return 0;
}

char *spojeni(char *t1, char *t2) {
	int len = strlen(t1) + strlen(t2);
	char *join = (char*)malloc(sizeof(char) * len);
	for (int i=0;i<len;i++) {
		if (i<strlen(t1)) {
			join[i] = *(t1+i);
		}
		else {
			join[i] = *(t2+i-strlen(t1));
		}
	}

	return join;
}

