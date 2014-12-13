#include "stdio.h"
#include "stdlib.h"

int porovnej(char *t1, char *t2);

int main()
{
	char slovo1[] = "ahoj";
	char slovo2[] = "abcde";

	switch(porovnej(slovo1,slovo2)) {
		case 1:
			printf("Slovo %s je vetsi nez slovo %s.\n",slovo1,slovo2);
		break;		
		case -1:
			printf("Slovo %s je mensi nez slovo %s.\n",slovo1,slovo2);
		break;
		case 0:
			printf("Slovo %s je stejne jako slovo %s.\n",slovo1,slovo2);
		break;
	}
	
	return 0;
}

int porovnej(char *t1, char *t2) {
	 while (*t1 != '\0') {
	 	if(*t1 < *t2) {
	 		return -1; 
	 	}
	 	else if (*t1 > *t2) {
	 		return 1;
	 	}
	 	t1++,t2++;	 	
	 }
	 
	 //na konci prvniho slova zkontrolujeme, jestli druhe slovo skoncilo taky nebo jestli obsahuje nejaky znak
	 if(*(t2++) != '\0') {
	 	return -1;
	 }
	 else {
	 	return 0;
	 }
}