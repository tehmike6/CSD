package model.Board;

import model.Tile.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * An bag that stores all the tiles.
 * @version 1.0
 * @author Mike Bastakis csd4406
 * */
public class Bag {
    private final ArrayList<Tile> bag;

    /**
     * <b>Constructor</b>
     * Allocates space to the arraylist, inserts the right ammount of tiles and suffles them.
     * */
    public Bag() {
        this.bag = new ArrayList<>();
        bagInit();
    }
    /**
     * <b>Accessor</b> Returns the bag.
     * <b>Postcondition</b> Returns the array list with the bag.
     * @return ArrayList the bag.
     */
    public ArrayList<Tile> getBag() {
        return bag;
    }

    /**
     * <b>Transformer</b> Inserts all the tiles with their types in the bag.
     * <b>Postcondition</b>Initializes bag creating all the tiles to be used.
     * */
    private void bagInit() {
        Color color;
        SkeletonParts parts;
        for(int i=0;i<12;i++){
            this.bag.add(new StatueTile(StatueType.CARYATID));
            this.bag.add(new StatueTile(StatueType.SPHINX));
        }

        for(int i=0;i<24;i++){
            this.bag.add(new Landslide());
        }

        for(int i=0;i<27;i++){
            if(i<8) color = Color.GREEN;
            else if(i<17) color = Color.RED;
            else color = Color.YELLOW;

            this.bag.add(new MosaicTile(color));
        }

        for(int i=0;i<30;i++){
            if(i<9) parts = SkeletonParts.UPPER_LARGE;
            else if(i<19) parts = SkeletonParts.LEGS_LARGE;
            else if(i<24) parts = SkeletonParts.UPPER_SMALL;
            else parts = SkeletonParts.LEGS_SMALL;

            this.bag.add(new SkeletonTile(parts));
        }

        for(int i=0;i<30;i++){
            if(i<4) color = Color.GREEN;
            else if(i<9) color = Color.RED;
            else if(i<14) color = Color.YELLOW;
            else if(i<19) color = Color.BLUE;
            else if(i<24) color = Color.BROWN;
            else color = Color.PINK;

            this.bag.add(new AmphoraTile(color));
        }
        bagSuffle();
    }

    /**
     * <b>Transformer</b> Suffles the tiles inside the list.So players can draw random tiles.
     * */
    private void bagSuffle() {  Collections.shuffle(bag); }
    /**
     * <b>Accessor</b> Returns the first tile in the bag and removes  it from the bag.
     * <b>Precondition</b> The bag must not be empty.
     * @return the first tile from the bag.
     * */
    public Tile getTile(){ return bag.remove(0); }
    /**
     * <b>Accessor</b> finds if the bag is empty
     * @return true if the bag is empty, or false if it isn't.
     * */
    public boolean isEmpty(){ return bag.isEmpty(); }
    /**
     * <b>Accessor</b> Returns the number of tiles that are still in the bag.
     * @return the number of tiles that are still in the bag.
     * */
    public int tilesLeft(){ return bag.size(); }
}
