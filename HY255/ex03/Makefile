CC=gcc -ansi -pedantic -Wall
DEPS = symtable.h

list: symtablelist.o testsymtab.o
	$(CC) -o testsymtab $^

hash: symtablehash.o testsymtab.o
	$(CC) -o testsymtab $^

%.o: %.c $(DEPS)
	$(CC) -c -o $@ $<

clean:
	rm -rf *.o
	rm -rf *.out
	rm -rf testsymtab
	touch *

