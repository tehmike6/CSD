/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.round;

import java.util.Collections;
import model.Sullogi.Sullogi;
import model.card.CardColor;

/**
 * @version 1.0
 * @author dxanthak - mountanton
 */
public class Round {
    int type;
    private int euxi;
    private boolean roundStarted,selection,bomb_round,drache;
    private Sullogi cards = new Sullogi();
    private Sullogi roundcards=new Sullogi();
    private Sullogi Playerallcards=new Sullogi();
    private String[] PreviousMove;
    
    /**  Constructor.
     * 
     * <b>Postcondition</b>Creates a new Round with type=0, roundStarted=false,
     * bomb_round=false, selection=false
     */
    public Round (){
       euxi=0; 
       type=0;
       roundStarted=false;
       bomb_round=false;
       selection=false;
       this.drache=false;
   }

    

    /**
     * <b>transformer(mutative)</b>: Sets the Cards that a player wants to play
     * <b>Postcondition:</b> the cardsToPlay is set
     * @param cards
     */
    public void setCardsToPlay(Sullogi cards ){
       this.cards=cards;
   }
    
    
    /**
     *<b>transformer:</b> Sets the round 
     * <b>Postcondition:</b> the type of the round has been set
     */
    public void setRoundType(){
       if ((cards.size()>=4) && (checkBomb(cards)==true)){
           type=10;
       }
       
       else if ((cards.size()==1)&& (cards.getValue(0)==-1)){
           type=-1;
       }
       else if ((cards.size()==1)&& (cards.getValue(0)!=-1)){
           type=1;
       }
       else if ((cards.getCard(0).getValue()==-1) || (cards.getCard(cards.size()-1).getValue()==16)) {
           type=0;
           return;
       }
       else if ((cards.size()==2) && ((cards.getValue(0)==cards.getValue(1))||(cards.getValue(1)==15)) && (cards.getCard(0).getValue()!=1)){
           
           type=2;
       }
       else if ((cards.size()==3) && (cards.getValue(0)==cards.getValue(1)) && ((cards.getValue(1)==cards.getValue(2))||(cards.getValue(2)==15))) {
           type=3;
       }
       else if ((cards.size()>=4) && (cards.getCard(0).getValue()>1) && (cards.size()<=14) && (cards.size()%2==0) && (checkPairs()==true)){
           
           type=4;
       }
       else if (cards.size()>=5){
           
           if ((cards.size()==5)&&(checkFull()==true)) {
               type=5;
           }
           else if (checkStraight()==true){
               type=6;
           }    
       }
       else{
           type=0;
       }
   }
    
    
    /**
     *<b>Accessor:</b> Returns the round type
     * <b>Postcondition:</b> returns a valid round type
     * @return int type
     */
    public int getRoundType(){
       return type;
   }
    /**
     * <b>Observer:</b> Checks if Sullogi is full house
     * <b>Postcondition:</b> returns true if Sullogi is FullHouse, else false
     * @return true if Sullogi is FullHouse, else false
     */
    public boolean checkFull(){
       if (cards.getCard(0).getValue()==1){
               return false;
           }  
       if (cards.getCard(4).getValue()!=15){
        if ((cards.getCard(0).getValue()==cards.getCard(1).getValue()) && (cards.getCard(3).getValue()==cards.getCard(4).getValue()) 
            && ((cards.getCard(2).getValue()==cards.getCard(3).getValue()) || cards.getCard(2).getValue()==cards.getCard(1).getValue())){
               return true;
       }
       else if (cards.getCard(0).getValue()==15){
           return true;
       } 
       }
       
       else if ((cards.getCard(0).getValue()==cards.getCard(1).getValue())
               && (cards.getCard(2).getValue()==cards.getCard(3).getValue())) {
               return true;
            
        } 
       else if ((cards.getCard(1).getValue()==cards.getCard(3).getValue()) && (cards.getCard(4).getValue()==15)){
           this.selection=true;
           Collections.swap(cards.getCards(),0,4);
           cards.sort(1, 5);
           return true;
       }
       else if ((cards.getCard(0).getValue()==cards.getCard(2).getValue()) && (cards.getCard(4).getValue()==15)){
           this.selection=true;
           return true;
       }
       
       
       return false;
   }
    
