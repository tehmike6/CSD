#include <stdio.h>

int main(){
	enum{RED,BLACK,BLUE}color;

	printf("char = %d\n",sizeof(char));
	printf("int = %d\n",sizeof(int));
	printf("float = %d\n",sizeof(float));
	printf("double = %d\n",sizeof(double));
	printf("long = %d\n",sizeof(long));
	printf("short = %d\n",sizeof(short));
	printf("pointer = %d\n",sizeof(int*));
	printf("pointer = %d\n",sizeof(char*));

	color = RED;
	if(color == RED){
		printf("%d\n",sizeof(color));
	}else{
		printf("FALSE\n");
	}
	return 0;
}
