package model.round;

import model.Sullogi.Sullogi;
import model.card.Card;

/**
 * This class represents a Monofyllia Round 
 * @version 1.0
 * @author dxanthak - mountanton
 */
public class Monofyllia extends Round 
{
    private Card lastCardPlayed;
   /**
    * <b>constructor</b>: Constructs a new Monofyllia round with the given card c
    * <b>postcondition</b>: creates a new Monofyllia round and save the last card
    * that has been played to card lastCardPlayed
    * @param c the last card which has been played
    */
    public Monofyllia(Card c)
    {
       this.lastCardPlayed=c;
        if (this.lastCardPlayed.getValue()==15)
        {
            this.lastCardPlayed.SetTempValue(0);
        }
    }
    
    /**
    * <b>transformer(mutative)</b>: This method sees if a Monofyllia card that a player wants to play 
    * is better than the last Monofyllia card that has been played.So if it is better
    * return true and saves this card to lastCardPlayed,otherwise return false.<br />
    * <b>precondition</b>:other must be a valid Monofyllia collection <br />
    * <b>postcondition</b>: If the card int Sullogi other wins card lastCardsPlayed
    * returns true, else false.
    * @param other the collection in which included the 1 card that a player wants to play
    * @return true if card in sullogi other is better than card lastCardPlayed, false otherwise.
    */
    @Override
    public boolean compareCollections(Sullogi other)
    {
        
        return false;
    }    
  
}
