#include <stdio.h>
#include <stdlib.h>
#include <string.h>

 
int main(int argc, char *argv[]){
  char *code, temp, nums;
  int i,j =0;
  int ch,count = 1,num= 0, n;
  
  code = (char*)calloc(5,sizeof(char));
  
  for ( i = 0;(( ch = getchar()) != EOF) && ( ch != 0 ) && (ch != '\n'); ++i ){
      code[i] = ch;
      code = (char *)realloc(code,i+2);
}
 code[i] = '\0';
   
if(argc == 1)return -1;

if (strcmp(argv[1], "e")==0 ){
while(code[j] != '\0'){
    temp = code[j];
    if(temp == code[j+1]){
        count++;
    }else if(temp != code[j+1]){
        printf("%d%c", count,temp);
        count=1;
    }
        
    j++;
}
printf("\n");
}   

if (strcmp(argv[1], "d")== 0){
for(j=0;j<=i;j++){
    n = code[j] - 48;
    if((n >= 0) && (n <= 9)){
        num *= 10;
        num += n;
    }else if(n< 0 || n> 9){
        temp = code[j];
        while(num > 0){
        printf("%c",temp);
        num--;
        }
    }

}
printf("\n");
}
free(code);
}

