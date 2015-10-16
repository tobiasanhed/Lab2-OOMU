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
import grupp2.view.GameFrame;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author S142015
 */
public class GameManager implements Runnable {
    private static int currentPlayer = 1;
    private static boolean isComputer;
    private static GameGrid board = new GameGrid();
    private static Point draw = new Point();
    private static final Object coordO = new Object();
    private static final Object boardO = new Object();
    
    public static void startGame(){
       
        board.initializeBoard();
        
        IPlayer player1 = new HumanPlayer();
        IPlayer player2 = new ComputerPlayer();
        
        player1.setMarkerID(1);
        player2.setMarkerID(2);
        

        while(true){
            currentPlayer = player1.getMarkerID();
            isComputer = player1.getIsComputer();

            printBoard(board);
            //if(board.isGameOver())
              //  break;
            while (true) {
                draw = player1.getDraw();
                if(draw == null)
                    break;
                isComputer = false;
                System.out.println(draw.x + "" + draw.y);
                if (board.isPossibleMove(draw)) {
                    board.setBoard(draw, currentPlayer);
                    GameFrame.updateBoard();
                    break;
                } else {
                    System.out.println("No!");
                }
            }

            currentPlayer = player2.getMarkerID();
            isComputer = player2.getIsComputer();
            
            printBoard(board);
            if(board.isGameOver())
                break;
            while (true) {
                draw = player2.getDraw();
                if(draw == null)
                    break;
                isComputer = false;

                if (board.isPossibleMove(draw)) {
                    board.setBoard(draw, currentPlayer);
                    GameFrame.updateBoard();
                    break;
                } else {
                    System.out.println("No!");
                }

            }
        }
            
    }
    
    public static void setCoord(Point draw){
        synchronized(coordO){
            GameManager.draw = draw;
            coordO.notify();
        }
    }
    public static Point getCoord(){
        synchronized(coordO){
            try {
                coordO.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            return draw;
        }
    }
    
    public static void setBoardNotifier(int[][] board){
        synchronized(boardO){
            GameManager.board.setWholeBoard(board);
            boardO.notify();
        }
    }
    public static int[][] getBoardNotifier(){
        synchronized(boardO){
          
            return GameManager.board.getBoard();
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
