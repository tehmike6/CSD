#include <iostream>
#include <vector>
#include <fstream>
#include <sstream>
using namespace std;

// This struct holds all the information for its specific Vehicle.
typedef struct vehicle{
	string licensePlate;
	string vehicleType;
	vector<string> damageType;
	int estFixDays;
// Initializes the array for damageType by writing in every slot of the array the string "0"
	initDamageType(string _damageType[]){
		for(int i=0;i<5;i++){
			if(_damageType[i].compare("0") == 0){
				break;
			}
			damageType.push_back(_damageType[i]);
		}
	}
// Initializes a struct Vehicle and adds values to every variable of the struct.
	init(string _licensePlate,string _vehicleType,string _damageType[],int _estFixDays){
		licensePlate = _licensePlate;
		vehicleType = _vehicleType;
		initDamageType(_damageType);
		estFixDays = _estFixDays;
	}
// Returns a string that has every element of the vector damageType.
	string PrintDamageType(int size){
		int i=0;
		string word = "";
		while(i<size){
			word += damageType[i];
			i++;
			if(i < size ) word += "-";
		}
		return word;
	}
// Prints the Vehicle and calls the fuction printDamageType to take and print a string that has the damageType of this Vehicle.
	void PrintVehicle(string line){
		ofstream out;
		out.open(line.c_str(), std::ofstream::out | std::ofstream::app);
		out << licensePlate << "," << vehicleType << ",";
		out << PrintDamageType(damageType.size()); 
		out << "," << estFixDays << endl;
		out.close();
	}
// Returns the value of the given Vehicle.
	string giveLicense(){
		return licensePlate;
	}
// Returns the EstFixDays of the given Vehicle.
	int giveEstDays(){
		return estFixDays;
	}
// Returns the type of the given Vehicle.
	string giveType(){
		return vehicleType;
	}
// Checks if the srting damage that is given as an argument exists in the vector DamageType of this Vehicle. Returns 1 if it is or 0.
	int checkDamage(string damage){
		for(int i=0;i<damageType.size();i++){
			string stack = damageType[i];
			if(damage.compare(stack) == 0){
				return 1;
			}
		}
		return 0;
	}
// Returns the ammount of the Damages in damageType of the Vehicle.
	int giveSumDamage(){
		return damageType.size();
	}
// Erases an element _damageType from the vector damageType if it exists. And returns the new size of the vector (the ammount of the Damages in damageType of the Vehicle).
	int eraseDamageType(string _damageType){
		for(int i=0;i<damageType.size();i++){
			string stack = damageType[i];
			if(_damageType.compare(stack) == 0 ){
				damageType.erase(damageType.begin() + i);
				return damageType.size();
			}
		}
	}
}Vehicle_T;

