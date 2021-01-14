package model.Tile;

/**
 * This enum contains the type of the Tiles.
 * @version 1.0
 * @author Mike Bastakis csd4406
 * */
public enum TileType {
    LANDSLIDE(4),
    MOSAIC(0),
    SKELETON(1),
    STATUE(2),
    AMPHORA(3);

    private final int value;

    TileType(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }
}
