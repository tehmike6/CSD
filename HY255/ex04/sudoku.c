#include "sudoku.h"

int check_doubles(int box[9]){
    int check[10] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },i;

    for(i=0;i<9;i++){
        if(box[i]== 0) {
        	return 1;
        }
        if (check[box[i]] == 0){
		    check[box[i]] = 1;
        }else {
            check[0] = 1;
		}
    }
    return check[0];
}

int check_box(Grid_T g,int i,int j,int box[]){
	int row,col,n=0;

	i = i - i%3;
	j = j - j%3;

	for(col=0;col<3;col++){
		for(row=0;row<3;row++){
			box[n] = grid_read_value(g,col+i,row+j);
			n++;
		}
	}
	if(check_doubles(box) == 1){
		return 0;
	}
	return 1;
}

Grid_T sudoku_read(void){
	int i,j,val;
	Grid_T *g;
	g = (Grid_T*)malloc(sizeof(Grid_T));
	if(g == NULL){
		exit(0);
	}

	for(i=0;i<9;i++){
		for(j=0;j<9;j++){
			scanf("%d",&val);
			grid_update_value(g,i,j,val);
		}
	}
	return *g;
}

void sudoku_print(FILE *s, Grid_T g){
	int i,j,val;

	for(i=0;i<9;i++){
		for(j=0;j<9;j++){
			val = grid_read_value(g,i,j);
			fprintf(s,"%d ",val);
		}
		fprintf(s,"\n");
	}
	return;
}

void sudoku_print_errors(Grid_T g){
	int i,j,box[9]={0,0,0,0,0,0,0,0,0};
	
	for(i=0;i<9;i++){
		for(j=0;j<9;j++){
			box[j]= grid_read_value(g,i,j);
		}
		if(check_doubles(box) == 1){
			printf("Error/Line(%d)/Number/Found multiple times..\n",i+1);
		}
	}
	for(i=0;i<9;i++){
		for(j=0;j<9;j++){
			box[j] = grid_read_value(g,j,i);
		}
		if(check_doubles(box) == 1){
			printf("Error/Column(%d)/Number/Found multiple times..\n",i+1);
		}
	}

	for(i=0;i<9;i+=3){
		for(j=0;j<9;j+=3){
			if(check_box(g,i,j,box) == 0){
				printf("Error/Box(%d)/Has multiple copies of a number..\n",i+(j/3)+1);
			}
		}
	}

}

int sudoku_is_correct(Grid_T g){
	int i,j,box[9];

	for(i=0;i<9;i++){
		for(j=0;j<9;j++){
			box[j]= grid_read_value(g,i,j);
		}
		if(check_doubles(box)== 1){
			return 0;
		}
	}
	for(i=0;i<9;i++){
		for(j=0;j<9;j++){
			box[j] = grid_read_value(g,j,i);
		}
		if(check_doubles(box) == 1){
			return 0;
		}
	}
	for(i=0;i<9;i+=3){
		for(j=0;j<9;j+=3){
			if(check_box(g,i,j,box) == 0){
				return 0;
			}
		}
	}
	return 1;
} 

static void sudoku_init_choices(Grid_T *g){
	int i,j,k,l,pi,pj,box[9],n=0;
	for(i=0;i<9;i++){
		for(j=0;j<9;j++){
			if(grid_read_value(*g,i,j)!= 0){
				continue;
			}

			pi = i - i%3;
			pj = j - j%3;
			n=0;
			for(k=0;k<3;k++){
				for(l=0;l<3;l++){
					box[n] = grid_read_value(*g,pi+k,pj+l);
					n++;
				}
			}
			for(n=1;n<10;n++){
				grid_set_choice(g,i,j,n);
			}
			grid_set_count(g,i,j);

			for(n=0;n<9;n++){
                grid_remove_choice(g,i,j,grid_read_value(*g,i,n));
                grid_remove_choice(g,i,j,grid_read_value(*g,n,j));
                grid_remove_choice(g,i,j,box[n]);
            }
		}
	}
}

static int sudoku_try_next(Grid_T g, int *row, int *col){
	int i,j,n,min =9;
	srand(getpid());
	n = rand() % 9;

	for(i=0;i<9;i++){
		for(j=0;j<9;j++){
			if(grid_read_value(g,n,j) != 0){
				continue;
			}
			if(grid_read_count(g,n,j) == 0){
				return 0;
			}
			if(grid_read_count(g,n,j) < min){
				*row = n;
				*col = j;
				min = grid_read_count(g,n,j);
			}
		}
		n++;
		if(n==9){
			n=0;	/*Because we know that the puzzle is not solved*/
		}
	}
	
	for(i=1;i<10;i++){
		if(grid_choice_is_valid(g,*row,*col,i) == 1){
			return i;
		}
	}
	return 0;
}

static int sudoku_update_choice(Grid_T *g, int i, int j, int n){
	grid_remove_choice(g,i,j,n);
	grid_update_value(g,i,j,n);
	return grid_read_count(*g,i,j);
}

