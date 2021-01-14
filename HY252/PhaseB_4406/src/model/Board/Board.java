package model.Board;

import model.Tile.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * A board class that represents the board of the game and stores the .
 * @version 1.0
 * @author Mike Bastakis csd4406
 * */
public class Board {
    private final ArrayList<Tile>[] TileAreas;
    private final Tile[] LandslideArea;
    /**
     * <b>Constructor</b>
     * Initializes an array list that stores 4 other array lists one of each area except Landslide.
     * Initialize the Landslide area with 16 slots that are assigned with null.
     * */
    public Board(){
        TileAreas = new ArrayList[4];
        LandslideArea = new Tile[16];

        for(int i=0;i<16;i++) LandslideArea[i] = null;
        for(int i=0;i<4;i++) TileAreas[i] = new ArrayList<>();

    }
    /**
     * <b>Accessor</b>
     * Get an area with all its tiles in an array list.
     * <b>Precondition</b>
     * The TilesAreas must have been initialized and the @param area must not be null.
     * <b>PostCondtion</b>
     * The area TileArea with value = @param area.getValue() is returned
     * @param area This area's tiles will be returned.
     * @return The @param area's tiles.
     * */
    public ArrayList<Tile> getTileArea(Areas area){
        return TileAreas[area.getValue()];
    }
    /**
     * <b>Accessor</b>
     * Get the tile in the Landslide Area.
     * <b>Postcandition</b>
     * Returns the tiles in Landslide Area.
     * @return the Landslide Tiles in the Landslide Area.
     * */
    public Tile[] getLandslideArea() {
        return LandslideArea;
    }
    /**
     * <b>Accessor</b>
     * Checks if the landslide area is full by checking if their are any slots in the
     * array that are still null.
     * <b>Postcondition</b>
     * Returns if the landslide area if full.
     * @return true if landslide area is full and false if it not.
     * */
    public boolean isLandslideAreaFull(){ return Arrays.stream(LandslideArea).filter(Objects::nonNull).count() == 16; }
    /**
     * <b>Transformer</b>
     * Adds a @param tile to the corresponding area of the board.
     * <b>Postcondition</b>
     * To the array list of the corresponding area is added a new tile.
     * @param tile the tile to be added in an area.
     * */
    public void addTileToArea(Tile tile){
        switch (tile.getTileType()){
            case AMPHORA:
                TileAreas[Areas.AMPHORA_AREA.getValue()].add(tile);
                break;
            case MOSAIC:
                TileAreas[Areas.MOSAIC_AREA.getValue()].add(tile);
                break;
            case SKELETON:
                TileAreas[Areas.SKELETON_AREA.getValue()].add(tile);
                break;
            case STATUE:
                TileAreas[Areas.STATUE_AREA.getValue()].add(tile);
                break;
            case LANDSLIDE:
                int size = (int) (Arrays.stream(LandslideArea).filter(Objects::nonNull).count());
                if(size != 16) LandslideArea[size] = tile;


        }
    }
}
