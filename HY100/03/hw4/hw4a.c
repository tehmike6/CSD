#include <stdio.h>
#define maxN 100

int main(){
int array[maxN][maxN];
int N,i,j,num,check, sum=0, magic=0,unique=0, y,chk=0;


scanf("%d",&N);


for(i=0;i<N;++i){
	for(j=0;j<N;++j){
		scanf("%d",&array[i][j]);
        if(array[i][j]<1 || array[i][j]>N*N){
            unique=-1;
        }
    }
}

check=N*(N*N+1)/2;

for(i=0;i<N;i++){
	for(j=0;j<N;j++){
			sum += array[i][j];
	}
	if(check == sum){
		printf("ROW %d YES\n",i+1);
		magic += 1;
	}else{
		printf("ROW %d NO\n",i+1);
	}
	sum = 0;
}

for(i=0;i<N;i++){
	for(j=0;j<N;j++){
			sum += array[j][i];
	}
	if(check == sum){
		printf("COLUMN %d YES\n",i+1);
		magic += 1;
	}else{
		printf("COLUMN %d NO\n",i+1);
	}
	sum = 0;
}

for(i=0;i<N;i++){
	for(j=0;j<N;j++){
		if(i==j){
			sum += array[i][j];
		}
	}
}
if(sum==check){
	printf("DIAG1 YES\n");
	sum=0;
	magic += 1;
}else{
	printf("DIAG1 NO\n");
	sum=0;
}

for(i=0;i<N;i++){
	sum+=array[i][N-1-i];
}
if(sum==check){
	printf("DIAG2 YES\n");
	magic += 1;
}else{
	printf("DIAG2 NO\n");
}
for(y=1;y<=N*N;y++){
	for(i=0;i<N;i++){
		for(j=0;j<N;j++){
			if(y == array[i][j]){
				unique++;
				if(chk == array[i][j]){
					unique++;
				}
				chk = array[i][j];
			}
		}
	}
}
if(unique == N*N){
	printf("UNIQUE YES\n");
	magic ++;
}else{
	printf("UNIQUE NO\n");
}
if(magic == N*2+3){
	printf("MAGIC YES\n");
}else{
	printf("MAGIC NO\n");
}
}