// This is a struct that stores the Date and a vector of Struct Vehicle that holds a lot of struct Vehicle items.
typedef struct DailyCustomers{
	string Date;
	vector<Vehicle_T> V;
// This adds Vehicle to the vector.
	add(string _licensePlate,string _vehicleType,string _damageType[],int _estFixDays){
		Vehicle_T temp;
		temp.init(_licensePlate,_vehicleType,_damageType,_estFixDays);
		V.push_back(temp);
		
	}
// This sets the Date for the Date veriable.
	initDay(string Day){
		Date = Day;
	}
// This prints the Date and all the vehicles that are in the vector, in the file specified by the argument line.
	printDailyCustomers(string line){
		ofstream out(line.c_str());
		out << Date << endl;
		out.close();
		for(int i=0;i<V.size();i++){
			V[i].PrintVehicle(line);
		}
	}
// Checks the licensePlate of a vehicle if it is the same to some other vehicle.
	int checkLicense(string license){
		for(int i=0;i<V.size();i++){
			string stack = V[i].giveLicense();
			if(license.compare(stack) == 0){
				return 0;
			}
		}
		return 1;
	}
// This erases a vehicle from the vector by just giving it its license plate.
	erase(string _licensePlate){
		for(int i=0;i<V.size();i++){
			string stack = V[i].giveLicense();
			if(_licensePlate.compare(stack) == 0){
				V.erase(V.begin()+i);
				break;
			}
		}
	}
// Returns the sum of the EstFixDays for every element of the vector.
	int giveSumDays(){
		int days;
		for(int i=0;i<V.size();i++){
			days += V[i].giveEstDays();
		}
		return days;
	}
// Returns the ammount of vehicles that are type of <type> ( argument ).
	int giveSumVehType(string type){
		int counter = 0;
		for(int i=0;i< V.size();i++){
			string stack = V[i].giveType();
			if(type.compare(stack) == 0){
				counter++;
			}
		}
		return counter;
	}
// This returns the sum of the vehicles that are of type <type> and have the specific <damageType> (arguments in <...>).
	int giveSumDamageType(string type,string damageType){
		int counter = 0;
		for(int i=0;i<V.size();i++){
			string veh = V[i].giveType();
			if(type.compare(veh) == 0){
				if(V[i].checkDamage(damageType)){
					counter++;
				}
			}
		}
		return counter;
	}
// This returns the sum of the damages inside the damageType vector of the <lp> vehicle.
	int damageSum(string lp){
		int sum =0;
		for(int i=0;i<V.size();i++){
			string stack = V[i].giveLicense();
			if(lp.compare(stack) == 0){
				sum = V[i].giveSumDamage();
			}
		}
		return sum;
	}
// This deletes an element of damageType vector if its the same as <damageType> from the vehicle <lp>. 
	eraseDamage(string lp,string damageType){
		for(int i=0;i<V.size();i++){
			string stack = V[i].giveLicense();
			if(lp.compare(stack) == 0 ){
				if(V[i].checkDamage(damageType) == 0){
					cout << "The vehicle does not have the specific type of damage" << endl;
				}else{ 
				int size = V[i].eraseDamageType(damageType);
				if(V[i].giveSumDamage() == 0){

				}else{
					cout << "The damage that's left is " << V[i].PrintDamageType(size) << endl;
				}
				}
			}
		}
	}	


}Customers_T;
/*This fuction takes an array of strings a delimeter and and line and seperates the string composing the line that are connected with the string delimiter
and puts them in an index of the arrat.*/
void parString(string arr[],string delimeter,string line){
	int pos=0,i=0;
	string token;
	while((pos = line.find(delimeter)) != string::npos){
		token = line.substr(0,pos);
		arr[i] = token;
		line.erase(0,pos + delimeter.length());
		i++;
	}
	arr[i] = line;
}
// Turns a string <number> to integer.
int StrToInt(string number){
	int actualNum;
	stringstream changer;

	changer << number;
	changer >> actualNum;
	return actualNum;
}
// Checks if <needle> is found in the array <haystack> that is size of <size>. 
int found(string needle,string haystack[],int size){
	for(int i=0;i<size;i++){
		if(needle.compare(haystack[i]) == 0){
			return 1;
		}
	}
	return 0;
}
// This chekcs the <arr> if it has invalid vehicleType and needs to many days to be fixed if flag = 0.Else if flag = 1 , it checks if the <arr> has valid damage type.
int checkArr(string arr[],int flag){
	string vehicleType[4] = {"Motorcycle","Car","Van","Semitruck"};
	string damageType[5] = {"Engine","Brakes","Transmission","Gearbox","Electrical"};
	if(flag == 1){
		int j=0;
		while(arr[j].compare("0")){
			if(!found(arr[j],damageType,5)){
				return 0;
			}
			j++;
			if(j==5){
				break;
			}
		}
		return 1;
	}

	int num = StrToInt(arr[3]);

	if(!found(arr[1],vehicleType,4)){	//Checks if Vehicle type is valid.
		cout << "Vehicle with license plate " << arr[0] << " has invalid vehicle type of : " << arr[1] << endl;
		return 0; 
	}
	if(num < 0 || num > 12){
		cout << "Vehicle with license plate " << arr[0] << " needs many days.It can't be fixed" << endl;
		return 0;
	}

	return 1;
}
// Initializes given array <arr> that is size of <size> by placing the string "0".
void initArr(string arr[],int size){
	for(int i=0;i<size;i++){
		arr[i] = "0";
	}
}
// Checks if the string <Days> is integer or float.
int checkDays(string Days){
	if( Days.find(".") != string::npos){
		return 1;
	}
	return 0;
}

