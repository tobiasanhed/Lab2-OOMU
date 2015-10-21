package grupp2.controller;

import grupp2.exceptions.InvalidMoveException;
import grupp2.model.GameGrid;
import grupp2.model.IPlayer;
import grupp2.view.DrawDialog;
import grupp2.view.GameFrame;
import grupp2.view.IEndDialog;
import grupp2.view.SetUpGameDialog;
import grupp2.view.WinnerDialog;
import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 * This is the controller class that runs the game and makes a bridge between the GUI and the logic
 * of the othello game.
 * @author Tobias
 */
public class GameManager implements Runnable {
    private static int currentPlayer = 1;
    private static boolean isComputer;
    private static GameGrid board = new GameGrid();
    private static Point draw = new Point();
    private static final Object coordO = new Object();
    private static final Object boardO = new Object();
    private static int notAvailableDraws = 0;
    private static IPlayer player1;
    private static IPlayer player2;
    /**
     * This is the core-method of the game which initalizes the board and players
     * and call the right methods for making draws and updating the GUI.
     */
    public static void startGame(){
        int[] results;
        
        SetUpGameDialog newGame = new SetUpGameDialog();

        final Lock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();
        lock.lock();
        try {
            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    lock.lock();
                    GameFrame.hideGameWindow();
                    ArrayList<IPlayer> players = newGame.getPlayers();
                    player1 = players.get(0);
                    player2 = players.get(1);
                    GameFrame.showGameWindow();
                    try {
                         condition.signal();
                    } finally {
                        lock.unlock();
                    }

                }
            });


        try {
            condition.await();
        } catch (InterruptedException ex) {
            Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        }finally {
            lock.unlock();
        }
       
        
        
        board.initializeBoard();
        GameFrame.updateBoard();
        
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
                }else{
                    try {
                        throw new InvalidMoveException("Illegal move.");
                    } catch (InvalidMoveException ex) {
                        Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
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
                }else{
                    try {
                        throw new InvalidMoveException("Illegal move.");
                    } catch (InvalidMoveException ex) {
                        Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
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
    
    public static ArrayList<IPlayer> getPlayers(){
        ArrayList<IPlayer> players = new ArrayList();
        players.add(player1);
        players.add(player2);
        return players;
    }
    
    /**
     * This function is called by the GUI and notifies the getCoord function whichs is called
     * by the humanplayer. It makes it possible to get a draw from the GUI to the logic of the game.
     * @param draw The coordinates of the draw.
     */
    public static void setCoord(Point draw){
        synchronized(coordO){
            GameManager.draw = draw;
            coordO.notify();
        }
    }
    
    /**
     * This function waits for the setCoord which is called when a draw is made on the GUI and then returns
     * the coordinates to the logic of the game.
     * @return The coordinates of the draw.
     */
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
    
    /**
     * This method is called from the logic of the game when the board-matrix has been updated and
     * the notifies the getBoardNotifier which returns the matrix to the GUI.
     * @param board A matrix representing the board of the game.
     */
    public static void setBoardNotifier(int[][] board){
        synchronized(boardO){
            GameManager.board.setWholeBoard(board);
            boardO.notify();
        }
    }
    
    /**
     * This method is called from the GUI and waits for the setBoardNotifier to signal that it's been updated.
     * @return A matrix representing the board.
     */
    public static int[][] getBoardNotifier(){
        synchronized(boardO){
          
            return GameManager.board.getBoard();
        }
    }
    
    /**
     * Calls the logic of the game to calculate the scores of the game and returns it as an array.
     * @return An array with the scores.
     */
    public static int[] getResult(){
        return board.getResult();
    }
    
    /**
     * Calls the logic of the game to get the matrix that represents the board.
     * @return A matrix that represents the board.
     */
    public static int[][] getBoard(){
        return(board.getBoard());
    }
    
    /**
     * Returns the marker of the current player.
     * @return An int which is the current players marker.
     */
    public static int getCurrentPlayer(){
        return currentPlayer;
    }
    
    /**
     * A function that sets the boolean variable isComputer which represents if it's a
     * computer player or not.
     * @param state A boolean value that you want to be the new state.
     */
    public static void setIsComputerPlayer(boolean state){
       isComputer = state;
    }

    /**
     * Takes in a coordinate and runs the test to see if it's a legal draw. 
     * @param draw A Point representing the coordinate.
     * @return A boolean value.
     */
    public static boolean isPossibleDraw(Point draw){
        return board.isPossibleMove(draw);
    }
    
    /**
     * Returns the result from getPossibleMoves which lays in the logic of the gameGrid.
     * @return An ArrayList with coordinates of the legal moves.
     */
    public static ArrayList getPossibleDraws(){
        return board.getPossibleMoves();
    }
    
    /**
     * Returns the boolean value of the variable isComputer.
     * @return A boolean value.
     */
    public static boolean getIsComputerPlayer(){
        return isComputer;
    }
    
    /**
     * This method starts the game again.
     */
    public static void resetGame(){
        startGame();
    }
    
    /**
     * This function is just for testing the logic of the game and is not used in the final game.
     * @param board An object of the GameGrid class.
     */
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

    /**
     * Overrides the run-method of the Runnable interface.
     */
    @Override
    public void run() {
        GameManager.startGame();
    }
}
