#include "stdio.h"
#include "stdlib.h"

int main()
{
	int strop,jePrvocislo;
	printf("Zadejte strop: ");
	scanf("%d",&strop);

	for (int i = 2; i < strop; i++) {
		jePrvocislo = 1;
		//lepe bylo pouzit j < sqrt(i), ale pro to je vyzadovana knihovna math.h
		for(int j = 2; j < i;j++) {
			if (i%j == 0) {
				jePrvocislo = 0;
				break;
			}
		}
		if (jePrvocislo == 1) {
			printf("%d, ", i);
		}
	}

	printf("\n");
	return 0;
}
