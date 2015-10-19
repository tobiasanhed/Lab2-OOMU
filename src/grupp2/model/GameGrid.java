/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.model;

import grupp2.controller.GameManager;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author S142015
 */
public class GameGrid extends Observable{

    private static final int boardSize = 8;
    private static final int whitePlayer = 2;
    private static final int blackPlayer = 1;
    private int[][] board = new int[boardSize][boardSize];
    



    public int[][] getBoard() {
        return board;
    }

    public void setBoard(Point coordinates, int marker) {
        board[coordinates.x][coordinates.y] = marker;
        GameManager.setBoardNotifier(board);
    }

    public void setWholeBoard(int[][] tempBoard) {
        board = tempBoard;
    }

    public static int getBoardSize() {
        return boardSize;
    }

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

    private boolean checkIfInsideBoard(Point coordinates) {
        return ((coordinates.x >= 0 && coordinates.x < getBoardSize()) && (coordinates.y >= 0 && coordinates.y < getBoardSize()));

    }


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

    public boolean isGameOver() {
        /*kolla om brädet är fullt, köra en getPossibleMoves, är ArrayListen tom så 
         finns det inga drag att göra och spelet är slut.*/
        ArrayList moves = getPossibleMoves();
        return checkIfBoardIsFull() || moves.isEmpty();
    }

    public ArrayList<Point> getPossibleMoves() {
        ArrayList<Point> possibleDraws = new ArrayList();
        Point coordinates = new Point();
        boolean whereComputer = GameManager.getIsComputerPlayer();
        
        if(!whereComputer)
            GameManager.setIsComputerPlayer(true);
        for (int i = 0; i < getBoardSize(); i++) {
            for (int j = 0; j < getBoardSize(); j++) {
                coordinates.setLocation(j, i);

                if (isPossibleMove(coordinates)) {
                    possibleDraws.add(new Point(coordinates.x, coordinates.y));

                }
            }
        }

        GameManager.setIsComputerPlayer(whereComputer);
        return possibleDraws;
    }

    private boolean checkRight(Point coordinates) {
        //Kolla höger

        for (int i = coordinates.x + 1; i < getBoardSize(); i++) {
            if (board[i][coordinates.y] == 0) {
                return false;
            }
            if ((board[i][coordinates.y] == GameManager.getCurrentPlayer()) && i > coordinates.x + 1) {
                if(!GameManager.getIsComputerPlayer()){
                    for(int ii = i; ii != coordinates.x; ii--)
                        setBoard(new Point(ii, coordinates.y), GameManager.getCurrentPlayer());
                }
                return true;
            } else if ((board[i][coordinates.y] == GameManager.getCurrentPlayer()) && i <= coordinates.x + 1) {
                return false;
            }
        }
        return false;
    }

    private boolean checkLeft(Point coordinates) {

        for (int i = coordinates.x - 1; i >= 0; i--) {
            if (board[i][coordinates.y] == 0) {
                return false;
            }
            if ((board[i][coordinates.y] == GameManager.getCurrentPlayer()) && i < coordinates.x - 1) {
                if(!GameManager.getIsComputerPlayer()){
                    for(int ii = i; ii != coordinates.x; ii++)
                        setBoard(new Point(ii, coordinates.y), GameManager.getCurrentPlayer());
                }
                return true;
            } else if ((board[i][coordinates.y] == GameManager.getCurrentPlayer()) && i >= coordinates.x - 1) {
                return false;
            }
        }

        return false;
    }

    private boolean checkDown(Point coordinates) {

        for (int i = coordinates.y + 1; i < getBoardSize(); i++) {
            if (board[coordinates.x][i] == 0) {
                return false;
            }
            if ((board[coordinates.x][i] == GameManager.getCurrentPlayer()) && i > coordinates.y + 1) {
                if(!GameManager.getIsComputerPlayer()){
                    for(int ii = i; ii != coordinates.y; ii--)
                        setBoard(new Point(coordinates.x, ii), GameManager.getCurrentPlayer());
                }
                return true;
            } else if ((board[coordinates.x][i] == GameManager.getCurrentPlayer()) && i <= coordinates.y + 1) {
                return false;
            }
        }
        return false;
    }

