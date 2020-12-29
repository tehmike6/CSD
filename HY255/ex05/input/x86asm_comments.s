# -----------------------------------
# Program x86asm.s
# Compute and print the sum 0+...+N
# -----------------------------------

	.section .data	#Initializes the data memory
N:	.long 1000	    #Sets N as a long of value 1000 and stores it in memory
S:	.long 0		    #Sets S as a long of value 0  and stores it in memory
Msg:	.ascii "The sum from 0 to %d is %d\n\0"#Sets Msg to a string and stores it in memory


	.section .text	# program section/memory
.globl main
main:	
	pushl %ebp	    # Makes a copy of the stack frame of main and puts it to stack and increases %esp
	movl %esp, %ebp	# Moves %esp stack pointer to %ebp 

 	# initialize
    movl N, %ebx	# Copies address of lond 1000 to %ebx (general reg)

 	# compute sum
L1:
	addl %ebx, S	# S = S + %ebx(value)
	decl %ebx       # %ebx = %ebx - 1 decrements %ebx value by one
	cmpl $0, %ebx   # Compares %ebx(value) to zero and stores it to a register
	jng  L2	    	# If ebx <= 0 jumps to L2 based on the data stores from the register above.
    movl $L1, %eax	# Sets the label's address to %eax  
    jmp *%eax   	# Jumps to address pointed by %eax

L2:
	# print result
	pushl S	    	# Copies the value of S to the stack and increases the stack pointer esp
	pushl N	        # Copies the value of N to the stack and increases the stack pointer esp
	pushl $Msg  	# Copies Msg string to the stack and increases the stack pointer esp
	call printf 	# Calls the printf method to print the Msg N and S
	popl %eax   	# pops Msg out of the stack and decreases esp
	popl %eax   	# pops N out of the stack and decreases esp
	popl %eax   	# pops S out of the stack and decreases esp

	# exit
	movl $0, %eax  	# Sets register %eax to zero
    leave	    	# exit()
 	ret             # Jumpas to the address pointed by stackpointer esp
