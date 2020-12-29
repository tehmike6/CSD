#include <stdio.h>

int main() {
	int num, reversenum = 0, remain,x=0;
	scanf("%d", &num);
	x = num ;
	while (num != 0) {
	 
		remain = num % 10;
		reversenum = reversenum*10 + remain;
		num /= 10;
		}
	if (x < 0){
	printf("%d\n", reversenum);
	} else{
		printf("%d\n", reversenum);
	}
	return 0;
}
