/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.round;

import model.Sullogi.Sullogi;
import model.card.Card;

/**
 * @version 1.0
 * @author dxanthak - mountanton
 */
public class Monofyllia extends Round {

    private Card lastCardPlayed;
    private boolean haswish;
    /**
	 * <b>constructor</b>: Constructs a new Monofyllia round with the given card c
         * <b>postcondition</b>: creates a new Monofyllia round and save the last card
         * that has been played to card lastCardPlayed
         * @param c the last card which has been played
	 */
    public Monofyllia(Card c){
        
        this.lastCardPlayed=c;
        
        if (this.lastCardPlayed.getValue()==15){
            this.lastCardPlayed.SetTempValue(0);
        }
        else if (this.lastCardPlayed.getValue()==16){
            super.SetDracheInRound();
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
    public boolean compareCollections(Sullogi other){
        haswish=false;
        if ((super.GetEuxi()!=0) &&(euxiMeMahjong(other)==false)){
            return false;
        } 
        super.setCardsToPlay(other);
       super.setRoundType();
       if (super.getRoundType()!=1){
           return false;
       } 
       if ((this.lastCardPlayed.getValue()==16) || (this.lastCardPlayed.getValue()==-1)){
           return false;
       }
       if (other.getValue(0)==15){
           other.getCard(0).SetTempValue(this.lastCardPlayed.getValue());
           this.lastCardPlayed=other.getCard(0);
           
           return true;
           
       }
       if (this.lastCardPlayed.GetTempValue()!=-1){
           if (other.getValue(0)>this.lastCardPlayed.GetTempValue()){
               this.lastCardPlayed=other.getCard(0);
                if (this.lastCardPlayed.getValue()==16){
                    super.SetDracheInRound();
                }  
            if (haswish==true){
                super.SetEuxi(0);
            }
            return true;
           }
       }
       if (other.getValue(0)> this.lastCardPlayed.getValue()){
           
           this.lastCardPlayed=other.getCard(0);
           if (this.lastCardPlayed.getValue()==16){
                    super.SetDracheInRound();
                }  
            if (haswish==true){
                super.SetEuxi(0);
            }
           return true;
        }
      return false;
   }
    /**
	 * <b>transformer(mutative)</b>: check if a wish has played and set euxi value to 0 <br />
	 * <p><b>Postcondition:</b> check if a wish has played and set euxi value to 0 </p>
	 *
     */
    @Override
   public void  SetIfWishChanged(){
         if (this.lastCardPlayed.getValue()==super.GetEuxi()){
                super.SetEuxi(0);
            }
   }
     /**
     * <b>Observer:</b> Checks if player has  wish in his cards
     * <b>Postcondition:</b> returns true if a player can play, else false
     * (because he has a wish which can be played but he didnt played it
     * @return true if a player can play, else false
     */
    @Override
   public boolean euxiMeMahjong(Sullogi other){
           if (other.isEmpty()==false){
            if (other.getCard(0).getValue()==super.GetEuxi()){
               haswish=true;
               return true;
            }
           }
            
            if (super.EuxiMeBomb()==false){
                
                return false;
            }
            if ((lastCardPlayed.GetTempValue()!=-1) && 
                    (lastCardPlayed.GetTempValue()>super.GetEuxi())){
                return true;
       
            }
            
            else if ((lastCardPlayed.GetTempValue()==-1) && (lastCardPlayed.getValue()>super.GetEuxi())){
               return true;
             }
            else if (lastCardPlayed.getValue()==16){
                return true;
            }
            
           for (int i=0;i<super.GetPlayerAllCards().size();i++){
               if (super.GetPlayerAllCards().getCard(i).getValue()==super.GetEuxi()){
                   return false;
               }
   } 
            return true;
   }         
}
