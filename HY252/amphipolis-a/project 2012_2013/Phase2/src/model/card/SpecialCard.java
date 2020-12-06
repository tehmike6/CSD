/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.card;

/**
 * @version 1.0
 * @author dxanthak - mountanton
 */
public class SpecialCard implements Card {
     
        private CardColor col;
        private int temp_val;
        private int value,points;
        private String type;

    /**Constructor.
     * 
     * <b>Postcondition</b>Creates a new multiset with 'value' value, 'points' points and 'type' type.
     *
     * @param value
     * @param points
     * @param type
     */
    public SpecialCard(int value,int points,String type) {
                this.value=value;
                this.points=points;
                this.type=type;
                this.temp_val=0;
                
                
        }

    /**
     * <b>Transformer:</b> sets the card's type
     * <b>Postcondition:</b> the card's type has been set
     * @param String type
     */
    public void setType(String type) {
                this.type=type;
        }

    /**
     * <b>Accessor:</b> returns the card's type
     * <b>Postcondition:</b> the card's color has been returned
     * @return cards type
     */
    public String getType() {
             return this.type;
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
        public String toString(){
                return this.type.toString();
        }

    /**
     * <b>Transformer:</b> sets the card's temp value
     * <b>Postcondition:</b> the card's temp value has been set
     * @param int j
     */
    @Override
    public void SetTempValue(int j){
            this.temp_val=j;
        }

    /**
     * <b>Accessor:</b> returns the card's temp value
     * <b>Postcondition:</b> card's temp value has been returned
     * @return the card's temp value
     */
    @Override
    public int GetTempValue(){
            return temp_val;
        }
    /**
     * <b>Accessor:</b> returns the card's color
     * <b>Postcondition:</b> card's color has been returned
     * @return the card's color
     */
     public CardColor getColor(){
             return null;
     } 
}
