package csd.uoc.gr.A21;

public class SensorLine {
    private String name;
    private Sensor[] SensorArr = new Sensor[1000];
    private String Ids = "";
    private int activeCapacity = 0;
    private int violated = 0;

    public String getName() {
        return name;
    }

    public SensorLine(String name) {
        this.name = name;
    }
    public SensorLine(){
        this.name = "Anonymous sensorLine";
    }

    @Override
    public String toString() {
        return "SensorLine "+name+
                "\nNumber of sensors= "+activeCapacity+
                "\nViolated= "+ isViolated()+
                "\nViolated= "+violated+
                "\nIds of Violated:"+Ids;
    }

    public void add(Sensor s) throws Exception {
        if(activeCapacity == 999)
            throw new Exception("Sensor Line array is full");
        SensorArr[activeCapacity] = s;
        activeCapacity++;
    }
    public void setOn(boolean b){
        for(int i=0;i<activeCapacity;i++){
            SensorArr[i].setOn(b);
        }
    }
    public boolean isViolated(){
        violated = 0;
        for(int i=0;i<activeCapacity;i++){
            if(SensorArr[i].isViolation()) {
                violated++;
                Ids += " " + SensorArr[i].getId();
            }
        }
        if(violated != 0){
            return true;
        }
        return false;
    }

    public Sensor[] getSensors() {
        Sensor[] newArr = new Sensor[activeCapacity];
        for(int i=0;i<activeCapacity;i++) {
            newArr[i] = SensorArr[i];
        }
        return newArr;
    }
}
