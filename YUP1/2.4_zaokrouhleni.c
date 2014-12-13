#include <stdio.h>

int main () {
    double des_cislo,presnost,vysledek,pomocny_nasobek;
    
    printf("Zadejte cislo:");
    scanf("%lf",&des_cislo);
    printf("Zadejte presnost:");
    scanf("%lf",&presnost);
    
    pomocny_nasobek = des_cislo / presnost;
    
    if ((pomocny_nasobek) - (int)(pomocny_nasobek) >= 0.5) {
        vysledek = ((int)(pomocny_nasobek) + 1) * presnost;
    }
    else {
        vysledek = ((int)(pomocny_nasobek)) * presnost;
    }
    
    printf("%f",vysledek);
    return 0;
}