package grupp2.model;

import grupp2.controller.GameManager;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;

/**
 * The GameGrid class manages the logic (rules) of the Othello game.
 * @author Tobias
 */
public class GameGrid extends Observable{

    private static final int boardSize = 8;
    private static final int whitePlayer = 2;
    private static final int blackPlayer = 1;
    private int[][] board = new int[boardSize][boardSize];
    


    /**
     * The getBoard method is a accessor function
     * @return the int matrix which stores the markers placed on the board.
     */
    public int[][] getBoard() {
        return board;
    }
    
    /**
     * setBoard method is a accessor function which lets the caller place a marker within
     * the matrix that stores the markers placed on the board. This is used whenever
     * the users makes a valid draw.
     * @param coordinates is the parameter which is a Point with an x and a y value 
     * that represents where the draw was made.
     * @param marker is the value representing which player who made the move. 
     * The values needs to be distinct in order to identify the different players.
     */
    public void setBoard(Point coordinates, int marker) {
        board[coordinates.x][coordinates.y] = marker;
        GameManager.getInstance().setBoardNotifier(board);
    }

    /**
     * setWholeBoard method is an accessor method which lets you set the whole 
     * bord.
     * @param tempBoard the parameter is the matrix which you want to replace.
     */
    public void setWholeBoard(int[][] tempBoard) {
        board = tempBoard;
    }

    /**
     * getBoardSize method
     * @return returns the size of the board as a int.
     */
    public static int getBoardSize() {
        return boardSize;
    }
    
    /**
     * initializeBoard is a function that sets the board into the starting state
     * of a game of Othello. In other words sets the four starting markers in the
     * matrix which represent their starting possitions.
     */
    public void initializeBoard() {
        for (int i = 0; i < getBoardSize(); i++) {
            for (int j = 0; j < getBoardSize(); j++) {
                board[i][j] = 0;
            }
        }
        board[3][4] = whitePlayer;
        board[4][3] = whitePlayer;
        board[3][3] = blackPlayer;
        board[4][4] = blackPlayer;

    }
    
    /**
     * getResult method makes it possible to count the different markers currently
     * in the matrix(on the board) in able to see the score durring the 
     * running of the game. Also to be able to decide the winner of a whole game 
     * of Othello.
     * @return returns an int array storing the results of counting both markers for 
     * the two players.
     */
    public int[] getResult() {
        int[] results = new int[2];
        results[0] = 0;
        results[1] = 0;
        
        for (int i = 0; i < getBoardSize(); i++) {
            for (int j = 0; j < getBoardSize(); j++) {
                if(board[i][j] == 1)
                    results[0]++;
                if(board[i][j] == 2)
                    results[1]++;
            }
        }
        
        
        return results;
    }
    
    /**
     * checkIfInsideBoard method is used when there is a need for checking if the
     * coordinates is inside the board.
     * @param coordinates is a Point objekt which needs to contain both x and y value
     * for the possition which needs to be checked if it's inside the board.
     * @return returns either true if the possition is within the board.
     */
    private boolean checkIfInsideBoard(Point coordinates) {
        return ((coordinates.x >= 0 && coordinates.x < getBoardSize()) && (coordinates.y >= 0 && coordinates.y < getBoardSize()));

    }

    /**
     * isPossibleMove this method is used for checking if the move is a valid 
     * move by calling for functions that checks every direction.
     * @param coordinates Takes a Point objekt that needs a x and a y value, which
     * represent the place the player whishes to place it's marker.
     * @return return true if one of the possible directions are true, therefore
     * it is a valid move.
     */
    public boolean isPossibleMove(Point coordinates) {
        board = getBoard();
        boolean v1 = false, v2 = false, v3 = false, v4 = false, v5 = false,
                v6 = false, v7 = false, v8 = false;
        if (checkIfInsideBoard(coordinates) && checkIfEmptySpot(coordinates)) {
            v1 = checkRight(coordinates);
            //GameManager.printBoard(this);
            v2 = checkLeft(coordinates);
            //GameManager.printBoard(this);
            v3 = checkUp(coordinates);
            //GameManager.printBoard(this);
            v4 = checkDown(coordinates);
            //GameManager.printBoard(this);
            v5 = checkDiagonallyRightUp(coordinates);
            //GameManager.printBoard(this);
            v6 = checkDiagonallyRightDown(coordinates);
            //GameManager.printBoard(this);
            v7 = checkDiagonallyLeftUp(coordinates);
            //GameManager.printBoard(this);
            v8 = checkDiagonallyLeftDown(coordinates);
            //GameManager.printBoard(this);

        }
        return (v1 || v2 || v3 || v4 || v5 || v6 || v7 || v8);
    }

