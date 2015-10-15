/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.controller;

import grupp2.model.ComputerPlayer;
import grupp2.model.GameGrid;
import grupp2.model.HumanPlayer;
import grupp2.model.IPlayer;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author S142015
 */
public class GameManager implements Runnable {
    private static int currentPlayer = 1;
    private static boolean isComputer;
    private static GameGrid board = new GameGrid();
    
    public static void startGame(){
        board.initializeBoard();
        
        IPlayer player1 = new HumanPlayer();
        IPlayer player2 = new ComputerPlayer();
        Point draw;
        
        player1.setMarkerID(1);
        player2.setMarkerID(2);
        

        while(true){
            currentPlayer = player1.getMarkerID();
            isComputer = player1.getIsComputer();

            printBoard(board);
            
            while (true) {
                draw = player1.getDraw();
                isComputer = false;
                
                if (board.isPossibleMove(draw)) {
                    board.setBoard(draw, currentPlayer);
                    break;
                } else {
                    System.out.println("No!");
                }
            }

            currentPlayer = player2.getMarkerID();
            isComputer = player2.getIsComputer();
            
            printBoard(board);
            
            while (true) {
                draw = player2.getDraw();
                isComputer = false;

                if (board.isPossibleMove(draw)) {
                    board.setBoard(draw, currentPlayer);
                    break;
                } else {
                    System.out.println("No!");
                }

            }
        }
            
    }
    public static int[][] getBoard(){
        return(board.getBoard());
    }
    public static int getCurrentPlayer(){
        return currentPlayer;
    }
   
    public static ArrayList getPossibleDraws(){
        return board.getPossibleMoves();
    }
    
    public static boolean getIsComputerPlayer(){
        return isComputer;
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
        GameManager.startGame();
    }
}
