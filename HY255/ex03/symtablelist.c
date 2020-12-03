#include "symtable.h"
/* This struct contains an abstract data type and is a linked list*/
struct binding{
    char *key;
    void *value;
    struct binding * next;
};
/*This is the root of the linked list and also contains how many lists are linked*/
struct SymTable_S{
    struct binding * root;
    unsigned int length;
};
/*This function creates an empty list and returns it */
SymTable_T SymTable_new(void){
    SymTable_T newSymTable = malloc(sizeof(SymTable_T*)+1);
    newSymTable -> length = 0;
    newSymTable -> root = NULL;
    return newSymTable;
}
/*This function frees the memory we alloccated for the lists by starting from the root of the lists and gradually
removing them from start to finish*/
void SymTable_free(SymTable_T oSymTable){
    struct binding *current,*next;
    int i;
    if(oSymTable){
        return;
    }
    current = oSymTable -> root;
    for(i=0;i<oSymTable -> length;i++){
        next = current -> next;
        free(current -> key);
        free(current);
        current = next;
    }
    free(oSymTable);
    return;
}
/*This function returns a number that indicates how many lists are created and linked */
unsigned int SymTable_getLength(SymTable_T oSymTable){
    assert(oSymTable);
    return oSymTable -> length;
}
/*This function checks if the pcKey is a key in the oSymTable if it is the function returns 1 or else it returns 0
*/
int SymTable_contains(SymTable_T oSymTable, const char *pcKey){
    int i;
    struct binding *searchNode;
    assert(oSymTable);
    assert(pcKey);

    searchNode = oSymTable -> root;
    for(i=0;i<oSymTable->length;i++){   /*This runs until i is less equal to the number of linked lists*/
        if(strcmp(searchNode -> key, pcKey)==0){
            return 1;
        }
        searchNode = searchNode -> next;
    }
    return 0;
}
/*This function creates a node at the root if the pcKey is not used as a key from the oSymTable*/
int SymTable_put(SymTable_T oSymTable, const char *pcKey, const void *pvValue){
    char *copyPcKey;
    struct binding *node;

    assert(oSymTable);
    assert(pcKey);

    if(SymTable_contains(oSymTable,pcKey)==1){
        return 0;
    }

    copyPcKey =(char*) malloc(strlen(pcKey)+1);
    node = (struct binding*)malloc(sizeof(struct binding));
    assert(node);
    assert(copyPcKey);
    strcpy(copyPcKey,pcKey);    /*We create a copy of pcKey and store it to copyPcKey*/

    node -> key = copyPcKey;
    
    node -> value = (void*)pvValue;
    node -> next = oSymTable -> root;
    oSymTable ->root = node;
    oSymTable -> length++;
    return 1;
}
/*This function removes the node with the key pcKey from oSymTable and returns 0 if the key waasnt found
and returns 1 if it was found and was successfully removed*/
int SymTable_remove(SymTable_T oSymTable, const char *pcKey){
    int i;
    struct binding *currentNode,*previousNode;
    assert(oSymTable);
    assert(pcKey);
    
    currentNode = oSymTable -> root;
    previousNode = NULL;
    for(i=0;i<oSymTable -> length;i++){
        if(strcmp(currentNode -> key,pcKey)==0){
            break;
        }
        previousNode = currentNode;
        currentNode = currentNode -> next;
    }
    /*This checks if the key wasnt found*/
    if(currentNode == NULL){
        return 0;
    }
    /*If the pcKey is the first key then */
    if(previousNode == NULL){
        oSymTable -> root = currentNode -> next;
    }else{    
        previousNode -> next = currentNode -> next;
    }
    free(currentNode -> key);
    free(currentNode);
    oSymTable -> length--;
    return 1;
}
/*This function looks for a node with pcKey in oSymTable if it doesnt exist it returns NULL if it exists it
returns the value of the node with the key pcKey*/
void *SymTable_get(SymTable_T oSymTable, const char *pcKey){
    struct binding *getNode;
    int i;
    assert(oSymTable);
    assert(pcKey);

    getNode = oSymTable -> root;
    for(i=0;i<oSymTable->length;i++){
        if(strcmp(getNode->key,pcKey)==0){
            return getNode -> value;
        }
        getNode = getNode -> next;
    }
    return NULL;
}
/*This function starts from the root of the list and it runs the function pfApply for every node of the linked list*/
void SymTable_map(SymTable_T oSymTable,
void (*pfApply)(const char *pcKey, void *pvValue, void *pvExtra),
const void *pvExtra){
    int i;
    struct binding *node;
    assert(oSymTable);
    node = oSymTable -> root;
    for(i=0;i<oSymTable -> length;i++){
        pfApply((const char *)(node -> key), node->value, (void *)pvExtra);
        node = node -> next;
    }
    return;
}