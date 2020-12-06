package csd.uoc.gr.A23;

import java.util.Random;

public class Archer extends Soldier{

    private static int getRand(){
        Random rand = new Random();
        return (rand.nextInt(5) + 1);
    }
    private static int isValid(int health){
        if(health>=1 && health <=5){
            return health;
        }else
            return getRand();
    }

    Archer(String firstName,String lastName){
        super(firstName,lastName,getRand(),2);
    }
    Archer (String firstName, String lastName, int health){
        super(firstName,lastName,isValid(health),2);
    }
    @Override
    public void setWeapon(Weapon weapon){
        if(weapon != null && weapon instanceof Arc){
            super.weapon = weapon;
            super.weapon.setHolder(this);
        }else
            throw new RuntimeException("Weapon type is not Bow");
    }
    @Override
    public String toString(){
        return "The Archer "+ firstName+" "+lastName+" has power "+power+" and health "+getHealthCondition();
    }
}
