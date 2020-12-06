package model.turn;

import java.util.ArrayList;
import model.player.Player;

/**
 * This class represents the turn of the game
 * @version 1.0
 * @author dxanthak - mountanton
 */
public class Turn 
{
    private int currentID,round_players;
    private int num;
    private int last_player;
    
    /**  Constructor.
     * 
     * <b>Postcondition</b>Creates a new instance of Turn with currentID=0, 
     *      num=4, round_players=4, last_player=0.
     */
    public Turn()
    {
        currentID=0;
        num=4;
        this.round_players=4;
        this.last_player=0;
    }
   
    /**
     * <b>Transformer(Mutative):</b> Sets the player's turn.(which player has the turn to play)
     * <b>Postcondition:</b> Player's turn has been set.
     * @param players
     */
    public void setID(ArrayList <Player> players)
    {
       
    }
    
    
    /**
     * <b>Accessor(Selector):</b> returns the player's ID whose turn is to play
     * <b>Postcondition:</b> returns the player's ID whose turn is to play
     * @return the player's ID whose turn is to play
     */
    public int  getID()
    {
    
        return this.currentID;
    }
      
    
    /**
     * <b>Observer:</b> Checks if a player has finished
     * <b>Postcondition:</b> returns true if a player has finished, else false
     * @return true if a player has finished, else false
     */
    public boolean checkIfPlayerFinished(Player p)
    {

        return false;
     }
    
    
    /**
     * <b>Transformer(Mutative):</b> Sets the number of players.
     * <b>Postcondition:</b> The number of players has been set.
     */
    public void NumberOfPlayers()
    {
        
    }
    
    
    /**
     * <b>Accessor(Selector):</b> returns the number of players
     * <b>Postcondition:</b> Returns the number of players is returned
     * @return the number of players
     */
    public int GetNumberOfPlayers()
    {
        return this.num;
    }
    
    
    /**
     * <b>Accessor(Selector):</b> returns the number of players in this round
     * <b>Postcondition:</b> the number of players in this round is returned
     * @return the number of players in this round
     */
    public int Get_Round_Players()
    {
        return this.round_players;
    }
    
    
    /**
     * <b>Transformer(Mutative):</b> Sets the number of round players.
     * <b>Postcondition:</b> The number of round players has been set.
     * @param int j
     */
    public void SetRoundPlayers(int j)
    {
          
    }
    
    
    /**
     * <b>Transformer(Mutative):</b> Sets the most recent player, who has dropped cards in the table.
     * <b>Postcondition:</b> The most recent player, who has dropped cards in the table has been set.
     * @param int k
     */
    public void Set_last_player(int k)
    {
        
    }
    
    
    /**
     * <b>Accessor(Selector):</b> returns the most recent player, who has dropped cards in the table
     * <b>Postcondition:</b> the most recent player, who has dropped cards in the table is returned
     * @return the most recent player, who has dropped cards in the table
     */
    public int Get_last_player()
    {
        return this.last_player;
    }
}