    /**
     * <b>Observer:</b> Checks if Sullogi is Straight
     * <b>Postcondition:</b> returns true if Sullogi is Straight, else false
     * @return true if Sullogi is Straight, else false
     */
    public boolean checkStraight(){
        
        int i,j=0,temp=0;
        
        if (this.selection==true){
            
            return true;
        }
        if (cards.getCard(cards.size()-1).getValue()==15){ 
            for ( i=1;i<cards.size()-1;i++){
            if (cards.getCard(i).getValue()-cards.getCard(i-1).getValue()!=1){
                 temp=1;
            }
           
            }
           if (temp==0){
               if (cards.getCard(cards.size()-2).getValue()==14){
                   Collections.swap(cards.getCards(), 0, cards.size()-1);
                   cards.sort(1, cards.size());
                   this.selection=true;
                   
               }
               else if (cards.getCard(0).getValue()<=2){
                   this.selection=true;
                   
               }
               
               return true;
           }
           else{
               
               for ( i=1;i<cards.size();i++){
                    if ((cards.getCard(i).getValue()-cards.getCard(i-1).getValue()!=1) && (temp==1)){
                        Collections.swap(cards.getCards(), i, cards.size()-1);
                        cards.sort(i+1, cards.size());
                        
                        
                        if ((temp==1) && (cards.getCard(i+1).getValue()-cards.getCard(i-1).getValue()!=2)){
                           
                            temp=0;
                            return false;
                        }
                        temp=0;
                        i++;
                    }
                    else if (cards.getCard(i).getValue()-cards.getCard(i-1).getValue()==1){
                        j++;
                    }
                    
               }    
               if (j!=cards.size()-3){
                   
                   
                   return false;
               }
               
           }
           
        }
        else{
        for (i=1;i<cards.size();i++){
            
           if (cards.getCard(i).getValue()-cards.getCard(i-1).getValue()!=1){
                
               return false;
           }
       }
        } 
       return true;
   }
  
    public boolean compareCollections(Sullogi other){
       return false;
   }

    
    /**
     * <b>Observer:</b> Checks if Sullogi is Pair
     * <b>Postcondition:</b> returns true if Sullogi is Pair, else false
     * @return true if Sullogi is Pair, else false
     */
    public boolean checkPairs(){
            int i=0;
            boolean temp=false;
           if (cards.getCard(0).getValue()==1){
               return false;
           } 
           if (cards.getCard(cards.size()-1).getValue()==15){
               while ((i<cards.size()) && (temp==false)){
                   if (cards.getCard(i).getValue()!=cards.getCard(i+1).getValue()){
                       Collections.swap(cards.getCards(), i+1, cards.size()-1);
                       cards.sort(i+2, cards.size());
                       temp=true;
                       i=i+2;
                   }
                   else{
                       i=i+2;
                   }
           }
           }
               
           for (int j=i;j<cards.size();j+=2){
               if (cards.getCard(j).getValue()!=cards.getCard(j+1).getValue()){
                   
                   
                   cards.sort(0, cards.size());
                   return false;
               }
           }
           
           for (int k=0;k<cards.size()-2;k+=2){
               if (cards.getCard(k+2).getValue()-cards.getCard(k).getValue()!=1){
                    
           
                   cards.sort(0, cards.size());
                   return false;
               }
           }
           return true;
           
       
       
   }

    
    
