package model.Tile;

/**
 *  Interface containing methods and data for all the tile in the game.
 * @version 1.0
 * @author Mike Bastakis csd4406
 * */
public interface Tile {
    /**
     * <b>Accessor</b> returns the tile's type.
     * <b>Postcondition</b> Tile's type has been returned in a string.
     * @return String containing tile's type.
     * */
    public TileType getTileType();

    /**
     * <b>Accessor</b>
     * <b>Postcondition</b> The string representation of a tile is returned.
     * @return The string representation of a tile.
     * */
    @Override
    public String toString();


}
