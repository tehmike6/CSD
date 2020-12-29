package model.Board;

import model.Cards.CharacterEnum;
import model.Cards.Characters;
import model.Tile.Tile;

import java.util.ArrayList;

/**
 * A player class that describes a player and stores he's data.
 * @version 1.0
 * @author Mike Bastakis csd4406
 * */
public class Player {

    private int ID; // Distinguish Players from one another.
    private int score; // Score of the player.
    private Characters[] characters; // Character cards of the player.
    private Tile[] drawnTiles; // Tiles that player draws on he's turn.
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
        this.drawnTiles = new Tile[4];
        this.collection = new ArrayList<>();

    }
    /**
     * <b>Transformer</b> Sets all the characters for the player
     * <b>Postcondition</b> characters array must have been allocated;
     * */
    private void initCharacters() {
        this.characters[0] = new Characters(CharacterEnum.DIGGER);
        this.characters[1] = new Characters(CharacterEnum.ARCHEOLOGIST);
        this.characters[2] = new Characters(CharacterEnum.ASSISTANT);
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
    /**
     * <b>Accessor</b> returns the character array of the player.
     * @return character array of player.
     * */
    public Characters[] getCharacters() {
        return characters;
    }
    /**
     * <b>Accessor</b> returns the drawn tiles of the player.
     * @return drawn tiles of player.
     * */
    public Tile[] getDrawnTiles() {
        return drawnTiles;
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
        return score;
    }
    /**
     * <b>Accessor</b>
     * Finds the character with @param name in the array and returns it.
     * @param name name of the character.
     * @return returns the character with @param name.
     * */
    public Characters findCharacter(CharacterEnum name){ return  null; }
    /**
     * <b>Transformer</b>
     * Sets the drawn tiles from the player to the board.
     * <b>Postcondition</b> Empties the array drawnTiles and set's the tiles to the right spaces of the board.
     * */
    public void setDrawnTilesToBoard() {}
    /**
     * <b>Transformer</b>
     * Sorts the collection array list based on type so that finding the score is easier.
     * <b>Postcondition</b> Reformats the collection array list by sorting it.
     * */
    public void sortCollection() {}
    /**
     * <b>Transformer</b>
     * Adds to the collection another Tile.
     * <b>Postcondition</b> Reformats the collection array list by adding a tile.
     * @param tile the tile that will be inserted.
     * */
    public void addToCollection(Tile tile) {}
}
