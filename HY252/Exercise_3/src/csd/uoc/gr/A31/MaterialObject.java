package csd.uoc.gr.A31;

public interface MaterialObject {
    default public double getMass(){
        return 0;
    }
}

/**
 * 1.2
 * For the same reason as before the class Human class must implement every declared function from the interfaces.
 * */
/*
class Human implements LivingBeing,MaterialObject{

    @Override
    public void grow() {

    }

    @Override
    public void reproduce() {

    }

    @Override
    public void respondToEnvironment() {

    }

    @Override
    public double getMass() {
        return 0;
    }
}
 */