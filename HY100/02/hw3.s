	.file	"hw3.c"
	.section	.rodata
.LC0:
	.string	"No arguments found"
.LC1:
	.string	"-calculate"
.LC2:
	.string	"No array size provided"
.LC3:
	.string	"-create"
.LC4:
	.string	"%d "
.LC5:
	.string	"Not valid array size"
.LC6:
	.string	"%d"
.LC7:
	.string	"%d\n"
	.text
	.globl	main
	.type	main, @function
main:
.LFB2:
	.cfi_startproc
	leal	4(%esp), %ecx
	.cfi_def_cfa 1, 0
	andl	$-16, %esp
	pushl	-4(%ecx)
	pushl	%ebp
	.cfi_escape 0x10,0x5,0x2,0x75,0
	movl	%esp, %ebp
	pushl	%ebx
	pushl	%ecx
	.cfi_escape 0xf,0x3,0x75,0x78,0x6
	.cfi_escape 0x10,0x3,0x2,0x75,0x7c
	subl	$832, %esp
	movl	%ecx, %ebx
	movl	$1, -12(%ebp)
	movl	$0, -16(%ebp)
	movl	$0, -20(%ebp)
	subl	$12, %esp
	pushl	$0
	call	time
	addl	$16, %esp
	subl	$12, %esp
	pushl	%eax
	call	srand
	addl	$16, %esp
	cmpl	$1, (%ebx)
	jne	.L2
	subl	$12, %esp
	pushl	$.LC0
	call	puts
	addl	$16, %esp
	movl	$0, %eax
	jmp	.L1
.L2:
	cmpl	$2, (%ebx)
	jne	.L4
	movl	4(%ebx), %eax
	addl	$4, %eax
	movl	(%eax), %eax
	subl	$8, %esp
	pushl	$.LC1
	pushl	%eax
	call	strcmp
	addl	$16, %esp
	testl	%eax, %eax
	je	.L4
	subl	$12, %esp
	pushl	$.LC2
	call	puts
	addl	$16, %esp
	movl	$0, %eax
	jmp	.L1
.L4:
	movl	4(%ebx), %eax
	addl	$4, %eax
	movl	(%eax), %eax
	subl	$8, %esp
	pushl	$.LC3
	pushl	%eax
	call	strcmp
	addl	$16, %esp
	testl	%eax, %eax
	jne	.L5
	movl	4(%ebx), %eax
	addl	$8, %eax
	movl	(%eax), %eax
	subl	$12, %esp
	pushl	%eax
	call	atoi
	addl	$16, %esp
	cmpl	$1, %eax
	jle	.L6
	movl	4(%ebx), %eax
	addl	$8, %eax
	movl	(%eax), %eax
	subl	$12, %esp
	pushl	%eax
	call	atoi
	addl	$16, %esp
	cmpl	$10, %eax
	jg	.L6
	movl	$0, -16(%ebp)
	jmp	.L7
.L12:
	movl	$0, -20(%ebp)
	jmp	.L8
.L11:
	movl	-16(%ebp), %eax
	cmpl	-20(%ebp), %eax
	jg	.L9
	call	rand
	movl	%eax, %ecx
	movl	$-1840700269, %edx
	movl	%ecx, %eax
	imull	%edx
	leal	(%edx,%ecx), %eax
	sarl	$2, %eax
	movl	%eax, %edx
	movl	%ecx, %eax
	sarl	$31, %eax
	subl	%eax, %edx
	movl	%edx, %eax
	movl	%eax, %edx
	sall	$3, %edx
	subl	%eax, %edx
	movl	%ecx, %eax
	subl	%edx, %eax
	leal	1(%eax), %ecx
	movl	-16(%ebp), %edx
	movl	%edx, %eax
	sall	$2, %eax
	addl	%edx, %eax
	addl	%eax, %eax
	movl	-20(%ebp), %edx
	addl	%edx, %eax
	movl	%ecx, -428(%ebp,%eax,4)
	jmp	.L10
.L9:
	movl	-16(%ebp), %edx
	movl	%edx, %eax
	sall	$2, %eax
	addl	%edx, %eax
	addl	%eax, %eax
	movl	-20(%ebp), %edx
	addl	%edx, %eax
	movl	$0, -428(%ebp,%eax,4)
.L10:
	addl	$1, -20(%ebp)
.L8:
	movl	4(%ebx), %eax
	addl	$8, %eax
	movl	(%eax), %eax
	subl	$12, %esp
	pushl	%eax
	call	atoi
	addl	$16, %esp
	cmpl	-20(%ebp), %eax
	jg	.L11
	addl	$1, -16(%ebp)
.L7:
	movl	4(%ebx), %eax
	addl	$8, %eax
	movl	(%eax), %eax
	subl	$12, %esp
	pushl	%eax
	call	atoi
	addl	$16, %esp
	cmpl	-16(%ebp), %eax
	jg	.L12
	movl	4(%ebx), %eax
	addl	$8, %eax
	movl	(%eax), %eax
	subl	$12, %esp
	pushl	%eax
	call	puts
	addl	$16, %esp
	movl	$0, -16(%ebp)
	jmp	.L13
