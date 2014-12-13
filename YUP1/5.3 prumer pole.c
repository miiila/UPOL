#include "stdio.h"
int arraySize = 9;

void printArray(int array[]) {
	for (int i = 0; i < arraySize;i++) {
		printf(" %i", array[i]);
	}
	printf("\n");
} 

int main() {
	int array[] = {1,2,3,4,5,6,7,8,9};
	int arraySum = 0;

	printArray(array);

	for (int i = 0; i < arraySize; i++) {
		arraySum += array[i];
	}

	printf("Prumer pole je %g.\n", (double)arraySum / arraySize);

}