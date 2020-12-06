
package controller;

import java.util.ArrayList;
import java.util.Collections;
import model.Sullogi.Sullogi;
import model.player.Player;
import model.player.Team;
import model.round.Bomb;
import model.round.FullHouse;
import model.round.Monofyllia;
import model.round.Pairs;
import model.round.Round;
import model.round.SimplePair;
import model.round.Straight;
import model.round.Tripleta;
import model.turn.Turn;
import view.WishDialog;
import view.MenuDialog;

/**
 * @version 1.0
 * @author dxanthak - mountanton
 */
public class Controller {
    private int fold,isready,points,isready2,euxi;
    private boolean notstarted,empty_table,new_round,collectionHasPlayed,antallages_finished;
    private Team team1=new Team("team1",0);
    private    Team team2=new Team("team2",0);
    private    Player P1=new Player("player1",team1,0);
    private    Player P3=new Player("player3",team1,2);
    private    Player P2=new Player("player2",team2,1);
    private    Player P4=new Player("player4",team2,3);
    private    ArrayList <Player> players=new ArrayList<Player>();
    private    ArrayList <Player> winners=new ArrayList<Player>();
    private     Turn turn=new Turn();
    public Round round =new Round();
    private   String wish=new String();
    private Sullogi allcards = new Sullogi();
    private Sullogi cardsToPlay = new Sullogi();
    private  Sullogi round_bomb=new Sullogi();
    /**
	 * <b>constructor</b>: Constructs a new Controller and sets the game as 
	 * eligible to start .<br />
	 * <b>postcondition</b>: constructs a new Controller,with new 4 players,new 
         * instances of Turn Class , Round Class and Sullogi Class and initialize
          * some int or boolean variables.So,is responsible for creating a new game and 
	 * initializing it.
	 */
        public Controller(){
            players.add(P1);
            players.add(P2);
            players.add(P3);
            players.add(P4);
            allcards.init_cards();
            
            this.empty_table=true;
            this.notstarted=true;
            this.fold=0;
            this.isready=0;
            this.isready2=0;
            this.collectionHasPlayed=false;
            antallages_finished=false;
         
        }
        
       
         /*
	 *<b>Observer</b>: Return true if the collection that player wanted to play , 
         * was finally played ,false otherwise
	 * <p><b>Postcondition:</b> return true if the collection that player 
         * wanted to play , was finally played, false otherwise
	 * </p>
	 *
	 * @return true if the collection that player 
         * wanted to play , was finally played, false otherwise
	 */
     
    public boolean GetCollectionHasPlayed(){
                 
                return this.collectionHasPlayed;
        }

