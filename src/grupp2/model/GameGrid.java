/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.model;

import grupp2.controller.GameManager;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author S142015
 */
public class GameGrid {
    private final static int boardSize = 8;
    private final static int whitePlayer = 2;
    private final static int blackPlayer = 1;
    private static int [][]board = new int[boardSize][boardSize];
    
    public int[][] getBoard(){
        return board;
    }
    
    public void setBoard(Point coordinates, int marker){
        board[coordinates.x][coordinates.y] = marker;
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
    
    private boolean checkIfInsideBoard(Point coordinates){
        return((coordinates.x >= 0 && coordinates.x < getBoardSize())&&(coordinates.y >= 0 && coordinates.y < getBoardSize()));

    }
    
    public boolean isPossibleMove(Point coordinates){
        board = getBoard();
        if(checkIfInsideBoard(coordinates)){
            if(checkRight(coordinates)|| checkLeft(coordinates) || checkUp(coordinates) || checkDown(coordinates)
                    || checkDiagonallyRightUp(coordinates) || checkDiagonallyRightDown(coordinates)
                    || checkDiagonallyLeftUp(coordinates) || checkDiagonallyLeftDown(coordinates))
                return true;  
        }
        return false;
    }
    
    public boolean isGameOver(){
        return false;
    }
    
    public ArrayList<Point> getPossibleMoves(){
        ArrayList<Point> possibleDraws = new ArrayList();
        Point coordinates = new Point();
        
        for(int i = 0; i < getBoardSize(); i++){
            for(int j = 0; j < getBoardSize(); j++){
                coordinates.setLocation(i, j);
                if(isPossibleMove(coordinates))
                    possibleDraws.add(coordinates);
            }
        }
        
        return possibleDraws;
    }
    
    private boolean checkRight(Point coordinates){
        //Kolla höger
        for(int i = coordinates.x + 1; i < getBoardSize(); i++){
            if(board[i][coordinates.y] == 0)
                return false;
            if((board[i][coordinates.y] == GameManager.getCurrentPlayer()) && i > coordinates.x + 1)
                return true;
            else if((board[i][coordinates.y] == GameManager.getCurrentPlayer()) && i <= coordinates.x + 1)
                return false;
        }
        return false;
    }
    
    private boolean checkLeft(Point coordinates){
        for(int i = coordinates.x - 1; i >= 0; i--){
            if(board[i][coordinates.y] == 0)
                return false;
            if((board[i][coordinates.y] == GameManager.getCurrentPlayer()) && i < coordinates.x - 1)
                return true;
            else if((board[i][coordinates.y] == GameManager.getCurrentPlayer()) && i >= coordinates.x - 1)
                return false;
        }
        return false;   
    }
    
    
    private boolean checkDown(Point coordinates){
        for(int i = coordinates.y + 1; i < getBoardSize(); i++){
            if(board[coordinates.x][i] == 0)
                return false;
            if((board[coordinates.x][i] == GameManager.getCurrentPlayer()) && i > coordinates.y + 1)
                return true;
            else if((board[coordinates.x][i] == GameManager.getCurrentPlayer()) && i <= coordinates.y + 1)
                return false;
        }
        return false;   
    }
    
    
    private boolean checkUp(Point coordinates){
        for(int i = coordinates.y - 1; i >= getBoardSize(); i--){
            if(board[coordinates.x][i] == 0)
                return false;
            if((board[coordinates.x][i] == GameManager.getCurrentPlayer()) && i < coordinates.y - 1)
                return true;
            else if((board[coordinates.x][i] == GameManager.getCurrentPlayer()) && i >= coordinates.y - 1)
                return false;
        }
        return false; 
    }
    
    
    private boolean checkDiagonallyRightUp(Point coordinates){
        return true;
    }
    
    
    private boolean checkDiagonallyRightDown(Point coordinates){
        return true;
    }
    
    
    private boolean checkDiagonallyLeftDown(Point coordinates){
        return true;
    }
    
    
    private boolean checkDiagonallyLeftUp(Point coordinates){
        return true;
    }
}
