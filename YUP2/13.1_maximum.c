
#include "stdio.h"
#include "stdlib.h"

int maximum(int prvky[][4], int radku);

int main()
{

int data[3][4] = {{105,2,15,-2},{-52,41,0,12},{15,3,1,-8}};
int max = maximum(data,3);

printf("Maximum je %i\n", max);

}

int maximum(int prvky[][4], int radku) {
	int i,k;
	int max = 0;

	for (i=0;i<radku;i++) {
		for (k=0;k<4;k++) {
			max = prvky[i][k] > max ? prvky[i][k] : max;
		}
	}

	return max;
}