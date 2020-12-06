/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.turn;

import java.util.ArrayList;
import model.player.Player;

/**
 * @version 1.0
 * @author dxanthak - mountanton
 */
public class Turn {
    private int currentID,round_players;
    private int i,num;
    private int last_player;
    
    /**  Constructor.
     * 
     * <b>Postcondition</b>Creates a new instance of Turn with currentID=0, 
     *      num=4, round_players=4, last_player=0.
     */
    public Turn(){
        currentID=0;
        num=4;
        this.round_players=4;
        this.last_player=0;
    }
   
    /**
     * <b>Transformer:</b> Sets the player's turn.
     * <b>Postcondition:</b> Player's turn has been set.
     * @param players
     */
    public void setID(ArrayList <Player> players){
       
        if ((currentID==3) && (players.get(0).Get_has_finished()==false)) {
            this.currentID=0;
    }
        else if ((currentID==3) && (players.get(1).Get_has_finished()==false)){
            this.currentID=1;
        }
        else if ((currentID==3) && (players.get(2).Get_has_finished()==false)){
            this.currentID=2;
        }
        else if (currentID!=3){
            i=currentID;
            while (players.get(i+1).Get_has_finished()==true){
                if (i==2){
                    i=-1;
                }
                else{
                    i++;
                }
            }
            this.currentID=i+1;
            
        }
      
    }
    
    
    /**
     * <b>Accessor:</b> returns the player's ID whose turn is to play
     * <b>Postcondition:</b> the player's ID whose turn is to play
     * @return the player's ID whose turn is to play
     */
    public int  getID(){
    
     return this.currentID;
    }
      
    
     /**
     * <b>Observer:</b> Checks if a player has finished
     * <b>Postcondition:</b> returns true if a player has finished, else false
     * @return true if a player has finished, else false
     */
    public boolean checkIfPlayerFinished(Player p){
        if (p.getCards().isEmpty()==true){
            p.has_finished();
            NumberOfPlayers();
            return true;
        }
        return false;
    }
    
    
    /**
     * <b>Transformer:</b> Sets the number of players.
     * <b>Postcondition:</b> The number of players has been set.
     */
    public void NumberOfPlayers(){
        this.num--;
    }
    
    
     /**
     * <b>Accessor:</b> returns the number of players
     * <b>Postcondition:</b> the number of players is returned
     * @return the number of players
     */
    public int GetNumberOfPlayers(){
        return this.num;
    }
    
    
     /**
     * <b>Accessor:</b> returns the number of players in this round
     * <b>Postcondition:</b> the number of players in this round is returned
     * @return the number of players in this round
     */
    public int Get_Round_Players(){
        return this.round_players;
    }
    
    
    /**
     * <b>Transformer:</b> Sets the number of round players.
     * <b>Postcondition:</b> The number of round players has been set.
     * @param int j
     */
    public void SetRoundPlayers(int j){
       this.round_players=this.num+j;
       
    }
    
    
    /**
     * <b>Transformer:</b> Sets the most recent player, who has dropped cards in the table.
     * <b>Postcondition:</b> The most recent player, who has dropped cards in the table has been set.
     * @param int k
     */
    public void Set_last_player(int k){
        this.last_player=k;
    }
    
    
    /**
     * <b>Accessor:</b> returns the most recent player, who has dropped cards in the table
     * <b>Postcondition:</b> the most recent player, who has dropped cards in the table is returned
     * @return the most recent player, who has dropped cards in the table
     */
    public int Get_last_player(){
        return this.last_player;
    }
}
