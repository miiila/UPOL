/*
	@module: ascii_canvas_lib
	@version: 1.0.0
	@author: mvotradovec
	@date: 2015/04/06
*/

#include "stdio.h"
#include "stdlib.h"
#include "ascii_canvas_lib.h"

//prazdny znak pro znazsi pouziti
const char EMPTY_POINT = ' ';
static int i,j;

extern canvas *canvas_create(int x, int y) {
	canvas *myCanvas;
	/*
	pokud chceme dodrzet konvenci, ze x -> horizontalni, y -> vertikalni,
	je treba prijimat dvojici xy, ale pouzivat yx
	*/
	char **arr = (char **)malloc(y * sizeof(char *));
	if (arr == NULL) return NULL;
	for(int i = 0; i <= y; i++) {
		arr[i] = (char *)malloc(x * sizeof(char));
		if (arr[i] == NULL) {
		//uklid, kdyz se nepovede
			for (int j = 0; j < i; j++) free(arr[j]); 
			free(arr);
			return NULL;
		}
	}
	myCanvas = (canvas  *)malloc(sizeof(canvas));
	myCanvas->rows = y;
	myCanvas->cols = x;
	myCanvas->platno = arr;

	//vycistime platno, at mame vsude definovany prazdny znak
	canvas_clear(myCanvas);
	
	return myCanvas;
}
/* nastavi dany bod na zadanou hodnotu */
extern void canvas_set_point(canvas *c, int x, int y, char character) {
	if (y < c->rows && x < c->cols && x >= 0 && y >= 0) c->platno[y][x] = character;	
}
/* vrati znak daneho bodu */
extern char canvas_get_point(canvas *c, int x, int y) {
	return (y < c->rows && x < c->cols && x >= 0 && y >= 0) ? c->platno[x][y] : EMPTY_POINT;
}
/* nakresli obdelnik */
extern void canvas_draw_rect(canvas *c, int x, int y, int width, int height, char ch) {
	//copy&paste a uprava kodu ze cviceni 4.3_cterec
	// -1 protoze dostavame delku (resp. sirku), nikoli cilovy bod
	for (i = x; i <= x+width-1; i++)
	{
		for (j = y; j <= y+height-1; j++)
		{
			if(i == x || i == x+width-1 || j == y || j == y+height-1) {
				canvas_set_point(c,i,j,ch);
			}
		}
	}
}
/* vycisti platno */
extern void canvas_clear(canvas *c) {
	for (i = 0; i < c->rows; i++)
	{
		for (j = 0; j < c->cols; j++)
		{
			canvas_set_point(c,i,j,EMPTY_POINT);
		}
	}
}
/* vykresli obsah platna na standardni vystup */
extern void canvas_print(canvas *c) {
	for (i = 0; i < c->rows; i++)
	{
		for (j = 0; j < c->cols; j++)
		{
			printf("%c",canvas_get_point(c,i,j));			
		}
		printf("\n");			
	}
}