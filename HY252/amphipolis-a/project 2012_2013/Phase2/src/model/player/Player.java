package model.player;

import model.Sullogi.Sullogi;
import model.card.Card;



/**
 * @version 1.0
 * @author dxanthak - mountanton
 */
public class Player {
        private String name;
        private Team team;
        private Sullogi cards,cards_to_play,mpaza;
        private int choice,ID;
        private boolean hasPlayed,finished;
        /**
	 * <b>constructor</b>: Constructs a new Player with the given
	 * parameter name  team  and ID.<br />
	 * <b>postcondition</b>: Creates and initializes a player with the given
	 * name,team and ID.Also initializes some variables (for example initialize variables 
         * that give us information if a player has said tichu or grand tichu and 
         * if player has started or finished in a game(se mia partida)
	 * @param name is the name of the player.
	 * @param team is the team of the player
         * @param ID is the ID of the player
	 */
        public Player(String name, Team team,int ID) {
                
                this.name = name;
                this.team = team;
                this.cards=new Sullogi();
                this.cards_to_play=new Sullogi();
                this.mpaza=new Sullogi();
                this.choice=0;
                this.hasPlayed=false;
                
                this.ID=ID;
                this.finished=false;
        }
        /**
	 * <b>transformer(mutative)</b>: It initializes a player for a new deal(moirasma) <br />
	 * <b>postcondition</b>:  clear the contents of players collection and initialize some variables
	 * (for example initialize variables that give us information if a player has said tichu or grand tichu and 
         * if player has started or finished in a game(se mia partida))
	 */
        public void init_player(){
            this.mpaza.clearAll();
                this.choice=0;
                this.hasPlayed=false;
                this.cards.clearAll();

                this.finished=false;
        }
        
	/**
	 * <b>accessor(selector)</b>: Returns the ID of a player. <br />
 	 * <b>postcondition</b>: Returns the ID of the player. 
	 * @return the ID of the player (int)
	 */
        public int getID() {
                return ID;
        }
        /**
	 * <b>transformer(mutative)</b>: It sets the ID of a plater <br />
	 * <b>postcondition</b>:the ID of the player is changed to id  
	 * @param id the new ID of the player
	 */
        public void setID(int id) {
                this.ID = id;
        }

        /**
	 * <b>accessor(selector)</b>:Returns the name of the player <br />
	 * 
	 * <p><b>Postcondition:</b> returns the name of the player </p>
	 *
	 * @return the name of the player
	 */
        public String getName() {
                return name;
        }
        	
