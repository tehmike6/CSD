package controller;

import java.util.ArrayList;
import model.Sullogi.Sullogi;
import model.player.Player;
import model.player.Team;
import model.round.Round;
import model.turn.Turn;

/**
 * Controller is the master of the game and controls all 
 * of the operations executed
 * @version 1.0
 * @author dxanthak - mountanton
 */
public class Controller 
{
    private int fold, isready, points;
    private boolean notstarted,empty_table,new_round,collectionHasPlayed;
    private Team team1,team2;
    private Player P1,P3,P2,P4;
    private ArrayList <Player> players=new ArrayList<Player>();
    private Turn turn=new Turn();
    private Round round =new Round();
    private Sullogi allcards = new Sullogi();
    private Sullogi cardsToPlay = new Sullogi();
    
    
    
        /**
	* <b>constructor</b>: Constructs a new Controller and sets the game as 
	* eligible to start .<br />
	* <b>postcondition</b>: constructs a new Controller,with new 4 players,new 
        * instances of Turn Class , Round Class and Sullogi Class and initialize
        * some int or boolean variables.So,is responsible for creating a new game and 
	* initializing it.
	*/
        public Controller()
        {
            team1=new Team("team1",0);
            team2=new Team("team2",0);
            P1=new Player("p1",team1,0);
            P3=new Player("p3",team1,2);
            P2=new Player("p2",team2,1);
            P4=new Player("p4",team2,3);
            players.add(P1);
            players.add(P2);
            players.add(P3);
            players.add(P4);
            allcards.init_cards();
            this.empty_table=true;
            this.notstarted=true;
            this.fold=0;
            this.isready=0;
            this.collectionHasPlayed=false;

        }
        

    /**
    *<b>Observer</b>: Return true if the collection that player wanted to play , 
    * was finally played ,false otherwise
    * <p><b>Postcondition:</b> return true if the collection that player 
    * wanted to play , was finally played, false otherwise</p>
    * @return true if the collection that player 
    * wanted to play , was finally played, false otherwise
    */
            
    public boolean GetCollectionHasPlayed(){
                 System.out.println(this.collectionHasPlayed);  
                return this.collectionHasPlayed;
        }

    /**
     * <b>transformer(mutative)</b>:take an integer arraylist from view Class (there are 56 buttons and 56 cards),
     * so for example button with id 0 show the card in position 0 in allcards collection.After that
     * check if these cards  can be played and if this true, set the boolean variable collectionHasPlayed to true.
     * <p><b>Postcondition:</b>  set the collectionHasPlayed  to true if the collection played, otherwise false </p>
     * @param cardsPosition the position of the cards in allcards(Sullogi with all the 56 cards) that player wants to play 
     */ 
    public void PlayCollection(ArrayList<Integer> cardsPosition)
    {

    }
    
