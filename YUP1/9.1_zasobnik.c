#include "stdio.h"
#include "stdlib.h"

typedef struct prv {
	struct prv *predchozi;
	int data;
} prvek;

prvek *pridej(prvek *zasobnik, int data);
int vrchol(prvek* zasobnik);
prvek *odeber(prvek* zasobnik);

int main()
{
	prvek* z=NULL;
	int i;

	for (i=1; i<11; i++) {
		z = pridej(z, i);
	}
	
	while (z!=NULL){
		printf("%i\n", vrchol(z));
		z=odeber(z);
   }

	return 0;
}

prvek *pridej(prvek *zasobnik, int data) {
	prvek *z = (prvek *) malloc(sizeof(prvek));
	z->data = data;
	z->predchozi = zasobnik;

	return z;
}

int vrchol(prvek* zasobnik) {
	return zasobnik->data;
}

prvek *odeber(prvek* zasobnik) {
	prvek *predchozi = zasobnik->predchozi;
	free(zasobnik);

	return predchozi;
}

