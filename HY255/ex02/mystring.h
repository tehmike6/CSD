/*
 Mike Bastakis
 csd 4406
 */
#ifndef MYSTRING_H
#define	MYSTRING_H

#ifdef	__cplusplus
extern "C" {
#endif
/*
This function uses a pointer//array str and checks every character that is stored until it finds \0 and returns the number of the
characters that it scanned as an size_t variable length. 
*/
size_t ms_length(const char * str);
/*
This function is given a space other_string to store a string that it will copy from this_string.It places the character that the
index is pointing to in the other_string until the index reaches \0.
*/
char* ms_copy(char *other_string,char* this_string);
/*This function does the same job as ms_copy but the difference is that we take as a third argument a number.So the index is 
pointing this_string and it places the character the index is pointing to the other_string and it stops only if it finds \0 or 
if the numbe rwe got as the third argument is equal to the characters we have stored in the other_srting */
char *ms_ncopy(char* other_string,const char* this_string,size_t length);
/*
This function is given a space other_string to store a string that it will copy from this_string it first finds where the first string ends 
by looking for \0 and then until it finds the \0 in the second string and everytime it finds a character different of \0 it inserts it
in the other_string.
*/
char *ms_concat(char* other_string,const char* this_string);
/*This function does the same thing as the ms_concat but the third argument that is given to it is the length of the second string that 
we want to copy in to the first string.So this also appends the second string in to the first but only the number of characters
that are given as the third argument*/
char *ms_nconcat(char* other_string,const char* this_string,size_t length);
/*This function compares two strings by looking at the characters of those strings and checking which one is bigger smaller or equal
if the str1 has a bigger char than str2 the it returns a positive number if its smaller an negative number and if all characters
are equals then it returns zero*/
int ms_compare(const char* str1, const char* str2);
/*This is the same as ms_compare but it compares the first n characters where n is equal to the third argument of this function (length)  */
int ms_ncompare(const char* str1, const char* str2,size_t length);
/*This function sets a pointer to the first character of the string we are looking in haystack and the pointer goes to the next address of haystack
until it finds a character in haystack that is equal with the first character in needle.Then a second and a third pointer is set in the adress the 
haystack is in right now and needle and starts comparing the next characters that are in the next address everytime it finds equal characters
the pointers go to the next address until the pointer of needle finds \0 or the two pointer dont point the same chaacter.If needle finds 
\0 then we print the haystack pointer where the  initial location was saved or the loop continues and haystack pointer points in the next address.*/
char *ms_search(char* haystack, const char* needle);

#ifdef	__cplusplus
}

#endif

#endif	/* MYSTRING_H */


