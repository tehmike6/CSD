package model.Tile;

/**
 * A class that extends the FindingTile and represents an Statue Tile that contains type.
 * @version 1.0
 * @author Mike Bastakis csd4406
 * */
public class StatueTile extends FindingTile {
    StatueType statueType;
    /**
     * <b>Constructor</b>
     * <b>Postcondition</b> Sets the variable super.type to StatueTile and set's the statueType.
     * @param statueType type of statue
     * */
    public StatueTile(StatueType statueType) {
        super(TileType.STATUE);
        this.statueType = statueType;
    }

    /**
     * <b>Accessor</b> Get's the type of the Statue.
     * @return the type of the Statue.
     * */
    public StatueType getStatueType() {
        return statueType;
    }

    /**
     * <b>Accessor</b> Returns the Parts of a Statue Tile.
     * <b>Postcondition</b> Returns a string representation of an Statue with it's type.
     * @return A string representation of an Statue with it's type.
     * */
    @Override
    public String toString() {
        return null;
    }
}