   /**
	 * <b>transformer(mutative)</b>:take an integer arraylist from view Class (there are 56 buttons and 56 cards),
         * so for example button with id 0 show the card in position 0 in allcards collection.After that
         * check if these cards  can be played and if this true, set the boolean variable collectionHasPlayed to true.
	 * <p><b>Postcondition:</b>  set the collectionHasPlayed  to true if the collection played, otherwise false </p>
         * @param cardsPosition the position of the cards in allcards(Sullogi with all the 56 cards) that player wants to play 
  	 */ 
    public void PlayCollection(ArrayList<Integer> cardsPosition){
        int x;
        
        for (int i=0;i<cardsPosition.size();i++){
            cardsToPlay.addCard(allcards.getCard(cardsPosition.get(i)));
        }
        cardsToPlay.sort(0, cardsPosition.size());
        
        players.get(turn.getID()).getCards_to_play();
        round.SetPlayerAllCards(players.get(turn.getID()).getCards());
        if (round.getRoundStarted()==false){
            
            round.setCardsToPlay(cardsToPlay);
            if (euxi!=0){
                round.SetEuxi(euxi);   
            }
            if(round.CheckEuxi()==false){  
                cardsToPlay.clearAll();
                this.collectionHasPlayed=false;
                return;
            }
            round.setRoundType();
            if (round.getRoundType()==-1){
                players.get(turn.getID()).getCards().removeCard(allcards.getCard(cardsPosition.get(0)));
                setHundTurn();
                cardsToPlay.clearAll();
                this.collectionHasPlayed=true;
            }
            else if (round.getRoundType()==1){
                round=(Round) new Monofyllia(cardsToPlay.getCard(0));
                if (cardsToPlay.getCard(0).getValue()==1){
                    WishDialog rr=new WishDialog();
                    wish=rr.choice();
                    if (rr.choice().equals("no")){
                        
                    }
                    else if (rr.choice().equals("J")==true)
                        {
                        round.SetEuxi(11);
                    } 
                    else if (rr.choice().equals("Q")==true){
                        round.SetEuxi(12);
                    }
                    else if (rr.choice().equals("K")==true){
                        round.SetEuxi(13);
                    }
                    else if (rr.choice().equals("A")==true){
                        round.SetEuxi(14);
                    }
                    else{
                        round.SetEuxi(Integer.parseInt(rr.choice()));
                    }
                    
                    
                }
                makeChanges(cardsPosition);
                this.collectionHasPlayed=true;
            }
            else if (round.getRoundType()==10){
                round=(Round) new Bomb(cardsToPlay);
               
                makeChanges(cardsPosition);
                this.collectionHasPlayed=true;
            }
            else if (round.getRoundType()==2){
                round=(Round) new SimplePair(cardsToPlay);
                makeChanges(cardsPosition);
                this.collectionHasPlayed=true;
            }
            else if (round.getRoundType()==3){
                round=(Round) new Tripleta(cardsToPlay);
                makeChanges(cardsPosition);
                this.collectionHasPlayed=true;
            }
            else if (round.getRoundType()==4){
                round=(Round) new Pairs(cardsToPlay,cardsPosition.size());
                makeChanges(cardsPosition);
                this.collectionHasPlayed=true;
            }
             else if (round.getRoundType()==5){
                if ((cardsToPlay.getCard(4).getValue()==15) && (round.SelectPhoenixValue()==false)){
                     MenuDialog J=new MenuDialog(cardsToPlay.getCard(0).getValue(),cardsToPlay.getCard(3).getValue(),
                            "Which Card do you want phoenix to be?","Phoenix Value" );
                     while (J.choice()==0){
                         J=new MenuDialog(cardsToPlay.getCard(0).getValue(),cardsToPlay.getCard(3).getValue(),
                                "Which Card do you want phoenix to be?","Phoenix Value" );
                     }
                     if (J.choice()==1){
                         Collections.swap(cardsToPlay.getCards(),0,4);
                         cardsToPlay.sort(1, 5);
                     }
                }
                
                round=(Round) new FullHouse(cardsToPlay);
                
                makeChanges(cardsPosition);
                this.collectionHasPlayed=true;
            }
             else if (round.getRoundType()==6){
                 if ((cardsToPlay.getCard(cardsToPlay.size()-1).getValue()==15)&&
                    (round.SelectPhoenixValue()==false)){
                     MenuDialog J=new MenuDialog(cardsToPlay.getCard(0).getValue()-1,cardsToPlay.getCard(cardsToPlay.size()-2).getValue()+1,
                             "Which Card do you want phoenix to be?","Phoenix Value");
                     while (J.choice()==0){
                         J=new MenuDialog(cardsToPlay.getCard(0).getValue(),cardsToPlay.getCard(3).getValue(),
                                "Which Card do you want phoenix to be?","Phoenix Value" );
                     }
                     if (J.choice()==1){
                         Collections.swap(cardsToPlay.getCards(),0,cardsToPlay.size()-1);
                         cardsToPlay.sort(1, cardsToPlay.size());
                     }
                }
                 round=(Round) new Straight(cardsToPlay,cardsToPlay.size());
                  if (cardsToPlay.getCard(0).getValue()==1){
                    WishDialog rr=new WishDialog();
                    
                    while (rr.choice()==null){
                        rr=new WishDialog();
                    }
                    wish=rr.choice();
                    if (rr.choice().equals("no")){
                        
                    }
                    else if (rr.choice().equals("J")==true)
                        {
                        round.SetEuxi(11);
                    } 
                    else if (rr.choice().equals("Q")==true){
                        round.SetEuxi(12);
                    }
                    else if (rr.choice().equals("K")==true){
                        round.SetEuxi(13);
                    }
                    else if (rr.choice().equals("A")==true){
                        round.SetEuxi(14);
                    }
                    else{
                        round.SetEuxi(Integer.parseInt(rr.choice()));
                    }
                    
                    
                    
                }
                makeChanges(cardsPosition);
                this.collectionHasPlayed=true;
             }
             else{
                 cardsToPlay.clearAll();
                 this.collectionHasPlayed=false;
             }
           if (round.GetEuxi()!=0){
            euxi=round.GetEuxi();
            
            }
           else if(euxi!=0){
            round.SetEuxi(euxi); 
            round.SetIfWishChanged();
           }
           
        }
        else if ((round.bomb_round()==false) && 
                (euxi==0 || round.BombHasEuxi(cardsToPlay)==true || round.euxiMeMahjong(new Sullogi())==true ) && round.checkBomb(cardsToPlay)==true){   
                if (players.get(turn.getID()).Has_Played()==false){
                       players.get(turn.getID()).Played();
                   }
                for (int i=0;i<round.GetRoundCards().size();i++){
                    round_bomb.addCard(round.GetRoundCards().getCard(i));
                }
                round=(Round) new Bomb(cardsToPlay);
                round.Update_Cards(round_bomb);
                round.Update_Cards(cardsToPlay);
                round.roundStarted();
                 for (int i=0;i<cardsPosition.size();i++){
                    players.get(turn.getID()).getCards().removeCard(allcards.getCard(cardsPosition.get(i)));
                 }
                  if (turn.checkIfPlayerFinished(players.get(turn.getID()))==true){
                    turn.SetRoundPlayers(1);
                    players.get(turn.getID()).has_finished();
                    winners.add(players.get(turn.getID()));
                }
                else{
                    turn.SetRoundPlayers(0);
                }
               turn.Set_last_player(turn.getID());
               cardsToPlay.clearAll();
               turn.setID(players);
               this.fold=0;
               if (round.GetEuxi()!=0){
                     euxi=round.GetEuxi();
                    
                }
                else if(euxi!=0){
                    round.SetEuxi(euxi); 
                    round.SetIfWishChanged();
                }
               this.collectionHasPlayed=true;
        } 
        else if (round.compareCollections(cardsToPlay)==true){
               if ((round.getRoundType()==5)&&(cardsToPlay.getCard(4).getValue()==15) && (round.SelectPhoenixValue()==false)){
                     MenuDialog J=new MenuDialog(cardsToPlay.getCard(0).getValue(),cardsToPlay.getCard(3).getValue(),
                            "Which Card do you want phoenix to be?","Phoenix Value" );
                     while (J.choice()==0){
                         J=new MenuDialog(cardsToPlay.getCard(0).getValue(),cardsToPlay.getCard(3).getValue(),
                                 "Which Card do you want phoenix to be?","Phoenix Value");
                     }
                     if (J.choice()==1){
                         Collections.swap(cardsToPlay.getCards(),0,4);
                         cardsToPlay.sort(1, 5);
                     }
                     round.SetSelection(true);
                     
                     if (round.compareCollections(cardsToPlay)==false){
                         this.collectionHasPlayed=false;
                         cardsToPlay.clearAll();
                         round.SetSelection(false);
                         return;
                     }
                }
               if ((round.getRoundType()==6)&&(cardsToPlay.getCard(cardsToPlay.size()-1).getValue()==15)&&
                    (round.SelectPhoenixValue()==false)){
                     MenuDialog J=new MenuDialog(cardsToPlay.getCard(0).getValue()-1,cardsToPlay.getCard(cardsToPlay.size()-2).getValue()+1
                             ,"Which Card do you want phoenix to be?","Phoenix Value");
                     while (J.choice()==0){
                        J=new MenuDialog(cardsToPlay.getCard(0).getValue()-1,cardsToPlay.getCard(cardsToPlay.size()-2).getValue()+1
                             ,"Which Card do you want phoenix to be?","Phoenix Value");
                     }
                     if (J.choice()==1){
                         Collections.swap(cardsToPlay.getCards(),0,cardsToPlay.size()-1);
                         cardsToPlay.sort(1, cardsToPlay.size());
                     }
                     round.SetSelection(true);
                     if (round.compareCollections(cardsToPlay)==false){
                         this.collectionHasPlayed=false;
                         cardsToPlay.clearAll();
                         round.SetSelection(false);
                         return;
                     }
                }
               if (players.get(turn.getID()).Has_Played()==false){
                       players.get(turn.getID()).Played();
                   }
               round.Update_Cards(cardsToPlay);
               
               for (int i=0;i<cardsPosition.size();i++){
                    players.get(turn.getID()).getCards().removeCard(allcards.getCard(cardsPosition.get(i)));
                 }
               
               if (turn.checkIfPlayerFinished(players.get(turn.getID()))==true){
                    turn.SetRoundPlayers(1);
                    
                    players.get(turn.getID()).has_finished();
                    winners.add(players.get(turn.getID()));
                }
                else{
                    turn.SetRoundPlayers(0);
                }
               turn.Set_last_player(turn.getID());
               cardsToPlay.clearAll();
               turn.setID(players);
               this.fold=0;
               round.SetSelection(false);
               this.collectionHasPlayed=true;
           }
        
        else{
            cardsToPlay.clearAll();
            this.collectionHasPlayed=false;
        }
        if (round.GetEuxi()==0){
            euxi=0;
        }
        
    }
     /**
	 * <b>transformer(mutative)</b>: sets the turn when hund card played 
	 * <p><b>Postcondition:</b> sets the turn when hund card played  </p>
  	 */ 
    public void setHundTurn(){
       
               if (turn.checkIfPlayerFinished(players.get(turn.getID()))==true){
                    turn.SetRoundPlayers(1);
                    
                    players.get(turn.getID()).has_finished();
                    winners.add(players.get(turn.getID()));
                }
                else{
                    turn.SetRoundPlayers(0);
                }
               turn.Set_last_player(turn.getID());
               cardsToPlay.clearAll();
        if (turn.GetNumberOfPlayers()==3){
            if (turn.getID()<3){
               if  (players.get(turn.getID()+1).Get_has_finished()==true){
                   turn.setID(players);
                   return;
               }
            }
            else if (turn.getID()==3){
               if  (players.get(0).Get_has_finished()==true){
                   turn.setID(players);
                   return;
               } 
            }
                   
        }
        else if (turn.GetNumberOfPlayers()==2){
            if (turn.getID()>0){
               if  (players.get(turn.getID()-1).Get_has_finished()==false){
                   turn.setID(players);
                   return;
               }
            }
             else if (turn.getID()==0){
               if  (players.get(3).Get_has_finished()==false){
                   turn.setID(players);
                   return;
               } 
            }
        }
        turn.setID(players);
        turn.setID(players);
    }
      /**
	 * <b>accessor(selector)</b>:Returns which player has the turn <br />
	 * 
	 * <p><b>Postcondition:</b> Returns which player has the turn </p>
	 *
	 * @return which player has the turn (for example 0 if p1 has the turn )
	 */
    public int seeTurn(){
        return turn.getID();
}
     /**
	 * <b>transformer(mutative)</b>:if a player press the button fold ,it increases
         * the variable fold (fold++) or sets the variable fold to 0 if a round finished
         * and of course gives the turn to next player
	 * <p><b>Postcondition:</b> increase the fold variable(fold++) 
         * or if a round finished sets the fold to 0 and of course give the turn to next player</p>
  	 */ 
    public boolean set_Fold()
    {
         round.SetPlayerAllCards(players.get(turn.getID()).getCards());
         if ((euxi!=0) &&  (round.euxiMeMahjong(cardsToPlay)==false)){
             return false;
         }
         if (this.fold!=turn.Get_Round_Players()-2){
            this.fold++;
             turn.setID(players);
             
        }
        else if (this.fold==turn.Get_Round_Players()-2){
            
             turn.setID(players);
            this.new_round=true;
             setmpaza();   
            
            this.fold=0;
            //cardsToPlay.cards.clear();
            round=new Round();
            this.empty_table=true;
             turn.SetRoundPlayers(0);
        }
         return true;
    }
    /*
	 *<b>Observer</b>: Return true if the table is empty false otherwise
         * (table:the space in which the player drop their cards)
	 * <p><b>Postcondition:</b> return  true if the table is empty 
         * false otherwise
	 * @return true if the table is empty false otherwise
	 */
  
