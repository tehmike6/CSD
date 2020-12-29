package model.Tile;

/**
 * A class that implements all tiles except Landslide tiles storing the type of the tile in String type.
 * @version 1.0
 * @author Mike Bastakis csd4406
 * */
public abstract class FindingTile implements Tile {
    TileType type;
    /**
     * <b>Constructor</b>
     * <b>Postcondition</b> Sets the variable type.
     * */
    FindingTile(TileType type){
        this.type = type;
    }

    /**
     * <b>Accessor</b> returns the tile's type.
     * <b>Postcondition</b> Tile's type has been returned in a string.
     * @return String containing tile's type.
     * */
    @Override
    public TileType getTileType() {
        return this.type;
    }

    /**
     * <b>Accessor</b>
     * <b>Postcondition</b> The string representation of a tile is returned.
     * @return The string representation of a tile.
     * */
    @Override
    public abstract String toString();
}
