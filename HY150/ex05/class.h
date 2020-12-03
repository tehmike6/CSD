#include <iostream>
using namespace std;

typedef struct Motherboard_T{
    int ramSlots;
    int pcieSlots;

    init(int _ramSlots,int _pcieSlots){
        ramSlots = _ramSlots;
        pcieSlots = _pcieSlots;
    }
    printM(){
        cout << "RamSlots: " <<  ramSlots << " (RamSlots) ";
        cout << "PcieSlots: " << pcieSlots << " (GPU Slots)" << endl;;
    }
    getRS(){
        return ramSlots;
    }
}Mb;

typedef struct Ram_T{
    int totalRamSlots;
    int gbPerRam;

    init(int _totalRamSlots,int _gbPerRam){
        totalRamSlots = _totalRamSlots;
        gbPerRam = _gbPerRam;
    }
    PrintR(){
        cout << "TotalRamSlots: " <<  totalRamSlots << " (Ram Stick) ";
        cout << "GbPerRam: " << gbPerRam << " GB" << endl;
    }
    set_totalRamSlots(int num){
        totalRamSlots = num;
    }
    int getTotalRam(){
        return totalRamSlots * gbPerRam;
    }
    set_gbPerRam(int num){
        gbPerRam = num;
    }
}Ram;

class PC{
    public:
        //Virtual function.
        virtual void Update(int _cpu,int number_of_ram_sticks,int GB_per_ram,int number_of_GPUS,bool _ab) = 0;
        virtual void Print() = 0;

        initialize(string _model,string _OS,Mb _motherboard,int _cpu,Ram _ram,int _ssd,bool _ab,int _GPU,int _cost){
            model = _model;
            OS = _OS;
            motherboard = _motherboard;
            cpu = _cpu;
            ram = _ram;
            ssd = _ssd;
            GPU = _GPU;
            ab = _ab;
            cost = _cost;
        }
        string getPcType(){
            if(cost == 800){
                return "homepc";
            }else if(cost == 1300){
                return "gamingpc";
            }else if(cost == 1600){
                return "workstationpc";
            }
        }
        string getModel(){
            return model;
        }
        int getRamSlots(){
            return motherboard.getRS();
        }
        int getCpu(){
            return cpu;
        }
        int getRam(){
            return ram.getTotalRam();
        }
        int getSSD(){
            return ssd;
        }
        int getCost(){
            return cost;
        }

    protected:
        string model;
        string OS;
        Mb motherboard;
        int cpu;
        Ram ram;
        int ssd;
        int GPU;
        bool ab;
        int cost;
};

class HomePc:public PC {
    public:
        void Update(int _cpu,int number_of_ram_sticks,int GB_per_ram,int number_of_GPUS,bool _ab){
            cpu = _cpu;
            ram.set_gbPerRam(GB_per_ram);
            ram.set_totalRamSlots(number_of_ram_sticks);

        }
        void Print(){
            cout << "______________________" << endl;
            cout <<"Model: " << model <<endl<< "OS: " << OS <<endl<< "Cpu: " << cpu <<endl<<"SSD: " << ssd <<endl<< "Cost: " <<  cost <<endl;
            ram.PrintR();
            motherboard.printM();
            cout << endl;
            cout << "______________________" << endl;
        }

};
class GamingPc:public PC {
    public:
        void Update(int _cpu,int number_of_ram_sticks,int GB_per_ram,int number_of_GPUS,bool _ab){
            cpu = _cpu;
            ram.set_gbPerRam(GB_per_ram);
            ram.set_totalRamSlots(number_of_ram_sticks);
            GPU = number_of_GPUS;

        }
        void Print(){
            cout << "______________________" << endl;
            cout <<"Model: " << model <<endl << "OS: " << OS <<endl<< "Cpu: " << cpu <<endl<<"SSD: " << ssd <<endl<<"GPU: " << GPU <<endl<< "Cost: " <<  cost <<endl;
            ram.PrintR();
            motherboard.printM();
            cout << endl;
            cout << "______________________" << endl;
        }
        
};
class WorkStationPc:public PC {
    public:
        void Update(int _cpu,int number_of_ram_sticks,int GB_per_ram,int number_of_GPUS,bool _ab){
            cpu = _cpu;
            ram.set_gbPerRam(GB_per_ram);
            ram.set_totalRamSlots(number_of_ram_sticks);
            GPU = number_of_GPUS;
            ab = _ab;

        }
        void Print(){
            cout << "______________________" << endl;
            cout <<"Model: " << model <<endl<< "OS: " << OS <<endl<< "Cpu: " << cpu <<endl<<"SSD: " << ssd <<endl<< "AB: " << ab <<endl<<"GPU: " << GPU <<endl<< "Cost: " <<  cost <<endl;
            ram.PrintR();
            motherboard.printM();
            cout << endl;
            cout << "______________________" << endl;
        }
        
};

