#include <stdio.h>

#define tonos 39
#define dialytika 34


enum FSM{any, M_cap, N_cap, m_small, n_small}state; //dhlwsh katastasewn FSM 


void gemismapinaka(char letters[256][3])
{
    int i = 0,j = 0;
	char smalls[] = "avgdezh8iklmnqoprsstufxyw";
	char head[] = "AVGDEZH8IKLMNQOPR-STUFXYW";
	
   	for(i = 0; i < 256; i++)
    {
		letters[i][0] = i;
		letters[i][1] = '\0';
		letters[i][2] = '\0';
	}
	
	/*mikra*/
	for(i = 225; i < 250; i++)
    {
		letters[i][0] = smalls[j];
		j++;
	}
    
    /*kefalaia*/
    j=0;
	for(i = 193; i <218; i++)
    {
		letters[i][0] = head[j];
		j++;
	}
	
	/*mikra me tonous*/
	letters[220][0] = 'a';
	letters[220][1] = tonos;
	letters[221][0] = 'e';
	letters[221][1] = tonos;
	letters[222][0]  = 'h';
	letters[222][1] = tonos;
	letters[223][0] = 'i';
	letters[223][1] = tonos;
	letters[252][0] = 'o';
	letters[252][1] = tonos;
	letters[253][0] = 'u';
	letters[253][1] = tonos;
	letters[254][0] = 'w';
	letters[254][1] = tonos;
	/*kefalaia me tonous*/
	letters[182][0] = tonos;
    letters[182][1] = 'A';
	letters[184][0] = tonos;
	letters[184][1] = 'E';
	letters[185][0] = tonos;
	letters[185][1] = 'H';
	letters[186][0] = tonos;
	letters[186][1] = 'I';
	letters[188][0] = tonos;
	letters[188][1] = 'O';
	letters[190][0] = tonos;
	letters[190][1] = 'Y';
	letters[191][0] = tonos;
	letters[191][1] = 'W';
	/*dialytika*/
	letters[218][0] = 'I';
	letters[218][1] = dialytika;
	letters[219][0] = 'Y';
	letters[219][1] = dialytika;
	letters[250][0] = 'i';
	letters[250][1] = dialytika;
	letters[251][0] = 'u';
	letters[251][1] = dialytika;
	letters[192][0] = 'i';
	letters[192][1] = tonos;
	letters[192][2] = dialytika;
	letters[224][0] = 'u';
	letters[224][1] = tonos;
	letters[224][2] = dialytika;

	letters[238][0] = 'k';
	letters[238][1] = 's'; //  î
	letters[248][0] = 'p';
	letters[248][1] = 's'; //  ø
	
	letters[206][0] = 'K';
	letters[206][1] = 'S'; //  Î
	letters[216][0] = 'P';
	letters[216][1] = 'S'; //  Ø
    
}

void printstate(int c)
{
    char letters[256][3];
    gemismapinaka(letters);
	
    if(letters[c][0] != '\0')	
		putchar(letters[c][0]);
		
	if(letters[c][1] != '\0')
		putchar(letters[c][1]);
		
	if(letters[c][2] != '\0')
		putchar(letters[c][2]);
}

/*synarthseis metavashs katastasewn */
enum FSM dflt(int c)
{
   switch (c)
   {
		case 204://Mgreek
			return M_cap;
			break;
		case 236://mgreek
			return m_small;
			break;
		case 205://Ngreek
			return N_cap;
			break;
		case 237://ngreek
			return n_small;
			break;
		default:
			printstate(c);
			return any;
			break;
		
   }
}
enum FSM M(int c)
{
    switch(c)
    {
        case 208://Pgreek
			printstate(66); // B caps
			return any;
			break;
		case 240:// pgreek
			printstate(66); // B caps
			return any;
			break;
		case 204://Mgreek
			printstate(204); // M greek
			return M_cap;
			break;
		case 236://mgreek
			printstate(204); // M greek
			return m_small;
			break;
		case 205://Ngreek
			printstate(204); // M greek
			return N_cap;
			break;
		case 237://ngreek
			printstate(204); // M greek
			return n_small;
			break;
		default:
			printstate(204); // M greek
			printstate(c);
			return any;
			break;
	}
}


enum FSM N(int c)
{       
        switch(c)
        {
                case 212://Tgreek
                        printstate(68); // D caps
                        return any;
                        break;
                case 244://tgreek
                        printstate(68); // D caps
                        return any;
                        break;
                case 205://Ngreek
                        printstate(205); // N greek
                        return N_cap;
                        break;
                case 237://ngreek
                        printstate(205); // N greek
                        return n_small;
                        break;
                case 204://Mgreek
                        printstate(205); // N greek
                        return M_cap;
                        break;
                case 236://mgreek
                        printstate(205); // N greek
                        return m_small;
                        break;
                default:
                        printstate(205); // N greek
                        printstate(c);
                        return any;
                        break;
        }
}


enum FSM m(int c)
{
        switch(c)
        {
                case 208://Pgreek
                        printstate(98); // b
                        return any;
                        break;
                case 240:// pgreek
                        printstate(98); // b 
                        return any;
                        break;
                case 204://Mgreek
                        printstate(236); // m greek
                        return M_cap;
                        break;
                case 236://mgreek
                        printstate(236); // m greek
                        return m_small;
                        break;
                case 205://Ngreek
                        printstate(236); // m greek
                        return N_cap;
                        break;
                case 237://ngreek
                        printstate(236); // m greek
                        return n_small;
                        break;
                default:
                        printstate(236); // m greek 
                        printstate(c);
                        return any;
                        break;
        }
}

enum FSM n(int c)
{
        switch(c)
        {
                case 212://Tgreek
                        printstate(100); // d
                        return any;
                        break;
                case 244://tgreek
                        printstate(100); // d
                        return any;
                        break;
                case 204://Mgreek
                        printstate(237); // n greek
                        return M_cap;
                        break;
                case 236://mgreek
                        printstate(237); // n greek
                        return m_small;
                        break;
                case 205://Ngreek
                        printstate(237); // n greek
                        return N_cap;
                        break;
                case 237://ngreek
                        printstate(237); // n greek
                        return n_small;
                        break;
                default:
                        printstate(237); // n greek
                        printstate(c);
                        return any;
                        break;
        }
}


enum FSM (*action[])(int c) =  {dflt,M,N,m,n};

int main()
{
    state = any;//arxikopoihsh state
    int c;
    
    while((c = getchar())!=EOF)
    {
		state = (action[state])(c);
	}
    
}
