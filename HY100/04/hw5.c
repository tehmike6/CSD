#include <stdio.h>
#include <stdlib.h>

struct students{
    char name[32];
    char surname[32];
    int id;
    float grade;
} * student, tmp;

void swap(struct students *xP, struct students *yP){
    struct students temp;
    temp = *xP;
    *xP = *yP;
    *yP = temp;
}

int main(int argc, char const *argv[]){
    
    int stnumber,i,j;
    float gradeTotal=0,gradeAverage;
    
    FILE *fp1 = fopen(argv[1], "r");
    FILE *fp2 = fopen(argv[2], "w");
    
    if(fp1 == NULL || fp2 == NULL ){
        printf("File didn't open.");
    }
    
    fscanf(fp1,"%d",&stnumber);
    
    student = (struct students *)malloc(stnumber * sizeof(struct students));
    
    
    for(i=0;i<stnumber;i++){
        fscanf(fp1, "%s%s%d%f", &student[i].name, &student[i].surname, &student[i].id, &student[i].grade);
        gradeTotal=gradeTotal + student[i].grade;
        }
    
    for(i=0;i<stnumber-1;i++){
        for(j=0;j<stnumber-i-1;j++){
            if(student[j].grade>student[j+1].grade){
                swap(&student[j],&student[j+1]);
            }
            else if(student[j].grade==student[j+1].grade){
                if(student[j].id>student[j+1].id){
                    swap(&student[j],&student[j+1]);
                }
            }
        }
    }
    
    
    fclose(fp1);
    gradeAverage=gradeTotal/stnumber-0.005;
    
    fprintf(fp2,"max: %s %s %d %.4g\n",student[stnumber-1].name, student[stnumber-1].surname, student[stnumber-1].id,student[stnumber-1].grade);
    fprintf(fp2,"min: %s %s %d %.4g\n",student[0].name, student[0].surname, student[0].id,student[0].grade);
    fprintf(fp2,"average: %.2f\n",gradeAverage);
    
    for(i=0;i<stnumber;i++){
        fprintf(fp2, "%s %s %d %.4g\n",student[i].name, student[i].surname, student[i].id, student[i].grade);
    }
   return 0; 
}

