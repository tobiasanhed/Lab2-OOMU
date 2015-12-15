package grupp2.controller;

import grupp2.exceptions.InvalidMoveException;
import grupp2.model.GameGrid;
import grupp2.model.IPlayer;
import grupp2.view.DrawDialog;
import grupp2.view.ErrorDialog;
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
    private int currentPlayer = 1;
    private boolean isComputer;
    private GameGrid board = new GameGrid();
    private Point draw = new Point();
    private final Object coordO = new Object();
    private final Object boardO = new Object();
    private int notAvailableDraws = 0;
    private IPlayer player1;
    private IPlayer player2;
    
    private static final GameManager INSTANCE = new GameManager();
    
    private GameManager (){
        
    }
    public static GameManager getInstance(){
        return INSTANCE;
    }
    /**
     * This is the core-method of the game which initalizes the board and players
     * and call the right methods for making draws and updating the GUI.
     */
    public void startGame(){
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
            
            // If none of the players where able to make a draw we will break this loop and the game will finish.
            if(notAvailableDraws > 1)
                break;
            
            while (true) {
                if(board.isGameOver()){
                    notAvailableDraws++; // This player was not able to make a draw and the turn goes to the other player.
                    break;
                }
                draw = player1.getDraw();
                
                notAvailableDraws = 0; // When a player makes a move we reset this variable.
                if(isComputer){ // If it's the computer that plays we want to make the illusion that it thinks.
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
                        new ErrorDialog("Illegal move!");
                        throw new InvalidMoveException();
                    } catch (InvalidMoveException ex) {
                        Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }

            currentPlayer = player2.getMarkerID();
            isComputer = player2.getIsComputer();
            
            // If none of the players where able to make a draw we will break this loop and the game will finish.
            if(notAvailableDraws > 1)
                break;            
            
            while (true) {
                if(board.isGameOver()){
                    notAvailableDraws++; // This player was not able to make a draw and the turn goes to the other player.
                    break;
                }
                draw = player2.getDraw();
                
                notAvailableDraws = 0; // When a player makes a move we reset this variable.
                if(isComputer){ // If it's the computer that plays we want to make the illusion that it thinks.
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
                        new ErrorDialog("Illegal move!");
                        throw new InvalidMoveException();
                    } catch (InvalidMoveException ex) {
                        Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                   
            }
        }
        results = getResult();
        
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
    
    /**
     * This function puts the players in a ArrayList and returns it to the caller. Is called from the GameFrame to put the names 
     * in the resultfield.
     * @return An arraylist with the current players.
     */
    public ArrayList<IPlayer> getPlayers(){
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
    public void setCoord(Point draw){
        synchronized(coordO){
            this.draw = draw;
            coordO.notify();
        }
    }
    
    /**
     * This function waits for the setCoord which is called when a draw is made on the GUI and then returns
     * the coordinates to the logic of the game.
     * @return The coordinates of the draw.
     */
    public Point getCoord(){
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
    public void setBoardNotifier(int[][] board){
        synchronized(boardO){
            this.board.setWholeBoard(board);
            boardO.notify();
        }
    }
    
    /**
     * This method is called from the GUI and waits for the setBoardNotifier to signal that it's been updated.
     * @return A matrix representing the board.
     */
    public int[][] getBoardNotifier(){
        synchronized(boardO){
          
            return board.getBoard();
        }
    }
    
    /**
     * Calls the logic of the game to calculate the scores of the game and returns it as an array.
     * @return An array with the scores.
     */
    public int[] getResult(){
        return board.getResult();
    }
    
    /**
     * Calls the logic of the game to get the matrix that represents the board.
     * @return A matrix that represents the board.
     */
    public int[][] getBoard(){
        return(board.getBoard());
    }
    
    /**
     * Returns the marker of the current player.
     * @return An int which is the current players marker.
     */
    public int getCurrentPlayer(){
        return currentPlayer;
    }
    
    /**
     * A function that sets the boolean variable isComputer which represents if it's a
     * computer player or not.
     * @param state A boolean value that you want to be the new state.
     */
    public void setIsComputerPlayer(boolean state){
       isComputer = state;
    }

    /**
     * Takes in a coordinate and runs the test to see if it's a legal draw. 
     * @param draw A Point representing the coordinate.
     * @return A boolean value.
     */
    public boolean isPossibleDraw(Point draw){
        return board.isPossibleMove(draw);
    }
    
    /**
     * Returns the result from getPossibleMoves which lays in the logic of the gameGrid.
     * @return An ArrayList with coordinates of the legal moves.
     */
    public ArrayList getPossibleDraws(){
        return board.getPossibleMoves();
    }
    
    /**
     * Returns the boolean value of the variable isComputer.
     * @return A boolean value.
     */
    public boolean getIsComputerPlayer(){
        return isComputer;
    }
    
    /**
     * This method starts the game again.
     */
    public void resetGame(){
        startGame();
    }
    
    /**
     * This function is just for testing the logic of the game and is not used in the final game.
     * @param board An object of the GameGrid class.
     */
    public void printBoard(GameGrid board){
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
        startGame();
    }
}
