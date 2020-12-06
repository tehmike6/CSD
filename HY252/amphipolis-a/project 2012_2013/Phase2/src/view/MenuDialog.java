
package view;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * @version 1.0
 * @author dxanthak - mountanton
 */
public class MenuDialog{
    int option; 
    String strin;
     URL imageURL;
    ClassLoader cldr = this.getClass().getClassLoader();  
    /**
	 * <b>constructor</b>: Creates a new Menu Window  <br />
	 * <b>postconditions</b>: Creates a new Menu Window 
	 * starting a new Menu
	 */
    public MenuDialog(Object a,Object b,String str,String str2){
  JFrame parent = new JFrame();
    if (str2.charAt(0)=='P'){
        strin="phoenix";
    }
    else if (str2.charAt(0)=='E'){
        strin="Exit";
    }
    else if (str2.charAt(0)=='N'){
        strin="neww";
    }
    else{
        strin="drache";
    }
    if (a.equals(11)==true){
        a="J";
    }
    else if (a.equals(12)==true){
        a="Q";
    }
    else if (a.equals(13)==true){
        a="K";
    }
    else if (a.equals(14)==true){
        a="A";
    }
    if (b.equals(11)==true){
        b="J";
    }
    else if (b.equals(12)==true){
        b="Q";
    }
    else if (b.equals(13)==true){
        b="K";
    }
    else if (b.equals(14)==true){
        b="A";
    }
    Object[] options = {a,
                    b};
    imageURL= cldr.getResource("images/" +strin+"_47x70.png");
int n = JOptionPane.showOptionDialog(parent,
    str,
    str2,
    JOptionPane.YES_NO_OPTION,
    JOptionPane.QUESTION_MESSAGE,
    new ImageIcon(imageURL),     //do not use a custom Icon
    options,  //the titles of buttons
    options[0]); //default button title
  
    
    if (n == JOptionPane.YES_OPTION) {
                        option=1;
                    } else if (n == JOptionPane.NO_OPTION) {
                       option=2;
                    } else {
                        option=0;
                    }
    }
      /**
	 * <b>accessor(selector)</b>:Returns the choice of a player <br />
	 * 
	 * <p><b>Postcondition:</b> Returns the choice of a player </p>
	 *
	 * @return the choice of the player
	 */
    public int choice(){
        return this.option;
    }
}