package csd.uoc.gr.A21;

public class Sensor {
    private String id;
    private boolean violation;
    private  boolean on;

    public Sensor(String id,boolean violation,boolean on){
        setId(id);
        setViolation(violation);
        setOn(on);
    }

    public void setId(String id){
        this.id = id;
    }

    public void setViolation(boolean violation){
        this.violation = violation;
    }

    public  void setOn(boolean on){
        this.on = on;
    }
    @Override
    public String toString(){
        return ("Sensors with id="+id+", On="+on+", Violation="+violation);
    }

    public String getId() {
        return id;
    }

    public boolean isViolation() {
        return violation;
    }

    public boolean isOn() {
        return on;
    }
}
