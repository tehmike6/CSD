package view;

import model.Board.Areas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * A class that extends JFrame class and is responsible for Graphical Interface .
 * @version 1.0
 * @author Mike Bastakis csd4406
 * */
public class View extends JFrame {
    private JPanel main;
    private JButton Draw;
    private JButton EndTurn;
    private JButton[] Characters;
    private JLabel PlayersTurn;
    private JPanel collection;
    private ArrayList<JButton> collectedTiles;
    private ArrayList<JButton>[] BoardTiles;
    private JPanel[] TileAreas;

    /**
     * <b>Constructor</b>
     * Creates a window and showcases the start point of the board,
     * by initializing panels,buttons.
     */
    public View(){
        initView();
    }

    /**
     * <b>Transformer</b>
     * Initiallizes buttons and panels and assignes images to buttons and panels
     * It is being called by the constructor.
     */
    public void initView() {
        // Set default configuration for the window.
        this.setTitle("Amphipolis");
        this.setLayout(null);
        this.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        this.setResizable(false);
        this.setSize(1200,900);

        // Set Icon for window.
        this.setIconImage(new ImageIcon(this.getClass().getResource("/images/tile_back.png")).getImage());

        // Main Board Jpannel
        main = new JPanel();
        JLabel label = new JLabel();
        ImageIcon icon = getImageIcon("background.png",900,750);
        label.setIcon(icon);
        main.add(label);
        main.setBounds(0,0,900,750);

        // Set Tile areas in Main Panel
        JPanel board = new JPanel();
        board.setLayout(null);
        TileAreas = new JPanel[5];
        TileAreas[Areas.MOSAIC_AREA.getValue()] = new JPanel(new GridLayout(4,4));
        TileAreas[Areas.STATUE_AREA.getValue()] = new JPanel(new GridLayout(4,4));
        TileAreas[Areas.AMPHORA_AREA.getValue()] = new JPanel(new GridLayout(4,4));
        TileAreas[Areas.SKELETON_AREA.getValue()] = new JPanel(new GridLayout(4,4));
        TileAreas[Areas.LANDSLIDE_AREA.getValue()] = new JPanel((new GridLayout(4,4)));

        BoardTiles = new ArrayList[5];
        for(int i=0; i<5; i++){
            BoardTiles[i] = new ArrayList<>();
            for(int j=0; j<16; j++){
                BoardTiles[i].add(new JButton());
                BoardTiles[i].get(j).setVisible(false);
                BoardTiles[i].get(j).setBorderPainted(false);
                TileAreas[i].add(BoardTiles[i].get(j));
            }
        }
        TileAreas[Areas.MOSAIC_AREA.getValue()].setBounds(25,25,265,221);
        TileAreas[Areas.STATUE_AREA.getValue()].setBounds(610,25,265,221);
        TileAreas[Areas.AMPHORA_AREA.getValue()].setBounds(25,513,265,221);
        TileAreas[Areas.SKELETON_AREA.getValue()].setBounds(610,513,265,221);
        TileAreas[Areas.LANDSLIDE_AREA.getValue()].setBounds(320,330,265,221);
        board.setBounds(0,0,1200,900);

        //Set Collection of player
        collection = new JPanel(new GridLayout(2,20));
        collection.setBounds(0,750,1190,110);
        collection.setBackground(Color.LIGHT_GRAY);
        collectedTiles = new ArrayList<>();
        ImageIcon tile_back = getImageIcon("tile_back.png",66,55);
        for(int i=0;i<40;i++) {
            collectedTiles.add(new JButton(tile_back));
            collection.add(collectedTiles.get(i));
            collectedTiles.get(i).setBorderPainted(false);
            collectedTiles.get(i).setVisible(false);
        }

        // Set Draw Button, End Turn Button
        JPanel buttonMenu = new JPanel(new GridLayout(2,1));
        Draw = new JButton("Draw Tiles");
        EndTurn = new JButton("End Turn");
        buttonMenu.add(Draw);
        buttonMenu.add(EndTurn);



        //Set Player, Special Cards
        JPanel menu = new JPanel(new GridLayout(4,1));
        JPanel characters = new JPanel(new GridLayout(1,2));
        JPanel characters2 = new JPanel(new GridLayout(1,2));


        Characters = new JButton[4];
        Characters[0] = new JButton(getImageIcon("archaeologist.png",140,190));
        Characters[1] = new JButton(getImageIcon("assistant.png",140,190));
        Characters[2] = new JButton(getImageIcon("digger.png",140,190));
        Characters[3] = new JButton(getImageIcon("professor.png",140,190));
        Characters[0].setBorderPainted(false);
        Characters[1].setBorderPainted(false);
        Characters[2].setBorderPainted(false);
        Characters[3].setBorderPainted(false);
        characters.add(Characters[0]);
        characters.add(Characters[1]);
        characters2.add(Characters[2]);
        characters2.add(Characters[3]);

        menu.setBounds(900,0,300,750);
        PlayersTurn = new JLabel("Player: "+1);
        PlayersTurn.setFont(new Font(PlayersTurn.getName(),Font.PLAIN,40));
        menu.add(PlayersTurn);
        //menu.add(new JLabel("Use Character: "));
        menu.add(characters);
        menu.add(characters2);
        menu.add(buttonMenu);


        board.add(TileAreas[Areas.MOSAIC_AREA.getValue()]);
        board.add(TileAreas[Areas.STATUE_AREA.getValue()]);
        board.add(TileAreas[Areas.AMPHORA_AREA.getValue()]);
        board.add(TileAreas[Areas.LANDSLIDE_AREA.getValue()]);
        board.add(TileAreas[Areas.SKELETON_AREA.getValue()]);
        board.add(collection);
        board.add(menu);

        board.add(main);

        this.add(board);
        this.setVisible(true);
    }
    /**
     * <b>Transformer</b>
     * Does an action that enables or disables the buttons of the area based on action
     * @param area the area's buttons will be affected
     * @param action The action that will be performed an the buttons.
     * */
    public void disableAreas(Areas area, boolean action){
        for(int i=0;i<16;i++) BoardTiles[area.getValue()].get(i).setEnabled(action);
    }
    /**
     * <b>Transformer</b>
     * Disables the buttons of every area.
     * */
    public void disableAllAreas(){
        for(int k=0;k<4;k++){
            disableAreas(Areas.valueOf(k),false);
        }
    }
    /**
     *  Returns an image icon from a file filename and resizes it to width and height
     * @param filename The image that will be returned.
     * @param width The width of the image that will be returned.
     * @param height The height of the image that will be returned.
     * */
    private ImageIcon getImageIcon(String filename,int width,int height){
       return new ImageIcon(new ImageIcon(this.getClass().getResource("/images/"+filename)).getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH));
    }
    /**
     * <b>Transformer</b>
     * Changes a buttons image
     * @param filename The image of the file specified by filename will be the image of image icon
     * @param area The area of the button that will change.
     * @param index The index of the button in an area.
     * */
    public void printImageInButton(String filename, Areas area, int index){
        ImageIcon icon = getImageIcon(filename,66,55);
        BoardTiles[area.getValue()].get(index).setIcon(icon);
        BoardTiles[area.getValue()].get(index).setVisible(true);
    }
    /**
     * <b>Transformer</b>
     * Changes a button image in the collection panel.
     * @param filename The image of the file specified by filename will be the image of image icon
     * @param index The index of the button that will be painted.
     * */
    public void printImageInCollection(String filename,int index){
        ImageIcon icon = getImageIcon(filename,66,55);
        collectedTiles.get(index).setIcon(icon);
        collectedTiles.get(index).setVisible(true);
    }
    /**
     * All of the rest are getters and setters
     * */
    public JButton getDraw() {
        return Draw;
    }

    public JButton getEndTurn() {
        return EndTurn;
    }

    public JButton[] getCharacters() {
        return Characters;
    }

    public ArrayList<JButton>[] getBoardTiles() {
        return BoardTiles;
    }
    public ArrayList<JButton> getCollectedTiles() {
        return collectedTiles;
    }

    public void setPlayer(int player) {
        PlayersTurn.setText("Player: "+ player);
    }

    public JLabel getPlayersTurn() {
        return PlayersTurn;
    }
}
