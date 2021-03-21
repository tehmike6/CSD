#include <stdio.h>

/*This is where we declare the mappings of our translation*/
char SmallLetA[25]= 
    {'a','v','g','d','e','z','h','8','i','k','l','m' ,'n','k','o','p','r','s','s','t','y','f','x','p','w'};
    char CapitalLetA[25] = {'A','V','G','D','E','Z','H','8','I','K','L','M','N','K','O','P','R','S','S','T','Y','F','X','P','W'};
    char CapitalLetTA[10] = 
    {'A','0','E','H','I','0','O','0','Y','W'};
    char SmallLetT_1A[4] = 
    {'a','e','h','i'};
    char SmallLetT_2A[3] = 
    {'o','y','w'};
    char SmallLetDA[2] = {'i','y'};
    char CapitalLetDA[2] = {'I','Y'};

int choice(int);    
    
    /*This will be the function that is a finite state machine that transaltes mp = b , Mp = B*/
int fsm_m(int index,int flag){
    unsigned char next_c;/*This will store the next character of the string*/
    next_c = getchar();
    if( next_c == 208 || next_c == 240){
        if(flag == 0){
            printf("%c",'b');
        }else{
            printf("%c",'B');
        }
    }else if(flag == 0){    /*The flag helps us understand if the character m is capital or small */
        putchar(SmallLetA[index]);
        choice(next_c);
    }else{
        putchar(CapitalLetA[index]);
        choice(next_c);
    }
    return 0;
}
    /*This will be the function that is a finite state machine that transaltes nt = d , Nt = D */
int fsm_n(int index,int flag){
    unsigned char next_c;/*This will store the next character of the string*/
    next_c = getchar();
    if( next_c == 212 || next_c == 244){
        if(flag == 0){
            printf("%c",'d');
        }else{
            printf("%c",'D');
        }
    }else if(flag == 0){    /*The flag helps us understand if the character n is capital or small */
        putchar(SmallLetA[index]);
        choice(next_c);
    }else{
        putchar(CapitalLetA[index]);
        choice(next_c);
    }
    return 0;
}

int SmallLet(int input){
    int index,counter=0;
    index = input - 225;
    if( index == 11){
        fsm_m(index,0);
        counter ++;
    }else if(index == 12){
        fsm_n(index,0);
        counter++;
    }
    if(counter == 0){ /*Counter keeps track of our character if fsm is called then the char will be printed there so we dont*/
        putchar(SmallLetA[index]);                  /*need to print it here*/
    }
    if(index == 13 || index == 23){
        printf("%c",'s');
    }
    counter = 0;
    return 0;
}

int CapitalLet(int input){
    int index,counter = 0;
    index = input - 193;
    if( index == 11){
        fsm_m(index,1);
        counter++;
    }else if(index == 12){
        fsm_n(index,1);
        counter++;
    }
    if(counter == 0){ /*Counter keeps track of our character if fsm is called then the char will be printed there so we dont*/                       
        putchar(CapitalLetA[index]);                /*need to print it here*/
    }
    if(index == 13 || index == 23){
        printf("%c",'S');
    }
    counter = 0;
    return 0;
}

int CapitalLetT(int input){
    int index;
    index = input - 182;
    printf("\'");
    putchar(CapitalLetTA[index]);
    return 0;
}

int SmallLetT_1(int input){
    int index;
    index = input - 220;
    putchar(SmallLetT_1A[index]);
    printf("\'");
    return 0;
    
}

int SmallLetT_2(int input){
    int index;
    index = input - 252;
    putchar(SmallLetT_2A[index]);
    printf("\'");
    return 0;
}

int SmallLetD(int input){
    int index;
    index = input - 250;
    putchar(SmallLetDA[index]);
    printf("\"");
    return 0;
}

int CapitalLetD(int input){
    int index;
    index = input - 218;
    printf("\"");
    putchar(CapitalLetDA[index]);
    return 0;
}

/*This function will choose ,based on the info the gets, what function to call to translate the character*/
int choice(int input){
    if(input >= 225 && input <= 249){       /* Those are the values for a-w*/
        SmallLet(input);
    }else if(input >= 193 && input <= 217){ /* Those are the values for A-W*/
        CapitalLet(input);
    }else if(input >= 182 && input <= 191){ /*Those are the values for A-W with t*/
        CapitalLetT(input);
    }else if(input >= 220 && input <= 223){ /* Those are the values for a,e,h,i with t */
        SmallLetT_1(input);
    }else if(input >= 252 && input <= 254){ /* Those are the values for y, o, w with t*/
        SmallLetT_2(input);
    }else if(input == 250 || input == 251){ /* Those are the values for i, y with dialytika*/
        SmallLetD(input);
    }else if(input == 218 || input == 219){ /* Those are the values for I, Y with dialytika*/
        CapitalLetD(input);
    }else if(input == 192){
        printf("i\'\"");                    /* This prints the i with t and dialytika */
    }else if(input == 224){
        printf("y\'\"");                    /* This prints the y with t and dialytika */
    }else{
        putchar(input);                     /* This line prints any character that is not a letter*/
    }
    return 0;
}

int main(void){
    
    unsigned char input; /* This is the variable that will hold every char*/
    
    while(input != 255){ /* The loop will run until the input is 255 = EOF*/
        input = getchar(); 
        choice(input);
        
    }
    return 0;
}   
