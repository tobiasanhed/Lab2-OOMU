/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.controller;

import grupp2.exceptions.InvalidMoveException;
import grupp2.model.ComputerPlayer;
import grupp2.model.GameGrid;
import grupp2.model.HumanPlayer;
import grupp2.model.IPlayer;
import grupp2.view.DrawDialog;
import grupp2.view.GameFrame;
import grupp2.view.IEndDialog;
import grupp2.view.WinnerDialog;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

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
    private static int notAvailableDraws = 0;
    
    public static void startGame(){
        int[] results;
        
        board.initializeBoard();
        
        IPlayer player1 = new HumanPlayer("P1", 1);
        IPlayer player2 = new ComputerPlayer("P2", 2);
        
        while(true){
            currentPlayer = player1.getMarkerID();
            isComputer = player1.getIsComputer();
            
            if(notAvailableDraws > 1)
                break;
            
            while (true) {
                if(board.isGameOver()){
                    notAvailableDraws++;
                    break;
                }
                draw = player1.getDraw();
                
                notAvailableDraws = 0;
                if(isComputer){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                isComputer = false;
                
                if(board.isPossibleMove(draw)){
                    board.setBoard(draw, currentPlayer);
                    GameFrame.updateBoard();
                    break;
                }
            }

            currentPlayer = player2.getMarkerID();
            isComputer = player2.getIsComputer();
            
            if(notAvailableDraws > 1)
                break;            
            
            while (true) {
                if(board.isGameOver()){
                    notAvailableDraws++;
                    break;
                }
                draw = player2.getDraw();
                
                notAvailableDraws = 0;
                if(isComputer){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                isComputer = false;

                if(board.isPossibleMove(draw)){
                    board.setBoard(draw, currentPlayer);
                    GameFrame.updateBoard();
                    break;
                }
            }
        }
        results = GameManager.getResult();
        
        Platform.runLater(new Runnable(){

            @Override
            public void run() {
                IEndDialog endDialog;

                if(results[0] == results[1])
                    endDialog = new DrawDialog();
                else
                    endDialog = new WinnerDialog();

                endDialog.printResult(player1, player2);
            }
        });
           
    
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
    public static int[] getResult(){
        return board.getResult();
    }
    public static int[][] getBoard(){
        return(board.getBoard());
    }
    public static int getCurrentPlayer(){
        return currentPlayer;
    }
    public static void setIsComputerPlayer(boolean state){
       isComputer = state;
    }

    public static boolean isPossibleDraw(Point draw){
        return board.isPossibleMove(draw);
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
