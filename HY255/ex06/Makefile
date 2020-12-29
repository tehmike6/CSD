all: data3 data6 data9

data3:  
	gcc -o create3 createdata3.c
	./create3
	hello < data3
data6:
	gcc -o create6 createdata6.c
	./create6
	hello < data6
data9:
	gcc -o create9 createdata9.c
	./create9
	hello < data9
clean:
	rm -f *.out
	rm -f *create3
	rm -f *create6
	rm -f *create9
	rm -f *data6
	rm -f *data9
	rm -f *data3
