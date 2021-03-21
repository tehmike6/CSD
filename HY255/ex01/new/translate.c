#include <stdio.h>

int main(void){
	char c = getchar();

	while(c != EOF){

		printf("%d ", c);

		c = getchar();
	}
}
