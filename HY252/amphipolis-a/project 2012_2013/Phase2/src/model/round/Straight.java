/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.round;

import java.util.ArrayList;
import model.Sullogi.Sullogi;
import model.card.Card;
import model.card.SimpleCard;
/**
 * @version 1.0
 * @author dxanthak - mountanton
 */
public class Straight extends Round{
      private Sullogi lastCardsPlayed=new Sullogi();
      private int size;
      boolean haswish;
      
    /**
     * <b>constructor</b>: Constructs a new Straight round with the given sullogi a and int size
     * <b>postcondition</b>: creates a new Straight round and save the last collection
     * that has been played to collection lastCardsPlayed
     * @param a the last collection which has been played
     * @param size the size of the straight that have been played
     */
    public Straight(Sullogi a,int size){
             this.size=size;
       super.type=6;
        for (int i=0;i<a.size();i++){
            this.lastCardsPlayed.addCard(a.getCard(i));
        }
        super.lastCards(lastCardsPlayed);
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
    public boolean compareCollections(Sullogi other){
      haswish=false;
        if ((super.GetEuxi()!=0) &&(euxiMeMahjong(other)==false)){
            return false;
        } 
        
    
     super.setCardsToPlay(other);
       
       
        if ((other.size()!=this.size) || (super.checkStraight()==false)){
           return false;
       } 
       if ((other.getCard(other.size()-1).getValue()==15) && (super.SelectPhoenixValue()==false)){
         return true;
      }
       
       if (checkIfBetter(other)==true)  {
           this.lastCardsPlayed.clearAll();
           for (int i=0;i<size;i++){
             this.lastCardsPlayed.addCard(other.getCard(i));
        }
           super.lastCards(lastCardsPlayed);
           
           if (haswish==true){
                super.SetEuxi(0);
            }
           return true;
       }
       
      return false;
   }
    public boolean checkIfBetter(Sullogi other){
         if ((other.getCard(0).getValue()!=15) && (this.lastCardsPlayed.getCard(0).getValue()!=15)) {
              
             if (other.getCard(0).getValue()>this.lastCardsPlayed.getCard(0).getValue())
       {
              
                 return true;
             }
             
         }
         
         else if ((other.getCard(0).getValue()==15) ||(this.lastCardsPlayed.getCard(0).getValue()==15)) {
             
             if (other.getCard(1).getValue()>this.lastCardsPlayed.getCard(1).getValue()){
                 return true;
                 }
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
     * <b>Observer:</b> Checks if player has a better Straight with wish in his cards
     * <b>Postcondition:</b> returns true if a player can play, else false
     * (because he has a better Straight with wish but he didnt played it
     * @return true if a player can play, else false
     */
    @Override
   public boolean euxiMeMahjong(Sullogi other){
           int count=0;
           
           ArrayList <Integer> temp=new ArrayList<Integer>();
           Sullogi temp2=new Sullogi();
           Sullogi temp3=new Sullogi();
           Card wish=new SimpleCard(null,2);
           Round newr=new Round();
           if (other.isEmpty()==false){ 
               for (int i=0;i<other.size();i++){
                    if (other.getCard(i).getValue()==super.GetEuxi()){
                         
                            haswish=true;
                            return true;
                    }             
           }
           }
           if (super.EuxiMeBomb()==false){
           
                return false;
            }
           if (lastCardsPlayed.getCard(0).getValue()>super.GetEuxi()){
               return true;
           }
           if (super.GetPlayerAllCards().size()<size){
               return true;
           }
           for (int i=0;i<super.GetPlayerAllCards().size();i++){
               if (super.GetPlayerAllCards().getCard(i).getValue()==super.GetEuxi())
               {
                   count=1;
                   wish=super.GetPlayerAllCards().getCard(i);
                   i=15;
               }
           } 
           if (count==0){
               return true;
           }
           for (int i=0;i<super.GetPlayerAllCards().size();i++){
               if ((super.GetPlayerAllCards().getCard(i).getValue()>=2) &&(super.GetPlayerAllCards().getCard(i).getValue()<16) )    
               {
                    if (temp.contains(super.GetPlayerAllCards().getCard(i).getValue())==false){
                    temp.add(super.GetPlayerAllCards().getCard(i).getValue());
                    temp2.addCard(super.GetPlayerAllCards().getCard(i));
                    }
                   
               }
           }
           if (temp.size()<size){
               return true;
           }
           for (int i=0;i<temp.size()-size+1;i++){
               for (int j=i;j<i+size;j++){
                   temp3.addCard(temp2.getCard(j));
               }
               newr.setCardsToPlay(temp3);
               if((temp3.getCards().contains(wish)==true)&&(newr.checkStraight()==true) && temp2.getCard(temp2.size()-1).getValue()!=15) {
                 if  (checkIfBetter(temp3)==true){
              
                   return false;
                 }  
               }
               temp3.clearAll();
           }
           
              if (temp2.getCard(temp2.size()-1).getValue()==15){
     
              for (int i=0;i<temp.size()-size+1;i++){
               for (int j=i;j<i+size-1;j++){
                   temp3.addCard(temp2.getCard(j));
               }
               temp3.addCard(temp2.getCard(temp2.size()-1));
               newr.setCardsToPlay(temp3);
               
               if((temp3.getCards().contains(wish)==true)&&(newr.checkStraight()==true)) {
                if  (checkIfBetter(temp3)==true){
                   
                   return false;
                 }  
               }
               newr.SetSelection(false);
               temp3.clearAll();
           }
              }
            return true;
   }       
}