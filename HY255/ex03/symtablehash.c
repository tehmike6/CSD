#include "symtable.h"

#define HASH_MULTIPLIER 65599

struct SymTable_S{
	struct binding **table;
	unsigned int length;
	unsigned int buckets;
};

typedef struct binding{
	char *key;
	void *value;
	struct binding *next;
}binding;

static unsigned int SymTable_hash(const char *pcKey){
size_t ui;
unsigned int uiHash = 0U;
for (ui = 0U; pcKey[ui] != '\0'; ui++)
uiHash = uiHash * HASH_MULTIPLIER + pcKey[ui];
return uiHash%509;
}

SymTable_T SymTable_new(void){
	SymTable_T newTable = malloc(sizeof(SymTable_T*));
	assert(newTable);
	newTable -> length = 0;
	newTable -> table = malloc(509 * sizeof(binding*));
	newTable -> buckets = 509;
	return newTable;
}

void SymTable_free(SymTable_T oSymTable){
	binding *current,*next;
 	int i;
	if(oSymTable == NULL){
		return;
	}
	for(i=0;i<oSymTable->buckets;i++){
		current = oSymTable->table[i];
		while(current != NULL){
			next = current -> next;
			free(current -> key);
			free(current);	
			current = next;		
		}
	}
	free(oSymTable->table);
	return;
}

unsigned int SymTable_getLength(SymTable_T oSymTable){
	assert(oSymTable);
	return oSymTable -> length;
}

int SymTable_put(SymTable_T oSymTable, const char *pcKey, const void *pvValue){
	binding *newnode;
	char *newkey;
	int index;

	assert(oSymTable);
	assert(pcKey);

	if(SymTable_contains(oSymTable,pcKey)==1){
		return 0;
	}

	newkey = (char*)malloc(strlen(pcKey)+1);
	assert(newkey);
	strcpy(newkey,pcKey);

	newnode = (binding*)malloc(sizeof(binding));
	assert(newnode);
	newnode -> value = (void*)pvValue;
	newnode -> key = newkey;

	index = SymTable_hash(pcKey);
	newnode -> next = oSymTable -> table[index];
	oSymTable -> table[index] = newnode;
	oSymTable -> length = oSymTable -> length + 1;

	return 1;
}

int SymTable_remove(SymTable_T oSymTable, const char *pcKey){
	binding *currentNode,*previousNode;

	assert(oSymTable);
	assert(pcKey);

	currentNode = oSymTable->table[SymTable_hash(pcKey)];
	previousNode =NULL;

	while(currentNode != NULL){
		if(strcmp(currentNode->key,pcKey)==0){
			break;
		}
		previousNode = currentNode;
		currentNode = currentNode -> next;
	}
	if(currentNode == NULL){
		return 0;
	}

	if(previousNode == NULL){
		oSymTable->table[SymTable_hash(pcKey)] = currentNode -> next;

	}else{
		previousNode -> next = currentNode -> next;	
	}
	free(currentNode->key);
	free(currentNode);
	oSymTable -> length = oSymTable -> length - 1;
	return 1;

}

int SymTable_contains(SymTable_T oSymTable, const char *pcKey){
	binding *search;

	assert(oSymTable);
	assert(pcKey);

	search = oSymTable -> table[SymTable_hash(pcKey)];
	while(search != NULL){
		if(strcmp(search->key,pcKey)==0){
			return 1;
		}
		search = search -> next;
	}
	return 0;
}

void *SymTable_get(SymTable_T oSymTable, const char *pcKey){
	binding *getNode;

	assert(oSymTable);
	assert(pcKey);

	getNode = oSymTable -> table[SymTable_hash(pcKey)];
	while(getNode != NULL){
		if(strcmp(getNode -> key,pcKey)==0){
			return getNode -> value;
		}
		getNode = getNode -> next;
	}
	return NULL;
}

void SymTable_map(SymTable_T oSymTable,
				void (*pfApply)(const char *pcKey, void *pvValue, void *pvExtra),
				const void *pvExtra){

	int i;
	binding *currentNode;

	assert(oSymTable);

	for(i=0;i<oSymTable->buckets;i++){
		currentNode = oSymTable -> table[i];
		while(currentNode != NULL){
			pfApply((const char *)(currentNode->key),currentNode->value,(void*)pvExtra);
			currentNode = currentNode -> next;
		}
	}
}