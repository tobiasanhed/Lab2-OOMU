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
public class GameManager implements Runnable {
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
            currentPlayer = player1.getMarkerID();

            printBoard(board);
            
            while (true) {
                draw = player1.getDraw();
                if (board.isPossibleMove(draw)) {
                    board.setBoard(draw, currentPlayer);
                    break;
                } else {
                    System.out.println("No!");
                }
            }

            currentPlayer = player2.getMarkerID();

            printBoard(board);
            
            while (true) {
                draw = player2.getDraw();
                if (board.isPossibleMove(draw)) {
                    board.setBoard(draw, currentPlayer);
                    break;
                } else {
                    System.out.println("No!");
                }

            }
        }
            
    }
    
    public static int getCurrentPlayer(){
        return currentPlayer;
    }
    
    public static void printBoard(GameGrid board){
        int[][] boardArray = board.getBoard();
        for(int i = 0; i < GameGrid.getBoardSize(); i++){
            for(int j = 0; j < GameGrid.getBoardSize(); j++){
                System.out.print("|");
                if(boardArray[j][i] == 1)
                    System.out.print("B");
                else if(boardArray[j][i] == 2)
                    System.out.print("W");
                else
                    System.out.print(" ");
            }
            System.out.print("|\n");
        }
    }

    @Override
    public void run() {
        this.startGame();
    }
}
