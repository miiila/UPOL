#include "stdio.h"

double arraySum (double array[], int arraySize) {
	double arraySum = 0;
	for (int i = 0; i < arraySize; i++) {
		arraySum += array[i];
	}

	return arraySum;
}

void printArray(double array[], int arraySize) {
	for (int i = 0; i < arraySize;i++) {
		printf(" %g", array[i]);
	}
	printf("\n");
} 


int main()
{
	double array[] = {1,2,3,4,5,6,7,8,9,10};
	int arraySize = 10;

	printf("Pole obsahuje cisla:");
	printArray(array,arraySize);
	printf("Suma pole je: %g\n", arraySum(array,arraySize));
}