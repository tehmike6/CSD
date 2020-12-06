package model.round;

import model.Sullogi.Sullogi;

/**
 * This class represents a Round 
 * @version 1.0
 * @author dxanthak - mountanton
 */
public class Round 
{
    private int type;
    private boolean roundStarted,selection,bomb_round;
    private Sullogi cards = new Sullogi();
    private Sullogi roundcards=new Sullogi();
    
    /**  Constructor.
     * 
     * <b>Postcondition</b>Creates a new Round with type=0, roundStarted=false,
     * bomb_round=false, selection=false
     */
    public Round ()
    {
       type=0;
       roundStarted=false;
       bomb_round=false;
       selection=false;
   }

    

    /**
     * <b>transformer(mutative)</b>: Sets the Cards that a player wants to play
     * <b>Postcondition:</b> the cardsToPlay is set
     * @param cards
     */
    public void setCardsToPlay(Sullogi cards )
    {
       
   }
    
    
    /**
     *<b>transformer:</b> Sets the round 
     * <b>Postcondition:</b> the type of the round has been set
     */
    public void setRoundType()
    {
       
    }
    
    
    /**
     *<b>Accessor(Selector):</b> Returns the round type
     * <b>Postcondition:</b> returns a valid round type
     * @return int type
     */
    public int getRoundType()
    {
       return type;
    }
    
    
    /**
     * <b>Observer:</b> Checks if Sullogi is full house
     * <b>Postcondition:</b> returns true if Sullogi is FullHouse, else false
     * @return true if Sullogi is FullHouse, else false
     */
    public boolean checkFull()
    {
        return false;
    }
    
    
    /**
     * <b>Observer:</b> Checks if Sullogi is Straight
     * <b>Postcondition:</b> returns true if Sullogi is Straight, else false
     * @return true if Sullogi is Straight, else false
     */
    public boolean checkStraight()
    {
        return false;
    }
   
    
   /**
     * <b>Observer:</b> Checks if Sullogi other is better than the previous which has been dropped to the table
     * <b>Postcondition:</b> returns true if Sullogi other is better than the previous which has been dropped to the table
     * @return true if Sullogi is better than the previous else false
     */
    public boolean compareCollections(Sullogi other)
    {
        return false;
    }

    
    /**
     * <b>Observer:</b> Checks if Sullogi is Pair
     * <b>Postcondition:</b> returns true if Sullogi is Pair, else false
     * @return true if Sullogi is Pair, else false
     */
    public boolean checkPairs()
    {
        return true;
   }

    
    
    /**
     * <b>Observer:</b> Checks if round is bomb
     * <b>Postcondition:</b> returns true if round is bomb, else false
     * @return true if round is bomb, else false
     */    
    public boolean bomb_round()
    {
        return this.bomb_round;
    }
    
    
    /**
     *<b>Transformer(Mutative):</b> Sets the bomb round 
     * <b>Postcondition:</b> the type of the bomb round has been set true
     */
    public void Set_bomb_round()
    {
        
    }
    
    
    /**
     * <b>Observer:</b> Checks if Sullogi is bomb
     * <b>Postcondition:</b> returns true if Sullogi is bomb, else false
     * @param cards2
     * @return true if Sullogi is bomb, else false
     */ 
    public boolean checkBomb(Sullogi cards2)
    {
        return false;
    }

    
    /**
     *<b>Transformer(Mutative):</b> Starts the round
     * <b>Postcondition:</b> the round has been started
     */    
    public void roundStarted()
    {
        
    }
    
    
    /**
     * <b>Observer:</b> Checks if Round has started
     * <b>Postcondition:</b> returns true if Round has started, else false
     * @return true if Round has started, else false
     */ 
    public boolean getRoundStarted()
    {
        return this.roundStarted;
    }
    
    
    /**
     *<b>Transformer(Mutative):</b> Updates the cards that have been played in this round 
     * <b>Postcondition:</b> the roundcards have been updated
     * @param other
     */
    public void Update_Cards(Sullogi other)
    {
        
    }
    
    
    /**
     *<b>Accessor(Selector):</b> Returns the cards that have been played in this round
     * <b>Postcondition:</b> returns the cards that have been played in this round
     * @return Sullogi roundcars
     */
    public Sullogi GetRoundCards()
    {
        return this.roundcards;
    }
    
    
    /**
     * <b>Transformer(Mutative):</b> Used in types: FullHouse or Straight
     * If a player have selected a value for phoenix, set selection variable to true
     * <b>Postcondition:</b> selection has been set to true
     */
    public void SetSelection()
    {
       
    }
    
    
    /**
     * <b>Observer:</b> Used in types: FullHouse or Straight
     * Returns true if a player has made a selection for phoenix value, otherwise false
     * <b>Postcondition:</b> return true if a player has made a selection for phoenix value, otherwise false
     * @return true if a player has made a selection for phoenix value, otherwise false
     */ 
    public boolean SelectPhoenixValue()
    {
       return this.selection;
    }
   
}

