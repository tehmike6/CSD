package model.Tile;

/**
 * A class that extends the FindingTile and represents an Amphora Tile that contains it's color.
 * @version 1.0
 * @author Mike Bastakis csd4406
 * */
public class AmphoraTile extends FindingTile{
    Color color;
    /**
     * <b>Constructor</b>
     * <b>Postcondition</b> Sets the variable super.type to Amphora and set's the color.
     * @param color
     * */
    public AmphoraTile(Color color){
        super(TileType.AMPHORA);
        this.color = color;
    }
    /**
     * <b>Accessor</b> Get's the color of the Amphora.
     * @return the color of the Amphora
     * */
    public Color getColor() {
        return color;
    }

    /**
     * <b>Accessor</b> Returns the color of an Amphora Tile.
     * <b>Postcondition</b> Returns a string representation of an Amphora with it's color.
     * @return A string representation of an Amphora with it's color.
     * */
    @Override
    public String toString() {
        return color.toString() + " Amphora Tile";
    }
}
