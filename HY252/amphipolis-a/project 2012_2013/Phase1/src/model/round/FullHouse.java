package model.round;

import model.Sullogi.Sullogi;

/**
 * This class represents a FullHouse Round 
 * @version 1.0
 * @author dxanthak - mountanton
 */
public class FullHouse extends Round 
{
     private Sullogi lastCardsPlayed=new Sullogi();
     
    /**
     * <b>constructor</b>: Constructs a new full house round with the given sullogi a
     * <b>postcondition</b>: creates a new full house round and save the last collection
     * that has been played to collection lastCardsPlayed
     * @param a the last collection which has been played
     */
    public FullHouse(Sullogi a)
    {
       for (int i=0;i<a.size();i++)
       {
            this.lastCardsPlayed.addCard(a.getCard(i));
       }
    }
    
    
    /**
     * <b>transformer(mutative)</b>: This method sees if a full house collection that a player wants to play 
     * is better than the last full house Collection that has been played.So if it is better
     * return true and saves this collection to lastCardsPlayed,otherwise return false.<br />
     *  <b>precondition</b>:other must be a valid Full house collection <br />
     * <b>postcondition</b>: If the Sullogi other wins Sullogi lastCardsPlayed
     * returns true, else false.
     * @param other the collection that a player wants to play
     * @return true if other  is better than lastCardsPlayed, false otherwise.
     */
    @Override
    public boolean compareCollections(Sullogi other)
    {
        
       
        return false;
    }
}
