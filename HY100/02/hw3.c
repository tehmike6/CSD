#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

int main(int argc, char *argv[5]){
int horizon = 1 ;
int reps;
int num;
int i=0;
int j=0;
int array[10][10];
int determinant[10][10];
srand(time(NULL));

if(argc == 1){
	printf("No arguments found\n");
	return 0;
}else if(argc == 2 && strcmp(argv[1],"-calculate")!=0){
	printf("No array size provided\n");
return 0;}

if(strcmp(argv[1],"-create")== 0){
	if(atoi(argv[2])>=2 && atoi(argv[2])<=10){
			for(i=0;i<atoi(argv[2]);i++){
		for(j=0;j<atoi(argv[2]);j++){
			if(i<=j){
			array[i][j]= (rand()%7 + 1);
			}else{
				array[i][j] = 0;
			}
		}		
	}
	printf("%s\n",argv[2] );
	for(i=0;i<atoi(argv[2]);i++){
		for(j=0;j<atoi(argv[2]);j++){
			printf("%d ",array[i][j]);
		}
	printf("\n");
	}
	}else if(atoi(argv[2])<2 || atoi(argv[2])>10){
		printf("Not valid array size\n");
	}
}else if(strcmp(argv[1],"-calculate")== 0){
	scanf("%d", &reps);
	for(i=0;i<reps;++i){
		for(j=0;j<reps;++j){
			scanf("%d", &num);
			determinant[i][j]=num ;	
		}
		
	}
	
	for(i=0;i<reps;++i){
		for(j=0;j=i;++j){
			horizon = horizon * determinant[i][j];
			break;
		}	
	}
	printf("%d\n",horizon*determinant[0][0]);
}
}