    /**
     * isGameOver method checks if the board is full or if there is any possible
     * moves left(because a game of Othello can end before the board is completely full)
     * @return returs true if the game is over and it is when the board is full or there 
     * is not any possible moves.
     */
    public boolean isGameOver() {
        /*kolla om brädet är fullt, köra en getPossibleMoves, är ArrayListen tom så 
         finns det inga drag att göra och spelet är slut.*/
        ArrayList moves = getPossibleMoves();
        return checkIfBoardIsFull() || moves.isEmpty();
    }

    /**
     * getPossibleMoves method searches for all the possible moves and adds them to an array
     * that the computer(in case one or both of the players are a CPU) can choose
     * from as it's move. 
     * @return returns a ArrayList containing all possible moves on the board.
     */
    public ArrayList<Point> getPossibleMoves() {
        ArrayList<Point> possibleDraws = new ArrayList();
        Point coordinates = new Point();
        boolean whereComputer = GameManager.getInstance().getIsComputerPlayer();
        
        // Our functions to find legal moves is constructed to flip when the player is human and when we
        // search to see if it exist any legal moves we don't want that.
        // The simpliest way to solve this was to change this variable and then change it back to its originally state in the
        // end of the function.
        if(!whereComputer)
            GameManager.getInstance().setIsComputerPlayer(true);
        for (int i = 0; i < getBoardSize(); i++) {
            for (int j = 0; j < getBoardSize(); j++) {
                coordinates.setLocation(j, i);

                if (isPossibleMove(coordinates)) {
                    possibleDraws.add(new Point(coordinates.x, coordinates.y));

                }
            }
        }

        GameManager.getInstance().setIsComputerPlayer(whereComputer);
        return possibleDraws;
    }

    /**
     * checkRight method checks from the position given to the right to investigate if
     * the move made is a valid move. 
     * @param coordinates is the Parameter where the player whishes to make it's move
     * is a Point object and needs a x and y value.
     * @return The move is valid if all the markers we have checked is the 
     * opponents and when we se another of our own marker the position of that 
     * marker must have at least another marker inbetween. All
     * the other cases will result in a illegal move and this function will then 
     * return false as it's result.
     */
    private boolean checkRight(Point coordinates) {
        //Kolla höger

        for (int i = coordinates.x + 1; i < getBoardSize(); i++) {
            if (board[i][coordinates.y] == 0) {
                return false;
            }
            if ((board[i][coordinates.y] == GameManager.getInstance().getCurrentPlayer()) && i > coordinates.x + 1) {
                if(!GameManager.getInstance().getIsComputerPlayer()){
                    for(int ii = i; ii != coordinates.x; ii--)
                        setBoard(new Point(ii, coordinates.y), GameManager.getInstance().getCurrentPlayer());
                }
                return true;
            } else if ((board[i][coordinates.y] == GameManager.getInstance().getCurrentPlayer()) && i <= coordinates.x + 1) {
                return false;
            }
        }
        return false;
    }
    
    /**
     * checkLeft method checks from the position given to the left to investigate if
     * the move made is a valid move. 
     * @param coordinates is the Parameter where the player whishes to make it's move
     * is a Point object and needs a x and y value.
     * @return The move is valid if all the markers we have checked is the 
     * opponents and when we se another of our own marker the position of that 
     * marker must have at least another marker inbetween. All
     * the other cases will result in a illegal move and this function will then 
     * return false as it's result.
     */
    private boolean checkLeft(Point coordinates) {

        for (int i = coordinates.x - 1; i >= 0; i--) {
            if (board[i][coordinates.y] == 0) {
                return false;
            }
            if ((board[i][coordinates.y] == GameManager.getInstance().getCurrentPlayer()) && i < coordinates.x - 1) {
                if(!GameManager.getInstance().getIsComputerPlayer()){
                    for(int ii = i; ii != coordinates.x; ii++)
                        setBoard(new Point(ii, coordinates.y), GameManager.getInstance().getCurrentPlayer());
                }
                return true;
            } else if ((board[i][coordinates.y] == GameManager.getInstance().getCurrentPlayer()) && i >= coordinates.x - 1) {
                return false;
            }
        }

        return false;
    }
    
