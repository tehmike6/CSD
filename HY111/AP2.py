import sympy as sym
from sympy import Pow
from sympy.parsing.sympy_parser import parse_expr

max_iteration = input("Enter number of iterations: ")
a = input("Enter convergence factor: ")
x0 = input("Enter initial value: ")

input_f1 = input("F1: ")
input_f2 = input("F2: ")
x = sym.Symbol('x')

if input_f2 != '0':
	temp = parse_expr(input_f1 + '-(' + input_f2+')', evaluate= False)
	L = Pow(temp,2)
else:
	L = parse_expr(input_f1)

L_Der = sym.diff(L)
current = x0

for i in range(int(max_iteration)):
	next_value = float(current) - float(a)*L_Der.subs(x,current)
	current = float(next_value)
	##print(current)
print(current)