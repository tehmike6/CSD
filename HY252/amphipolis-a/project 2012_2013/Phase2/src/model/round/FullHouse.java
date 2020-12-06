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
public class FullHouse extends Round {
     private Sullogi lastCardsPlayed=new Sullogi();
     boolean haswish;
    /**
     * <b>constructor</b>: Constructs a new full house round with the given sullogi a
     * <b>postcondition</b>: creates a new full house round and save the last collection
     * that has been played to collection lastCardsPlayed
     * @param a the last collection which has been played
     */
    public FullHouse(Sullogi a){
       super.type=5;
       for (int i=0;i<a.size();i++){
            this.lastCardsPlayed.addCard(a.getCard(i));
        }
       super.lastCards(lastCardsPlayed);
    }
    
    
    /**
     * <b>transformer(mutative)</b>: This method sees if a full house collection that a player wants to play 
     * is better than the last full house Collection that has been played.So if it is better
     * return true and saves this collection to lastCardsPlayed,otherwise return false.<br />
     *  <b>precondition</b>:other must be a valid Full house collection <br />
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
       
      
       if (other.size()!=5 || super.checkFull()==false){
       
           return false;
       } 
       if ((other.getCard(4).getValue()==15) && (super.SelectPhoenixValue()==false)){
         
         return true;
      }
      
       if (other.getCard(2).getValue()>this.lastCardsPlayed.getCard(2).getValue()){
           this.lastCardsPlayed.clearAll();
           for (int i=0;i<5;i++){
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
     * <b>Observer:</b> Checks if player has a better full house with wish in his cards
     * <b>Postcondition:</b> returns true if a player can play, else false
     * (because he has a better full house with wish but he didnt played it
     * @return true if a player can play, else false
     */
    @Override
    public boolean euxiMeMahjong(Sullogi other){
           int count=0;
           
           ArrayList <Integer> temp=new ArrayList<Integer>();
           Sullogi Trifylla=new Sullogi();
           Sullogi Zeygi=new Sullogi();
           Sullogi EnaFullo=new Sullogi();
           Sullogi cardsForCheck=new Sullogi();
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
            if (super.GetPlayerAllCards().size()<5){
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
            for (int i=0;i<super.GetPlayerAllCards().size()-2;i++){
               if ((super.GetPlayerAllCards().getCard(i).getValue()>=2) &&(super.GetPlayerAllCards().getCard(i).getValue()<16) )    
               {
                    if ((super.GetPlayerAllCards().getCard(i).getValue()==super.GetPlayerAllCards().getCard(i+2).getValue())){
                    for (int j=0;j<3;j++) {   
                        temp.add(super.GetPlayerAllCards().getCard(i+j).getValue());
                        Trifylla.addCard(super.GetPlayerAllCards().getCard(i+j));
                    }
                    }
                   
               }
           }
            for (int i=0;i<super.GetPlayerAllCards().size()-1;i++){
               if ((super.GetPlayerAllCards().getCard(i).getValue()>=2) &&(super.GetPlayerAllCards().getCard(i).getValue()<16) )    
               {
                   if ((super.GetPlayerAllCards().getCard(i).getValue()==super.GetPlayerAllCards().getCard(i+1).getValue())){
                       Zeygi.addCard(super.GetPlayerAllCards().getCard(i));
                       temp.add(super.GetPlayerAllCards().getCard(i).getValue());
                       Zeygi.addCard(super.GetPlayerAllCards().getCard(i+1));
                       temp.add(super.GetPlayerAllCards().getCard(i+1).getValue());
                   }
               }
           } 
           for (int i=0;i<super.GetPlayerAllCards().size();i++){
               if ((super.GetPlayerAllCards().getCard(i).getValue()>=2) &&(super.GetPlayerAllCards().getCard(i).getValue()<16) )    
               {
                   if (temp.contains(super.GetPlayerAllCards().getCard(i).getValue())==false){
                       EnaFullo.addCard(super.GetPlayerAllCards().getCard(i));
                   }
               }
           } 
           
               if (((Trifylla.isEmpty()==true) || (Zeygi.isEmpty()==true)) 
                       &&((EnaFullo.isEmpty()==false) &&(EnaFullo.getCard(EnaFullo.size()-1).getValue()!=15))){
                    
                   return true;
               }
               for (int i=0;i<Trifylla.size();i=i+3){
                   for (int j=i;j<i+3;j++){
                       cardsForCheck.addCard(Trifylla.getCard(j));
                   }
                   for (int x=0;x<Zeygi.size()-1;x=x+2){
                       
                           if (cardsForCheck.getCards().contains(Zeygi.getCard(x))==false){
                                cardsForCheck.addCard(Zeygi.getCard(x));
                                 cardsForCheck.addCard(Zeygi.getCard(x+1));
                           
                           
                           
                           newr.setCardsToPlay(cardsForCheck);
                           cardsForCheck.sort(0, cardsForCheck.size());
                        if((cardsForCheck.getCards().contains(wish)==true)&&(newr.checkFull()==true)) {
                        if  ((cardsForCheck.getCard(2).getValue()>this.lastCardsPlayed.getCard(2).getValue())){
                                 
                                return false;
                 }  
                       }
                        
                        
                       cardsForCheck.removeCard(Zeygi.getCard(x+1));
                        cardsForCheck.removeCard(Zeygi.getCard(x));
                   }
                   }
                   cardsForCheck.clearAll();
           }
               
           if (EnaFullo.getCard(EnaFullo.size()-1).getValue()==15){
                
               if ((Trifylla.isEmpty()==false) && (EnaFullo.isEmpty()==false)){
                     for (int i=0;i<Trifylla.size();i=i+3){
                            cardsForCheck.addCard(Trifylla.getCard(i));
                            cardsForCheck.addCard(Trifylla.getCard(i+1));
                            cardsForCheck.addCard(Trifylla.getCard(i+2));
                            cardsForCheck.addCard(EnaFullo.getCard(EnaFullo.size()-1));
                            for (int j=0;j<EnaFullo.size()-1;j++){
                                cardsForCheck.addCard(EnaFullo.getCard(j));
                                cardsForCheck.sort(0, cardsForCheck.size());
                                newr.setCardsToPlay(cardsForCheck);
                                
                                
                                
                                if((cardsForCheck.getCards().contains(wish)==true)&&(newr.checkFull()==true)) {
                                    if  ((cardsForCheck.getCard(2).getValue()>this.lastCardsPlayed.getCard(2).getValue())){
                                             
                                          return false;
                                     }  
                                }
                                cardsForCheck.removeCard(EnaFullo.getCard(j));
                            }
                            cardsForCheck.clearAll();
                            newr.SetSelection(false);
                   }
                     
               }
               if ((Zeygi.isEmpty()==false)
                       && (EnaFullo.getCards().contains(wish)==false)) {
                    for (int x=0;x<Zeygi.size()-1;x=x+2){
                            cardsForCheck.addCard(Zeygi.getCard(x));
                            cardsForCheck.addCard(Zeygi.getCard(x+1));
                            cardsForCheck.addCard(EnaFullo.getCard(EnaFullo.size()-1));
                        for (int z=0;z<Zeygi.size()-1;z=z+2){
                             if (Zeygi.getCard(x).getValue()!=Zeygi.getCard(z).getValue()) {
                                cardsForCheck.addCard(Zeygi.getCard(z));
                                cardsForCheck.addCard(Zeygi.getCard(z+1)); 
                                cardsForCheck.sort(0, cardsForCheck.size());
                                newr.setCardsToPlay(cardsForCheck);
                                
                                
                                if((cardsForCheck.getCards().contains(wish)==true)&&(newr.checkFull()==true)) {
                                    if  ((cardsForCheck.getCard(2).getValue()>this.lastCardsPlayed.getCard(2).getValue())){
                                            
                                          return false;
                                     }  
                                }
                                cardsForCheck.removeCard(Zeygi.getCard(z));
                                cardsForCheck.removeCard(Zeygi.getCard(z+1));
                            }
                            
                             }
                        cardsForCheck.clearAll();
                        newr.SetSelection(false);
                    }
                    }
               }
           return true;
    }
           }
           
           