    /**
     * <b>accessor(selector)</b>:Returns which player has the turn <br />
     * 
     * <p><b>Postcondition:</b> Returns which player has the turn </p>
     *
     * @return which player has the turn (for example 0 if p1 has the turn )
     */
    public int seeTurn()
    {
        return turn.getID();
    }
    
    
    /**
     * <b>transformer(mutative)</b>:if a player press the button fold ,it increases
     * the variable fold (fold++) or sets the variable fold to 0 if a round finished
     * and of course gives the turn to next player
     * <p><b>Postcondition:</b> increase the fold variable(fold++) 
     * or if a round finished sets the fold to 0 and of course give the turn to next player</p>
     */ 
    public void set_Fold()
    {

    }
    
    
    /**
     *<b>Observer</b>: Return true if the table is empty false otherwise
     * (table:the space in which the players drop their cards)
     * <p><b>Postcondition:</b> return  true if the table is empty 
     * false otherwise
     * @return true if the table is empty false otherwise
     */
    public boolean tableIsEmpty()
    {
        
        return this.empty_table;
    }
    
    
    /**
     * <b>transformer(mutative)</b>: sets the variable not_started to false
     * <p><b>Postcondition:</b>  sets the variable not_started to false</p>
     */ 
    public void set_started()
    {
        this.notstarted=false;
    }
    
    
    /*
     *<b>Observer</b>: Return true if the game has not started  false otherwise
     * <p><b>Postcondition:</b> return true if the game  has not started  false otherwise
     * @return true if the game has not started  false otherwise
     */
    public boolean not_started()
    {
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
    public int Get_isready()
    {
        return isready;
    }
    
    
    /**
     * <b>transformer(mutative):</b> increases the variable isready by 1(isready++)
     * <p><b>Postcondition:</b>  increases the variable isready by 1(isready++)</p>
     */ 
    public void isready()
    {
        
    }
       
    /**
     * <b>transformer(mutative):</b> sort the cards for each player 
     * <p><b>Postcondition:</b> sort the cards for each player  </p>
     */ 
    public void sort_cards()
    {
        
    }
    
    
    /**
     * <b>transformer(mutative)</b>: sets Grand Tichu for a player if a player push Grand Tichu button
     * <p><b>Postcondition:</b> sets Grand Tichu for a player if a player push Grand Tichu button </p>
     */ 
    public void setGrandTichu()
    {
       
    }
    
    
   /**
    * <b>transformer(mutative)</b>: sets Tichu for a player if a player push Tichu button 
    * <p><b>Postcondition:</b> sets Tichu for a player if a player push Tichu button if he hasnt played yet </p>
    */ 
    public void setTichu()
    {
       
    }
    
    
    /**
     * <b>transformer(mutative)</b>: sets the score of a game after one deal(partida) has finished
     * <p><b>Postcondition:</b>  sets the score of a game after one deal(partida) has finished </p>
     */ 
    public void setScore()
    {
        
    }
    
  
   /**
    * <b>accessor(selector)</b>:Returns the score of the game <br />
    * <p><b>Postcondition:</b> returns the score of the game </p>
    * @return the number of players which are ready for dealing the last 6 cards
    */
    public int GetScore()
    {
        return 1;
    }
    
    
   /**
    * <b>transformer(mutative)</b>: sets the wish of the player when the player has dropped a collection with mahjong
    * <p><b>Postcondition:</b> sets the wish of the player when the player has dropped a collection with mahjong </p>
    */ 
    public void euxiMeMahjong()
    {
        
    }
    
    
   /**
    * <b>transformer(mutative)</b>: initializes players cards in the beginning
    * <p><b>Postcondition:</b> initializes players cards in the beginning </p>
    */ 
    public void init_player_cards()
    {
       
    }
    
    
   /**
    * <b>transformer(mutative)</b>: initializes some things(allcards,turn,round) for a new deal(partida)
    * <p><b>Postcondition:</b>  initializes some things(allcards,turn,round) for a new deal(partida)</p>
    */ 
    public void init_table()
    {
          
    }
    
   /**
    *<b>Observer</b>: Return true if we have a new round false otherwise
    * <p><b>Postcondition:</b> return true if we have a new round false otherwise
    * </p>
    * @return true if we have a new round false otherwise
    */
    public boolean Get_new_round()
    {
        return this.new_round;
    }
    
    
   /**
    * <b>transformer(mutative)</b>: make changes after a round started
    * <p><b>Postcondition:</b> make changes after a round started </p>
    * @param cardsPosition 
    */ 
    public void makeChanges(ArrayList<Integer> cardsPosition)
    {
               
    }
    
    
   /**
    * <b>Observer</b>:Return true if a deal(partida) has finished, false otherwise
    * <p><b>Postcondition:</b> return true if a deal(partida) has finished, false otherwise
    * </p>
    * @return true if a deal(partida) has finished, false otherwise
    */
    public boolean partida_has_finished()
    {

        return false;
    }

    
   /**
    * <b>Observer</b>:Return true if a game(one team reaches 1000 points) has finished, false otherwise
    * <p><b>Postcondition:</b> return true if a game(one team reaches 1000 points)  has finished, false otherwise
    * </p>
    * @return true if a game(one team reaches 1000 points) has finished, false otherwise
    */
    public boolean game_has_finished()
    {
       
        return false;
    }
    
    /**
     * <b>transformer(mutative)</b>: give the turn to player who has mahjong
     * <p><b>Postcondition:</b> give the turn to player who has mahjong in the beggining</p>
     */ 
    public void set_Turn()
    {
       
    }
}
