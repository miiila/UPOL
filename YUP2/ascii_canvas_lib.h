/*
	@module: ascii_canvas_lib
	@version: 1.0.0
	@author: mvotradovec
	@date: 2015/04/06
*/

#ifndef ASCII_CANVAS_LIB
	/* struktura pro platno jako dvourozmerne pole znaku */
	typedef struct {
		int rows;
		int cols;
		char **platno;
	} canvas;
	/* vytvori platno */
	extern canvas *canvas_create(int x, int y);
	/* nastavi dany bod na zadanou hodnotu */
	extern void canvas_set_point(canvas *c, int x, int y, char character);
	/* vrati znak daneho bodu */
	extern char canvas_get_point(canvas *c, int x, int y);
	/* nakresli obdelnik */
	extern void canvas_draw_rect(canvas *c, int x, int y, int width, int height, char ch);
	/* vycisti platno */
	extern void canvas_clear(canvas *c);
	/* vykresli obsah platna na standardni vystup */
	extern void canvas_print(canvas *c);
#define ASCII_CANVAS_LIB 1
#endif