    public boolean tableIsEmpty(){
        
        return this.empty_table;
    }
     /**
	 * <b>transformer(mutative)</b>: sets the variable not_started to false
	 * <p><b>Postcondition:</b>  sets the variable not_started to false</p>
  	 */ 
    public void set_started(){
        this.notstarted=false;
    }
      /*
	 *<b>Observer</b>: Return true if the game has not started  false otherwise
	 * <p><b>Postcondition:</b> return true if the game  has not started  false otherwise
	 * @return true if the game has not started  false otherwise
	 */
    /**
     *
     * @return
     */
    public boolean not_started(){
        return this.notstarted;
    }
     /**
	 * <b>accessor(selector)</b>:Returns the number of players 
         * which are ready for dealing the last 6 cards<br />
	 * <p><b>Postcondition:</b> returns the number of players 
         * which are ready for dealing the last 6 cards </p>
	 *
	 * @return the number of players which are ready for dealing the last 6 cards
	 */
    public int Get_isready(){
        return isready;
    }
     /**
	 * <b>transformer(mutative)</b>: increases the variable isready by 1(isready++)
	 * <p><b>Postcondition:</b>  increases the variable isready by 1(isready++)</p>
  	 */ 
    public void isready(){
        turn.setID(players);
        isready++;
    }
         /**
	 * <b>transformer(mutative)</b>: sort the cards for each player 
	 * <p><b>Postcondition:</b> sort the cards for each player  </p>
  	 */ 
    public void sort_cards(){
        allcards.sort(0,14);
        allcards.sort(14,28);
        allcards.sort(28,42);
        allcards.sort(42,56);
    }
    /**
	 * <b>transformer(mutative)</b>: set Grand Tichu for a player if a player push Grand Tichu button
	 * <p><b>Postcondition:</b> set Grand Tichu for a player if a player push Grand Tichu button </p>
  	 */ 
    public void setGrandTichu(){
        players.get(turn.getID()).GrandTichu();
        turn.setID(players);
        isready++;
    }
    /**
	 * <b>transformer(mutative)</b>: set Tichu for a player if a player push Tichu button 
	 * <p><b>Postcondition:</b> set Tichu for a player if a player push Tichu button if he hasnt played yet </p>
  	 */ 
    public boolean setTichu(){
       if ((players.get(turn.getID()).Has_Played()==false) && (players.get(turn.getID()).getChoice()==0)){
           
           players.get(turn.getID()).Tichu();
           return true;
       }
       return false;
    }
    /**
	 * <b>transformer(mutative)</b>: set the score of a game after one deal(partida) has finished
	 * <p><b>Postcondition:</b>  set the score of a game after one deal(partida) has finished </p>
  	 */ 
    public void setScore(){
        setmpaza();
        int pointsA=0,pointsB=0;
        boolean ena_duo=true;
        if ((winners.get(0).getID()==0) && (winners.get(1).getID()==2)){
                pointsA=200+ P1.getChoice()*100-P3.getChoice()*100;
                pointsB=0-P2.getChoice()*100-P4.getChoice()*100;
           }
        else if ((winners.get(0).getID()==2) && (winners.get(1).getID()==0)){
                pointsA=200+ P3.getChoice()*100-P1.getChoice()*100;
                pointsB=0-P2.getChoice()*100-P4.getChoice()*100;
           }
        else if ((winners.get(0).getID()==1) && (winners.get(1).getID()==3)){
                pointsB=200+ P2.getChoice()*100-P4.getChoice()*100;
                pointsA=0-P1.getChoice()*100-P3.getChoice()*100;
           }
        else if ((winners.get(0).getID()==3) && (winners.get(1).getID()==1)){
                
                pointsB=200+ P4.getChoice()*100-P2.getChoice()*100;
                pointsA=0-P1.getChoice()*100-P3.getChoice()*100;
           }
        else{
            ena_duo=false;
            for (int i=0;i<winners.get(0).get_mpaza().size();i++){
                winners.get(0).getTeam().setPoints(winners.get(0).get_mpaza().getCard(i).getPoints());
            }
            for (int i=0;i<winners.get(1).get_mpaza().size();i++){
                winners.get(1).getTeam().setPoints(winners.get(1).get_mpaza().getCard(i).getPoints());
            }
            for (int i=0;i<winners.get(2).get_mpaza().size();i++){
                winners.get(2).getTeam().setPoints(winners.get(2).get_mpaza().getCard(i).getPoints());
            }
            for (int i=0;i<winners.get(3).get_mpaza().size();i++){
                winners.get(0).getTeam().setPoints(winners.get(3).get_mpaza().getCard(i).getPoints());
                
            }
            for (int i=0;i<winners.get(3).getCards().size();i++){
                winners.get(2).getTeam().setPoints(winners.get(3).getCards().getCard(i).getPoints());
                
            }
            winners.get(0).getTeam().setPoints(winners.get(0).getChoice()*100);
            winners.get(1).getTeam().setPoints(winners.get(1).getChoice()*(-100));
            winners.get(2).getTeam().setPoints(winners.get(2).getChoice()*(-100));
            winners.get(3).getTeam().setPoints(winners.get(3).getChoice()*(-100));
        }
        if (ena_duo==true){
                team2.setPoints(pointsB);
             team1.setPoints(pointsA);
        }
    }
    /**
	 * <b>accessor(selector)</b>:Returns the score of the game <br />
	 * <p><b>Postcondition:</b> returns the score of the game </p>
	 * @return the number of players which are ready for dealing the last 6 cards
	 */
    public String GetScore(){
        return "Score: "+ "Team A: "+team1.getPoints()+ " points" + " Team B: " + team2.getPoints() + " points";
    }
    /**
	 * <b>accessor(selector)</b>: Returns the wish as an integer. <br />
 	 * <b>postcondition</b>: Returns the wish as an integer 
	 * @return the wish as an integer
	 */
    public int euxiMeMahjong(){
        return euxi;
    }
    /**
	 * <b>accessor(selector)</b>: Returns the wish as a string. <br />
 	 * <b>postcondition</b>: Returns the wish as a string
	 * @return the wish as a string
	 */
    public String euxi(){
        return wish;
    }    
       /**
	 * <b>transformer(mutative)</b>: initializes players cards in the beggining
	 * <p><b>Postcondition:</b> initializes players cards in the beggining </p>
  	 */ 
    public void init_player_cards(){
        for (int i=0;i<4;i++){
            for(int j=0;j<14;j++){
                players.get(i).setCards(allcards.getCard(14*i+j));
            }
            players.get(i).getCards().sort(0, 14);
        }
    }
    
