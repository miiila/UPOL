#include<stdio.h>

int main()
{
	char input, *output;

	printf("Zadejte znak:");
	scanf("%c",&input);
	switch (input) {
		case 33:
			output = "Vykricnik";
			break;
		case 42:
			output = "Hvezdicka";
			break;
		case 63:
			output = "Otaznik";
			break;
		case 64:
			output = "Zavinac";
			break;
		case 94:
			output = "Striska";
			break;
		case 35:
			output = "Krizek";
			break;
		default:
			if (input >= 65 && input <= 90) {
				output = "Velke pismeno";
			}
			else if (input >= 97 && input <= 122 )
			{
				output = "Male pismeno";
			}
			else if (input >= 48 && input <= 57 )
			{
				output = "Cislice";
			}
			else {
				output = "Jiny znak";
			}
			break;
	}
	
	printf("Zadany znak %c je %s.\n",input,output);
	return 0;
}