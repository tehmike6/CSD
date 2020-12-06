package model.round;

import model.Sullogi.Sullogi;

/**
 * This class represents a Straight Round 
 * @version 1.0
 * @author dxanthak - mountanton
 */
public class Straight extends Round
{
    private Sullogi lastCardsPlayed=new Sullogi();
    private int size;
      
    /**
     * <b>constructor</b>: Constructs a new Straight round with the given sullogi a and int size
     * <b>postcondition</b>: creates a new Straight round and save the last collection
     * that has been played to collection lastCardsPlayed
     * @param a the last collection which has been played
     * @param size the size of the straight that have been played
     */
    public Straight(Sullogi a,int size)
    {

    }
    
    
    /**
    * <b>transformer(mutative)</b>: This method sees if a Straight collection that a player wants to play 
    * is better than the last Straight Collection that has been played.So if it is better
    * return true and saves this collection to lastCardsPlayed,otherwise return false.<br />
    * <b>precondition</b>:other must be a valid Straight collection 
*   * and must have the same size as the previous <br />
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
