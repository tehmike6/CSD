package csd.uoc.gr.A23;

import java.util.Random;

public class Arc extends Weapon{

    private static int getRand(){
        Random rand = new Random();
        return (rand.nextInt(2)%2 + 1);
    }

    private static int isValid(int power){
        if(power==3 || power ==4){
            return power;
        }else
            return getRand();
    }
    public Arc(int power) {
        super(isValid(power));
    }

    public Arc() {
        super(getRand());
    }

    @Override
    public String toString() {
        return "The arc has power "+super.power+" and it is owned by "+super.getHolder();
    }
}
