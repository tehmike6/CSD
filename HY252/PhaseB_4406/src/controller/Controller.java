package controller;

import model.Board.Areas;
import model.Board.Bag;
import model.Board.Board;
import model.Board.Player;
import model.Cards.Characters;
import model.Tile.MosaicTile;
import model.Tile.Tile;
import model.Tile.TileType;
import view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class that controls the Models and View class and makes them work
 * together.
 * */
public class Controller {
    View view;
    private Player p1,p2,p3,p4;
    private Board board;
    private Bag bag;
    int turn;
    int action;
    int area;
    boolean characterUsed;
    /**
     * <b>Constructor</b>
     * Initializes players,board and bag.
     * */
    public Controller(){
        p1 = new Player(1);
        p2 = new Player(2);
        p3 = new Player(3);
        p4 = new Player(4);
        board = new Board();
        bag = new Bag();
        view = new View();
        turn = 0;
        view.setPlayer(turn+1);
        initBoard();
    }
    /**
     * <b>Transformer</b>
     * Initializes the board by setting a tile of each type in the board by drawing from the bag.
     * */
    private void initBoard() {
        Tile tile;

        int counter = 0;
        while(counter < 4) {
            tile = bag.getTile();

            if (tile.getTileType() == TileType.LANDSLIDE) {
                bag.getBag().add(tile);
                continue;
            }
            switch(tile.getTileType()){

                case AMPHORA:
                    if(board.getTileArea(Areas.AMPHORA_AREA).size() == 0){
                        board.getTileArea(Areas.AMPHORA_AREA).add(tile);
                        view.printImageInButton(tile.toString()+".png",Areas.AMPHORA_AREA,0);
                        view.getBoardTiles()[Areas.AMPHORA_AREA.getValue()].get(0).setEnabled(false);
                        counter++;
                    }else{
                        bag.getBag().add(tile);
                    }
                    break;
                case SKELETON:
                    if(board.getTileArea(Areas.SKELETON_AREA).size() == 0){
                        board.getTileArea(Areas.SKELETON_AREA).add(tile);
                        view.printImageInButton(tile.toString()+".png",Areas.SKELETON_AREA,0);
                        view.getBoardTiles()[Areas.SKELETON_AREA.getValue()].get(0).setEnabled(false);
                        counter++;
                    }else{
                        bag.getBag().add(tile);
                    }
                    break;
                case MOSAIC:
                    if(board.getTileArea(Areas.MOSAIC_AREA).size() == 0){
                        board.getTileArea(Areas.MOSAIC_AREA).add(tile);
                        view.printImageInButton(tile.toString()+".png",Areas.MOSAIC_AREA,0);
                        view.getBoardTiles()[Areas.MOSAIC_AREA.getValue()].get(0).setEnabled(false);
                        counter++;
                    }else{
                        bag.getBag().add(tile);
                    }
                    break;
                case STATUE:
                    if(board.getTileArea(Areas.STATUE_AREA).size() == 0){
                        board.getTileArea(Areas.STATUE_AREA).add(tile);
                        view.printImageInButton(tile.toString()+".png",Areas.STATUE_AREA,0);
                        view.getBoardTiles()[Areas.STATUE_AREA.getValue()].get(0).setEnabled(false);
                        counter++;
                    }else{
                        bag.getBag().add(tile);
                    }
                    break;
            }
        }
        bag.bagSuffle();

        view.getDraw().addActionListener(e -> {
            for(int i=0;i<4;i++) board.addTileToArea(bag.getTile());
            refreshAreas();
            view.getDraw().setEnabled(false);
            view.getEndTurn().setEnabled(false);
            view.disableAreas(Areas.MOSAIC_AREA,true);
            view.disableAreas(Areas.STATUE_AREA,true);
            view.disableAreas(Areas.AMPHORA_AREA,true);
            view.disableAreas(Areas.SKELETON_AREA,true);
            if(board.isLandslideAreaFull()) gameIsFinished();
        });

        view.getEndTurn().addActionListener(e-> {
            PlayersTurn();
        });
        view.getEndTurn().setEnabled(false);

        for(int i=0;i<4;i++){
            ArrayList<JButton> BoardTiles = view.getBoardTiles()[i];
            BoardTiles.forEach(button -> button.addActionListener(this::actionPerformed));
        }

        Arrays.stream(view.getCharacters()).forEach(e -> {
            e.addActionListener( action -> {
                int i;
                for(i=0;i<4;i++){
                    if(view.getCharacters()[i].equals(action.getSource())) break;
                }
                characterUsed = true;
                getPlayer(turn).getCharacters()[i].setUsed(true);
                disableCharacterButtons(false);
                setupForCharacter(i);

            });
        });
        disableCharacterButtons(false);
    }

