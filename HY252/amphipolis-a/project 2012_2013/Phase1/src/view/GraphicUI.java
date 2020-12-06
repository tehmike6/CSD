
package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import controller.Controller;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

        
/**
 * @version 1.0
 * @author dxanthak - mountanton
 */
public class GraphicUI extends JFrame  {
  
    private Image image;
    private JButton GrandTichuButton;
    private JButton TichuButton;
    private JButton PlayButton,newGame,Exit,HundButton;
    private JButton FoldButton;
    private myDesktopPane basic_panel;
    private JDesktopPane player3_field;
    private JDesktopPane player2_field;
    private JDesktopPane player1_field;
    private JDesktopPane player4_field;
    private JDesktopPane player2_field2;
    private JDesktopPane player1_field2;
    private JDesktopPane player3_field2;
    private JDesktopPane player4_field2,tablo;
    private JPanel jPanel1;
    private JTextField jTextField1;
    private URL imageURL;
    private ClassLoader cldr;  
    private JButton[] buttons = new JButton[56];
    /**
	 * <b>constructor</b>: Creates a new Window and initializes some buttons and panels <br />
	 * <b>postconditions</b>: Creates a new Window and initializes some buttons and panels
	 * starting a new game.
	 */
    public GraphicUI() {
        
   
        
    }
   
    
    /**
	 * <b>transformer(mutative)</b>:initializes some buttons and labels <br />
	 * <p><b>Postcondition:</b> initializes some buttons and labels </p>
	 *
     */
    private void initComponents() {
      
    }
        
    /**
	 * <b>transformer(mutative)</b>:sets some buttons and labels for a new deal<br />
	 * <p><b>Postcondition:</b> sets some buttons and labels for a new deal </p>
	 *
     */
    public void init_buttons(){
      
     
    }
    /**
	 * <b>transformer(mutative)</b>:sets some buttons and labels after every player has 14 cards<br />
	 * <p><b>Postcondition:</b> sets some buttons and labels after every player has 14 cards</p>
	 *
     */
    public void mirasma(){
     
   }
  /* a class which is used for putting a background image to a jdesktoppane*/  
public class myDesktopPane extends JDesktopPane
{
   private Image backImage = null;
 
   public myDesktopPane()
   {      
      
   }
 
        @Override
   public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
 } 
/* a class which is used for doing some action after a card button has been pushed */  
 private class CardListener implements ActionListener 	{
  
                    
                    
 }
     /* a class which is used for doing some action after play button has been pushed */
      private class PlayListener implements ActionListener 	{
     
      }
      /* a class which is used for doing some action after a Fold button has been pushed */  
      private class FoldListener implements ActionListener 	{
                        /**
	 * <b>transformer(mutative)</b>:doing some action after Fold button has been pushed<br />
	 * <p><b>Postcondition:</b> doing some action after Fold button has been pushed</p>
	 *
     */
		public void actionPerformed(ActionEvent e) 	{
                        
                }
      }
    /* a class which is used for doing some action after a Tichu button has been pushed */    
    private class TichuListener implements ActionListener 	{
                      /**
	 * <b>transformer(mutative)</b>:doing some action after Tichu or GrandTichu button has been pushed<br />
	 * <p><b>Postcondition:</b> doing some action after Tichu or GrandTichu button has been pushed</p>
	 *
     */
        @Override
		public void actionPerformed(ActionEvent e) 	{
                       
                }
      }
    /* a class which is used for doing some action after a Settings button has been pushed */    
    private class SettingsListener implements ActionListener 	{
                    /**
	 * <b>transformer(mutative)</b>:doing some action after New Game or Exit button has been pushed<br />
	 * <p><b>Postcondition:</b> doing some action after New Game or Exit button has been pushed</p>
	 *
     */
        @Override
		public void actionPerformed(ActionEvent e) 	{
                   
                }
    }  
}
