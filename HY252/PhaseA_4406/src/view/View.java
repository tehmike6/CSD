package view;

import controller.Controller;

import javax.swing.*;
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
    public View() {
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

        public myDesktopPane() {

        }
        /**
         * <b>Transformer</b>
         * Changes the backgorund image of an desktop pane.
         * */
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }
    /**
     * <b>Listener</b>
     * Perform action when a tile in an area of the board is clicked.
     * */
    public class getTile implements ActionListener{
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