    /**
     * <b>Transformer</b
     * This method is used to disable the areas that need to be disabled because of the use of a character.
     * It also sets the right amount af actions that can be done now that the character is used.
     * @param i the character that we are setting up for.
     * */
    private void setupForCharacter(int i) {
        switch(i){
            case 0:
                view.disableAreas(Areas.MOSAIC_AREA,true);
                view.disableAreas(Areas.STATUE_AREA,true);
                view.disableAreas(Areas.AMPHORA_AREA,true);
                view.disableAreas(Areas.SKELETON_AREA,true);
                view.disableAreas(Areas.valueOf(area),false);
                action = 0;
                break;
            case 1:
                view.disableAreas(Areas.MOSAIC_AREA,true);
                view.disableAreas(Areas.STATUE_AREA,true);
                view.disableAreas(Areas.AMPHORA_AREA,true);
                view.disableAreas(Areas.SKELETON_AREA,true);
                action = 1;
                break;
            case 2:
                view.disableAreas(Areas.MOSAIC_AREA,false);
                view.disableAreas(Areas.STATUE_AREA,false);
                view.disableAreas(Areas.AMPHORA_AREA,false);
                view.disableAreas(Areas.SKELETON_AREA,false);
                view.disableAreas(Areas.valueOf(area),true);
                action = 0;
                break;
            case 3:
                view.disableAreas(Areas.MOSAIC_AREA,true);
                view.disableAreas(Areas.STATUE_AREA,true);
                view.disableAreas(Areas.AMPHORA_AREA,true);
                view.disableAreas(Areas.SKELETON_AREA,true);
                view.disableAreas(Areas.valueOf(area),false);
                action = 3;
                break;
        }
    }
    /**
     * <b>Transformer</b>
     * Based on the param action it enables or disables all the character buttons.
     * @param action The action that will enable or disable all the characters.
     * */
    private void disableCharacterButtons(boolean action){
        for(int i=0;i<4;i++){
            if(getPlayer(turn).getCharacters()[i].isUsed()) view.getCharacters()[i].setEnabled(false);
            else view.getCharacters()[i].setEnabled(action);
        }
    }
    /**
     * <b>Transformer</b>
     * Used to print all the area buttons if a change is made on the board.
     * */
    private void refreshAreas() {
        ArrayList<JButton>[] BoardTiles = view.getBoardTiles();
        for(int i=0;i<4;i++){
            ArrayList<Tile> TileArea = board.getTileArea(Areas.valueOf(i));
            for(int j=0;j<16;j++){
                if(j > TileArea.size()-1) BoardTiles[i].get(j).setVisible(false);
                else view.printImageInButton(TileArea.get(j).toString()+".png",Areas.valueOf(i),j);
            }
        }
        Tile[] LandslideArea = board.getLandslideArea();
        for(int i=0;i<16;i++){
            if(LandslideArea[i] != null){
                view.printImageInButton(LandslideArea[i].toString()+".png",Areas.LANDSLIDE_AREA,i);
            }
        }
    }
    /**
     * <b>Listener</b>
     * This is a Listener for the buttons that are on the areas.
     * */
    public void actionPerformed(ActionEvent e) {
        JButton targetButton = (JButton) e.getSource();
        for(int i=0;i<4;i++){
            ArrayList<JButton> BoardTiles = view.getBoardTiles()[i];
            for(int j=0;j<16;j++){
                if(BoardTiles.get(j).equals(targetButton)){
                    area = i;
                    ArrayList<Tile> TileArea = board.getTileArea(Areas.valueOf(i));
                    getPlayer(turn).getCollection().add(TileArea.get(j));
                    TileArea.remove(j);
                    refreshAreas();
                    getPlayer(turn).sortCollection();
                    refreshCollection();
                    if(action == 0) {
                        view.disableAllAreas();
                        view.disableAreas(Areas.valueOf(i),true);
                    }
                    action++;
                    if(characterUsed && action >= 3) {
                        view.disableAreas(Areas.valueOf(i),false);
                        if(action == 6){
                            view.getEndTurn().setEnabled(true);
                            view.disableAllAreas();
                        }
                    }
                    else if(action == 2 || (action == 1 && board.getTileArea(Areas.valueOf(i)).size() == 0)) {
                        view.getEndTurn().setEnabled(true);
                        view.disableAllAreas();
                        if(!characterUsed) disableCharacterButtons(true);
                    }
                    return;
                }
            }
        }

    }
    /**
     * <b>Transformer</b>
     * Repaints the collection buttons of the view.
     * */
    private void refreshCollection() {
        Player p = getPlayer(turn);
        if(p == null) return;
        for(int i=0;i<40; i++){
            if(p.getCollection().size() > i) view.printImageInCollection(p.getCollection().get(i).toString()+".png",i);
            else view.getCollectedTiles().get(i).setVisible(false);
        }
    }
    /**
     * <b>Accessor</b>
     * Returns the player that corresponds to index turn
     * @param turn the index of the player
     * @return a player
     * */
    private Player getPlayer(int turn) {
        switch (turn){
            case 0:
                return p1;
            case 1:
                return p2;
            case 2:
                return p3;
            case 3:
                return p4;
        }
        return p1;
    }