    /**
	 * <b>transformer(mutative)</b>: initializes some things(allcards,turn,round) for a new deal(partida)
	 * <p><b>Postcondition:</b>  initializes some things(allcards,turn,round) for a new deal(partida)</p>
  	 */ 
    public void init_table(){
            allcards.clearAll();
            allcards.init_cards();
            winners.clear();
            init_player_cards();
            this.turn=new Turn();
            this.empty_table=true;
            this.notstarted=true;
            this.fold=0;
            this.isready=0;
            this.isready2=0;
            this.collectionHasPlayed=false;
            antallages_finished=false;
            euxi=0;
            players.get(0).init_player();
            players.get(1).init_player();
            players.get(2).init_player();
            players.get(3).init_player();
            round=new Round();
    }
    
    public void setmpaza(){
        if (round.DracheInRound()==true){
                    if (turn.Get_last_player()==0 ||turn.Get_last_player()==2){
                        if (P2.Get_has_finished()==true){
                            P4.set_mpaza(round.GetRoundCards());
                        }
                        else if (P4.Get_has_finished()==true){
                            P2.set_mpaza(round.GetRoundCards());
                        }
                        else{
                           MenuDialog J=new MenuDialog(players.get(3).getName(),players.get(1).getName(),
                                       "Which Player do you want to take Drache?","Drache choice");
                           while (J.choice()==0){
                               J=new MenuDialog(players.get(3).getName(),players.get(1).getName(),
                                       "Which Player do you want to take Drache?","Drache choice");
                           }   
                            if (J.choice()==1){
                                players.get(3).set_mpaza(round.GetRoundCards());
                             }
                             else if (J.choice()==2){
                                    players.get(1).set_mpaza(round.GetRoundCards());
                            }
                        }
                    }
                    else  if (turn.Get_last_player()==1 ||turn.Get_last_player()==3){
                        if (P1.Get_has_finished()==true){
                            P3.set_mpaza(round.GetRoundCards());
                        }
                        else if (P3.Get_has_finished()==true){
                            P1.set_mpaza(round.GetRoundCards());
                        }
                        else{
                            MenuDialog J=new MenuDialog(players.get(0).getName(),players.get(2).getName(),
                                       "Which Player do you want to take Drache?","Drache choice");
                            while (J.choice()==0){
                              J=new MenuDialog(players.get(0).getName(),players.get(2).getName(),
                                       "Which Player do you want to take Drache?","Drache choice");
                           }   
                            if (J.choice()==1){
                                players.get(0).set_mpaza(round.GetRoundCards());
                             }
                             else if (J.choice()==2){
                                    players.get(2).set_mpaza(round.GetRoundCards());
                            }
                        }
                    }
                }
                else{
                    players.get(turn.Get_last_player()).set_mpaza(round.GetRoundCards());
                }
    }
    /*
	 *<b>Observer</b>: Return true if we have a new round false otherwise
	 * <p><b>Postcondition:</b> return true if we have a new round false otherwise
	 * </p>
	 * @return true if we have a new round false otherwise
	 */
   
