#include "stdio.h"
#include "stdlib.h"

int main()
{
	int cislo,strop,i;
	printf("Zadejte cislo: ");
	scanf("%d",&cislo);
	if (cislo == 0) {
		printf("Zadana 0.\n");
		return 0;
	}
	printf("Zadejte strop: ");
	scanf("%d",&strop);

	i = cislo;
	while (i < strop) {
		printf("%d, ",i);
		i += cislo;
	}
	return 0;
}