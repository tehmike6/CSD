/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.round;

import model.Sullogi.Sullogi;

/**
 * @version 1.0
 * @author dxanthak - mountanton
 */
public class SimplePair extends Round{
    private Sullogi lastCardsPlayed=new Sullogi();
    boolean haswish;

    
    /**
    * <b>constructor</b>: Constructs a new Simple Pair round with the given sullogi a
    * <b>postcondition</b>: creates a new Simple Pair round and save the last collection
    * that has been played to collection lastCardsPlayed
    * @param a the last collection which has been played
    */
    public SimplePair(Sullogi a){
        this.lastCardsPlayed.addCard(a.getCard(0));
        this.lastCardsPlayed.addCard(a.getCard(1));
    }

    
    /**
     * <b>transformer(mutative)</b>: This method sees if a Simple Pair collection that a player wants to play 
     * is better than the last Simple Pair Collection that has been played.So if it is better
     * return true and saves this collection to lastCardsPlayed,otherwise return false.<br />
     *  <b>precondition</b>:other must be a valid Simple Pair collection <br />
     * <b>postcondition</b>: If the Sullogi other wins Sullogi lastCardsPlayed
     * returns true, else false.
     * @param other the collection that a player wants to play
     * @return true if other  is better than lastCardsPlayed, false otherwise.
     */
    @Override
    public boolean compareCollections(Sullogi other){
        haswish=false;
        if ((super.GetEuxi()!=0) &&(euxiMeMahjong(other)==false)){
            return false;
        } 
        super.setCardsToPlay(other);
       super.setRoundType();
       if (super.getRoundType()!=2){
           return false;
       } 
       if (other.getCard(0).getValue()>this.lastCardsPlayed.getCard(0).getValue()){
           
           this.lastCardsPlayed.clearAll();
           this.lastCardsPlayed.addCard(other.getCard(0));
           this.lastCardsPlayed.addCard(other.getCard(1));
           super.lastCards(lastCardsPlayed);
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
         for (int i=0;i<lastCardsPlayed.size();i++){
            if (this.lastCardsPlayed.getCard(i).getValue()==super.GetEuxi()){
                 super.SetEuxi(0);
            }
        }
   } 
     /**
     * <b>Observer:</b> Checks if player has a better SimplePair with wish in his cards
     * <b>Postcondition:</b> returns true if a player can play, else false
     * (because he has a better SimplePair with wish but he didnt played it
     * @return true if a player can play, else false
     */
    @Override
   public boolean euxiMeMahjong(Sullogi other){
            int count=0;
           if (other.isEmpty()==false){ 
           if (other.getCard(0).getValue()==super.GetEuxi()){
               haswish=true;
               return true;
           }
           }
           if (super.EuxiMeBomb()==false){
                return false;
            }
           if (lastCardsPlayed.getCard(0).getValue()>super.GetEuxi()){
               return true;
           }
           
           for (int i=0;i<super.GetPlayerAllCards().size();i++){
               if (super.GetPlayerAllCards().getCard(i).getValue()==super.GetEuxi()
               || (super.GetPlayerAllCards().getCard(i).getValue()==15)){
                   count++;
               }
   } 
           if (count>=2){
               return false;
           }
            return true;
   }         
 
}
