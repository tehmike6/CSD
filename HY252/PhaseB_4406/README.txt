==================|
BUILD INSTRUCTIONS|
==================|

I build my application using the IDE Intellij IDEA.
It compiles the code using javac the default java compiler
and uses JDK module.All the compiled code is sent on a folder
called out but that can be specified from the Intellij app.

Run the program using Intellij: 
	Open Intellij and press File on the upper menu.
	Select the option Open and search threw your folders
	to find the PHASEB_4406 folder and press open.
	Then press the hammer icon on the right side of your
	screen to build the project and create the .iml file 
	that is required from Intellij. After that open the 
	package main find the Amphipolis.java, press right click
	and Run the main method and only method on that .java file.
	It will compile tha code and open the application(game).

Run the program using terminal:
	To successfuly build the project from the ground up using terminal
	is difficult. Assuming we have java installed on our machine
	we have to go in every file java file in the project and complie it
	to do so we use the command javac random.java . If the java file has
	packages we must use the command like this e.g. to complie the Tile.java
	javac  ./model/Tile.java
	After that a Tile.class will have been created we must then create
	a folder that is exactly the same as our project folders and place
	all the .class files in the same order as the .java .
	So basically it should look like exactly as the real project but
	instead of the .java files there must be .class files.
	To make it easy for you I have already compiled the .java files
	and put them on the folder named out. To run the code go with the 
	terminal on folder out/PHASEB_44006/main and type java main.class
	If you can not run it then most lickely you will get an error saying
	that this .class file has been compiled by a different version of your 
	compiler so maybe you must re compile every .java file with your compiler
	by using the steps listed above.

