#include <assert.h>
#include <stddef.h>
/*
Mike Bastakis 
CSD 4406
mystring_ars.c
*/
/*
This function uses a pointer//array str and checks every character that is stored until it finds \0 and returns the number of the
characters that it scanned as an size_t variable length. 
*/
size_t ms_length(const char * str){
    const char *length = str;
    assert(str);
    while(*length != '\0'){
        length++;
    }
    return  (length-str);
}
/*
This function is given a space other_string to store a string that it will copy from this_string it first finds where the first string ends 
vy looking for \0 and then until it finds the \0 in the second string and everytime it finds a character different of \0 it inserts it
in the other_string.
*/
char* ms_copy(char *other_string,char* this_string){
    char *index = other_string;
    assert(other_string);
    assert(this_string);
    while(*this_string != '\0'){
        *index++ = *this_string++;
    }
    return (other_string);
}

char *ms_ncopy(char* other_string,const char* this_string,size_t length){
    char *index = other_string;
    assert(other_string);
    assert(this_string);
    while(*this_string != '\0' && length != 0){
        *index++ = *this_string++;
        --length;
    }
    return (other_string);
}

char *ms_concat(char* other_string,const char* this_string){
    char* index = other_string;
    assert(other_string);
    assert(this_string);
    while(*index != '\0'){
        index++;
    }
    while(*this_string != '\0'){
        *index++ = *this_string++;
    }
    *index = '\0';
    return other_string;
}

char *ms_nconcat(char* other_string,const char* this_string,size_t length){
    char* index = other_string;
    assert(other_string);
    assert(this_string);
    while(*index != '\0'){
        index++;
    }
    while(*this_string != '\0' && length != 0){
        *index++ = *this_string++;
        --length;
    }
    *index = '\0';
    return other_string;
}

int ms_compare(const char* str1, const char* str2){
    assert(str1);
    assert(str2);
    while(str1 != '\0' && str2 != '\0' && *str1==*str2){
        str1++;
        str2++;
    }
    if(*str1 > *str2){
        return 1;
    }
    else if(*str1 < *str2){
        return -1;
    }
    else{
        return 0;
    }
}

int ms_ncompare(const char* str1, const char* str2,size_t length){
    assert(str1);
    assert(str2);
    while(str1 != '\0' && str2 != '\0' && *str1==*str2 && length != 0){
        str1++;
        str2++;
        length--;
    }
    if(*str1 > *str2){
        return 1;
    }
    else if(*str1 < *str2){
        return -1;
    }
    else{
        return 0;
    }
}

char *ms_search(char* haystack, const char* needle){
    assert(haystack);
    assert(needle);
    char* index = haystack;
    while(*index != '\0'){
        char* search = index, *find = (char*)needle;
        while(*search == *find && *find != '\0'){
            search++;
            find++;
        }
        if(*find == '\0'){
            return index; 
        }
        index++;
    }
    return NULL;
}
