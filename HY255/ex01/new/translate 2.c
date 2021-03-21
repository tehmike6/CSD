#include <stdio.h>

#define tonos 39
#define diali 34

#define M_gr 204 
#define N_gr 205
#define T_gr 212
#define P_gr 208
#define m_gr 236
#define n_gr 237
#define p_gr 240
#define t_gr 244
#define B_en 66
#define b_en 98
#define D_en 68
#define d_en 100

int translate(int x,int n);
void SimpleChar(int x);


enum states {St_B,St_b,St_D,St_d,St_S} st;

int main()
{
    int c,tmp='\0';
    st=St_S;
    while ((c=getchar())!=EOF) /*read character in iso7 code*/
    {
        if(c!='\n')
            tmp=translate(c,tmp); /*call a function to check the state and do what is needed*/ 
        else 
            putchar('\n');    
    }
    return 0;
}

int translate(int x,int n)
{
    switch(st) /*check the state*/
    {
        case St_B: 
            st=St_S;
            if(x==P_gr || x==p_gr) /*B case"*/
            {
                putchar(B_en);
                return 0;
            }
            else /*print "M"*/
                SimpleChar(n);
            break;
        case St_b:
            st=St_S;
            if(x==P_gr || x==p_gr) /*b case*/
            {
                putchar(b_en);
                return 0;
            }
            else
                SimpleChar(n); /*print "m"*/
            break;
        case St_D:
            st=St_S;
            if(x==T_gr || x==t_gr) /*D case*/
            {
                putchar(D_en);
                return 0;
            }
            else
                SimpleChar(n); /*print "N"*/
            break;
        case St_d:
            st=St_S;
            if(x==T_gr || x==t_gr) /*d case*/
            {
                putchar(d_en);
                return 0;
            }
            else
                SimpleChar(n); /*print "n"*/
            break;
        case St_S:
            break;
    }
    switch(x)
    {
        /*define the phase*/
        case M_gr:
            st=St_B;
            break;
        case m_gr:
            st=St_b;
            break;
        case N_gr:
            st=St_D;
            break;
        case n_gr:
            st=St_d;
            break;
        default:
            SimpleChar(x); /*simple case in which the letter is just replaced */
            break; 
    }
    return x;
}

void SimpleChar(int x)
{
    int map[73][3];

    int i,j;
    for(i=0;i<72;i++)
        for(j=0;j<3;j++)
            map[i][j]=0;
    /*replacing Dictionary*/
    map[0][0]=tonos;
    map[0][1]=65;  /*'Α*/
    map[1][0]=183; /*·*/
    map[2][0]=tonos;
    map[2][1]=69;  /*'E*/
    map[3][0]=tonos;
    map[3][1]=72;  /*'H*/
    map[4][0]=tonos;
    map[4][1]=73;  /*'I*/
    map[5][0]=187; /*»*/
    map[6][0]=tonos;
    map[6][1]=79;  /*'O*/
    map[7][0]=189; /*½*/
    map[8][0]=tonos;
    map[8][1]=89;  /*'Y*/
    map[9][0]=tonos;
    map[9][1]=87;  /*'W*/
    map[10][0]=105; /*i'"*/
    map[10][1]=tonos;
    map[10][2]=diali;
    map[11][0]=65; /*Α*/
    map[12][0]=86; /*V*/
    map[13][0]=71; /*G*/
    map[14][0]=68; /*D*/
    map[15][0]=69; /*E*/
    map[16][0]=90; /*Z*/
    map[17][0]=72; /*H*/
    map[18][0]=56; /*8*/
    map[19][0]=73; /*I*/
    map[20][0]=75; /*K*/
    map[21][0]=76; /*L*/
    map[22][0]=77; /*M*/
    map[23][0]=78; /*N*/
    map[24][0]=75; /*KS*/
    map[24][1]=83;
    map[25][0]=79; /*O*/
    map[26][0]=80; /*P*/
    map[27][0]=82; /*R*/
    map[29][0]=83; /*S*/
    map[30][0]=84; /*T*/
    map[31][0]=89; /*Y*/
    map[32][0]=70; /*F*/
    map[33][0]=88; /*X*/
    map[34][0]=80; /*PS*/
    map[34][1]=83;
    map[35][0]=87;  /*W*/
    map[36][0]=73;  /*I"*/
    map[36][0]=diali;
    map[37][0]=89;  /*Y"*/
    map[37][1]=diali;
    map[38][0]=97;  /*a'*/
    map[38][1]=tonos;
    map[39][0]=101; /*e'*/
    map[39][1]=tonos;
    map[40][0]=104; /*h'*/
    map[40][1]=tonos;
    map[41][0]=105; /*i'*/
    map[41][1]=tonos;
    map[42][0]=121; /*y'"*/
    map[42][1]=tonos;
    map[42][2]=diali;
    map[43][0]=97;  /*a*/
    map[44][0]=118; /*v*/
    map[45][0]=103; /*g*/
    map[46][0]=100; /*d*/
    map[47][0]=101; /*e*/
    map[48][0]=122; /*z*/
    map[49][0]=104; /*h*/
    map[50][0]=56;  /*8*/
    map[51][0]=105; /*i*/
    map[52][0]=107; /*k*/
    map[53][0]=108; /*l*/
    map[54][0]=109; /*m*/
    map[55][0]=110; /*n*/
    map[56][0]=107; /*ks*/
    map[56][1]=115;
    map[57][0]=111; /*o*/
    map[58][0]=112; /*p*/
    map[59][0]=114; /*r*/
    map[60][0]=115; /*s*/
    map[61][0]=115; /*s*/
    map[62][0]=116; /*t*/
    map[63][0]=121; /*y*/
    map[64][0]=102; /*f*/
    map[65][0]=120; /*x*/
    map[66][0]=112; /*ps*/
    map[66][1]=115;
    map[67][0]=119; /*w*/
    map[68][0]=105; /*i"*/
    map[68][1]=diali;
    map[69][0]=121; /*y"*/
    map[69][1]=diali;
    map[70][0]=111; /*o'*/
    map[70][1]=tonos;
    map[71][0]=121; /*y'*/
    map[71][1]=tonos;
    map[72][0]=119; /*w'*/
    map[72][1]=tonos;
    

    if(x>31 && x<182 )
        putchar(x); /*iso7 code is the same with iso1*/
    else
    {
        int n=x-182;

        for(i=0;i<3;i++)
        {
            if(map[n][i]!=0)
                putchar(map[n][i]);
        }
    }
}



