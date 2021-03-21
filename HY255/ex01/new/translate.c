#include <stdio.h>

enum type{C, S, C_T, S_T1, S_T2, S_D, C_D};


char letters[7][25] = {
	{'A','V','G','D','E','Z','H','8','I','K','L','M','N','K','O','P','R','S','S','T','Y','F','P','W'},
	{'a','v','g','d','e','z','h','8','i','k','l','m' ,'n','k','o','p','r','s','s','t','y','f','x','p','w'},
	{'A','0','E','H','I','0','O','0','Y','W'},
	{'a','e','h','i'},
	{'o','y','w'},
	{'i','y'},
	{'I','Y'},
};

void SmallLet(int input, char* buffer){
	buffer[0] = letters[S][input - 225];
}

void CapitalLet(int input, char* buffer){
    buffer[0] = letters[C][input - 193];
}

void CapitalLetT(int input, char* buffer){
    buffer[0] = letters[C_T][input - 182];
}

void SmallLetT_1(int input, char* buffer){
    buffer[0] = letters[S_T1][input - 220];  
}

void SmallLetT_2(int input, char* buffer){
    buffer[1] = letters[S_T2][input - 252];
}

void SmallLetD(int input, char* buffer){
    buffer[1] = letters[S_D][input - 250];
}

void CapitalLetD(int input, char* buffer){
    buffer[1] = letters[C_D][input - 218];
}

char* translate(int input){
    char* buffer = NULL;

	if(input >= 225 && input <= 249){       
        SmallLet(input, buffer);
    }else if(input >= 193 && input <= 217){ 
        CapitalLet(input, buffer);
    }else if(input >= 182 && input <= 191){ 
        buffer[0] = '\'';
        CapitalLetT(input, buffer);
    }else if(input >= 220 && input <= 223){ 
        buffer[0] = '\'';
        SmallLetT_1(input, buffer);
    }else if(input >= 252 && input <= 254){ 
        buffer[0] = '\'';
        SmallLetT_2(input, buffer);
    }else if(input == 250 || input == 251){ 
        buffer[0] = '\"';
        SmallLetD(input, buffer);
    }else if(input == 218 || input == 219){
        buffer[0] = '\"';
        CapitalLetD(input,buffer);
    }else if(input == 192){
        return "i\'\"";
    }else if(input == 224){
		return "y\'\"";
    }else{
        buffer[0] = input;
    }
    return buffer;
}

int main(void){
	unsigned char c = '\0';


	while(c != 255){
		c = getchar();
		printf("%s", translate(c));
	}
}
