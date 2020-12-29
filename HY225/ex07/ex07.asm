.data 
str1:	.asciz "Enter a number: "
str2:   .asciz "Enter a number for the search: "
str_nl: .asciz "\n"
.text 

	la x10,str1	# We load the address of str1 in register x10
	addi x17,x0,4	# We set the value of x17 to 4 so that we print string
	ecall		# We print the string
	
	jal node_alloc  # We allocate memory and we take a pointer to that memory
	
	add x8,x0,x10	# We set a pointer that points the starting address of the allocated memory at x8 so this will be the root of the linked list
	
	jal read_int	# This procedure calls read_int() and returns it.
	
	add x9,x8,x0	# We set a the next pointer our root pointer
	blt x0,x10,loop	# if( 0 < int ) then goto loop
	addi x17,x0,10	# else x17 makes ecall exit with value of 0
	ecall		# The program exits
	
loop:
	sw x10, (x9)	# We store at x10 the value of x9
	
	jal node_alloc
	
	addi x9,x9,4	# We make x9 point at the next 4 bytes of memory address 
	sw x10, (x9)	
	add x9,x10,x0
	addi x17,x0,5
	ecall
	blt x0,x10,loop	# if( 0 < int) goto loop
	
continue:
	la x10,str2	# Loads the address of hte string at x10
	addi x17,x0,4
	ecall		# We print the string
	
	jal read_int
	add x9,x10,x0	# We set the value of the int we scanned at the register x9
	add x18,x8,x0	# We set as our first pointer the first address (the root) of the list
	bge x9,x0,search# if( s1 >= 0) goto search
	jal exit

search:
	jal search_list
	
	
read_int:
	addi x17,x0,5
	ecall
	jr x1	

node_alloc:
	addi x17,x0,9	# We set the value of x17 to 9 so that we move the break point
	addi x10,x0,8	# We allocate 8 bytes of memory 
	ecall		# We allocate those bytes
	jr x1
	
print_node:
	add x10,x6,x0	#else
	addi x17,x0,1	
	ecall		# printf("%d",value);
	la x10,str_nl
	addi x17,x0,4
	ecall		# printf("\n");
	jr x1
	
search_list:
	lw x6, (x18)	# We load at x6 the value of x18 the next pointer
	bge x9,x6,cont2	# if( s1 >= value of the list) goto cont2
	jal print_node
	cont2:
	lw x18, 4(x18)		# We make x18 go to the next 4 bytes so that we go to the next pointer where the next address pf the list is stored
	bne x18,x0,search_list	# if( next pointer != 0) goto loop2
	jal exit
	
exit:
	addi x17,x0,10	# else
	ecall		# EXIT
