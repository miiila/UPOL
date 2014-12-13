#include<stdio.h>
#include<stdlib.h>

int main()
{
	float mzda,dan = 0;

	printf("Zadejte mzdu:\n");
	scanf("%g",&mzda);

	if (mzda > 20000) {
		dan += (mzda-20000)*0.3;
		mzda = 20000;
	}
	if (mzda > 10000) {
		dan += (mzda-10000)*0.2;
		mzda = 10000;
	}
	dan += mzda*0.1;

	printf("Dan je %g\n",dan);
	return 0;
}