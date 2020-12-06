package model.Sullogi;

import java.util.ArrayList;
import java.util.Collections;
import model.card.Card;
import model.card.CardColor;
import model.card.Drache;
import model.card.HUND;
import model.card.Mahjong;
import model.card.Phoenix;
import model.card.SimpleCard;

/**
 * This class creates a new Collection of cards.
 * @version 1.0
 * @author dxanthak - mountanton
 */
public class Sullogi 
{
    private ArrayList<Card> cards;
    
    /**Constructor.
     * 
     * <b>Postcondition</b>Creates a new Sullogi with a new card ArrayList.
     *
     */
    public Sullogi()
    {
        cards = new ArrayList<Card>();
    }
    
    /**
     * <b>Transformer:</b> Initializes and shuffles the 56 cards.
     * <b>Postcondition:</b> The cards have been initialised and shuffled.
     */
    public void init_cards()
    {

    }

    /**
     * <b>Transformer:</b> Sorts an ArrayList from position 'i' to position 'j-1'.
     * <b>Postcondition:</b> An ArrayList is sorted.
     * @param i
     * @param j
     */
    public void sort(int i, int j)
    {
        
    }
    
    
    /**
     * <b>Observer:</b> Returns true if this list contains no elements.
     * <b>Postcondition:</b> Returns true if this list contains no elements.
     * @return true if this list contains no elements
     */
    public boolean isEmpty()
    {

        return false;
    }
    
    
    /**
     * <b>Observer:</b> Adds a card to the list.
     * <b>Postcondition:</b> A card has been added to the list.
     * @param i
     */
    public void addCard(Card i)
    {
        
    }
    
    /**
     * <b>Accessor:</b> returns the card's value in position i
     * <b>Postcondition:</b> card's value in position i has been returned
     * @return the value of the card in position i
     */
    public int getValue(int i)
    {
        return cards.get(i).getValue();
    }

    
    /**
     * <b>Transformer:</b> Removes a card from the list.
     * <b>Postcondition:</b> A card has been removed from the list.
     * @param i
     */
    public void removeCard(Card i)
    {
        
    }
    
    
    /**
     * <b>Transformer:</b> Returns the size of a list.
     * <Postcondition:</b> The size of the list has been returned.
     * @return size of the list
     */
    public int size()
    {
        return cards.size();
    }
    
    
    /**
     * <b>Accessor:</b> returns the card in position 'index'
     * <b>Postcondition:</b> the card in position 'index' has been returned
     * @return the card in position 'index'
     */
    public Card getCard(int index)
    {
        return cards.get(index);
    }
    
    
    /**
    * <b>Transformer:</b> Clears an ArrayList 
     * <b>Postcondition:</b> An ArrayList is cleared.
     */
    public void clearAll()
    {
        
    }   
    
    
    /**
     * <b>Accessor:</b> returns all the cards
     * <b>Postcondition:</b> all the cards has been returned
     * @return all the cards
     */
    public ArrayList<Card> getCards()
    {
        return this.cards;
    }
}