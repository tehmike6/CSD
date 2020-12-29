#include <iostream>
#include <vector>
#include <sstream>
#include "class.h"
using namespace std;

int StrToInt(string number){
	int actualNum;
	stringstream changer;

	changer << number;
	changer >> actualNum;
	return actualNum;
}

bool IsPowerOfTwo(int x)
{
    return (x & (x - 1)) == 0;
}

int sizeArr(string arr[],int size){
    int i;
    for(i=0;i<size;i++){
        if(arr[i].compare("0") == 0){
            return i;
        }
    }
    return i;
}

void initArr(string arr[],int size){
	for(int i=0;i<size;i++){
		arr[i] = "0";
	}
}

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

int checkUpgrade(int cpu,int ram,int ramSlots,int ramgb,int gpu,int ab,int size){
    if(!((cpu == 1 || cpu%2==0) && cpu <= 8 && cpu != 0)){
        cout << "The cpu you entered is invalid" << endl;
        return 0;
    }
    if(!((ram <= ramSlots) && ram%2==0)){
        cout << "The number of ram sticks you entered is invalid" << endl;
        return 0;
    }
    if(!(IsPowerOfTwo(ramgb) && ramgb >=2 && ramgb <= 128)){
        cout << "The GB per ram you entered are invalid" << endl;
        return 0;
    }
    if(size > 3){
        if((gpu > 1 && size == 5)){
            cout << "The number of gpus you entered is invalid" << endl;
            return 0;
        }
        if( !(gpu >= 0 && gpu <= 2)){
            cout << "The number of gpus you entered is invalid" << endl;
            return 0;
        }
        if(size > 4){
            if(gpu == 0 && ab != 0){
                cout << "Cant have After Burn because you have no gpu" << endl;
                return 0;
            }
        }
    }
    return 1;
}

