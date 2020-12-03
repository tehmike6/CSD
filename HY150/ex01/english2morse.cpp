#include <iostream>
using namespace std;
string morse[26] = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",
						".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."}; // This is a dictionary for all characters for A-Z

string numbers[10] = {"-----",".----","..---","...--","....-",".....","-....","--...","---..","----."}; // This is a dictionary for all characters from 0-9

int check(char input){ // This function check every input and returns 0 or 1. if the input was correct it returns 1 but if the input is invalid it returns 0.
	unsigned char index; // This is the index that shows what string should be printed in morse[].
	
	if( input >= 65 && input <= 90){ //We check if the input is from A-Z and then we subtract that input by 65 so that the index shows the right position in our dictionary.
		index = input - 65;
		cout << morse[index];
		cout << ' ';
		return 1;
	}else if(input == 32){ // If input is a white space then we print /.
		cout << '/';
		cout << ' ';
		return 1;
	}else if (input >= 48 && input <= 57){ //We check if the input is from 0-9 and then we subtract that input by 48 so that the index shows the right position in our dictionary.
		index = input - 48;
		cout << numbers[index];
		cout << ' ';
		return 1;
	}else if(input == '\n'){ //If input is \n then we print \n
		cout << input;
		return 1;
	}else{ //Any other input than A-Z , 0-9 , ' ', \n  is treated as invalid input and it terminates the program.
		cout << "Invalid Input error::404 character not found::in function (main)\n";
		return 0;
	}
}

int main(void){
	unsigned char input;

	while((input = cin.get()) != 255){
		if(check(input) == 0){ //It calls the function check and checks what it returns if return is 1 then it continues if return is 0 then the program is termninated.
			return 0;
		}	
	}
	return 0;
}