.L16:
	movl	$0, -20(%ebp)
	jmp	.L14
.L15:
	movl	-16(%ebp), %edx
	movl	%edx, %eax
	sall	$2, %eax
	addl	%edx, %eax
	addl	%eax, %eax
	movl	-20(%ebp), %edx
	addl	%edx, %eax
	movl	-428(%ebp,%eax,4), %eax
	subl	$8, %esp
	pushl	%eax
	pushl	$.LC4
	call	printf
	addl	$16, %esp
	addl	$1, -20(%ebp)
.L14:
	movl	4(%ebx), %eax
	addl	$8, %eax
	movl	(%eax), %eax
	subl	$12, %esp
	pushl	%eax
	call	atoi
	addl	$16, %esp
	cmpl	-20(%ebp), %eax
	jg	.L15
	subl	$12, %esp
	pushl	$10
	call	putchar
	addl	$16, %esp
	addl	$1, -16(%ebp)
.L13:
	movl	4(%ebx), %eax
	addl	$8, %eax
	movl	(%eax), %eax
	subl	$12, %esp
	pushl	%eax
	call	atoi
	addl	$16, %esp
	cmpl	-16(%ebp), %eax
	jg	.L16
	nop
	jmp	.L19
.L6:
	movl	4(%ebx), %eax
	addl	$8, %eax
	movl	(%eax), %eax
	subl	$12, %esp
	pushl	%eax
	call	atoi
	addl	$16, %esp
	cmpl	$1, %eax
	jle	.L18
	movl	4(%ebx), %eax
	addl	$8, %eax
	movl	(%eax), %eax
	subl	$12, %esp
	pushl	%eax
	call	atoi
	addl	$16, %esp
	cmpl	$10, %eax
	jle	.L19
.L18:
	subl	$12, %esp
	pushl	$.LC5
	call	puts
	addl	$16, %esp
	jmp	.L19
.L5:
	movl	4(%ebx), %eax
	addl	$4, %eax
	movl	(%eax), %eax
	subl	$8, %esp
	pushl	$.LC1
	pushl	%eax
	call	strcmp
	addl	$16, %esp
	testl	%eax, %eax
	jne	.L19
	subl	$8, %esp
	leal	-24(%ebp), %eax
	pushl	%eax
	pushl	$.LC6
	call	__isoc99_scanf
	addl	$16, %esp
	movl	$0, -16(%ebp)
	jmp	.L20
.L23:
	movl	$0, -20(%ebp)
	jmp	.L21
.L22:
	subl	$8, %esp
	leal	-28(%ebp), %eax
	pushl	%eax
	pushl	$.LC6
	call	__isoc99_scanf
	addl	$16, %esp
	movl	-28(%ebp), %ecx
	movl	-16(%ebp), %edx
	movl	%edx, %eax
	sall	$2, %eax
	addl	%edx, %eax
	addl	%eax, %eax
	movl	-20(%ebp), %edx
	addl	%edx, %eax
	movl	%ecx, -828(%ebp,%eax,4)
	addl	$1, -20(%ebp)
.L21:
	movl	-24(%ebp), %eax
	cmpl	%eax, -20(%ebp)
	jl	.L22
	addl	$1, -16(%ebp)
.L20:
	movl	-24(%ebp), %eax
	cmpl	%eax, -16(%ebp)
	jl	.L23
	movl	$0, -16(%ebp)
	jmp	.L24
.L28:
	movl	$0, -20(%ebp)
	nop
	movl	-16(%ebp), %eax
	movl	%eax, -20(%ebp)
	cmpl	$0, -20(%ebp)
	je	.L26
	movl	-16(%ebp), %edx
	movl	%edx, %eax
	sall	$2, %eax
	addl	%edx, %eax
	addl	%eax, %eax
	movl	-20(%ebp), %edx
	addl	%edx, %eax
	movl	-828(%ebp,%eax,4), %eax
	movl	-12(%ebp), %edx
	imull	%edx, %eax
	movl	%eax, -12(%ebp)
	nop
.L26:
	addl	$1, -16(%ebp)
.L24:
	movl	-24(%ebp), %eax
	cmpl	%eax, -16(%ebp)
	jl	.L28
	movl	-828(%ebp), %eax
	imull	-12(%ebp), %eax
	subl	$8, %esp
	pushl	%eax
	pushl	$.LC7
	call	printf
	addl	$16, %esp
.L19:
.L1:
	leal	-8(%ebp), %esp
	popl	%ecx
	.cfi_restore 1
	.cfi_def_cfa 1, 0
	popl	%ebx
	.cfi_restore 3
	popl	%ebp
	.cfi_restore 5
	leal	-4(%ecx), %esp
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE2:
	.size	main, .-main
	.ident	"GCC: (Debian 4.9.2-10+deb8u2) 4.9.2"
	.section	.note.GNU-stack,"",@progbits
