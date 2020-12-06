package model.round;

import model.Sullogi.Sullogi;

/**
 * This class represents a Pairs Round 
 * @version 1.0
 * @author dxanthak - mountanton
 */
public class Pairs extends Round
{
    private Sullogi lastCardsPlayed=new Sullogi();
    private int size;
    
    /**
     * <b>constructor</b>: Constructs a new Pairs round with the given sullogi a and int size
     * <b>postcondition</b>: creates a new Pairs round and save the last collection
     * that has been played to collection lastCardsPlayed
     * @param a the last collection which has been played
     * @param size the size of the pairs that have been played
     */
    public Pairs(Sullogi a,int size)
    {
        this.size=size;
        for (int i=0;i<a.size();i++)
        {
            this.lastCardsPlayed.addCard(a.getCard(i));
        }
    }
    
    
    /**
     * <b>transformer(mutative)</b>: This method sees if a Pairs collection that a player wants to play 
     * is better than the last Pairs Collection that has been played.So if it is better
     * return true and saves this collection to lastCardsPlayed,otherwise return false.<br />
     * <b>precondition</b>:other must be a valid Pairs collection 
     * and must have the same size as the previous <br />
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
