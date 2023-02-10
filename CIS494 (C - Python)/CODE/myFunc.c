// This is the file that controls the hand

// Frederick Kelemen 2715308
// 03/10/2021

// This file will take input in the form of arguments passed
// in starting the code, the input will be put into a switch case
// using the switch functions will be called causing the hand to
// perform different actions.
// ALL SERVO FILES ARE DERIVED FROM OPEN SOURCE PYTHON CODE WHICH CAN
// BE FOUND ON GITHUB AT THE URL : HTTPS://github.com/sparkfun/Pi_Servo_Hat

#include <stdio.h>
#include <stdlib.h>
#include "output.h"

void main (int argc, char ** argv) {
	int input = atoi(argv[1]);
	switch(input) {
		case 1:
			// calls function 1
			//printf("HI, I CALLED FUNCTION 1\n");
			func1();
			break;
		case 2:
			// calls function 2
			//printf("HI, I CALLED FUNCTION 2\n");
			func2();
			break;
		case 3:
			// calls function 3
			func3();
			break;
		case 4:
			// calls function 4
			func4();
			break;
		default:
			printf("YOU HAVE CALLED AN UNIMPLEMENTED FUNCTION\n");
			break;
	}
}
