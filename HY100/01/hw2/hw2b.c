#include <stdio.h>

int main() {
	int reps, stars, spaces, rows;
	scanf("%d", &rows);
	
	for (reps = 1;reps <= rows;++reps) {
		for (spaces = rows - reps ;spaces > 0;spaces--) {
			printf(" ");
		}
		for (stars = 1;stars <= (2 * reps - 1);stars++) {
			printf("*");
		}
		printf("\n");
		
	}

	for (reps = 1; reps < rows; ++reps) {
		for (spaces = reps ;spaces > 0; spaces--) {
			printf(" ");
		}
		for (stars = 2*(rows-reps)+1;stars - 2 >0 ;stars--) {
			printf("*");
		}
		printf("\n");
	}
	return 0;
}
