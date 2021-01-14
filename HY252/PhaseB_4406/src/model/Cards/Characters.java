package model.Cards;
/**
 * A class that implements the special characters and keeps track is a character has been used.
 * @version 1.0
 * @author Mike Bastakis csd4406
 * */
public class Characters {
    CharacterEnum name;
    boolean used;
    /**
     * <b>Constructor</b>
     * @param name is the name of the character.
     * */
    public Characters(CharacterEnum name){
        this.name = name;
        this.used = false;
    }
    /**
     * <b>Accessor</b> Returns the name of the character.
     * @return the name of the character.
     * */
    public CharacterEnum getName() {
        return name;
    }

    /**
     * <b>Accessor</b> Returns if the character has been used.
     * @return if the character has been used.
     * */
    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
