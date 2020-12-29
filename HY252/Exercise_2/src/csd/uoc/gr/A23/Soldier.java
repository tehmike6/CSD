package csd.uoc.gr.A23;

import com.sun.istack.internal.NotNull;

public abstract class Soldier extends WeaponCarrier implements Warrior{
    final String firstName;
    final String lastName;
    private int healthCondition;
    final int power;

    public Soldier(@NotNull String firstName,@NotNull String lastName, int health, int power) {
//        if(firstName.compareTo("")==0 || lastName.compareTo("")==0){
//            throw new RuntimeException();
//        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.healthCondition = health;
        this.power = power;
    }
    @Override
    public void attack(Warrior warrior) throws DeadWarriorException, WarriorAttackHisselfException {
        if(this.healthCondition <= 0)
            throw new DeadWarriorException();
        if(this.getCallSign().compareTo(warrior.getCallSign()) == 0)
            throw new WarriorAttackHisselfException();
        int attack = this.getPower();
        if(this.hasWeapon()){
            attack += this.getWeapon().getPower();
        }
        int hp = warrior.getHealthCondition();
        warrior.setHealthCondition(hp-attack);
        System.out.println(this.getCallSign() + " attacked "+warrior.getCallSign());
        System.out.println(warrior.getCallSign() + " healthPoints: "+warrior.getHealthCondition() );
        System.out.println();
    }

    @Override
    public int getHealthCondition(){
        return healthCondition;
    }
    @Override
    public void setHealthCondition(int healthCondition) throws DeadWarriorException {
        if(this.isDefeated()){
            throw new DeadWarriorException("This warrior was defeated");
        }
        this.healthCondition = healthCondition;
    }
    @Override
    public boolean isDefeated(){
        return healthCondition<=0;
    }
    @Override
    public String getCallSign(){
        return firstName +" "+lastName;
    }
    @Override
    public int getPower() {
        return power;
    }
    @Override
    public String toString(){
        return "Soldier "+firstName+" "+lastName+" has health "+healthCondition+" and power "+power;
    }
}
