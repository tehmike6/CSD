.data
# Letters between a-z	Character	Hexadecimal Value	Binary Value
.align 2
	.asciz "a"	#a			61		01100001									
.align 2 
	.asciz "b"	#b			62		01100010
.align 2
	.asciz "c"	#c			63		01100011
.align 2 
	.asciz "d" 	#d 			64		01100100
.align 2
	.asciz "e"	#e			65		01100101
.align 2 
	.asciz "f"	#f			66		01100110
.align 2
	.asciz "g"	#g			67		01100111
.align 2 
	.asciz "h"	#h			68		01101000
.align 2
	.asciz "i"	#i 			69		01101001
.align 2 
	.asciz "j"	#j			6a		01101010
.align 2
	.asciz "k"	#k			6b		01101011
.align 2 
	.asciz "l"	#l			6c		01101100
.align 2
	.asciz "m"	#m			6d		01101101
.align 2 
	.asciz "n"	#n			6e		01101110
.align 2
	.asciz "o"	#o			6f		01101111
.align 2 
	.asciz "p"	#p			70		01110000
.align 2
	.asciz "q"	#q			71		01110001
.align 2 
	.asciz "r"	#r			72		01110010
.align 2	
	.asciz "s"	#s			73		01110011
.align 2 	
	.asciz "t"	#t			74		01110100
.align 2
	.asciz "u"	#u			75		01110101
.align 2 
	.asciz "v"	#v			76		01110110
.align 2
	.asciz "w"	#w			77		01110111
.align 2 
	.asciz "x"	#x			78		01111000
.align 2
	.asciz "y"	#y			79		01111001
.align 2 
	.asciz "z"	#z			7a		01111010
# By Observing we understand that the values of a-z are in order and increasing by 1
	
# Letters Between A-Z
.align 2
	.asciz "A"	#A			41		01000001
.align 2 
	.asciz "B"	#B			42		01000010
.align 2
	.asciz "C"	#C			43		01000011
.align 2 
	.asciz "D"	#D			44		01000100
.align 2
	.asciz "E"	#E			45		01000101
.align 2 
	.asciz "F"	#F			46		01000110
.align 2
	.asciz "G"	#G			47		01000111
.align 2 
	.asciz "H"	#H			48		01001000
.align 2
	.asciz "I"	#I			49		01001001
.align 2 
	.asciz "J"	#J			4a		01001010
.align 2
	.asciz "K"	#K			4b		01001011
.align 2 
	.asciz "L"	#L			4c		01001100
.align 2
	.asciz "M"	#M			4d		01001101
.align 2 
	.asciz "N"	#N			4e		01001110
.align 2
	.asciz "O"	#O			4f		01001111
.align 2 
	.asciz "P"	#P			50		01010000
.align 2
	.asciz "Q"	#Q			51		01010001
.align 2 
	.asciz "R"	#R			52		01010010
.align 2 
	.asciz "S"	#S			53		01010011
.align 2
	.asciz "T"	#T			54		01010100
.align 2
	.asciz "U"	#U			55		01010101
.align 2 
	.asciz "V"	#V			56		01010110
.align 2
	.asciz "W"	#W			57		01010111
.align 2 
	.asciz "X"	#X			58		01011000
.align 2
	.asciz "Y"	#Y			59		01011001
.align 2 
	.asciz "Z"	#Z			5a		01011010
# By Observing we understand that the values of A-Z are in order and increasing by 1

# Numbers between 0-9
.align 2
	.asciz "0"	#0			30		00110000
.align 2 
	.asciz "1"	#1			31		00110001
.align 2
	.asciz "2"	#2			32		00110010
.align 2 
	.asciz "3"	#3			33		00110011
.align 2
	.asciz "4"	#4			34		00110100
.align 2 
	.asciz "5"	#5			35		00110101
.align 2
	.asciz "6"	#6			36		00110110
.align 2 
	.asciz "7"	#7			37		00110111
.align 2
	.asciz "8"	#8			38		00111000
.align 2 
	.asciz "9"	#9			39		00111001
# By Observing we understand that the values of 0-9 are in order and increasing by 1

.align 2
word:	.ascii "xyz"

# The integer of "xyz" in hexadecimal value is 0x007a7978 = 8026488 in decimal value.
# The binary number is 11110100111100101111000
# A little Endian Machine would store this char as
# 	00000000	01111010	01111001	01111000
#	  \0		   z 		  y		  x
#	byte i+3	byte i+2      	 byte i+1      	 byte i
#
# A Big Endian Machine Would store this char as
#	01111000	01111001	01111010	00000000
#	  x		    y		   z		   \0
#	byte i		byte i+1 	byte i+2	byte i+3
# The little Endian when translated to integer decimal is 8026488 HEX : 0x007a7978
# The Big Endian when translated to integer decimal is 2021227008 HEX : 78797A00
.text
addi x17,x0,1
lw  x10,word
ecall









