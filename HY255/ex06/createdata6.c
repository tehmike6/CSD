#include <stdio.h>

void main() {
	int i;
	FILE *data6;

        if((data6 = fopen("data6", "w")) == NULL){

                fprintf(stderr, "\nCan not write file: data6\n\n");
                return;
        }

	fputs("Mike Bastakis",data6);
	for (i = 1; i<35; i++) 
		fputc('\0', data6);
	fputc(56, data6);
	fputc(0x48, data6);
	fputc(0x47, data6);
	fputc(0x0d, data6);
	fputc(0x08, data6);

	fputc(0x20, data6);
	fputc(0x66, data6);
	fputc(0x0d, data6);
	fputc(0x08, data6);
}

