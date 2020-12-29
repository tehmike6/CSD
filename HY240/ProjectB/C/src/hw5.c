#include <stdio.h>
#include <stdlib.h>
#include <string.h>

enum Colour{red , green , blue , yellow , cyan , magenta , black};

typedef struct rect{
  int id;
  int x;
  int y;
  int w;
  int h;
  enum Colour xrwma;
  int filled;
} rect_t;

typedef struct node{
  rect_t data;
  struct node *next;
}node_t;

node_t *head = NULL;
node_t  *current = NULL;

/*EISAGOGI SE TAXINOMIMENO*/
node_t *LLInsert(int x ,int y ,int w ,int h,enum Colour xrwma,int filled ,int id ,node_t *L){
  node_t *node;
  node_t *q=L;
  node_t *pq;
  pq=NULL;
  
  while(q!=NULL && (q->data.w*q->data.h<w*h|| ( q->data.w*q->data.h==w*h && q->data.id<id))){

    pq=q;
    q = q->next;

  }

  /* if(q!=NULL && q->data.x*q->data.y==x*y){ */ /* edo einai lathos, thes na vazeis kai tis isotites ,apla min to valeis */
  /*	exit(3); */
  /* } */
  
  node = (node_t*) malloc(sizeof(node_t));
  node->data.x=x;
  node->data.y=y;
  node->data.w=w;
  node->data.h=h;
  node->data.xrwma=xrwma;
  node->data.filled=filled;
  node->data.id=id;
  node->next=q;
  if(pq==NULL){
    L=node;
  }else{
    pq->next=node;
  }

  return L;
}


int main(int argc , char* argv[]){
  enum Colour colour;
  char *xrwma = malloc(8*sizeof(char));
  char xrwmata[7][8]={"red" , "green" , "blue" , "yellow" , "cyan" , "magenta" , "black"};
  int rectangles , canvasx , canvasy,x,y,w,h,filled,id,i,count,j;
  FILE *input_file;
  input_file = fopen(argv[1], "r");

  if( input_file == NULL ){
    fprintf(stderr , "Cannot open file %s\n" , argv[1]);
    exit(0);
  }
  fscanf( input_file , "%d %d %d " , &rectangles , &canvasx , &canvasy);
  for(i=0;i<rectangles;i++){
    /*diavasma dedomenwn*/
    fscanf(input_file , "%d %d %d %d %s %d" , &x , &y , &w , &h , xrwma , &filled);
    
    /*elegxos gia dekto orthogonio*/
    if(x<0 || x+w>canvasx || y+h>canvasy || y<0){
      printf("Rectangle %d has wrong definition\n" , i);
      exit(0);
    }
 
    for(j=0;j<7;j++){
    	if(strcmp(xrwma,xrwmata[j])==0){
    		colour = j;
    	}
    }

    id=i;
    head=LLInsert(x ,y ,w ,h,colour ,filled ,id ,head);
  }
  
  /*min kai max area*/
  if(current==NULL) return 0;
  current= head;
  printf("min area:%d:%d\n" , current->data.id , current->data.w*current->data.h);
  while(current->next!=NULL){
    current = current->next;
  }
  printf("max area:%d:%d\n", current->data.id , current->data.w*current->data.h);
  /*min to max area*/
  current = head ;
  printf("rectangles by area:\n");
  while(current->next!=NULL){
    printf("%d:%d\n" , current->data.id , current->data.w*current->data.h);
    current = current ->next;
  }
  printf("%d:%d\n" , current->data.id , current->data.w*current->data.h);

  /*rectangles by colour*/
  count=0;
  current = head;
  printf("rectangles by color:\n");
  printf("\nred:");
  while(current->next!=NULL){
    if(current->data.xrwma==0){
    	count+=1;
    	if(count>1){
    		printf(",");
    	}
        printf(" %d" , current->data.id);
    }
    current = current ->next;
  }
  count=0;
  current = head;
  printf("\ngreen:");
  while(current->next!=NULL){
    if(current->data.xrwma==1){
    	count+=1;
    	if(count>1){
    		printf(",");
    	}
        printf(" %d" , current->data.id);
    }
    current = current->next;
  }
  count=0;
  current = head;

  printf("\nblue:");
  while(current->next!=NULL){
    if(current->data.xrwma==2){
    	count+=1;
    	if(count>1){
    		printf(",");
    	}
        printf(" %d" , current->data.id);
    }
    current = current ->next;
  }
  count=0;
  current = head;

  printf("\nyellow:");
  while(current->next!=NULL){
    if(current->data.xrwma==3){
    	count+=1;
    	if(count>1){
    		printf(",");
    	}
        printf(" %d" , current->data.id);
    }
    current = current ->next;
  }
  count=0;
  current = head;

  printf("\ncyan:");
  while(current->next!=NULL){
    if(current->data.xrwma==4){
    	count+=1;
    	if(count>1){
    		printf(",");
    	}
        printf(" %d" , current->data.id);
    }
    current = current ->next;
  }
  count=0;
  current = head;

  printf("\nmagenta:");
  while(current->next!=NULL){
    if(current->data.xrwma==5){
    	count+=1;
    	if(count>1){
    		printf(",");
    	}
        printf(" %d " , current->data.id);
    }
    current = current ->next;
  }
  count=0;
  current = head;

  printf("\nblack:");
  while(current->next!=NULL){
    if(current->data.xrwma==6){
    	count+=1;
    	if(count>1){
    		printf(",");
    	}
        printf(" %d" , current->data.id);
    }
    current = current ->next;
  }
  fclose(input_file);
  return 0;
}


