package model.Cards;

/**
 * An enum that stores all the different characters.
 * @version 1.0
 * @author Mike Bastakis csd4406
 * */
public enum CharacterEnum {
    ASSISTANT,
    ARCHEOLOGIST,
    DIGGER,
    PROFESSOR;

    /**
     * <b>Accessor</b> Returns the enum CharactersEnum.
     * <b>Postcondition</b> Returns a string representation of the enum CharactersEnum.
     * @return A string representation of the enum CharactersEnum.
     * */
    @Override
    public String toString() {
        return this.name();
    }
}
