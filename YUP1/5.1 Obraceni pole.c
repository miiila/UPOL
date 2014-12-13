#include<stdio.h>

int arraySize = 10;

void printArray(int array[]) {
	for (int i = 0; i < arraySize;i++) {
		printf(" %i", array[i]);
	}
	printf("\n");
} 

int main()
{
	int inputArray[] = {1,2,3,4,5,6,7,8,9,10};
	int lastIndex = arraySize-1;
	int num;

	printf("%s:", "Puvodni hodnoty" );
	writeArray(inputArray);
	for (int i = 0; i < lastIndex;i++,lastIndex--) {
		num = inputArray[i];
		inputArray[i] = inputArray[lastIndex];
		inputArray[lastIndex] = num;
	}
	printf("%s:", "Nove hodnoty" );
	printArray(inputArray);
}

