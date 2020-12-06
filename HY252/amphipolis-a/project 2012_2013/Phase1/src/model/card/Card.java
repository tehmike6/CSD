package model.card;

/**
 * Contains the methods signatures needed for 
 * creating a tichu simple or special card
 * @version 1.0
 * @author dxanthak - mountanton
 */
public abstract interface Card 
{
        
       
    /**
     * <b>Accessor:</b> returns the card's value
     * <b>Postcondition:</b> card's value has been returned
     * @return int value
     */
    public int getValue();
    
    /**
     * <b>Transformer:</b> sets the card's value
     * <b>Postcondition:</b> card's value has been set
     * @param value int
     */
    public void setValue(int value);

        
    /**
     * <b>accessor(selector)</b>:Returns the points of a card <br />
     * <p><b>Postcondition:</b> the points of a card have been returned</p>
     *
     *@return the points of a card
     */
    public int getPoints();

    
    /**
     * <b>transformer(mutative)</b>: sets the points of a card <br />
     * <p><b>Postcondition:</b> the points of a card have been set</p>
     *
     * @param points points
     * 
     */ 
    public void setPoints(int points);
        
    
    /**
     * Returns the string representation of a card
     * <p><b>Postcondition:</b> The string representation of a card is returned</p>
     * @return The string representation of a card 
     */
    @Override
    public String toString();
    
    
    /**
     * <b>Transformer:</b> sets the card's temp value
     * <b>Postcondition:</b> the card's temp value has been set
     * @param int j
     */
    public void SetTempValue(int j);
    
    
    /**
     * <b>Accessor:</b> returns the card's temp value
     * <b>Postcondition:</b> card's temp value has been returned
     * @return the card's temp value
     */
    public int GetTempValue();
}
