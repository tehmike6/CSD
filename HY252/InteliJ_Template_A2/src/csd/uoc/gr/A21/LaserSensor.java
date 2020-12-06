package csd.uoc.gr.A21;

public class LaserSensor extends Sensor{
    private float range;

    public LaserSensor(String id, boolean violation, boolean on, float range) {
        super(id, violation, on);
        setRange(range);
    }

    public void setRange(float range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return ("Sensors with id="+super.getId()+", On="+super.isOn()+", Violation="+super.isViolation()+", Range="+range);
    }
}
