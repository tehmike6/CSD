/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.round;

import java.util.ArrayList;
import model.card.Card;
import model.Sullogi.Sullogi;
import model.card.SimpleCard;

/**
 * @version 1.0
 * @author dxanthak - mountanton
 */
public class Pairs extends Round{
    private Sullogi lastCardsPlayed=new Sullogi();
    private int size;
    boolean haswish;
    /**
     * <b>constructor</b>: Constructs a new Pairs round with the given sullogi a and int size
     * <b>postcondition</b>: creates a new Pairs round and save the last collection
     * that has been played to collection lastCardsPlayed
     * @param a the last collection which has been played
     * @param size the size of the pairs that have been played
     */
    public Pairs(Sullogi a,int size){
        this.size=size;
        for (int i=0;i<a.size();i++){
            this.lastCardsPlayed.addCard(a.getCard(i));
        }
        super.lastCards(lastCardsPlayed);
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
    public boolean compareCollections(Sullogi other){
         haswish=false;
        if ((super.GetEuxi()!=0) &&(euxiMeMahjong(other)==false)){
            return false;
        } 
        super.setCardsToPlay(other);
       
       super.setRoundType();
       if ((other.size()!=this.size) || (super.getRoundType()!=4)){
       
           return false;
       } 
       if (other.getCard(0).getValue()>this.lastCardsPlayed.getCard(0).getValue()){
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
     * <b>Observer:</b> Checks if player has a better Pairs with wish in his cards
     * <b>Postcondition:</b> returns true if a player can play, else false
     * (because he has a better Pairs with wish but he didnt played it
     * @return true if a player can play, else false
     */
    @Override
    public boolean euxiMeMahjong(Sullogi other){
           int count=0;
           
           ArrayList <Integer> temp=new ArrayList<Integer>();
           Sullogi temp2=new Sullogi();
           Sullogi temp3=new Sullogi();
           Sullogi temp4=new Sullogi();
           Card wish=new SimpleCard(null,2);
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
               if (super.GetPlayerAllCards().getCard(i).getValue()==super.GetEuxi()
                       || super.GetPlayerAllCards().getCard(i).getValue()==15)
               {
                   if (count==0){
                            wish=super.GetPlayerAllCards().getCard(i);
                   }
                   count++;
                   
               }
           } 
           if (count<2){
               return true;
           }
           for (int i=0;i<super.GetPlayerAllCards().size()-1;i++){
               
                    
                    if ((super.GetPlayerAllCards().getCard(i).getValue()==super.GetPlayerAllCards().getCard(i+1).getValue())
                        && (temp.contains(super.GetPlayerAllCards().getCard(i).getValue())==false)){
                        temp.add(super.GetPlayerAllCards().getCard(i).getValue());
                        temp2.addCard(super.GetPlayerAllCards().getCard(i));
                        temp.add(super.GetPlayerAllCards().getCard(i+1).getValue());
                        temp2.addCard(super.GetPlayerAllCards().getCard(i+1));
                        
                    
                    
               }
           }
           for (int i=0;i<super.GetPlayerAllCards().size()-1;i++){
               if ((super.GetPlayerAllCards().getCard(i).getValue()>=2) &&(super.GetPlayerAllCards().getCard(i).getValue()<15) )    
               {
                   if (temp.contains(super.GetPlayerAllCards().getCard(i).getValue())==false){
                       temp3.addCard(super.GetPlayerAllCards().getCard(i));
                   }
               }
           }
           if ((super.GetPlayerAllCards().getCard(super.GetPlayerAllCards().size()-1).getValue()!=15)|| (count>=3)){
               if (temp2.size()<size){
                   return true;
               }
               for (int i=0;i<temp2.size()-size+1;i=i+2){
                   for (int j=i;j<i+size;j++){
                       temp4.addCard(temp2.getCard(j));
                   }
                   super.setCardsToPlay(temp4);
                   
                   if((temp4.getCards().contains(wish)==true)&&(super.checkPairs()==true)) {
                 if  ((temp4.getCard(0).getValue()>this.lastCardsPlayed.getCard(0).getValue())){
                   
                   return false;
                 }  
                 
               }
                 temp4.clearAll();  
               }
               
           }
           if (super.GetPlayerAllCards().getCard(super.GetPlayerAllCards().size()-1).getValue()==15){
               if (count==2){
                   for (int i=0;i<temp2.size()-size+3;i=i+2){
                   for (int j=i;j<i+size-2;j++){
                       temp4.addCard(temp2.getCard(j));
                   }
                   temp4.addCard(wish);
                   temp4.addCard(super.GetPlayerAllCards().getCard(super.GetPlayerAllCards().size()-1));
                   temp4.sort(0, temp4.size());
                   super.setCardsToPlay(temp4);
                   
                   if((temp4.getCards().contains(wish)==true)&&(super.checkPairs()==true)) {
                 if  ((temp4.getCard(0).getValue()>this.lastCardsPlayed.getCard(0).getValue())){
                   
                   return false;
                 }  
                 
               }
                 temp4.clearAll();  
               }
                }
               else if (count>=3){ 
                for (int i=0;i<temp2.size()-size+3;i=i+2){
                   for (int j=i;j<i+size-2;j++){
                       temp4.addCard(temp2.getCard(j));
                   }
                   temp4.addCard(super.GetPlayerAllCards().getCard(super.GetPlayerAllCards().size()-1));
                   for(int x=0;x<temp3.size();x++){
                   temp4.addCard(temp3.getCard(x));
                   
                   temp4.sort(0, temp4.size());
                   super.setCardsToPlay(temp4);
                   
                   if((temp4.getCards().contains(wish)==true)&&(super.checkPairs()==true)) {
                 if  ((temp4.getCard(0).getValue()>this.lastCardsPlayed.getCard(0).getValue())){
                   
                   return false;
                 }  
                 
               }
                   temp4.removeCard(temp3.getCard(x));
                   } 
                   temp4.clearAll();  
               }    
               
           }   
           }    
            return true;
   }   
}
