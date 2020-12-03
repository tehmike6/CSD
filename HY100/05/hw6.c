#include <stdio.h>
#include <time.h>
#include <stdlib.h>

int move(int M,int N,char** maze,int row,int column){
    int check_up=0,check_down=0,check_left=0,check_right=0,count=0,choice[4],c,i,j;
    
    if(M > 1){
        if(maze[M-1][N] == 'X' && maze[M-2][N] == 'X' && M-2 > 0){
            check_up = 1;
            choice[count] = check_up;
            count++;}
    }
       
    if(M < row-2){
        if(maze[M+1][N] == 'X' && maze[M+2][N] == 'X' && M+2 < row - 1){
            check_down = 2;
            choice[count] = check_down;
            count++;}
    }
      
    if(N > 1){
        if(maze[M][N-1] == 'X' && maze[M][N-2] == 'X' && N-2 > 0){
            check_left = 3;
            choice[count] = check_left;
            count++;}
    }
            
    if(N < column-2){
        if(maze[M][N+1] == 'X' && maze[M][N+2] == 'X' && N+2 < column - 1){
            check_right = 4;
            choice[count] = check_right;
            count++;}
        }
    
    if(count != 0){
    c = choice[rand()%count];
    }else{return 0; }
    
    if(c == 1){
        maze[M-1][N] = ' ';
        maze[M-2][N] = ' ';
         move(M-2,N,maze,row,column);
         
    }
    if(c == 2){
      maze[M+1][N] = ' ';
      maze[M+2][N] = ' ';
       move(M+2,N,maze,row,column);
       
    }
    if(c == 3){
      maze[M][N-1] = ' ';
      maze[M][N-2] = ' ';
       move(M,N-2,maze,row,column);
       
    }
    if(c == 4){
     maze[M][N+1] = ' ';   
     maze[M][N+2] = ' ';
      move(M,N+2,maze,row,column);
      
    }
    
    move(M,N,maze,row,column);
    return 0;
}

int main(){
    int column,row,i,j;
    char **maze;
    
    scanf("%d", &row);
    scanf("%d", &column);
    maze=(char **)malloc(row * sizeof(char *));
    for(i=0;i<row;i++)
        maze[i]=(char*)malloc(column * sizeof(char));
        
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