    /**
     * <b>Transformer</b>
     * Runs every new turn and sets the board for the player to play by drawing four tiles
     * from the bag and putting them on the corresponding areas.
     * */
    private void PlayersTurn() {
        view.getDraw().setEnabled(true);
        view.getEndTurn().setEnabled(false);
        action = 0;
        characterUsed = false;

        if(turn == 3) turn = 0;
        else turn++;
        view.setPlayer(turn + 1);
        getPlayer(turn).sortCollection();
        refreshCollection();
        disableCharacterButtons(false);
    }
    /**
     * <b>Accessor</b>
     * Called when all landslide tiles have been placed and it's time to find the winner.
     * */
    private void gameIsFinished() {
        setScores();

        System.out.println(p1.getScore() + " " + p2.getScore()+" " +p3.getScore()+ " " + p4.getScore());
        ArrayList<Integer> winners = new ArrayList<>();
        int max = Integer.max(p1.getScore(),Integer.max(p2.getScore(),Integer.max(p3.getScore(),p4.getScore())));

        view.getPlayersTurn().setFont(new Font(view.getPlayersTurn().getName(),Font.PLAIN,15));

        for(int i=0;i<4;i++){
            if(getPlayer(i).getScore() == max) winners.add(i+1);
        }

        if(winners.size() == 1){
            view.getPlayersTurn().setText("Winner is Player: " + winners.get(0));
        }else{
            String Players = "";
            for(Integer p : winners){
                Players += " " + p.toString();
            }

            view.getPlayersTurn().setText("Tie between Players: "+ Players);
        }
        view.disableAllAreas();
        view.getEndTurn().addActionListener(e -> {
            view.dispatchEvent(new WindowEvent(view,WindowEvent.WINDOW_CLOSED));
            new Controller();
        });

    }
    /**
     * <b>Accessor</b>
     * Finds the score for every player.
     * <b>Postcondtion</b>
     * Changes the score attribute in players.
     * */
    private void setScores() {

        int[][] statues = new int[4][2];

        statues[0] = p1.findStatues();
        statues[1] = p2.findStatues();
        statues[2] = p3.findStatues();
        statues[3] = p4.findStatues();


        for(int i=0;i<2;i++) {
            int max = Integer.max(statues[0][i], Integer.max(statues[1][i], Integer.max(statues[2][i], statues[3][i])));
            int min = Integer.min(statues[0][i], Integer.min(statues[1][i], Integer.min(statues[2][i], statues[3][i])));

            for(int j=0;j<4;j++){
                if(statues[j][i] == max) getPlayer(j).setScore(getPlayer(j).getScore() + 6);
                else if(statues[j][i] > min) getPlayer(j).setScore(getPlayer(j).getScore() + 3);
            }
        }


    }


}