    public boolean Get_new_round(){
        return this.new_round;
    }
    /**
	 * <b>transformer(mutative)</b>: make changes after a round started
	 * <p><b>Postcondition:</b> make changes after a round started </p>
         * 
         * @param cardsPosition 
         */ 
    public void makeChanges(ArrayList<Integer> cardsPosition){
                this.new_round=false;
                round.roundStarted();
                   if (players.get(turn.getID()).Has_Played()==false){
                       players.get(turn.getID()).Played();
                   }
                turn.Set_last_player(turn.getID());
                for (int i=0;i<cardsPosition.size();i++){
                    players.get(turn.getID()).getCards().removeCard(allcards.getCard(cardsPosition.get(i)));
                    
                 }
                 
                if (turn.checkIfPlayerFinished(players.get(turn.getID()))==true){
                    players.get(turn.getID()).has_finished();
                    winners.add(players.get(turn.getID()));
                    
                    turn.SetRoundPlayers(1);
                }
                else{
                    turn.SetRoundPlayers(0);
                }
                turn.setID(players);
                
                round.Update_Cards(cardsToPlay);
                cardsToPlay.clearAll();
                this.fold=0;
                this.empty_table=false;
    }
      /*
	 * <b>Observer</b>:Return true if a deal(partida) has finished, false otherwise
	 * <p><b>Postcondition:</b> return true if a deal(partida) has finished, false otherwise
	 * </p>
	 * @return true if a deal(partida) has finished, false otherwise
	 */

