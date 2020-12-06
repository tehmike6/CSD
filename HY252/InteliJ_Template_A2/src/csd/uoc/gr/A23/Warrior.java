package csd.uoc.gr.A23;

public interface Warrior {
    // Our object warrior attacks the param warrior
    void attack(Warrior warrior) throws DeadWarriorException, WarriorAttackHisselfException;
    // Returns true if warriors health is 0
    boolean isDefeated();
    // Return the health of the warrior
    int getHealthCondition();
    // Sets the health of the warrior
    void setHealthCondition(int condition) throws DeadWarriorException;
    // Returns the power of the warrior
    int getPower();
    // Gets the warrior's name
    String getCallSign();
}