int main(){
    vector<PC*> PClist;
    string line;
    string input[7];
    HomePc homePc;
    GamingPc gamingPc;
    WorkStationPc workstationPc;

    while(true){
        
        Mb mb;
        Ram ram;
        HomePc *homePc;
        GamingPc *gamingPc;
        WorkStationPc *workstationPc;

        getline(cin,line);
        initArr(input,7);
        parString(input," ",line);

        if(input[0].compare("new") == 0){

            if(input[3].compare("0")){
                cout << "Invalid form for new pc. Try / new typePC modelName."<<endl;
                continue;
            }

            int found = 0;
            //Check if model name exists
            for(int i =0; i < PClist.size();i++){
                string model = PClist[i]->getModel();
                if(input[2].compare(model) == 0){
                    found = 1;
                }
            }
            // If it doesnt exit errors
            if(found == 1 ){
                cout << "PC with name "<<input[1] << " already exists." << endl;
                continue;
            }

            if(input[1].compare("homepc") == 0){
                mb.init(2,0);
                ram.init(1,4);
                HomePc *homePc = new HomePc;
                homePc->initialize(input[2],"MacOS",mb,2,ram,256,false,0,800);
                PClist.push_back(homePc);
            }else if(input[1].compare("gamingpc") == 0){
                mb.init(4,2);
                ram.init(2,8);
                GamingPc *gamingPc = new GamingPc;
                gamingPc->initialize(input[2],"Windows",mb,6,ram,1024,false,1,1300);
                PClist.push_back(gamingPc);
            }else if(input[1].compare("workstationpc") == 0){
                mb.init(8,1);
                ram.init(4,16);
                WorkStationPc *workstationPc = new WorkStationPc;
                workstationPc->initialize(input[2],"Linux",mb,6,ram,2048,false,0,1600);
                PClist.push_back(workstationPc);
            }   
        }else if(input[0].compare("upgrade") == 0){
            int found = 0;
            int index;
            // Checks if model name exists.
            for(int i =0; i < PClist.size();i++){
                string model = PClist[i]->getModel();
                if(input[1].compare(model) == 0){
                    found = 1;
                    index = i;
                }
            }
            // If it doesnt exit errors
            if(found == 0 ){
                cout << "Can't find PcModel with name "<<input[1] << endl;
                continue;
            }
            int cost = PClist[index]->getCost();
            int size = sizeArr(input,7);
            int ramSlts = PClist[index]->getRamSlots();
            string model;
            model = PClist[index]->getPcType();
            
            if(model.compare("homepc") == 0){
                if(size == 5){
                    if(checkUpgrade(StrToInt(input[2]),StrToInt(input[3]),ramSlts,StrToInt(input[4]),0,0,3) == 0){
                        cout << "Can't make the upgrade you want" << endl;
                        continue;
                    }
                    PClist[index]->Update(StrToInt(input[2]),StrToInt(input[3]),StrToInt(input[4]),0,0);
                }else{
                    cout << "The Pc is of type homepc the upgrade you are trying to do has wrong number of arguments" << endl;
                }

            }else if(model.compare("gamingpc") == 0){
                if(size == 6){
                    if(checkUpgrade(StrToInt(input[2]),StrToInt(input[3]),ramSlts,StrToInt(input[4]),StrToInt(input[5]),0,4) == 0){
                        cout << "Can't make the upgrade you want" << endl;
                        continue;
                    }
                    PClist[index]->Update(StrToInt(input[2]),StrToInt(input[3]),StrToInt(input[4]),StrToInt(input[5]),0);
                }else{
                    cout << "The Pc is of type gamingpc the upgrade you are trying to do has wrong number of arguments" << endl;
                }

            }else if(model.compare("workstationpc") == 0){
                if(size == 7){
                    if(checkUpgrade(StrToInt(input[2]),StrToInt(input[3]),ramSlts,StrToInt(input[4]),StrToInt(input[5]),StrToInt(input[6]),5) == 0){
                        cout << "Can't make the upgrade you want" << endl;
                        continue;
                    }
                    PClist[index]->Update(StrToInt(input[2]),StrToInt(input[3]),StrToInt(input[4]),StrToInt(input[5]),StrToInt(input[6]));
                }else{
                    cout << "The Pc is of type workstationpc the upgrade you are trying to do has wrong number of arguments" << endl;
                }

            }
        }else if(input[0].compare("delete") == 0){
            string needle = input[1];
            int found =0;
            for(int i=0;i < PClist.size();i++){
                string model = PClist[i]->getModel();
                if(needle.compare(model) == 0){
                    PClist.erase(PClist.begin() + i);
                    cout << "Succefully deleted PC with model name: "<< model << endl;
                    found = 1;
                }
            }
            if(found == 1){
                continue;
            }
            cout << "The PC with model name: " << needle << " doenst exist" << endl;
            
        }else if(input[0].compare("print") == 0){
            for(int i=0; i<PClist.size();i++){
                string type = PClist[i]->getPcType();
                if(type.compare(input[1]) == 0){
                    PClist[i]->Print();
                }
            }
        }else if(input[0].compare("compare") == 0){
            int flag=0,found=0,index[2],secFlag=0;
            for(int j=0;j<2;j++){
                // Checks if model name exists.
                for(int i =0; i < PClist.size();i++){
                    string model = PClist[i]->getModel();
                    if(input[j+1].compare(model) == 0){
                        flag = 1;
                        found = i;
                    }
                }
                // If it doesnt exit errors
                if(flag == 0 ){
                cout << "Can't find PcModel with name "<<input[j+1] << endl;
                secFlag = 1;
                continue;
                }
                index[j] = found;
                flag =0;
            }
            if(secFlag == 1){
                continue;
            }
            cout << "CPU ";
            if(PClist[index[0]]->getCpu() > PClist[index[1]]->getCpu() ){
                cout <<  PClist[index[0]]->getCpu() << " > " << PClist[index[1]]->getCpu() << endl;
            }else{
                cout <<  PClist[index[0]]->getCpu() << " < " << PClist[index[1]]->getCpu() << endl;
            }

            cout << "RAM ";
            if(PClist[index[0]]->getRam() > PClist[index[1]]->getRam() ){
                cout <<  PClist[index[0]]->getRam() << " > " << PClist[index[1]]->getRam() << endl;
            }else{
                cout <<  PClist[index[0]]->getRam() << " < " << PClist[index[1]]->getRam() << endl;
            }

            cout << "SSD ";
            if(PClist[index[0]]->getSSD() > PClist[index[1]]->getSSD() ){
                cout <<  PClist[index[0]]->getSSD() << " > " << PClist[index[1]]->getSSD() << endl;
            }else{
                cout <<  PClist[index[0]]->getSSD() << " < " << PClist[index[1]]->getSSD() << endl;
            }

            cout << "Cost ";
            if(PClist[index[0]]->getCost() > PClist[index[1]]->getCost() ){
                cout <<  PClist[index[0]]->getCost() << " > " << PClist[index[1]]->getCost() << endl;
            }else{
                cout <<  PClist[index[0]]->getCost() << " < " << PClist[index[1]]->getCost() << endl;
            }
        }

    }

}