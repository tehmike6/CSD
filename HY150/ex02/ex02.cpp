#include <iostream>
#include <fstream>
#include <locale>
#include<sstream>
#include <string.h> 
#include <cstdio>
using namespace std;

/* This function swaps the main file with the temp it opens the main file and takes everyline and prints
it to the temp txt file.But also it keeps track of the lines of words*/
int swap_main(char *filename,char *filename2){
    int volume=0;
    string copy;
    ifstream input;
    ofstream output;

    input.open(filename);
    output.open(filename2);
    while(getline(input, copy)){
        output<<copy<<endl;
        volume++;
    }
    input.close();
    output.close();
    return volume;
}
/* This swaps the filename txt with filename2 txt simillar to the swap_main but it doesnt keep track of the lines*/
void swap(char *filename,char *filename2){
    string copy;
    ifstream input;
    ofstream output;

    input.open(filename);
    output.open(filename2);
    while(getline(input, copy)){
        output<<copy<<endl;
    }
    input.close();
    output.close();
}

/*This function sorts the filename it creates 3 other files. First we copy the filename to temp file then
we search for the minimum word in temp after we find it we print all of the temp file without the minimum word we 
found to tmp2 then we place the minimum word to final and we copy the words of temp2 to temp. It runs until 
we have sorted all the lines and at last we swap the final with the filename and delete all of the junk files*/
void sort(char* filename){
    int i, volume=0;
    char temp[]  = "temp.txt";
    char temp2[] = "temp2.txt";
    char final[] = "final.txt";
    string copy, max;
    ifstream input;
    ofstream output;

    volume = swap_main(filename,temp);

    for(i=0; i<volume; i++){
        input.open(temp);
        getline(input, max);
        while(getline(input, copy)){
            if(max.compare(copy)>0){
                max=copy;
            }
        }
        input.close();
        output.open(final, std::ofstream::out | std::ofstream::app);
        output << max << endl;
        output.close();
        remove(temp2);
        input.open(temp);
        output.open(temp2);
        while(getline(input, copy)){
            if(copy!=max){
                output<<copy<<endl;
            }
        }
        output.close();
        input.close();
        remove(temp);
        swap(temp2,temp);
    }
    swap(final,filename);
    remove(temp);
    remove(temp2);
    remove(final);
    return;
}
/* This checks everyargument for errors we check if the argument counter is 1 if we miss an argument or if the 
arguments in place 3*i+1 is not -f and -p*/
int errors(int argc,char **argv){
    int i;
    if(argc == 1){
        cout << "No arguments were found please enter -f a file and a word or -p (encoded / decoded)\n";
    }
    if((argc-1)%3 != 0){
        cout << "Missing arguments please check your arguments and try again\n";
        return -1;
    }
    for(i=0;i<(argc-1)/3;i++){
        if(!(strcmp(argv[1+3*i],"-f") == 0) && !(strcmp(argv[1+3*i],"-p") == 0)){
            cout << "The argumnet " << argv[1+3*i] << " is not a valid argument please enter -f or -p\n";
            return -1;
        }

        ofstream file;
        file.open(argv[2+3*i],std::ofstream::out | std::ofstream::app);
        file.close();
    }
}
/*This function imports a word to the destination file if the word does not already exist*/
int insert(char **argv,int i){
    int character,j; 
    string str = argv[3+3*i];
    string word;
    stringstream ss;
    for(j=0;j<str.size();j++){
        character = (int) tolower(str[j]);
        if(character < 100){
            ss << 0;
        }
        ss << character;
    }
    ss >> word;

    string haystack;
    ifstream input;
    input.open(argv[2+3*i]);
    if(input.is_open()){
        while(getline(input,haystack)){
            if(word.compare(haystack) == 0){
                return -1;
            }
        }
    }else{
        cout << "The file named " << argv[2+3*i] << " doesn't exist\n";
    }
    input.close();

    ofstream output;
    output.open(argv[2+3*i],std::ofstream::out | std::ofstream::app);
    if(!output.is_open()){
        cout << "The file named " << argv[2+3*i] << " cannot be opened\n";
    }else{
        output << word << '\n';
    }
}
/* This function prints the file that is asked to if we tell it to print encoded then it encodes the words in the file
with ascii encryption and if we tell it to decode the words it prints them as words*/
void output(char **argv,int i){
    sort(argv[3*i+2]);
    if(strcmp(argv[3*i+3],"encode")==0){
        string word;
        ifstream input;
        input.open(argv[3*i + 2]);
        if(!input.is_open()){
            cout << "You do not have the premission to pen the file" << argv[3*i + 2];
        }else{
            while(getline(input,word)){
                cout << word << endl;
            }
        }
        input.close();
    }else if( strcmp(argv[3*i+3],"decode")==0){
        char c;
        ifstream input;
        input.open(argv[3*i + 2]);
        if(!input.is_open()){
            cout << "Couldnt open file";
        }
        c =input.get();
        while(c!= -1){
            int j,character= 0,mult=100;
            if(c=='\n'){
                cout << endl;
                c = input.get();
            }else{
            character=0;
            mult=100;
            for(j=0;j<3;j++){
                character += (c-48)*mult;
                mult = mult/10;
                c = input.get();
            }
            cout << (char)character;
            }
        }
        input.close();
    }else{
        cout << "Invalid argument on " << i << " set of arguments please check if it is encode/decode";
    }
}

int main(int argc,char **argv){
    int i;
    if(errors(argc,argv) == -1){    // This checks most of the errors that come from the arguments
        return -1;
    }

    for(i=0;i<(argc-1)/3;i++){      // This checks in which cycle we are 
        if(strcmp(argv[1+3*i],"-f") == 0){
            if(insert(argv,i) == -1){
                cout << "The word " << argv[3+3*i] << " is found multiple times continuing...\n";
            }
            sort(argv[3*i+2]);
        }else{
            output(argv,i);
        }
    }
}
