package model.Board;

import model.Cards.CharacterEnum;
import model.Cards.Characters;
import model.Tile.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A player class that describes a player and stores he's data.
 * @version 1.0
 * @author Mike Bastakis csd4406
 * */
public class Player {

    private int ID; // Distinguish Players from one another.
    private int score; // Score of the player.
    private Characters[] characters; // Character cards of the player.
    private ArrayList<Tile> collection; // Collection of the player's tiles.
    /**
     * <b>Constructor</b>
     * Allocates space for all the variables of the player and initializes the characters.
     * @param ID id of player
     * */
    public Player(int ID){
        this.ID = ID;
        this.score = 0;
        this.characters = new Characters[4];
        initCharacters();
        this.collection = new ArrayList<>();

    }
    /**
     * <b>Transformer</b> Sets all the characters for the player
     * <b>Postcondition</b> characters array must have been allocated;
     * */
    private void initCharacters() {
        this.characters[2] = new Characters(CharacterEnum.DIGGER);
        this.characters[0] = new Characters(CharacterEnum.ARCHEOLOGIST);
        this.characters[1] = new Characters(CharacterEnum.ASSISTANT);
        this.characters[3] = new Characters(CharacterEnum.PROFESSOR);
    }
    /**
     * <b>Accessor</b> returns the ID of the player.
     * @return ID of player.
     * */
    public int getID() {
        return ID;
    }
    /**
     * <b>Accessor</b> returns the Score of the player.
     * @return score of player.
     * */
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * <b>Accessor</b> returns the character array of the player.
     * @return character array of player.
     * */
    public Characters[] getCharacters() {
        return characters;
    }
    /**
     * <b>Accessor</b> returns the collection of the player.
     * @return collection of player.
     * */
    public ArrayList<Tile> getCollection() {
        return collection;
    }
    /**
     * <b>Accessor and Transformer</b>
     * Traverses the collection of tiles of the player, finds the score sets it and returns it.
     * <b>Postcondition</b> Sets the score and return it.
     * @return the score of the player.
     * */
    public int findScore(){
        this.score = 0;
        ArrayList<MosaicTile> mosaicTileList = new ArrayList<>();
        ArrayList<AmphoraTile> amphoraTilesList = new ArrayList<>();
        ArrayList<SkeletonTile> skeletonTileList = new ArrayList<>();

        for (Tile tile : collection) {
            if (tile.getTileType().equals(TileType.MOSAIC)) {
                mosaicTileList.add((MosaicTile) tile);
            } else if (tile.getTileType().equals(TileType.AMPHORA)) {
                amphoraTilesList.add((AmphoraTile) tile);
            } else if (tile.getTileType().equals(TileType.SKELETON)) {
                skeletonTileList.add((SkeletonTile) tile);
            }
        }

        List<Color> mosaic = mosaicTileList.stream().map(MosaicTile::getColor).sorted(Comparator.comparingInt(Color::getValue)).collect(Collectors.toList());
        List<Color> amphora = amphoraTilesList.stream().map(AmphoraTile::getColor).sorted(Comparator.comparingInt(Color::getValue)).collect(Collectors.toList());
        List<SkeletonParts> skeleton = skeletonTileList.stream().map(SkeletonTile::getSkeletonParts).sorted(Comparator.comparingInt(SkeletonParts::getValue)).collect(Collectors.toList());

        // Find points for Mosaics
        int green_mos = Collections.frequency(mosaic,Color.green);
        int red_mos = Collections.frequency(mosaic,Color.red);
        int yellow_mos = Collections.frequency(mosaic,Color.yellow);

        if(green_mos >= 4) { score += 4; green_mos -= 4;}
        if(red_mos >= 4){ score += 4; red_mos -= 4;}
        if(yellow_mos >= 4) { score += 4; yellow_mos -= 4;}
        score += ((green_mos + red_mos + yellow_mos)/4)*2;

        // Find points for amphoras
        int[] amph = new int[6];
        amph[0] = Collections.frequency(amphora,Color.blue);
        amph[1] = Collections.frequency(amphora,Color.brown);
        amph[2] = Collections.frequency(amphora,Color.red);
        amph[3] = Collections.frequency(amphora,Color.green);
        amph[4] = Collections.frequency(amphora,Color.yellow);
        amph[5] = Collections.frequency(amphora,Color.purple);
        int count = 0;
        while(true){
            for(int i=0;i<6;i++) {
                if (amph[i] != 0){ count++; amph[i]--;}
            }
            if(count > 3) score += (count - 3) * 2;
            else if(count == 3) score += 1;
            else if(count == 0) break;
            count = 0;
        }

        // Find points for Skeletons
        int big_top = Collections.frequency(skeleton,SkeletonParts.big_top);
        int big_bottom = Collections.frequency(skeleton,SkeletonParts.big_bottom);
        int small_top = Collections.frequency(skeleton,SkeletonParts.small_top);
        int small_bottom = Collections.frequency(skeleton,SkeletonParts.small_bottom);

        int big_skeletons = Integer.max(big_top,big_bottom) - (Integer.max(big_top,big_bottom) - Integer.min(big_bottom,big_top));
        int small_skeletons = Integer.max(small_bottom,small_top) - (Integer.max(small_bottom,small_top) - Integer.min(small_bottom,small_top));

        while(true){
            if(big_skeletons >=2 && small_skeletons >= 1){
                score += 6;
                big_skeletons -=2;
                small_skeletons -=1;
            }else{
                score += big_skeletons+small_skeletons;
                break;
            }
        }
        // Statue must be found separate from the rest and will be found in controller;

        return score;
    }
    /**
     * <b>Accessor</b> Returns an int Array that in index 0 contains the number of caryatids and in index 1
     * the number of sphinxes that are found in players collection
     * @return An array that contains information about the the statues inside of players collection.
     * */
    public int[] findStatues(){

        ArrayList<StatueTile> statueTileList = new ArrayList<>();
        for(Tile tile : collection){
            if(tile.getTileType().equals(TileType.STATUE)) {
                statueTileList.add((StatueTile) tile);
            }
        }
        List<StatueType> statue = statueTileList.stream().map(StatueTile::getStatueType).sorted(Comparator.comparingInt(StatueType::getValue)).collect(Collectors.toList());

        int[] statues = new int[2];
        statues[0] = Collections.frequency(statue,StatueType.caryatid);
        statues[1] = Collections.frequency(statue,StatueType.sphinx);
        return statues;
    }
    /**
     * <b>Transformer</b>
     * Sorts the collection array list based on type so that finding the score is easier.
     * <b>Postcondition</b> Reformats the collection array list by sorting it.
     * */
    public void sortCollection() {
        collection = (ArrayList<Tile>) collection.stream().sorted(Comparator.comparingInt(x -> x.getTileType().getValue())).collect(Collectors.toList());
    }
}
