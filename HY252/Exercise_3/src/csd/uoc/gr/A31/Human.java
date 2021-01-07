package csd.uoc.gr.A31;

public class Human implements LivingBeing,MaterialObject{
    // For 1.1
    @Override
    public void grow() {

    }

    @Override
    public void reproduce() {

    }

    @Override
    public void respondToEnvironment() {

    }
    // For 1.2
    @Override
    public double getMass() {
        return 0;
    }
    // For 1.3 it is commented to not generate conflicts with the declaration of getMass above that is used for
    // the 1.2 exercise.

    /*
    public double getMass(){
        return LivingBeing.super.getMass();
    }
     */
}
