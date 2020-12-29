package controller;

import model.Board.Bag;
import model.Board.Board;
import model.Board.Player;
import model.Cards.Characters;
import model.Tile.Tile;

/**
 * A class that controls the Models and View class and makes them work
 * together.
 * */
public class Controller {
    private Player p1,p2,p3,p4;
    private Board board;
    private Bag bag;
    int turn;
    int action;
    boolean characterUsed;
    /**
     * <b>Constructor</b>
     * Initializes players,board and bag.
     * */
    Controller(){
        p1 = new Player(1);
        p2 = new Player(2);
        p3 = new Player(3);
        p4 = new Player(4);
        board = new Board();
        bag = new Bag();
        initBoard();
    }
    /**
     * <b>Transformer</b>
     * Initializes the board by setting a tile of each type in the board by drawing from the bag.
     * */
    private void initBoard() { }
    /**
     * <b>Transformer</b>
     * Runs every new turn and sets the board for the player to play by drawing four tiles
     * from the bag and putting them on the corresponding areas.
     * */
    private void PlayersTurn() { }
    /**
     * <b>Transformer</b>
     * Called when a player uses a character and activates the special ability.
     * <b>Postcondition</b>
     * The Players character becomes used and characterUsed = true for this round
     * so that player can't use other characters this round.
     * */
    private void useCharacter(Characters character) { }
    /**
     * <b>Accessor</b>
     * Called when all landslide tiles have been placed and it's time to find the winner.
     * */
    private void gameIsFinished() { }
    /**
     * <b>Accessor</b>
     * Finds the score for every player.
     * <b>Postcondtion</b>
     * Changes the score attribute in players.
     * */
    private void setScores() { }
    /**
     * <b>Transformer</b>
     * Gets a tile from the board and gives it to the player that has his turn.
     * <b>Postcondtion</b>
     * The player has a new tile in his collection and the action for this turn are -1;
     * */
    private void getTiles(Tile tile) { }
    /**
     * <b>Accessor</b>
     * Checks if the player has used all his actions.
     * */
    private boolean finishedActions() { return action==2; }
    /**
     * <b>Accessor</b>
     * Checks if the player has used a character in this turn.
     * */
    private boolean hasUsedCharacter() { return characterUsed; }

}