    public boolean partida_has_finished(){
        if ((players.get(0).Get_has_finished()==true) && (players.get(2).Get_has_finished()==true)){
            turn.setID(players);
            winners.add(players.get(turn.getID()));
            return true;
    }
        else if ((players.get(1).Get_has_finished()==true) && (players.get(3).Get_has_finished()==true)){
            turn.setID(players);
            winners.add(players.get(turn.getID()));
            
            return true;
        }
        return false;
    }
    /*
	 * <b>Observer</b>:Return true if a game(one team reaches 1000 points) has finished, false otherwise
	 * <p><b>Postcondition:</b> return true if a game(one team reaches 1000 points)  has finished, false otherwise
	 * </p>
	 * @return true if a game(one team reaches 1000 points) has finished, false otherwise
	 */
   
    public String game_has_finished(){
        if (team1.getPoints()>1000 || team2.getPoints()>1000){
            if (team1.getPoints()>team2.getPoints()){
                return "Team A won the game!!";
            }
            else{
                return "Team B won the game!!";
            }
    }
       return "no";
    }
     /**
	 * <b>transformer(mutative)</b>: give the turn to player who has mahjong
	 * <p><b>Postcondition:</b> give the turn to player who has mahjong in the beggining</p>
  	 */ 
    public void set_Turn(){
        for (int i=0;i<56;i++){
            if (allcards.getCard(i).getValue()==1){
                if (i<14){
                    
                }
                else if ((i>13)&&(i<28)){
                    turn.setID(players);
                }
                else if ((i>27)&&(i<42)){
                    turn.setID(players);
                    turn.setID(players);
                }
                else if ((i>41)){
                    turn.setID(players);
                    turn.setID(players);
                    turn.setID(players);
                }
            
            }
        }
      
    }
    /**
	 * <b>transformer(mutative)</b>: sets antallages_finished to true if exchanges have finished
	 * <p><b>Postcondition:</b> sets antallages_finished to true if exchanges have finished</p>
         * @param b boolean (true or false)
  	 */ 
    public void Setantallages(boolean b){
        this.antallages_finished=b;
    }
      /*
	 *<b>Observer</b>: Return true if exchanges have finished false otherwise
	 * <p><b>Postcondition:</b> return true if exchanges have finished false otherwise
	 * </p>
	 * @return true if exchanges have finished false otherwise
	 */
    public boolean antallages(){
        return this.antallages_finished;
    }
    /**
	 * <b>accessor(selector)</b>:Returns the number of players 
         * which are ready for exchanging<br />
	 * <p><b>Postcondition:</b> returns the number of players 
         * which are ready for exchanging </p>
	 *
	 * @return the number of players which are ready for for exchanging
	 */
     public int Get_isready2(){
        return isready2;
    }
     /**
	 * <b>transformer(mutative)</b>: increases the variable isready2 by 1(isready2++)
	 * <p><b>Postcondition:</b>  increases the variable isready2 by 1(isready2++)</p>
  	 */ 
    public void isready2(){
        turn.setID(players);
        isready2++;
    }
    /**
	 * <b>transformer(mutative)</b>: makes the exchanges (3 for each player in the start of the game
	 * <p><b>Postcondition:</b>it changes players cards after exchanging  </p>
         * @param cardsPosition the position of the cards in allcards that players want to exchange
  	 */ 
    public void makeAntallages(ArrayList<Integer> cardsPosition){
     Collections.swap(allcards.getCards(),cardsPosition.get(0) ,cardsPosition.get(11));   
     Collections.swap(allcards.getCards(),cardsPosition.get(1) ,cardsPosition.get(7)); 
     Collections.swap(allcards.getCards(),cardsPosition.get(2) ,cardsPosition.get(5)); 
     Collections.swap(allcards.getCards(),cardsPosition.get(4) ,cardsPosition.get(10)); 
     Collections.swap(allcards.getCards(),cardsPosition.get(3) ,cardsPosition.get(8)); 
     Collections.swap(allcards.getCards(),cardsPosition.get(6) ,cardsPosition.get(9)); 
     allcards.sort(0,14);
     allcards.sort(14,28);
     allcards.sort(28,42);
     allcards.sort(42,56);
     init_player_cards();
        }
    /**
	 * <b>accessor(selector)</b>:Returns a string array of the last cards that have been played <br />
	 * 
	 * <p><b>Postcondition:</b> returns a string array of the last cards that have been played </p>
	 *
	 * @return a string array of the last cards that have been played
	 */
    public String[] PreviousMove(){
        return round.GetlastCards();
    }
    /**
	 * <b>accessor(selector)</b>:Returns all cards of the game <br />
	 * 
	 * <p><b>Postcondition:</b> returns all cards of the game </p>
	 *
	 * @return all cards of the game
	 */
    public Sullogi Get_all_Cards(){
        return this.allcards;
    }    
      /**
	 * <b>accessor(selector)</b>:Returns a round <br />
	 * 
	 * <p><b>Postcondition:</b> returns a round </p>
	 *
	 * @return a round
	 */
    public Round Ret_Round(){
        return this.round;
    }
}