	/**
	 * <b>transformer(mutative)</b>: sets the name of the player to newName <br />
	 * <p><b>Postcondition:</b> the name of this player is changed to newName</p>
	 *
	 * @param newName the new name of the player
         * 
  	 */ 
        public void setName(String newName) {
                this.name = newName;
        }
        /**
	 * <b>accessor(selector)</b>:Returns the team of the player <br />
	 * 
	 * <p><b>Postcondition:</b> returns the team of the player </p>
	 *
	 * @return the the team of the player
	 */
        public Team getTeam() {
                return team;
        }
        /**
	 * <b>transformer(mutative)</b>: sets the team of the player to newTeam <br />
	 * <p><b>Postcondition:</b> the team of this player is changed to newTeam</p>
	 *
         * 
         * 
         * @param team 
         */ 
        public void setTeam(Team team) {
                this.team = team;
        }
         /**
	 * <b>transformer(mutative)</b>: sets the collection(Sullogi) who want a player to play  to cardsforplaying  <br />
	 * <p><b>Postcondition:</b>  Sullogi(collection) of this player is changed to cardsforplaying</p>
	 *
	 * @param cardsforplaying the new Sullogi of the cards that player wants to play
         * 
  	 */ 
        public void setCards_to_play(Sullogi cardsforplaying){
            this.cards_to_play=cardsforplaying;
        }
          /**
	 * <b>accessor(selector)</b>:Returns the cards who wants a player to play <br />
	 * 
	 * <p><b>Postcondition:</b> returns the cards that player wants to play </p>
	 *
	 * @return the cards that player wants to play
	 */
         public Sullogi getCards_to_play(){
           return this.cards_to_play;
        }
          /**
	 * <b>transformer(mutative)</b>: add a Card to players cards  <br />
	 * <p><b>Postcondition:</b>  a card added to players cards</p>
	 *
	 * @param c the card that will be added to players Collection
         * 
  	 */ 
        public void setCards(Card c){
            this.cards.addCard(c);
        }
          /**
	 * <b>transformer(mutative)</b>: add a collection to players mpaza(cards which count for points)  <br />
	 * <p><b>Postcondition:</b>  a collection added to players mpaza</p>
	 *
	 * @param s the colleciton that will be added to players mpaza
         * 
  	 */ 
        public void set_mpaza(Sullogi s){
            for (int j=0;j<s.size();j++){
                this.mpaza.addCard(s.getCard(j));
            }
        }
         /**
	 * <b>accessor(selector)</b>:Returns the mpaza collection of a player <br />
	 * 
	 * <p><b>Postcondition:</b> returns the mpaza collection of a player </p>
	 *
	 * @return the mpaza collection of a player
	 */
         public Sullogi get_mpaza(){
            return this.mpaza;
        }
           /**
	 * <b>accessor(selector)</b>:Returns the Cards collection of a player <br />
	 * 
	 * <p><b>Postcondition:</b> returns the Cards collection of a player </p>
	 *
	 * @return the Cards collection of a player
	 */
         public Sullogi getCards(){
           return this.cards;
        }
           /**
	 * <b>transformer(mutative)</b>: set this.choice=2 
         * (if choice is 2 there is a tichu, if 1 is a Grand Tichu and if 0 nothing <br />
	 * <p><b>Postcondition:</b>  set the choice of this player to 2</p>
	 *
         * 
  	 */ 
        public void Tichu(){
            this.choice=1;
        }
        /**
	 * <b>transformer(mutative)</b>: set this.choice=1
         * (if choice is 2 there is a tichu, if 1 is a Grand Tichu and if 0 nothing <br />
	 * <p><b>Postcondition:</b>  set the choice of this player to 1</p>
  	 */ 
        public void GrandTichu(){
            this.choice=2;
        }
           /**
	 * <b>accessor(selector)</b>:Returns the choice of a player(tichu grand tichu or nothing) <br />
	 * 
	 * <p><b>Postcondition:</b> Returns the choice of a player(tichu grand tichu or nothing) </p>
	 *
	 * @return 1 if player choice is GrandTichu,2 if is Tichu or 0 if nothing
	 */
        public int getChoice(){
            return this.choice;
        }
         /**
	 * <b>transformer(mutative)</b>: if a player plays for the first time it sets the variable hasplayed to true
	 * <p><b>Postcondition:</b>  sets the variable hasplayed to true</p>
  	 */ 
        
        public void Played(){
            this.hasPlayed=true;
        }
         /**
	 * <b>transformer(mutative)</b>: if a player plays for the last time it sets the variable hasfinished to true
	 * <p><b>Postcondition:</b>  sets the variable hasfinished to true</p>
  	 */ 
        public void has_finished(){
            this.finished=true;
        }
         /**
	 * <b>Observer</b>:Returns if a player has played at least one time <br />
	 * 
	 * <p><b>Postcondition:</b> Returns if a player has played at least one time </p>
	 *
	 * @return true if a player has played at least one time false otherwise
	 */
         public boolean Has_Played(){
           return this.hasPlayed;
        }
         /**
	 * <b>Observer</b>:Returns if a player has finished the game(partida)<br />
	 * 
	 * <p><b>Postcondition:</b> Returns if a player has finished the game(partida) </p>
	 *
	 * @return true if a player has finished the game , false otherwise
	 */
        public boolean Get_has_finished(){
           return this.finished;
        }
        
}
