package model.Tile;

/**
 * This enum contains the type of the Statues.
 * @version 1.0
 * @author Mike Bastakis csd4406
 * */
public enum StatueType {
    caryatid(0),
    sphinx(1);

    private final int value;

    StatueType(int i) {
        this.value = i;
    }

    /**
     * <b>Accessor</b> Returns the type of the statue.
     * <b>Postcondtion</b> Returns a string containing the type of the statue.
     * @return A string containing the type of the statue.
     * */
    public String tile(){
        if(this == caryatid) return "CaryatidTile";
        else return "SphinxTile";
    }

    /**
     * <b>Accessor</b> Returns the enum StatueType.
     * <b>Postcondition</b> Returns a string representation of the enum StatueType.
     * @return A string representation of the enum StatueType.
     * */
    @Override
    public String toString() { return this.name(); }

    /**
     * <b>Accessor</b> Returns the value of enum.
     * @return the private variable value.
     * */
    public int getValue() {
        return value;
    }
}
