#include "stdio.h"
#include "stdlib.h"

int main()
{
	int hrana;

	printf("Zadajete velikost hrany:");
	scanf("%d",&hrana);
	//i -> radek
	//j -> sloupec
	for (int i = 1; i <= hrana; i++)
	{
		for (int j = 1; j <= hrana; j++)
		{
			//jine znaky, nez prvni a posledni sloupec jinde nez v prvnim a poslednim radkou jsou mezery
			if(i > 1 && i < hrana && j > 1 && j < hrana) {
				printf(" ");	
			}
			else {
				printf("*");		
			}
			
		}
		printf("\n");	
	}
	return 0;
}