int main(int argc,char **argv){
	Customers_T data;
	string arr_Vehicle[4];
	string arr_DamageType[5];


	//			INPUT HANDLER
	if(argc == 1){	
		cout << "Please enter a file from were data will be inputed" << endl;
		return 0;
	}
	ifstream ist(argv[1]);
	if(!ist){
		cout << "Can't open file named: " << argv[1] << endl;
		return 0;
	}
	string line;

	getline(ist,line);	// Sets the date on our data structure.
	data.initDay(line);

	while(getline(ist,line)){
		parString(arr_Vehicle,",",line);
		if(!checkArr(arr_Vehicle,0)){
			continue;
		}
		initArr(arr_DamageType,5);
		parString(arr_DamageType,"-",arr_Vehicle[2]);
		if(!checkArr(arr_DamageType,1)){
			cout << "Vehicle with license plate " << arr_Vehicle[0] << " has invalid damageType" << endl;
			continue;
		}
		data.add(arr_Vehicle[0],arr_Vehicle[1],arr_DamageType,StrToInt(arr_Vehicle[3]));
	}

	//		Actual Program
	while(true){
		string opcode[2];
		getline(cin,line);
		parString(opcode," ",line);
		initArr(arr_DamageType,5);
		initArr(arr_Vehicle,4);

		if(opcode[0].compare("a") == 0){		// If input is a ... (Adds a vehicle if correct format)
			parString(arr_Vehicle,",",opcode[1]);
			if(!checkArr(arr_Vehicle,0)){
				continue;
			}
			initArr(arr_DamageType,5);
			parString(arr_DamageType,"-",arr_Vehicle[2]);
			if(!checkArr(arr_DamageType,1)){
				cout << "Vehicle with license plate " << arr_Vehicle[0] << " has invalid damageType" << endl;
				continue;
			}
			if(data.checkLicense(arr_Vehicle[0])){
				if(checkDays(arr_Vehicle[3])){
					cout << "Invlalid days please enter an integer number for days" << endl;
					continue;
				}
				data.add(arr_Vehicle[0],arr_Vehicle[1],arr_DamageType,StrToInt(arr_Vehicle[3]));
			}else{
				cout << "Invalid licensePlate given please enter a new license plate" << endl;
			}
		}else if(opcode[0].compare("p") == 0){	// If input is p (Prints the data base in file)
			data.printDailyCustomers(argv[1]);
		}else if(opcode[0].compare("d") == 0){	// If input is d (Removes the specific vehicle if found)
			if(data.checkLicense(opcode[1]) == 0){
				data.erase(opcode[1]);
				cout << "Vehicle with license plate " << opcode[1] << " has been succefully been removed" << endl;
			}else{
				cout << "License Plate not found please enter an existing license plate" << endl;
			}
		}else if(opcode[0].compare("pd") == 0){	// If input is pd (Prints the days that are estimated for the vehicles to be fixed)
			int days = data.giveSumDays();
			cout << "The days that are estimated until all vehicles are completed are: " << days << endl;
		}else if(opcode[0].compare("pv") == 0){	// If input is pv (Prints the sum of a specific type of vehicle that need to be fixed)
			int num = data.giveSumVehType(opcode[1]);
			cout << "There are " << num << " " << opcode[1] << endl;
		}else if(opcode[0].compare("pdt") == 0){	// If input is pdt (Prints how many vehicles of every type has a specific damageType)
			cout << data.giveSumDamageType("Car",opcode[1]) << " Cars " << data.giveSumDamageType("Motorcycle",opcode[1]) << " Motorcycles ";
			cout << data.giveSumDamageType("Van",opcode[1]) << " Vans " << data.giveSumDamageType("Semitruck",opcode[1]) << " Semitrucks " << endl;
		}else if(opcode[0].compare("q") == 0){		// IF input is q (Prints the data base in a file and ends the program)
			data.printDailyCustomers(argv[1]);
			return 0;
		}else if(opcode[0].compare("rd") == 0 ){	// If input is rd (Removes a specif damageType for a vehicle and if all of the damageTypes are deleted for a vehicle it erases the vehicle).
			int sum = data.damageSum(opcode[1]);
			if(sum == 0 ){
				cout << "The vehicle with license plate " << opcode[1] << " is fixed" << endl;
			}else{
				data.eraseDamage(opcode[1],opcode[2]);
				sum = data.damageSum(opcode[1]);
				if(sum == 0 ){
					cout << "The vehicle with license plate " << opcode[1] << " is fixed" << endl;
					data.erase(opcode[1]);
				}
			}
		}
	}
}