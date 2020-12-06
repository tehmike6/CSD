/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.player;

/**
 * @version 1.0
 * @author dxanthak - mountanton
 */
public class Team {

        private String name;
        private int points;

    /**Constructor.
     * 
     * <b>Postcondition</b>Creates a new Team with 'name' name and 'points' points.
     *
     * @param name
     * @param points
     */
    public Team(String name, int points) {
                
                this.name = name;
                this.points = points;
        }

    /**
     * <b>accessor(selector)</b>:Returns the name of the team <br />
     * 
     * <p><b>Postcondition:</b> returns the name of the team </p>
     *
     * @return the the name of the team
     */
    public String getName() {
                return name;
        }

    /**
     * <b>transformer(mutative)</b>: sets the name of the team to newName <br />
     * <p><b>Postcondition:</b> the name of this team is changed to newName</p>
     *
     * @param newName the new name of the team
     * 
     */ 
    public void setName(String newName) {
                this.name = newName;
        }

    /**
     * <b>accessor(selector)</b>:Returns the points of the team <br />
     * <p><b>Postcondition:</b> the points of this team have been returned</p>
     *
     *@return the points of this team 
     */
    public int getPoints() {
                return points;
        }

    /**
     * <b>transformer(mutative)</b>: sets the points of the team <br />
     * <p><b>Postcondition:</b> the points of this team have been set</p>
     *
     * @param int points
     * 
     */ 
    public void setPoints(int points) {
                this.points=this.points+points;
        }

}

