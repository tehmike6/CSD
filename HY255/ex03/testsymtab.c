#include "symtable.h"

typedef struct{
    int sum;
    int count;
}Virus;

void print_virus(const char* pcKey, void* pvValue, void* pvExtra){
    printf("%s: %s\n", pcKey, (char*)pvValue);
}

void get_virus(const char* pcKey, void* pvValue, void* pvExtra){
    Virus *pvirus = (Virus*) pvExtra;
    pvirus->sum   += *((int*) pvValue);
    pvirus->count += 1;
}

int main(void){
    int counter =0;
    SymTable_T virus = SymTable_new();

    if(SymTable_put(virus,"China",(void*)"81.093")==1) counter++;
    else{
        printf("Program Failed");
    }
    if(SymTable_put(virus,"Italy",(void*)"59.138")==1)counter++;
    else{
        printf("Program Failed");
    }
    if(SymTable_put(virus,"USA",(void*)"34.717")==1)counter++;
    else{
        printf("Program Failed");
    }
    if(SymTable_put(virus,"Germany",(void*)"24.873")==1)counter++;
    else{
        printf("Program Failed");
    }
    if(SymTable_put(virus,"Iran",(void*)"21.765")==1)counter++;
    else{
        printf("Program Failed");
    }
    if(SymTable_put(virus,"Uganda",(void*)"1")==1)counter++;
    else{
        printf("Program Failed");
    }
    if(SymTable_put(virus,"Greece",(void*)"85")==1)counter++;
    else{
        printf("Program Failed");
    }
    if(SymTable_put(virus,"Greece",(void*)"81.093")==0)counter++;
    else{
        printf("Program Failed");
    }
    if(SymTable_put(virus,"Space","none")==1)counter++;
    else{
        printf("Program Failed");
    }

    if(counter == 9){
        printf("Corona Virus was successfully added to countries\n");
    }else{
        printf("Program Failed");
    }

    printf("The number of countries infested from corona virus is %u\n",SymTable_getLength(virus));
    SymTable_remove(virus,"China");
    printf("The number of countries after the removal of china is  %u\n",SymTable_getLength(virus));
    if(SymTable_remove(virus,"Gree")==0){
        printf("Gree wasnt found in keys of virus table success\n");
    }else{
        printf("Program Failed");
    }

    if(SymTable_contains(virus,"China")==0){
        printf("China was surely removed\n");
    }else{
        printf("Program Failed");
    }

    SymTable_put(virus,"Greece","0000");
    if(SymTable_contains(virus,"Greece")==1){
        printf("Greece was added\n");
    }else{
        printf("Program Failed");
    }


    printf("The number of cases in Italy is %s\n",(char*)SymTable_get(virus,"Italy"));
    printf("The number of cases in Space is %s\n",(char*)SymTable_get(virus,"Space"));

    SymTable_map(virus,print_virus,NULL);
    /*SymTable_map(virus, get_virus, &virus);*/

    SymTable_free(virus);
    return 0;
}