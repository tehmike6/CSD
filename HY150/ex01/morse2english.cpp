#include <iostream>
using namespace std;

char morseTree[38];

void set_line(char key,int parent){		//This function takes as the first argument the character that we want to save in the tree and as a second the index that the parent of
    morseTree[(parent * 2) + 1] = key;  //this branch.And it saves it to the left of the tree branch.
}

void set_dot(char key,int parent){		//This function takes as the first argument the character that we want to save in the tree and as a second the index that the parent of
     morseTree[(parent * 2) + 2] = key; //this branch.And it saves it to the right of the tree branch.
}

int main(void){
	char input;			//Input is where all of our input will be saved.
	int index=0,flag=0; //Index shows our potition in the tree. Flag is a var that shows when the EOF is in the input.

	morseTree[0] = ' ';

	set_line('T',0);
	set_dot('E',0);
	set_line('M',1);
	set_dot('N',1);
	set_line('A',2);
	set_dot('I',2);
	set_line('O',3);
	set_dot('G',3);
	set_line('K',4);
	set_dot('D',4);
	set_line('W',5);
	set_dot('R',5);
	set_line('U',6);
	set_dot('S',6);
	set_line('Q',8);
	set_dot('Z',8);
	set_line('Y',9);
	set_dot('C',9);
	set_line('X',10);
	set_dot('B',10);
	set_line('J',11);
	set_dot('P',11);
	set_dot('L',12);
	set_dot('F',13);
	set_line('V',14);
	set_dot('H',14);
	set_line('0',15);
	set_dot('9',15);
	set_dot('8',16);
	set_dot('7',18);
	set_dot('6',22);
	set_line('1',23);
	set_line('2',27);
	set_line('3',29);
	set_line('4',30);
	set_dot('5',30);
	
	
	while((input = cin.get())){  //This while takes every input until it reaches the EOF and it terminates the program.
		if(input == -1){
			flag = 1;
		}if(flag == 1){
			cout << morseTree[index];
			return -1;
		}if(input == 32){ 		//When input is a white space then it prints the character that the index shows on the tree.
			cout << morseTree[index];
			index = 0;
		}else if(input == '\n'){ //When input is \n then it prints the character that the index shows on the tree and also prints \n.
			cout << morseTree[index] << '\n';
			index = 0;
		}else if(input == '/'){ //When input is / then we print a space.
			index =0;
		}else{
			if(input == '.'){	//If input is '.' then we change the index position so that it goes to the next parent.
				index = (index*2)+2;
			}else if(input == '-'){ //If input is '-' then we change the index position so that it goes to the next parent.
				index = (index*2) +1;
			}else{	// Any other input than .,-,/,\n,' ' is treated as an error and it terminates the program.
				cout << "Fatal Error wrong input::check if input is encoded in morse code::program terminated\n";
				return -1;
			}
		}if( index > 62){
			cout <<"Fatal Error morse code not recognized please check if the syntax is correct";
			return -1;
		}
	}
	return 0;
}