    /**
     * <b>Observer:</b> Checks if round is bomb
     * <b>Postcondition:</b> returns true if round is bomb, else false
     * @return true if round is bomb, else false
     */    
    public boolean bomb_round(){
       return this.bomb_round;
   }
    
    
    /**
     *<b>transformer:</b> Sets the bomb round 
     * <b>Postcondition:</b> the type of the bomb round has been set true
     */
    public void Set_bomb_round(){
       this.bomb_round=true;
   }
    
    
    /**
     * <b>Observer:</b> Checks if Sullogi is bomb
     * <b>Postcondition:</b> returns true if Sullogi is bomb, else false
     * @param cards2
     * @return true if Sullogi is bomb, else false
     */ 
    public boolean checkBomb(Sullogi cards2){
        
        
       if ((cards2.getCard(0).getValue()<2) || (cards2.getCard(cards2.size()-1).getValue()>14)){
           
           return false;
       }
       if ((cards2.size()==4) && (cards2.getCard(0).getValue()==cards2.getCard(3).getValue())) {
           
           return true;
       } 
       if (cards2.size()>=5){
           for ( int i=1;i<cards2.size();i++){
           if (cards2.getCard(i).getValue()-cards2.getCard(i-1).getValue()!=1){
               return false;
           }
           
           if (cards2.getCard(i).getColor().toString().equals(cards2.getCard(i-1).getColor().toString())==false){
               
               return false;
           }
           }
           for ( int i=0;i<cards2.size();i++){
           if (cards2.getCard(i).getValue()==euxi){
               
           }
           }
           
           
       
        return true;   
       }    
       return false;
   }

    
    /**
     *<b>transformer:</b> Starts the round
     * <b>Postcondition:</b> the round has been started
     */    
    public void roundStarted(){
       this.roundStarted=true;
   }
    
    
    /**
     * <b>Observer:</b> Checks if Round has started
     * <b>Postcondition:</b> returns true if Round has started, else false
     * @return true if Round has started, else false
     */ 
    public boolean getRoundStarted(){
      return this.roundStarted;
   }
    
    
    /**
     *<b>transformer:</b> Updates the cards that have been played in this round 
     * <b>Postcondition:</b> the roundcards have been updated
     * @param other
     */
    public void Update_Cards(Sullogi other){
       for (int i=0;i<other.size();i++){
           this.roundcards.addCard(other.getCard(i));
       }
   }
    
    
    /**
     *<b>Accessor:</b> Returns the cards that have been played in this round
     * <b>Postcondition:</b> returns the cards that have been played in this round
     * @return Sullogi roundcars
     */
    public Sullogi GetRoundCards(){
       return this.roundcards;
   }
    
    
    /**
     * <b>transformer:</b> Used in types: FullHouse or Straight
     * If a player have selected a value for phoenix, set selection variable to true
     * <b>Postcondition:</b> selection has been set to true
     */
    public void SetSelection(boolean b){
       this.selection=b;
   }
    
    
    /**
     * <b>Observer:</b> Used in types: FullHouse or Straight
     * Returns true if a player has made a selection for phoenix value, otherwise false
     * <b>Postcondition:</b> return true if a player has made a selection for phoenix value, otherwise false
     * @return true if a player has made a selection for phoenix value, otherwise false
     */ 
    public boolean SelectPhoenixValue(){
       return this.selection;
       }
   /**
     * <b>transformer:</b> if this round has drache set this.drache to true
     * <b>Postcondition:</b>  if this round has drache set this.drache to true
     */ 
   public void SetDracheInRound(){
       this.drache=true;
   }
   
    /**
     * <b>Observer:</b> Returns true if there is drache in round ,otherwise false
     * <b>Postcondition:</b> return true if there is drache in round ,otherwise false
     * @return true if there is drache in round ,otherwise false
     */ 
   public boolean DracheInRound(){
       return this.drache;
   }
   /**
     * <b>transformer:</b> saves the last Cards that have been played
     * <b>Postcondition:</b> saves the last Cards that have been played
     */ 
   public void lastCards(Sullogi lastCards){
       PreviousMove = new String[lastCards.size()];
       for (int j=0;j<lastCards.size();j++){
           PreviousMove[j]=lastCards.getCard(j).toString();
       }
      
   }
   /**
     *<b>Accessor:</b> Returns the last cards that have been played as a string array
     * <b>Postcondition:</b> returns the last cards that have been played as a string array
     * @return  the last cards that have been played as a string array
     */
   public String [] GetlastCards(){
       
       return PreviousMove;
   }
   /**
     * <b>transformer:</b> sets euxi to k
     * <b>Postcondition:</b> sets euxi to k
    * @param int k
     */ 
   public void SetEuxi(int k){
       euxi=k;
   }
   /**
     *<b>Accessor:</b> Returns the wish(euxi)
     * <b>Postcondition:</b> returns the wish(euxi)
     * @return the wish(euxi)
     */
   public int GetEuxi(){
       return this.euxi;
   }
   /**
     * <b>Observer:</b> Returns true if there is a card with euxi value in cards
     * <b>Postcondition:</b> return  true if there is a card with euxi value in cards ,otherwise false
     * @return  true if there is a card with euxi value in cards ,otherwise false
     */
   public boolean CheckEuxi(){
       for (int i=0;i<cards.size();i++){
           if (cards.getCard(i).getValue()==euxi){
               return true;
           }
       }
       
       for (int j=0;j<Playerallcards.size();j++){
           
           if (Playerallcards.getCard(j).getValue() ==euxi){
               
               return false;
           }
       }
       return true;
       
   }
   /**
     * <b>Observer:</b> Returns true if there is a bomb with euxi value in cards
     * <b>Postcondition:</b> return true if there is a bomb with euxi value in cards ,otherwise false
     * @return true if there is a bomb with euxi value in cards,otherwise false
    *  @param Sullogi a
     */
   public boolean BombHasEuxi(Sullogi a){
       for (int j=0;j<a.size();j++){
           if (a.getCard(j).getValue() ==euxi){
               
               return true;
           }
       }
       return false;
   }
    /**
     * <b>transformer:</b> sets players all cards to Sullogi Playerallcards
     * <b>Postcondition:</b> sets players all cards to Sullogi Playerallcards
     * @param Sullogi a
     */ 
   public void SetPlayerAllCards(Sullogi a){
       this.Playerallcards=a;
   }
    /**
     *<b>Accessor:</b> Returns all the cards of a player
     * <b>Postcondition:</b> returns all the cards of a player
     */
   public Sullogi GetPlayerAllCards(){
       return this.Playerallcards;
   }
   
