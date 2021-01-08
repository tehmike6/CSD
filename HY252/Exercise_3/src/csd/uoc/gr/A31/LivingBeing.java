package csd.uoc.gr.A31;

public interface LivingBeing {
    public void grow();
    public void reproduce();
    public void respondToEnvironment();
    //1.3
    default public double getMass(){
        return 0;
    }
}

/**
 * 1.1
 * For a class to implement an interface it must implement every method the interface has declared.
 * So the method grow, reproduce, respondToEnvironment must be implemented in class Human.
 **/
/*
class Human implements LivingBeing{

    @Override
    public void grow() {

    }

    @Override
    public void reproduce() {

    }

    @Override
    public void respondToEnvironment() {

    }
}
*/