# -----------------------------------
# Program x86asm.s
# Compute and print the sum 0+...+N
# -----------------------------------

	.section .data	# 
N:	.long 1000	    # 
S:	.long 0		    # 
Msg:	.ascii "The sum from 0 to %d is %d\n\0"


	.section .text	# 
.globl main
main:	
	pushl %ebp	    # 
	movl %esp, %ebp	# 

 	# initialize
    movl N, %ebx	# 

 	# compute sum
L1:
	addl %ebx, S	# 
	decl %ebx       # 
	cmpl $0, %ebx   # 
	jng  L2	    	# 
    movl $L1, %eax	# 
    jmp *%eax   	# 

L2:
	# print result
	pushl S	    	# 
	pushl N	        # 
	pushl $Msg  	# 
	call printf 	# 
	popl %eax   	# 
	popl %eax   	# 
	popl %eax   	# 

	# exit
	movl $0, %eax  	# 
    leave	    	#
 	ret             #

