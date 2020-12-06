package csd.uoc.gr.A23;

import java.util.Random;

public class Sword extends Weapon{

    private static int getRand(){
        Random rand = new Random();
        return (rand.nextInt(2)%2 + 3);
    }

    private static int isValid(int power){
        if(power==3 || power ==4){
            return power;
        }else
            return getRand();
    }

    Sword() {
        super(getRand());
    }

    public Sword(int power) {
        super(isValid(power));
    }

    @Override
    public String toString() {
        return "The sword has power "+super.getPower()+" and it is owned by "+super.getHolder();
    }
}