    /**
     * checkDown method checks from the position given and downwards direction to investigate if
     * the move made is a valid move. 
     * @param coordinates is the Parameter where the player whishes to make it's move
     * is a Point object and needs a x and y value.
     * @return The move is valid if all the markers we have checked is the 
     * opponents and when we se another of our own marker the position of that 
     * marker must have at least another marker inbetween. All
     * the other cases will result in a illegal move and this function will then 
     * return false as it's result.
     */
    private boolean checkDown(Point coordinates) {

        for (int i = coordinates.y + 1; i < getBoardSize(); i++) {
            if (board[coordinates.x][i] == 0) {
                return false;
            }
            if ((board[coordinates.x][i] == GameManager.getInstance().getCurrentPlayer()) && i > coordinates.y + 1) {
                if(!GameManager.getInstance().getIsComputerPlayer()){
                    for(int ii = i; ii != coordinates.y; ii--)
                        setBoard(new Point(coordinates.x, ii), GameManager.getInstance().getCurrentPlayer());
                }
                return true;
            } else if ((board[coordinates.x][i] == GameManager.getInstance().getCurrentPlayer()) && i <= coordinates.y + 1) {
                return false;
            }
        }
        return false;
    }

    /**
     * checkUp method checks from the position given upwards direction to investigate if
     * the move made is a valid move. 
     * @param coordinates is the Parameter where the player whishes to make it's move
     * is a Point object and needs a x and y value.
     * @return The move is valid if all the markers we have checked is the 
     * opponents and when we se another of our own marker the position of that 
     * marker must have at least another marker inbetween. All
     * the other cases will result in a illegal move and this function will then 
     * return false as it's result.
     */
    private boolean checkUp(Point coordinates) {

        for (int i = coordinates.y - 1; i >= 0; i--) {
            if (board[coordinates.x][i] == 0) {
                return false;
            }
            if ((board[coordinates.x][i] == GameManager.getInstance().getCurrentPlayer()) && i < coordinates.y - 1) {
                if(!GameManager.getInstance().getIsComputerPlayer()){
                    for(int ii = i; ii != coordinates.y; ii++)
                        setBoard(new Point(coordinates.x, ii), GameManager.getInstance().getCurrentPlayer());
                }
                return true;
            } else if ((board[coordinates.x][i] == GameManager.getInstance().getCurrentPlayer()) && i >= coordinates.y - 1) {
                return false;
            }
        }
        return false;
    }

    /**
     * checkDiagonallyRightUp method checks from the position given in the diagonally direction
     * upwards and to the right to investigate if the move made is a valid move. 
     * @param coordinates is the Parameter where the player whishes to make it's move
     * is a Point object and needs a x and y value.
     * @return The move is valid if all the markers we have checked is the 
     * opponents and when we se another of our own marker the position of that 
     * marker must have at least another marker inbetween. All
     * the other cases will result in a illegal move and this function will then 
     * return false as it's result.
     */
    private boolean checkDiagonallyRightUp(Point coordinates) {

        int y = coordinates.y - 1;
        for (int x = coordinates.x + 1; x < getBoardSize() && y >= 0; x++) {
            if (board[x][y] == 0) {
                return false;
            }
            if ((board[x][y] == GameManager.getInstance().getCurrentPlayer()) && x > coordinates.x + 1) {
                if(!GameManager.getInstance().getIsComputerPlayer()){
                    for(int xx = x; xx != coordinates.x; xx--){
                        setBoard(new Point(xx, y), GameManager.getInstance().getCurrentPlayer());
                        y++;
                    }
                }
                return true;
            } else if ((board[x][y] == GameManager.getInstance().getCurrentPlayer()) && x <= coordinates.x + 1) {
                return false;
            }
            y--;

        }

        return false;
    }

