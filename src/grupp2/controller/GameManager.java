/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.controller;

import grupp2.model.GameGrid;
import grupp2.model.HumanPlayer;
import grupp2.model.IPlayer;
import java.awt.Point;

/**
 *
 * @author S142015
 */
public class GameManager {
    private static int currentPlayer = 1;
    
    public void startGame(){
        GameGrid board = new GameGrid();
        board.initializeBoard();
        
        IPlayer player1 = new HumanPlayer();
        IPlayer player2 = new HumanPlayer();
        Point draw;
        
        player1.setMarkerID(1);
        player2.setMarkerID(2);
        

        while(true){
            printBoard(board);
            currentPlayer = player1.getMarkerID();
            draw = player1.getDraw();
            board.setBoard(draw, currentPlayer);
            
            printBoard(board);
            currentPlayer = player2.getMarkerID();
            draw = player2.getDraw(); 
            board.setBoard(draw, currentPlayer);
        }
            
    }
    
    public static int getCurrentPlayer(){
        return currentPlayer;
    }
    
    public void printBoard(GameGrid board){
        int[][] boardArray = board.getBoard();
        for(int i = 0; i < board.getBoardSize(); i++){
            for(int j = 0; j < board.getBoardSize(); j++){
                System.out.print("|");
                if(boardArray[i][j] == 1)
                    System.out.print("B");
                else if(boardArray[i][j] == 2)
                    System.out.print("W");
                else
                    System.out.print(" ");
            }
            System.out.print("|\n");
        }
    }
}
