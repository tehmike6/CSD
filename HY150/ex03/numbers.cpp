#include <iostream>
#include <fstream>
#include <sstream>

using namespace std;

struct person{
    int key;
    string name;
    struct person* next;
};

struct person *newNode(){
    struct person *root;
    root = new struct person;
    root -> key = 0;
    root -> next = NULL;
}

int list_length(struct person *list){
    int i=0;
    struct person *length = list;

    while(length -> next != NULL){
        i++;
        length = length -> next;
    }
    return i;
}

void pushNode(struct person *list,int value,string word){
    struct person *newNode;
    struct person *temp = list;

   
    while(temp -> next != NULL){
        temp = temp -> next;
    }

    newNode = new struct person;
    newNode -> key = value;
    newNode -> name = word;
    newNode -> next = NULL;
    temp -> next = newNode; 
    return;
}

int contains(struct person *list,int value){
    struct person *searchNode = list;

    while(searchNode -> key != value){
        if(searchNode -> next == NULL){
            cout << "Could not find the number you were searching for in the list" << endl;
            return 0;
        }
        searchNode = searchNode -> next;
    }
    cout <<"String -> " <<  searchNode -> name << endl;
    return 1;
}

bool isNumber(string s) 
{ 
    for (int i = 0; i < s.length(); i++) 
        if (s[i]<48 || s[i]>57){ 
            return false; 
        }
    return true; 
}

int digits(int n){
    int count =0;
    while(n!=0){
        n /= 10;
        count++;
    }
    return count;
}

int main(int argc,char **argv){
    ifstream input;
    struct person *list;
    int number,i;
    string str;

    input.open(argv[1]);

    if(input.is_open()){
        list = newNode();
        input >> number >> str;

        while(!input.eof()){
            pushNode(list,number,str);
            input >> number >> str;
        }
    }else{
        cout << "File not found" << endl;
        return 0;
    }

    input.close();
    
    while(true){
        string search;
        int number;
        stringstream ss;

        cout << "Please enter a 5-digit number: ";
        cin >> search;
        if(isNumber(search) == false){
            cout << "The input you have entered <" << search << "> is not an integer number."<<endl;;
            continue;
        }
        ss << search;
        ss >> number;
        if(number == 0){
            cout << "Terminating program ..."<<endl;
            return 0;
        }
        int digit = digits(number);
        if(digit > 5){
            cout << "The number you entered has more than five digits."<< endl;
        }else if(digit < 5){
            cout << "The number you entered has less than five digits"<<endl;
        }else{
            contains(list,number);
        }
    }
    return 0;
}