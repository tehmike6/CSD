#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int move(int M,int N,char **maze,int row,int column){
    int i=1,j,count,upc=0,downc=0,leftc=0,rightc=0;
        count=1;
    while(i){
       /* for(i=0;i<row;i++){
        for(j=0;j<column;j++){
            printf("%c", maze[i][j]);
        }
        printf("\n");
    }*/
//     printf("%d\n",rand()%4);
            if(rand()%4 == 0){
            count++;
            upc=1;
    if(M > 2){
        if(maze[M-1][N] == 'X' && maze[M-2][N] == 'X' && M-2 > 0){
                maze[M-1][N] = ' ';
                maze[M-2][N] = ' ';
                move(M-2,N,maze,row,column);
            }
        }
    }
            if(rand()%4 == 1){
            count++;
            downc=1;
    if(M < row - 3){
        if(maze[M+1][N] == 'X' && maze[M+2][N] == 'X' && M+2 < row - 1){
                maze[M+1][N] = ' ';
                maze[M+2][N] = ' ';
                move(M+2,N,maze,row,column);
            }
        }
    }
            if(rand()%4 == 2){
            count++;
            leftc=1;
    if(N > 2){
        if(maze[M][N-1] == 'X' && maze[M][N-2] == 'X' && N-2 > 0){
                maze[M][N-1] = ' ';
                maze[M][N-2] = ' ';
                move(M,N-2,maze,row,column);
            }
        }
    }
            if(rand()%4 == 3){
            count++;
            rightc=1;
    if(N < column-3){
        if(maze[M][N+1] == 'X' && maze[M][N+2] == 'X' && N+2 < column - 1){
                maze[M][N+1] = ' ';   
                maze[M][N+2] = ' ';
                move(M,N+2,maze,row,column);
            }
        }
    }
//     printf("count:%d",count);
    if( upc + downc + leftc + rightc == 4){
//         printf("M:%d,N:%d\n",M,N);
       return 0; 
    }else{
//         printf("Continue\n");
        continue;
    }
    }
}

void main(){
    int column,row,i,j;
    
    char **maze=(char **)malloc(row * sizeof(char *));
    for(i=0;i<row;i++)
        maze[i]=(char*)malloc(column * sizeof(char));
    
    scanf("%d", &row);
    scanf("%d", &column);
        
    for(i=0;i<row;i++){
        for(j=0;j<column;j++){
            maze[i][j] = 'X';
        }
    }
    
    maze[0][1]=' ';
    maze[1][1]=' ';
        
    srand(time(NULL));
    move(1,1,maze,row,column);
    
     for(i=0;i<row;i++){
        for(j=0;j<column;j++){
            printf("%c", maze[i][j]);
        }
        printf("\n");
    }
    
    for(i=0;i<row;i++)
        free(maze[i]);
     free(maze);
}
