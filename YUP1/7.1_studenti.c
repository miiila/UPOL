#include "stdio.h"

	typedef struct {
		char den;
		char mesic;
		short rok;
	} datum;

	typedef struct {
		char jmeno[20] ;
		char prijmeni[30];
		datum narozen;
	} student;

int porovnejVek(student s1, student s2);

int main()
{
	student s1 = { "Pepa", "Stary" , {25,7,1988}};
	student s2 = { "Adam", "Novak" , {25,7,1988}};

	switch (porovnejVek(s1,s2)) {
		case -1:
			printf("%s %s je starsi nez %s %s\n", s1.jmeno, s1.prijmeni,s2.jmeno,s2.prijmeni);
		break;
		case 1:
			printf("%s %s je mladsi nez %s %s\n", s1.jmeno, s1.prijmeni,s2.jmeno,s2.prijmeni);
		break;
		default:
			printf("%s %s je stejne stary jako %s %s\n", s1.jmeno, s1.prijmeni,s2.jmeno,s2.prijmeni);
		break;
	}

	return 0;
}

int porovnejVek(student s1,student s2) {
	if (s1.narozen.rok == s2.narozen.rok) {
		if (s1.narozen.mesic == s2.narozen.mesic) {
			if (s1.narozen.den == s2.narozen.den) {
				return 0;
			}
			else {
				return s1.narozen.den < s2.narozen.den ? -1 : 1;
			}
		}
		else {
			return s1.narozen.mesic < s2.narozen.mesic ? -1 : 1;
		}
	}
	else {
		return s1.narozen.rok < s2.narozen.rok ? -1 : 1;
	}
}