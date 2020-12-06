package csd.uoc.gr.A23;

import java.util.Random;

public class Swordman extends Soldier{

    private static int getRand(){
        Random rand = new Random();
        return (rand.nextInt(6)+5);
    }
    private static int isValid(int health){
        if(health>=5 && health <=10){
            return health;
        }else
            return getRand();
    }

    Swordman(String firstName,String lastName){
        super(firstName,lastName,getRand(),4);
    }
    Swordman(String firstName,String lastName,int health){
        super(firstName,lastName,isValid(health),4);
    }
    public void setWeapon(Weapon weapon){
        if(weapon != null && weapon instanceof Sword){
            super.weapon = weapon;
            super.weapon.setHolder(this);
        }else
            throw new RuntimeException("Weapon type is not Sword");
    }
    public String toString(){
        return "The swordsman "+ firstName+" "+lastName+" has power= "+power+" and health= "+getHealthCondition();
    }
}
