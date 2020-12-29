#include <iostream>
#include <sstream> 
#include <iomanip>

using namespace std;

double conversion(string);
int errors(string);


char units[10] = {'T','G','M','k','d','c','m','u','n','p'};
int  power[10] = {12,9,6,3,-1,-2,-3,-6,-9,-12};

int main(void){
    double number;
    string word;
    cout << "Enter a number that will be converted from a unit of messurement to meters : ";
    cin >> word;
    while(word != "q"){
        if(errors(word) == -1){
            cout << "Enter another number: ";
            cin >> word;
          continue;
        }
        
        number = conversion(word);
        cout << "Your number in meteres has been converted to: ";
        cout << setprecision(13) << number <<'m'<< endl;
        cout << "Enter another number: ";
        cin >> word;
    }
    cout << "Terminating program beep boop..." << endl;
    return 1;
}

// This fuction checks the string for errors/ If the number is invalid/ If the unit of messurement is invalid/ If m is missing.
// It returns 1 if no errors found and -1 if at least 1 error has occured.
int errors(string word){
    int length,number,i,dot=0;
    bool flagUnit;

    length = word.length();

    if(length > 1){
        for(i=0;i<10;i++){
            if(word[length-2] == units[i]){
                flagUnit = true;
            }
        }
        for(i=0;i<length-2;i++){
            if(word[i]<48 || word[i]> 57){
                if(word[i] == 46){
                    dot++;
                    if(dot>1){
                        dot=0;
                    }else{
                    continue;
                    }
                }
                cout << "The number you have entered is invalid\n";
                return -1;
            }
        }
        if(word[length-1] != 'm'){
            cout << "Missing meters in the end of the number you have inputed\n";
            return -1;
        }
        if(word[word.length()-2]>=48 && word[word.length()-2]<=57){
            cout << word << endl;
            return -1;
        }
        if(flagUnit != true){
            cout << "Invalid unit of messurement \'" << word[length -2] << "\' .\n";
            return -1;
        }
        if(length==2){
            cout << "Missing number../ You must enter a number before <"<<word<<"> \n";
            return -1;
        }
    }else{
        cout << "Invalid input missing number or unit of messurement\n";
        return -1;
    }
    return 1;

}

// This fuction converts the string to a number by searching what unit of messurement is inputed and returns the converted number.
double conversion(string word){
    int i,index,p;
    double number;
    bool flagNegative=false;
    string temp;

    for(i=0;i<10;i++){
        if(word[word.length()-2] == units[i]){
            index = i;
            break;
        }
    }

    stringstream ss;
    for(i=0;i<word.length()-2;i++){
        temp += word[i];
    }
    ss << temp;
    ss >> number;

    if(power[index] <0){
        flagNegative = true;
        p = power[index] * (-1);
    }else{
        p = power[index];
    }

    for(i=0;i<p;i++){
        if(flagNegative == true){
            number = number /10;
        }else{
            number = number *10;
        }
    }

    return number;
}