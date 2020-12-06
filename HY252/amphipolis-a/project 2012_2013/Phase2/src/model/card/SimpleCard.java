/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.card;

/**
 * @version 1.0
 * @author dxanthak - mountanton
 */
public class SimpleCard implements Card{
        private CardColor col;
        private int value;
        private int points;
        

    /**Constructor.
     * 
     * <b>Postcondition</b>Creates a new Simple Card with 'col' col and 'value' value.
     *
     * @param col
     * @param value
     */
    public SimpleCard(CardColor col, int value) {
                
                this.col = col;
                this.value = value;

                if (value== 5) {
                        this.points = 5;
                } else if (value == 10 || value == 13) {
                        this.points = 10;
                } else {
                        this.points = 0;
                }
        }

    /**
     * <b>Accessor:</b> returns the card's color
     * <b>Postcondition:</b> card's color has been returned
     * @return the card's color
     */
    public CardColor getColor() {
                return col;
        }

    /**
     * <b>Transformer:</b> sets the card's color
     * <b>Postcondition:</b> the card's color has been set
     * @param  CardColor col
     */
    public void setColor(CardColor col) {
                this.col = col;
        }

    /**
     * <b>Accessor:</b> returns the card's value
     * <b>Postcondition:</b> card's value has been returned
     * @return int value
     */
    @Override
    public int getValue() {
                return value;
        }

     /**
     * <b>Transformer:</b> sets the card's value
     * <b>Postcondition:</b> card's value has been set
     * @param int value
     */
    @Override
    public void setValue(int value) {
                this.value = value;
        }

     /**
     * <b>accessor(selector)</b>:Returns the points of a card <br />
     * <p><b>Postcondition:</b> the points of a card have been returned</p>
     *
     *@return the points of a card
     */
    @Override
    public int getPoints() {
                return points;
        }

       /**
     * <b>transformer(mutative)</b>: sets the points of a card <br />
     * <p><b>Postcondition:</b> the points of a card have been set</p>
     *
     * @param int points
     * 
     */ 
    @Override
    public void setPoints(int points) {
                this.points = points;
        }

     /**
     * Returns the string representation of a card
     * <p><b>Postcondition:</b> The string representation of a card is returned</p>
     * @return The string representation of a card 
     */
    @Override
        public String toString() {
                return this.value + "(" + col.toString() + ")";
        }

       
     /**
     * <b>Transformer:</b> sets the card's temp value
     * <b>Postcondition:</b> the card's temp value has been set
     * @param int j
     */
    @Override
    public void SetTempValue(int j){}

    /**
     * <b>Accessor:</b> returns the card's temp value
     * <b>Postcondition:</b> card's temp value has been returned
     * @return the card's temp value
     */
    @Override
    public int GetTempValue(){return -1;}
        

}