    private boolean checkUp(Point coordinates) {

        for (int i = coordinates.y - 1; i >= 0; i--) {
            if (board[coordinates.x][i] == 0) {
                return false;
            }
            if ((board[coordinates.x][i] == GameManager.getCurrentPlayer()) && i < coordinates.y - 1) {
                if(!GameManager.getIsComputerPlayer()){
                    for(int ii = i; ii != coordinates.y; ii++)
                        setBoard(new Point(coordinates.x, ii), GameManager.getCurrentPlayer());
                }
                return true;
            } else if ((board[coordinates.x][i] == GameManager.getCurrentPlayer()) && i >= coordinates.y - 1) {
                return false;
            }
        }
        return false;
    }

    private boolean checkDiagonallyRightUp(Point coordinates) {

        int y = coordinates.y - 1;
        for (int x = coordinates.x + 1; x < getBoardSize() && y >= 0; x++) {
            if (board[x][y] == 0) {
                return false;
            }
            if ((board[x][y] == GameManager.getCurrentPlayer()) && x > coordinates.x + 1) {
                if(!GameManager.getIsComputerPlayer()){
                    for(int xx = x; xx != coordinates.x; xx--){
                        setBoard(new Point(xx, y), GameManager.getCurrentPlayer());
                        y++;
                    }
                }
                return true;
            } else if ((board[x][y] == GameManager.getCurrentPlayer()) && x <= coordinates.x + 1) {
                return false;
            }
            y--;

        }

        return false;
    }

    private boolean checkDiagonallyRightDown(Point coordinates) {

        int y = coordinates.y + 1;
        for (int x = coordinates.x + 1; x < getBoardSize() && y < getBoardSize(); x++) {
            if (board[x][y] == 0) {
                return false;
            }
            if ((board[x][y] == GameManager.getCurrentPlayer()) && x > coordinates.x + 1) {
                if(!GameManager.getIsComputerPlayer()){
                    for(int xx = x; xx != coordinates.x; xx--){
                        setBoard(new Point(xx, y), GameManager.getCurrentPlayer());
                        y--;
                    }
                }
                return true;
            } else if ((board[x][y] == GameManager.getCurrentPlayer()) && x <= coordinates.x + 1) {
                return false;
            }
            y++;
        }

        return false;
    }

    private boolean checkDiagonallyLeftDown(Point coordinates) {

        int y = coordinates.y + 1;
        for (int x = coordinates.x - 1; x >= 0 && y < getBoardSize(); x--) {
            if (board[x][y] == 0) {
                return false;
            }
            if ((board[x][y] == GameManager.getCurrentPlayer()) && x < coordinates.x - 1) {
                if(!GameManager.getIsComputerPlayer()){
                    for(int xx = x; xx != coordinates.x; xx++){
                        setBoard(new Point(xx, y), GameManager.getCurrentPlayer());
                        y--;
                    }
                }
                return true;
            } else if ((board[x][y] == GameManager.getCurrentPlayer()) && x >= coordinates.x - 1) {
                return false;
            }
            y++;
        }

        return false;
    }

    private boolean checkDiagonallyLeftUp(Point coordinates) {

        int y = coordinates.y - 1;
        for (int x = coordinates.x - 1; x >= 0 && y >= 0; x--) {
            if (board[x][y] == 0) {

                return false;
            }
            if ((board[x][y] == GameManager.getCurrentPlayer()) && x < coordinates.x - 1) {
                if(!GameManager.getIsComputerPlayer()){
                    for(int xx = x; xx != coordinates.x; xx++){
                        setBoard(new Point(xx, y), GameManager.getCurrentPlayer());
                        y++;
                    }
                }
                return true;
            } else if ((board[x][y] == GameManager.getCurrentPlayer()) && x >= coordinates.x - 1) {

                return false;
            }
            y--;
        }

        return false;
    }

    public boolean checkIfEmptySpot(Point coordinates) {
        return ((board[coordinates.x][coordinates.y] == 0));
    }

    private boolean checkIfBoardIsFull(){
        for(int i = 0; i < getBoardSize(); i++)
            for(int j = 0; j < getBoardSize(); j++){
                if(checkIfEmptySpot(new Point(j, i)))
                    return false;
            }
        return true;
    }

}
