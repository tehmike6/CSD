#include <stdio.h>
#include <stdlib.h>

int main(void){
FILE *data3;
        int i;

        if((data3 = fopen("data3", "w")) == NULL){

                fprintf(stderr, "\nCan not write file: data3\n\n");
                exit(EXIT_FAILURE);
        }

        fprintf(data3, "Mike Bastakis");        /* 18 characters */

        fputc('0', data3);      /* end of my name */

        for(i = 0; i < 50000; i++)

                fputc('x',data3);
        fclose(data3);

        return(0);
}






