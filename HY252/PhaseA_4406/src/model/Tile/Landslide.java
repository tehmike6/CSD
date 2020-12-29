package model.Tile;

/**
 * A class that implements a Landslide Tile.
 * @version 1.0
 * @author Mike Bastakis csd4406
 * */
public class Landslide implements Tile {
    /**
     * <b>Accessor</b>
     * <b>Postcondition</b> Returns the string Landslide to inform the caller that
     * this tile is a Lanslide Tile.
     * @return The string Landslide.
     * */
    @Override
    public TileType getTileType() {
        return TileType.LANDSLIDE;
    }

    /**
     * <b>Accessor</b>
     * <b>Postcondition</b> The string representation of a Landslide tile is returned.
     * @return The string representation of a Landslide tile.
     * */
    @Override
    public String toString() {
        return "Landslide Tile";
    }
}
