package view;

import controller.Controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * A class that extends JFrame class and is responsible for Graphical Interface .
 * @version 1.0
 * @author Mike Bastakis csd4406
 * */
public class View extends JFrame {
    private Controller controller;
    private Image image;
    private JPanel main;
    private JButton Draw;
    private JButton EndTurn;
    private JButton[] Characters;
    private JLabel PlayersTurn;
    private JTextField Text;
    private JPanel collection;
    private ArrayList<Image> collectedTiles;
    private ArrayList<JButton> BoardTiles;
    private ArrayList<JPanel> TileAreas;

    /**
     * <b>Constructor</b>
     * Creates a window and showcases the start point of the board,
     * by initializing panels,buttons.
     */
    public View(){
        // Set default configuration for the window.
        this.setTitle("Amphipolis");
        this.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        this.setLayout(null);
        //this.setResizable(false);
        this.setSize(1200,1000);
        // Set Icon for window.
        this.setIconImage(new ImageIcon(this.getClass().getResource("/images/tile_back.png")).getImage());
        // Main Jpannel
        main = new JPanel();
        Border b =  BorderFactory.createLineBorder(Color.GREEN,3);
        JLabel label = new JLabel();
        ImageIcon icon = new ImageIcon(new ImageIcon(this.getClass().getResource("/images/background.png")).getImage().getScaledInstance(900,750,Image.SCALE_SMOOTH));
        label.setIcon(icon);
        label.setBounds(0,0,900,750);
        label.setBorder(b);


        main.add(label);
        main.setBounds(-150,0,1200,1000);
        this.add(main);
        this.setVisible(true);
    }

    /**
     * <b>Transformer</b>
     * Initiallizes buttons and panels and assignes images to buttons and panels
     * It is being called by the constructor.
     */
    public void initView() {
    }

    /**
     * <b>Transformer</b>
     * Changes the images and player data for the new player.
     * This is being called at the start of every turn.
     */
    public void newTurn() {
    }
    /**
     * Changes the background image of a desktop pane.
     * */
    public class myDesktopPane extends JDesktopPane {
        private Image backImage = null;

        public myDesktopPane(Image image) {
            backImage = image;
        }
        /**
         * <b>Transformer</b>
         * Changes the backgorund image of an desktop pane.
         * */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backImage, 0, 0, this);
        }
    }
    /**
     * <b>Listener</b>
     * Perform action when a tile in an area of the board is clicked.
     * */
    public class getTile implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    /**
     * <b>Listener</b>
     *  Perform action when a character card is clicked
     * */
    public class activateCharacter implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    /**
     * <b>Listener</b>
     * Perform action when the Draw TIles button is clicked.
     * */
    public class DrawTiles implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    /**
     * <b>Listener</b>
     * Perform action when the End Turn button is clicked.
     * */
    public class EndTurn implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
