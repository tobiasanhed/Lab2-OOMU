/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.model;

/**
 *
 * @author S142015
 */
public class GameGrid {
    private final static int boardSize = 8;
    private final static int whitePlayer = 1;
    private final static int blackPlayer = 2;
    private static int [][]board = new int[boardSize][boardSize];
    
    public int[][] getBoard(){
        return board;
    }
    
    public void setBoard(int boardCoordinates[], int marker){
        board[boardCoordinates[0]][boardCoordinates[1]] = marker;
    }
    
    public int getBoardSize(){
        return boardSize;
    }
    
    public void initializeBoard(){
        for(int i = 0; i < getBoardSize(); i++){
            for(int j = 0; j < getBoardSize(); j++){
                board[i][j] = 0;
            }
        }
        board[3][4] = whitePlayer;
        board[4][3] = whitePlayer;
        board[3][3] = blackPlayer;
        board[4][4] = blackPlayer;
        
    }
    
    public void resetBoard(){
        this.initializeBoard();
    }
    
    public int getResult(){
        return 0;
    }
    
    public boolean isPossibleMove(){
        return true;
    }
    
    public boolean isGameOver(){
        return false;
    }
    
    public int[] getPossibleMove(){
        int[] draw = {1,1};
        return draw;
    }
}