   public boolean euxiMeMahjong(Sullogi other){
       return true;   
   }
   /**
	 * <b>transformer(mutative)</b>: check if a wish has played and set euxi value to 0 <br />
	 * <p><b>Postcondition:</b> check if a wish has played and set euxi value to 0 </p>
	 *
     */
    public void  SetIfWishChanged(){}
    /**
     * <b>Observer:</b> Returns true if there is a bomb with euxi value in player all cards
     * <b>Postcondition:</b> return true if there is a bomb with euxi value in player all cards ,otherwise false
     * @return true if there is a bomb with euxi value in player cards,otherwise false
     */
    public boolean EuxiMeBomb(){
        int count=0;
        boolean bluewish=false,blackwish=false,redwish=false,greenwish=false;
        Sullogi blacks=new Sullogi();
        Sullogi blues=new Sullogi();
        Sullogi greens=new Sullogi();
        Sullogi reds=new Sullogi();
        Sullogi temp=new Sullogi();
        if (this.Playerallcards.size()<4){
            return true;
        }
        for (int i=0;i<this.Playerallcards.size();i++){
            if (this.Playerallcards.getCard(i).getValue()==euxi){
                count++;
            }
        }
        if (count==4){
            return false;
        }
        if (this.Playerallcards.size()<5){
            return true;
        }
         for (int i=0;i<this.Playerallcards.size();i++){
            if (this.Playerallcards.getCard(i).getColor()==CardColor.BLACK){
               
                blacks.addCard(this.Playerallcards.getCard(i));
                if (this.Playerallcards.getCard(i).getValue()==euxi){
                    blackwish=true;
                }
            }
            else if (this.Playerallcards.getCard(i).getColor()==CardColor.BLUE){
                blues.addCard(this.Playerallcards.getCard(i));
                if (this.Playerallcards.getCard(i).getValue()==euxi){
                    bluewish=true;
                }
            }
            else if (this.Playerallcards.getCard(i).getColor()==CardColor.GREEN){
                greens.addCard(this.Playerallcards.getCard(i));
                if (this.Playerallcards.getCard(i).getValue()==euxi){
                    greenwish=true;
                }
            }
            else if (this.Playerallcards.getCard(i).getColor()==CardColor.RED){
                reds.addCard(this.Playerallcards.getCard(i));
                if (this.Playerallcards.getCard(i).getValue()==euxi){
                    redwish=true;
                }
            }
        }
        if ((blues.size()>=5) && (bluewish==true)){
            for (int i=0;i<blues.size()-5+1;i++){
                bluewish=false;
               for (int j=i;j<i+5;j++){
                   if (blues.getCard(j).getValue()==euxi){
                       bluewish=true;
                   }
                    temp.addCard(blues.getCard(j));
               }    
               setCardsToPlay(temp);
              
                if ((bluewish==true) && (checkStraight()==true)){
                    return false;
                }
               temp.clearAll();
               
            }
        }
        if ((blacks.size()>=5) && (blackwish==true)){
            for (int i=0;i<blacks.size()-5+1;i++){
                blackwish=false;
               for (int j=i;j<i+5;j++){
                   if (blacks.getCard(j).getValue()==euxi){
                       blackwish=true;
                   }
                    temp.addCard(blacks.getCard(j));
               }    
               setCardsToPlay(temp);
               
                if ((blackwish==true) && (checkStraight()==true)){
                    return false;
                }
               temp.clearAll();
               
            }
        }
        if ((greens.size()>=5) && (greenwish==true)){
            for (int i=0;i<greens.size()-5+1;i++){
                greenwish=false;
               for (int j=i;j<i+5;j++){
                   if (greens.getCard(j).getValue()==euxi){
                       greenwish=true;
                   }
                    temp.addCard(greens.getCard(j));
               }    
               setCardsToPlay(temp);
                if ((greenwish==true) && (checkStraight()==true)){
                    return false;
                }
               temp.clearAll();
               
            }
        }
        if ((reds.size()>=5) && (redwish==true)){
            for (int i=0;i<reds.size()-5+1;i++){
                redwish=false;
               for (int j=i;j<i+5;j++){
                   if (reds.getCard(j).getValue()==euxi){
                       redwish=true;
                   }
                    temp.addCard(reds.getCard(j));
               }    
               setCardsToPlay(temp);
                if ((redwish==true) && (checkStraight()==true)){
                    return false;
                }
               temp.clearAll();
               
            }
        }
        return true;
    }
}

