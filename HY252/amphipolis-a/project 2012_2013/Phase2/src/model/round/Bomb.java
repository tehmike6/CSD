/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.round;

import model.Sullogi.Sullogi;
import model.card.Card;
import model.card.SimpleCard;

/**
 * @version 1.0
 * @author dxanthak - mountanton
 */
public class Bomb extends Round{
     private Sullogi lastCardsPlayed=new Sullogi();
     private int size=0;
     boolean haswish;
      /**
	 * <b>constructor</b>: Constructs a new bomb round with the given sullogi a
         * <b>postcondition</b>: creates a new bomb round and save the last collection
         * that has been played to collection lastCardsPlayed
         * @param a the last collection which has been played
	 */
    public Bomb(Sullogi a){
        
        super.Set_bomb_round();
        for (int i=0;i<a.size();i++){
            this.lastCardsPlayed.addCard(a.getCard(i));
            size++;
        }
        super.lastCards(lastCardsPlayed);
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
	 * <b>transformer(mutative)</b>: This method sees if a bomb collection that a player wants to play 
         * is better than the last bomb Collection that has been played.So if it is better
         * return true and saves this collection to lastCardsPlayed,otherwise return false.<br />
         * <b>precondition</b>:other must be a valid bomb collection <br />
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
        if (super.checkBomb(other)==true){
             if ((other.size()>size)|| ((other.size()==size) &&
                     (other.getCard(0).getValue()>this.lastCardsPlayed.getCard(0).getValue()))){
           this.lastCardsPlayed.clearAll();
           for (int i=0;i<other.size();i++){
             this.lastCardsPlayed.addCard(other.getCard(i));
             size=other.size();
             
            }
            if (haswish==true){
                super.SetEuxi(0);
            }
            super.lastCards(lastCardsPlayed);
            
            return true;
            }
        }
        return false;
    }
     /**
     * <b>Observer:</b> Checks if player has a better bomb with wish in his cards
     * <b>Postcondition:</b> returns true if a player can play, else false
     * (because he has a better bomb with wish but he didnt played it
     * @return true if a player can play, else false
     */ 
    @Override
    public boolean euxiMeMahjong(Sullogi other){
        int count=0;
        
         Card wish=new SimpleCard(null,2);
         if (other.isEmpty()==false){ 
              
               for (int i=0;i<other.size();i++){
                    if (other.getCard(i).getValue()==super.GetEuxi()){
                            haswish=true;
                            return true;
                    }             
           }
         }
            if (super.GetPlayerAllCards().size()<4){
                
               return true;
           }
           
            for (int i=0;i<super.GetPlayerAllCards().size();i++){
               
               if (super.GetPlayerAllCards().getCard(i).getValue()==super.GetEuxi())
                       
               {
                   if (count==0){
                            wish=super.GetPlayerAllCards().getCard(i);
                   }
                   count++;
                  
               }
           }     
           
          if (count==0){
              return true;
          }
           
          if ((count==4) && (size==4)&&(wish.getValue()>this.lastCardsPlayed.getCard(0).getValue())){
                    return false;
              
          }
          
         return true;
    }
}