#include "stdio.h"
#include "stdlib.h"

#define je_cislice(zaklad, znak) \
	(\
		(((znak)>=('A')) && ((znak)<=('Z')) && ((znak)<((zaklad)+('A'-10)))) \
		|| \
		(((znak)>=('0')) && ((znak)<=('9')) && ((znak)<((zaklad)+'0')))\
	)

int main()
{
	if (je_cislice(8,'8')!=0) printf("Ano\n"); else printf("Ne\n");
	if (je_cislice(10+6,'0'+4)!=0) printf("Ano\n"); else printf("Ne\n");
	if (je_cislice(30,'@')!=0) printf("Ano\n"); else printf("Ne\n");
	return 0;
}