static void sudoku_eliminate_choice(Grid_T *g, int r, int c, int n){
	int i,j;

	for(i=0;i<9;i++){
		grid_remove_choice(g,r,i,n); /*This removes the choice n from the line*/
		grid_remove_choice(g,i,c,n); /*This removes the choice n from the column*/
	}
	r = r - r%3;
 	c = c - c%3;
 	for(i=0;i<3;i++){
 		for(j=0;j<3;j++){
 			grid_remove_choice(g,i+r,j+c,n);
 		}
 	}
}

Grid_T sudoku_solve(Grid_T g){
	int i=0,j=0,n;
	Grid_T copy;

	sudoku_init_choices(&g);

	while(1){
		n = sudoku_try_next(g,&i,&j);
		if(n == 0 ){
			return g;
		}

		if(sudoku_update_choice(&g,i,j,n) != 0){
			copy = sudoku_solve(g);
			if(sudoku_is_correct(copy)){
				if(grid_read_count(copy,i,j) != 0){
					grid_clear_unique(&copy);
				}
				return copy;
			}
			grid_update_value(&g,i,j,0);
		}else{
			sudoku_eliminate_choice(&g,i,j,n);
		}
	}
	return g;
}

static Grid_T sudoku_generate_complete(int nelts,char par){
	int i,j,count =0,flag =0;
	int n,r,c;
	Grid_T *g = malloc(sizeof(Grid_T));

	if(g == NULL){
		exit(0);
	}

	if(par == 'g'){
		for(i=0;i<9;i++){
			for(j=0;j<9;j++){
				grid_update_value(g,i,j,0);
			}
		}
		*g =sudoku_solve(*g);
		srand(getpid());

		for(i=0;i<9;i++){
			for(j=0;j<9;j++){
				n = rand() % 9;
				if(grid_read_value(*g,i,n) == 0){
					continue;
				}
				grid_update_value(g,i,n,0);
				count++;
				if(count > nelts){
					flag =1;
					break;
				}

			}
			if(flag == 1){
				break;
			}
		}
	}else if(par == 'u'){

		for(i=0;i<9;i++){
			for(j=0;j<9;j++){
				grid_update_value(g,i,j,0);
			}
		}
		sudoku_print(stdout,*g);
		sudoku_init_choices(g);

		for(i=0;i<9;i++){
			for(j=0;j<9;j++){
				n = sudoku_try_next(*g,&r,&c);
			if(n == 0 ){
				return *g;
			}
			if(sudoku_update_choice(g,r,c,n) != 0){
				if(sudoku_is_correct(*g)){
					if(grid_read_unique(*g)){
						continue;
					}else{
						sudoku_eliminate_choice(g,r,c,n);
						continue;
					}
				}else{
					sudoku_eliminate_choice(g,r,c,n);
					continue;
				}
			}


			}
		}

		sudoku_print(stdout,*g);
		for(i=0;i<9;i++){
			for(j=0;j<9;j++){
				n = rand() % 9;
				if(grid_read_value(*g,i,n) == 0){
					continue;
				}
				grid_update_value(g,i,n,0);
				count++;
				if(count > nelts){
					flag =1;
					break;
				}
			}
			if(flag == 1){
				break;
			}
		}

		sudoku_print(stdout,*g);


	}

	return *g;
}

int main(int argc,char **argv){
	Grid_T g;


	if(argc == 1){
		printf("Your Puzzle: \n");
		g = sudoku_read();
		sudoku_print(stderr,g);
		g = sudoku_solve(g);
		if(!sudoku_is_correct(g)){
			sudoku_print_errors(g);
			return 0;
		}else{
			printf("Puzzle has a ");
            if(grid_read_unique(g)) printf("unique ");
            printf("solution:\n");
		}
		sudoku_print(stderr,g);
	}else if(!(strcmp("-c",argv[1]))){
		g = sudoku_read();
		printf("Your Puzzle: \n");
		sudoku_print(stderr,g);
		if(sudoku_is_correct(g)){
			printf("Your puzzle is correct");
			return 0;
		}else{
			sudoku_print_errors(g);
			return 0;
		}
	}else if(!(strcmp("-g",argv[1]))){
		if(argc < 3) printf("Wrong syntax/type <sudoku -g nelts (-u)");
		if(argc == 3){
			printf("Your puzzle with %d none zero numbers: \n",atoi(argv[2]));
			g = sudoku_generate_complete(81 - atoi(argv[2]),'g');
			sudoku_print(stderr,g);
			return 0;
		}else if(!(strcmp("-u",argv[3]))){
			printf("Your puzzle with %d none zero numbers and unique solution: \n",atoi(argv[2]));
			g = sudoku_generate_complete(81- atoi(argv[2]),'u');
			sudoku_print(stderr,g);
			return 0;
		}
	}
	return 0;
}