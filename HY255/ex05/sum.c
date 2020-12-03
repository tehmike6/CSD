#include <stdio.h> 
#include <stdlib.h> 

#define N 1000

int n = N;
int Sum = 0;

int main (void) 
{

  while (n >= 0) { Sum += n; n--;}

  printf("The sum from 0 to %d is %d\n", N, Sum);

  return 0;
}
