.data
str_in: .asciz "Enter :" 
str_nl: .asciz "\n"
str_line: .asciz "____________________________"
.align 2		# We use align because we want to store our variables in our array and make sure that we use 4 bytes for every shell 
Arr:	.space 32	# we use space 32 so that we save space for 32 bytes

.text
	
la x5, Arr		# This is our a[0]
addi x17,x0,4		# This makes the ecall print string 
la x10, str_in		# We save our string to a register so that ecall will print the string 

ecall			# Prints the string str_in = Enter :
addi x17,x0,5		# This makes the ecall to scan for a number
		
addi x7,x0,8		# i = 8 this is our counter
addi x8,x0,5		# j = 5 this is another counter for the multiplication

loop:			# We enter the loop and we store the 8 numbers and also multiply ecery number with 6 
ecall 			# We scan a number
add x28,x0,x10		# Here we store our scaned number so that we can add it to its self 5 times
mult:			# We multiply the number we scanned by adding it to its self 5 times
	add x10,x10,x28	# We add the number to it self
	addi x8,x8,-1	# This is our counter so that we use this loop 5 times j = j - 1
	blt x0,x8,mult  # if( 0 < j ) then jump to mult
addi x8,x0,5		# This resets j back to 5 for the next number
sw x10,(x5)		# This stores the number we scanned and multiplied to our a[k]
addi x5,x5, 4		# This makes k = k + 1 in our array a[ k + 4] and we 4 bytes because integers take 4 bytes of space 
addi x7,x7,-1		# This is i = i - 1 so that we end the loop
blt  x0,x7,loop		# if( 0 < i ) then jump loop

addi x7,x0,8		# resets value of i i= 8

la x10,str_line		# stores the address of the str_line on x10 
addi x17,x0,4		# makes the ecall print strings
ecall			# prints str_line
la x10,str_nl		# stores the address of str_nl on x10
ecall			# prints a new line

second_loop:		# This is a second loop that prints the integers we stored in reversed order
addi x5,x5,-4		# This makes k = k - 4 so that everytime we call this loop we get a[k-4]
lw x10, (x5)		# loads the contents of a[k] on x10
addi x17,x0,1		# This makes ecall print integers
ecall			# prints a[k]
la x10,str_nl		# loads on x10 the string str_nl
addi x17,x0,4		# makes ecall print strings
ecall			# prints a new line
addi x7,x7,-1		# i = i - 1
blt x0,x7,second_loop	# if( 0 < i ) then jump second_loop


