#include <stdio.h>

#define t 39
#define d 34
#define p_c 208
#define p_s 240
#define t_c 212 
#define t_s 244
#define n_c 205
#define n_s 237
#define m_c 204
#define m_s 236
#define d_c 68
#define d_s 100
#define b_c 66
#define b_s 98

enum State_E {S,SN,Sn,SM,Sm} state;
enum State_E any(int);
enum State_E N(int);
enum State_E n(int);
enum State_E M(int);
enum State_E m(int);

enum State_E (*action[])(int) = {any, N, n, M, m};

void init_map(int map[73][3]){
	int i,j;

	for(i = 0; i<73;i++){
		for(j = 0; j<3;j++){
			map[i][j] = -1;
		}
	}


    /* 'Α */
	map[0][0]=t;
    map[0][1]=65;
    /* · */
    map[1][0]=183;
    /* 'E */
    map[2][0]=t;
    map[2][1]=69;
    /* 'H */
    map[3][0]=t;
    map[3][1]=72;
    /* 'I */
    map[4][0]=t;
    map[4][1]=73;
    /* » */
    map[5][0]=187;
    /* 'O */
    map[6][0]=t;
    map[6][1]=79;
    /* ½ */
    map[7][0]=189;
    /* 'Y */
    map[8][0]=t;
    map[8][1]=89;
    /* 'W */  
    map[9][0]=t;
    map[9][1]=87;
    /* i'" */ 
    map[10][0]=105; 
    map[10][1]=t;
    map[10][2]=d;
    /* Α */
    map[11][0]=65;
    /* V */ 
    map[12][0]=86;
    /* G */ 
    map[13][0]=71;
    /* D */ 
    map[14][0]=68;
    /* E */ 
    map[15][0]=69;
    /* Z */ 
    map[16][0]=90;
    /*H*/ 
    map[17][0]=72;
    /*8*/ 
    map[18][0]=56;
    /*I*/ 
    map[19][0]=73;
    /*K*/ 
    map[20][0]=75;
    /*L*/ 
    map[21][0]=76; 
    /*M*/
    map[22][0]=77; 
    /*N*/
    map[23][0]=78; 
    /*KS*/
    map[24][0]=75; 
    map[24][1]=83;
    /*O*/
    map[25][0]=79; 
    /*P*/
    map[26][0]=80; 
    /*R*/
    map[27][0]=82; 
    /*S*/
    map[29][0]=83; 
    /*T*/
    map[30][0]=84; 
    /*Y*/
    map[31][0]=89; 
    /*F*/
    map[32][0]=70; 
    /*X*/
    map[33][0]=88; 
    /*PS*/
    map[34][0]=80; 
    map[34][1]=83;
    /*W*/
    map[35][0]=87;  
    /*I"*/
    map[36][0]=73;  
    map[36][1]=d;
    /*Y"*/
    map[37][0]=89;  
    map[37][1]=d;
    /*a'*/
    map[38][0]=97;  
    map[38][1]=t;
    /*e'*/
    map[39][0]=101; 
    map[39][1]=t;
    /*h'*/
    map[40][0]=104; 
    map[40][1]=t;
    /*i'*/
    map[41][0]=105; 
    map[41][1]=t;
    /*y'"*/
    map[42][0]=121; 
    map[42][1]=t;
    map[42][2]=d;
    /*a*/
    map[43][0]=97;  
    /*v*/
    map[44][0]=118; 
    /*g*/
    map[45][0]=103;
    /*d*/ 
    map[46][0]=100; 
    /*e*/
    map[47][0]=101; 
    /*z*/
    map[48][0]=122; 
    /*h*/
    map[49][0]=104; 
    /*8*/
    map[50][0]=56;  
    /*i*/
    map[51][0]=105; 
    /*k*/
    map[52][0]=107; 
    /*l*/
    map[53][0]=108; 
    /*m*/
    map[54][0]=109; 
    /*n*/
    map[55][0]=110; 
    /*ks*/
    map[56][0]=107; 
    map[56][1]=115;
    /*o*/
    map[57][0]=111; 
    /*p*/
    map[58][0]=112; 
    /*r*/
    map[59][0]=114; 
    /*s*/
    map[60][0]=115; 
    /*s*/
    map[61][0]=115; 
    /*t*/
    map[62][0]=116; 
    /*y*/
    map[63][0]=121; 
    /*f*/
    map[64][0]=102; 
    /*x*/
    map[65][0]=120; 
    /*ps*/
    map[66][0]=112; 
    map[66][1]=115;
    /*w*/
    map[67][0]=119; 
    /*i"*/
    map[68][0]=105; 
    map[68][1]=d;
    /*y"*/
    map[69][0]=121; 
    map[69][1]=d;
    /*o'*/
    map[70][0]=111; 
    map[70][1]=t;
    /*y'*/
    map[71][0]=121; 
    map[71][1]=t;
    /*w'*/
    map[72][0]=119; 
    map[72][1]=t;

}

void translate(int c){
	int i = c - 182;
	int map[73][3];

	if( c < 182 ) {
		putchar(c);
		return;
	} 
	
	init_map(map);

	if(map[i][0] != -1)
		putchar(map[i][0]);
	if(map[i][1] != -1)
		putchar(map[i][1]);
	if(map[i][2] != -1)
		putchar(map[i][2]);

	return ;
}



enum State_E any(int c){

	switch(c){
		case n_c:
			return SN;
		case n_s:
			return Sn;
		case m_c:
			return SM;
		case m_s:
			return Sm;
		default:
			translate(c);
			return S;
	}
}

enum State_E N(int c){

	switch(c){
		case t_c:
			translate(d_c);
			return S;
		case t_s:
			translate(d_c);
			return S;
		default:
			translate(n_c);
			return any(c);
	}
}

enum State_E n(int c){

	switch(c){
		case t_c:
			translate(d_s);
			return S;
		case t_s:
			translate(d_s);
			return S;
		default:
			translate(n_s);
			return any(c);
	}
}

enum State_E M(int c){

	switch(c){
		case p_c:
			translate(b_c);
			return S;
		case p_s:
			translate(b_c);
			return S;
		default:
			translate(m_c);
			return any(c);
	}
}

enum State_E m(int c){

	switch(c){
		case p_c:
			translate(b_s);
			return S;
		case p_s:
			translate(b_s);
			return S;
		default:
			translate(m_s);
			return any(c);
	}
}

int main(void){
	int c = getchar();
	state = S;

	while(c != 255){
		state = (action[state])(c);
		c = getchar();
	}
}
