
package view;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @version 1.0
 * @author dxanthak - mountanton
 */
public class WishDialog {
    String s ;
    URL imageURL;
    ClassLoader cldr = this.getClass().getClassLoader();  
     /**
	 * <b>constructor</b>: Creates a new Wish Dialog Window  <br />
	 * <b>postconditions</b>: Creates a new Wish Dialog Window
	 * starting a new Wish Dialog
	 */
    public WishDialog(){
         JFrame parent = new JFrame();
         Object[] possibilities = {"2", "3", "4","5","6","7","8","9","10","J","Q","K","A"};
         imageURL= cldr.getResource("images/Mahjong_47x70.png");
     s = (String)JOptionPane.showInputDialog(
                    parent,
                    "Make a wish:\n"
                    ,
                    "Mahjong wish",
                    JOptionPane.PLAIN_MESSAGE,
                   new ImageIcon(imageURL),
                    possibilities,
                    "2");


    }
     /**
	 * <b>accessor(selector)</b>:Returns the choice of a player <br />
	 * 
	 * <p><b>Postcondition:</b> Returns the choice of a player </p>
	 *
	 * @return the choice of the player
	 */
    public String choice(){
        if (s==null){
            s="no";
        }
        return s;
    }
}
