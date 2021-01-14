package model.Tile;

/**
 * A class that extends the FindingTile and represents an Mosaic Tile that contains it's color.
 * @version 1.0
 * @author Mike Bastakis csd4406
 * */
public class MosaicTile extends FindingTile {
    Color color;
    /**
     * <b>Constructor</b>
     * <b>Postcondition</b> Sets the variable super.type to MosaicTile and set's the color.
     * @param color
     * */
    public MosaicTile(Color color) {
        super(TileType.MOSAIC);
        this.color = color;
    }
    /**
     * <b>Accessor</b> Get's the color of the Mosaic.
     * @return the color of the Mosaic.
     * */
    public Color getColor() {
        return color;
    }

    /**
     * <b>Accessor</b> Returns the color of an Mosaic Tile.
     * <b>Postcondition</b> Returns a string representation of an Mosaic with it's color.
     * @return A string representation of an Mosaic with it's color.
     * */
    @Override
    public String toString() {
        return "mosaic_"+color.toString();
    }
}
