/*
	@module: ascii_art
	@version: 1.0.0
	@author: mvotradovec
	@date: 2015/04/06
*/

#include "stdio.h"
#include "stdlib.h"
#include "ascii_canvas_lib.h"

int main()
{
	canvas *c = canvas_create(15,15);
	canvas_draw_rect(c,3,3,5,6,'*');
	canvas_draw_rect(c,4,4,8,10,'x');
	canvas_print(c);
	canvas *d = canvas_create(5,5);
	canvas_set_point(d,3,3,'&');
	return 0;
}