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
    private static int [][]board = new int[boardSize][boardSize];
    
    public int[][] getBoard(){
        return board;
    }
    
    public void setBoard(int board[][]){
        
    }
    
    public int getBoardSize(){
        return boardSize;
    }
    
    public void initalizeBoard(){
        
    }
    
    public void resetBoard(){
        
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
