package model.Tile;

/**
 * This enum contains the type of the Statues.
 * @version 1.0
 * @author Mike Bastakis csd4406
 * */
public enum StatueType {
    CARYATID,
    SPHINX;

    /**
     * <b>Accessor</b> Returns the type of the statue.
     * <b>Postcondtion</b> Returns a string containing the type of the statue.
     * @return A string containing the type of the statue.
     * */
    public String tile(){
        if(this == CARYATID) return "CaryatidTile";
        else return "SphinxTile";
    }

    /**
     * <b>Accessor</b> Returns the enum StatueType.
     * <b>Postcondition</b> Returns a string representation of the enum StatueType.
     * @return A string representation of the enum StatueType.
     * */
    @Override
    public String toString() { return this.name(); }
}
