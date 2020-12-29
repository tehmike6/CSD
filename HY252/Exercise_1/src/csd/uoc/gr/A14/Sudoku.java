package csd.uoc.gr.A14;


import javax.swing.*;
import java.util.Random;
import java.util.stream.IntStream;

public class Sudoku {

    private static final int BOARD_SIZE = 9;
    private static final int SUBSECTION_SIZE = 3;
    private static final int BOARD_START_INDEX = 0;

    private static final int NO_VALUE = 0;
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 9;

    private static int invalidBoards = 0;

    private static int[][] board = {
            {8, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 3, 6, 0, 0, 0, 0, 0},
            {0, 7, 0, 0, 9, 0, 2, 0, 0},
            {0, 5, 0, 0, 0, 7, 0, 0, 0},
            {0, 0, 0, 0, 4, 5, 7, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 3, 0},
            {0, 0, 1, 0, 0, 0, 0, 6, 8},
            {0, 0, 8, 5, 0, 0, 0, 1, 0},
            {0, 9, 0, 0, 0, 0, 4, 0, 0}
    };

    public static void main(String[] args) {
        Sudoku solver = new Sudoku();
        int N = Integer.parseInt(JOptionPane.showInputDialog(
                "Number of sudoku's to be generated.",100));
        int x = Integer.parseInt(JOptionPane.showInputDialog(
                "Number of empty cells in sudoku's.",75));
        long start = System.currentTimeMillis();
        for(int i=0;i<N;i++){
            System.out.println("Board #"+(i+1));
            solver.generateSudoku(x);
            solver.printBoard();
            solver.solve(board);
            System.out.println("Solution of Board #"+(i+1));
            solver.printBoard();
        }
        long end = System.currentTimeMillis();
        System.out.println("Empty cells per board = "+x);
        System.out.println("Valid boards created = "+N);
        System.out.println("Invalid boards created = "+invalidBoards);
        //The way the code is implemented i cannot create unsolvable sudoku's because i create a solvable
        //and then I remove random numbers from it.
        System.out.println("Unsolvable boards created = None");
        System.out.println("Elapsed time in seconds: "+ ((end - start) / 1000F));




    }

    private static void init(int[][] a){
        for(int i =0;i<9;i++){
            for(int j=0;j<9;j++){
                a[i][j] = 0;
            }
        }

    }
/*
    private static void printB(int[][] a){
        for(int i =0;i<9;i++){
            for(int j=0;j<9;j++){
                System.out.print(a[i][j]);
            }
            System.out.println("");
        }
    }
*/

    private int[][] generateSudoku(int x){
        Sudoku sud = new Sudoku();
        int[][] brd = new int[9][9];


        Random rg = new Random();
        do{
            init(brd);
            for(int i=0;i<9;i++){
                int row = rg.nextInt(9);
                int col = rg.nextInt(9);
                if(brd[row][col] == 0){
                    brd[row][col] = rg.nextInt(9)+1;
                }else{
                    i++;
                    continue;
                }
            }
            board = brd;
            invalidBoards++;
        }while(!sud.isSolvableBoard());

        while(x!=0){
            int row = rg.nextInt(9);
            int col = rg.nextInt(9);
            if(brd[row][col] != 0){
                brd[row][col] = 0;
                x--;
            }
        }
        return brd;
    }


    private boolean isSolvableBoard(){
        return isValidBoard()&&solve(board);
    }

    private boolean isValidBoard(){
        //Check row,col
        int[][] brd = board;
        for(int i=0;i<9;i++){
            if(!checkRow(brd[i])) {
                return false;
            }
            if(!checkCol(brd,i)){
                return false;
            }
        }
        //check box
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(!(brd[i][j] >= 0 && brd[i][j] <10 && check_box(i,j,brd))){
                    return false;
                }
            }
        }

        return true;
    }

    private boolean check_box(int i,int j,int[][] brd){
        int n=0;
        int[] box = new int[9];
        i = i - i%3;
        j = j - j%3;

        for(int col=0;col<3;col++){
            for(int row=0;row<3;row++){
                box[n] = brd[col+i][row+j];
                n++;
            }
        }
        return checkRow(box);
    }

    private boolean checkCol(int[][] brd,int j){
        for(int i=0;i<9;i++){
            for(int k=0;k<9;k++){
                if(brd[i][j] == brd[k][j] && i!=k && brd[i][j] != 0){
                    //System.out.println("Error in column "+j+" number :"+ brd[i][j]+" found many times");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkRow(int[] row){
        for(int i =0; i<row.length;i++){
            for(int j=0;j<row.length;j++){
                if(row[i] == row[j] && i != j && row[i] != 0){
                    //System.out.println("Error number :"+ row[i]+" found many times in a row");
                    return false;
                }
            }
        }
        return true;
    }

    private void printBoard() {
        for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
            for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
                System.out.print(board[row][column] + " ");
            }
            System.out.println();
        }
    }

    private boolean solve(int[][] board) {
        for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
            for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
                if (board[row][column] == NO_VALUE) {
                    for (int k = MIN_VALUE; k <= MAX_VALUE; k++) {
                        board[row][column] = k;
                        if (isValid(board, row, column) && solve(board)) {
                            return true;
                        }
                        board[row][column] = NO_VALUE;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int[][] board, int row, int column) {
        return rowConstraint(board, row) &&
                columnConstraint(board, column) &&
                subsectionConstraint(board, row, column);
    }

    private boolean subsectionConstraint(int[][] board, int row, int column) {
        boolean[] constraint = new boolean[BOARD_SIZE];
        int subsectionRowStart = (row / SUBSECTION_SIZE) * SUBSECTION_SIZE;
        int subsectionRowEnd = subsectionRowStart + SUBSECTION_SIZE;

        int subsectionColumnStart = (column / SUBSECTION_SIZE) * SUBSECTION_SIZE;
        int subsectionColumnEnd = subsectionColumnStart + SUBSECTION_SIZE;

        for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
            for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
                if (!checkConstraint(board, r, constraint, c)) return false;
            }
        }
        return true;
    }

    private boolean columnConstraint(int[][] board, int column) {
        boolean[] constraint = new boolean[BOARD_SIZE];
        return IntStream.range(BOARD_START_INDEX, BOARD_SIZE)
                .allMatch(row -> checkConstraint(board, row, constraint, column));
    }

    private boolean rowConstraint(int[][] board, int row) {
        boolean[] constraint = new boolean[BOARD_SIZE];
        return IntStream.range(BOARD_START_INDEX, BOARD_SIZE)
                .allMatch(column -> checkConstraint(board, row, constraint, column));
    }

    private boolean checkConstraint(int[][] board, int row, boolean[] constraint, int column) {
        if (board[row][column] != NO_VALUE) {
            if (!constraint[board[row][column] - 1]) {
                constraint[board[row][column] - 1] = true;
            } else {
                return false;
            }
        }
        return true;
    }
}