    /**
     * checkDiagonallyRightDown method checks from the position given in the diagonally direction
     * downwards and to the right to investigate if the move made is a valid move. 
     * @param coordinates is the Parameter where the player whishes to make it's move
     * is a Point object and needs a x and y value.
     * @return The move is valid if all the markers we have checked is the 
     * opponents and when we se another of our own marker the position of that 
     * marker must have at least another marker inbetween. All
     * the other cases will result in a illegal move and this function will then 
     * return false as it's result.
     */
    private boolean checkDiagonallyRightDown(Point coordinates) {

        int y = coordinates.y + 1;
        for (int x = coordinates.x + 1; x < getBoardSize() && y < getBoardSize(); x++) {
            if (board[x][y] == 0) {
                return false;
            }
            if ((board[x][y] == GameManager.getInstance().getCurrentPlayer()) && x > coordinates.x + 1) {
                if(!GameManager.getInstance().getIsComputerPlayer()){
                    for(int xx = x; xx != coordinates.x; xx--){
                        setBoard(new Point(xx, y), GameManager.getInstance().getCurrentPlayer());
                        y--;
                    }
                }
                return true;
            } else if ((board[x][y] == GameManager.getInstance().getCurrentPlayer()) && x <= coordinates.x + 1) {
                return false;
            }
            y++;
        }

        return false;
    }

    /**
     * checkDiagonallyLeftDown method checks from the position given in the diagonally direction
     * downwards and to the left to investigate if the move made is a valid move. 
     * @param coordinates is the Parameter where the player whishes to make it's move
     * is a Point object and needs a x and y value.
     * @return The move is valid if all the markers we have checked is the 
     * opponents and when we se another of our own marker the position of that 
     * marker must have at least another marker inbetween. All
     * the other cases will result in a illegal move and this function will then 
     * return false as it's result.
     */
    private boolean checkDiagonallyLeftDown(Point coordinates) {

        int y = coordinates.y + 1;
        for (int x = coordinates.x - 1; x >= 0 && y < getBoardSize(); x--) {
            if (board[x][y] == 0) {
                return false;
            }
            if ((board[x][y] == GameManager.getInstance().getCurrentPlayer()) && x < coordinates.x - 1) {
                if(!GameManager.getInstance().getIsComputerPlayer()){
                    for(int xx = x; xx != coordinates.x; xx++){
                        setBoard(new Point(xx, y), GameManager.getInstance().getCurrentPlayer());
                        y--;
                    }
                }
                return true;
            } else if ((board[x][y] == GameManager.getInstance().getCurrentPlayer()) && x >= coordinates.x - 1) {
                return false;
            }
            y++;
        }

        return false;
    }

    /**
     * checkDiagonallyLeftUp method checks from the position given in the diagonally direction
     * upwards and to the left to investigate if the move made is a valid move. 
     * @param coordinates is the Parameter where the player whishes to make it's move
     * is a Point object and needs a x and y value.
     * @return The move is valid if all the markers we have checked is the 
     * opponents and when we se another of our own marker the position of that 
     * marker must have at least another marker inbetween. All
     * the other cases will result in a illegal move and this function will then 
     * return false as it's result.
     */
    private boolean checkDiagonallyLeftUp(Point coordinates) {

        int y = coordinates.y - 1;
        for (int x = coordinates.x - 1; x >= 0 && y >= 0; x--) {
            if (board[x][y] == 0) {

                return false;
            }
            if ((board[x][y] == GameManager.getInstance().getCurrentPlayer()) && x < coordinates.x - 1) {
                if(!GameManager.getInstance().getIsComputerPlayer()){
                    for(int xx = x; xx != coordinates.x; xx++){
                        setBoard(new Point(xx, y), GameManager.getInstance().getCurrentPlayer());
                        y++;
                    }
                }
                return true;
            } else if ((board[x][y] == GameManager.getInstance().getCurrentPlayer()) && x >= coordinates.x - 1) {

                return false;
            }
            y--;
        }

        return false;
    }

    /**
     * checkIfEmptySpot method checks if the coordinates given represent a empty
     * spot in the matrix(the board)
     * @param coordinates is a Point object which needs a x and y value
     * @return returns true if the coordinates represent a value inside the matrix
     * (board) and the position which it represent is not currently occupied by
     * another players marker.
     */
    public boolean checkIfEmptySpot(Point coordinates) {
        return ((board[coordinates.x][coordinates.y] == 0));
    }
    
    /**
     * checkIfBoardIsFull method is used to check if the matrix(board) does not
     * contain any empty possitions.
     * @return returns true if all the positions in the matrix(the board) contains
     * a marker of either player.
     */
    private boolean checkIfBoardIsFull(){
        for(int i = 0; i < getBoardSize(); i++)
            for(int j = 0; j < getBoardSize(); j++){
                if(checkIfEmptySpot(new Point(j, i)))
                    return false;
            }
        return true;
    }

}
