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
        boolean v1 = false, v2 = false, v3 = false, v4 = false, v5 = false,
                v6 = false, v7 = false, v8 = false;
        if(checkIfInsideBoard(coordinates) && checkIfEmptySpot(coordinates)){
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
    
    public boolean isGameOver(){
        return false;
    }
    
    public ArrayList<Point> getPossibleMoves(){
        ArrayList<Point> possibleDraws = new ArrayList();
        Point coordinates = new Point();
        GameGrid temp = this;
        for(int i = 0; i < getBoardSize(); i++){
            for(int j = 0; j < getBoardSize(); j++){
                coordinates.setLocation(j, i);
                
                if(isPossibleMove(coordinates)){
                    this.board = temp.board;
                    possibleDraws.add(coordinates);

                }
            }
        }
        
        return possibleDraws;
    }
    
    private boolean checkRight(Point coordinates){
        //Kolla höger
        CareTaker h = new CareTaker();
        h.setMemento(this.createMemento());
        Memento temp = h.getMemento();
        for(int i = coordinates.x + 1; i < getBoardSize(); i++){
            if(board[i][coordinates.y] == 0){
                this.board = temp.getState();
                return false;
            }
            if((board[i][coordinates.y] == GameManager.getCurrentPlayer()) && i > coordinates.x + 1)
                return true;
            else if((board[i][coordinates.y] == GameManager.getCurrentPlayer()) && i <= coordinates.x + 1){
                this.board = temp.getState();
                return false;
            }
            this.board[i][coordinates.y] = GameManager.getCurrentPlayer();
        }
        this.board = temp.getState();
        return false;
    }
    
    private boolean checkLeft(Point coordinates){
        CareTaker h = new CareTaker();
        h.setMemento(this.createMemento());
        Memento temp = h.getMemento();
        
        for(int i = coordinates.x - 1; i >= 0; i--){
            if(board[i][coordinates.y] == 0){
                board =  temp.getState();
                return false;
            }
            if((board[i][coordinates.y] == GameManager.getCurrentPlayer()) && i < coordinates.x - 1){

                return true;   
            }
            else if((board[i][coordinates.y] == GameManager.getCurrentPlayer()) && i >= coordinates.x - 1){
                board =  temp.getState();
                return false;
            }
            setBoard(coordinates, GameManager.getCurrentPlayer());
        }
 
        board = temp.getState();
        return false;   
    }
    
    
    private boolean checkDown(Point coordinates){
        CareTaker h = new CareTaker();
        h.setMemento(this.createMemento());
        Memento temp = h.getMemento();
        
        for(int i = coordinates.y + 1; i < getBoardSize(); i++){
            if(board[coordinates.x][i] == 0){
                this.board = temp.getState();
                return false;
            }
            if((board[coordinates.x][i] == GameManager.getCurrentPlayer()) && i > coordinates.y + 1)
                return true;
            else if((board[coordinates.x][i] == GameManager.getCurrentPlayer()) && i <= coordinates.y + 1){
                this.board = temp.getState();
                return false;
            }
            this.board[coordinates.x][i] = GameManager.getCurrentPlayer();
        }
        this.board = temp.getState();
        return false;   
    }
    
    
    private boolean checkUp(Point coordinates){
        CareTaker h = new CareTaker();
        h.setMemento(this.createMemento());
        Memento temp = h.getMemento();
        for(int i = coordinates.y - 1; i >= 0; i--){
            if(board[coordinates.x][i] == 0){
                this.board = temp.getState();
                return false;
            }
            if((board[coordinates.x][i] == GameManager.getCurrentPlayer()) && i < coordinates.y - 1)
                return true;
            else if((board[coordinates.x][i] == GameManager.getCurrentPlayer()) && i >= coordinates.y - 1){
                this.board = temp.getState();
                return false;
            }
            this.board[coordinates.x][i] = GameManager.getCurrentPlayer();
        }
        this.board = temp.getState();
        return false; 
    }
    
    
    private boolean checkDiagonallyRightUp(Point coordinates){
        CareTaker h = new CareTaker();
        h.setMemento(this.createMemento());
        Memento temp = h.getMemento();
        int y = coordinates.y -1;
          for(int x = coordinates.x + 1; x < getBoardSize() && y >= 0; x++){
            if(board[x][y] == 0){
                this.board = temp.getState();
                return false;
            }
            if((board[x][y] == GameManager.getCurrentPlayer()) && x > coordinates.x + 1)
                return true;
            else if((board[x][y] == GameManager.getCurrentPlayer()) && x <= coordinates.x + 1){
                this.board = temp.getState();
                return false;
            }
            this.board[x][y] = GameManager.getCurrentPlayer();
            y--;
            
        }
         this.board = temp.getState();
        return false; 
    }
    
    
    private boolean checkDiagonallyRightDown(Point coordinates){
        CareTaker h = new CareTaker();
        h.setMemento(this.createMemento());
        Memento temp = h.getMemento();
        
         int y = coordinates.y + 1;
          for(int x = coordinates.x + 1; x < getBoardSize() && y < getBoardSize(); x++){
            if(board[x][y] == 0){
                this.board = temp.getState();
                return false;
            }
            if((board[x][y] == GameManager.getCurrentPlayer()) && x > coordinates.x + 1)
                return true;
            else if((board[x][y] == GameManager.getCurrentPlayer()) && x <= coordinates.x + 1){
                this.board = temp.getState();
                return false;
            }
            this.board[x][y] = GameManager.getCurrentPlayer();
            y++;
        }
          this.board = temp.getState();
        return false; 
    }
    
    
    private boolean checkDiagonallyLeftDown(Point coordinates){
        CareTaker h = new CareTaker();
        h.setMemento(this.createMemento());
        Memento temp = h.getMemento();
        int y = coordinates.y + 1;
          for(int x = coordinates.x - 1; x >= 0 && y < getBoardSize(); x--){
            if(board[x][y] == 0){
                this.board = temp.getState();
                return false;
            }
            if((board[x][y] == GameManager.getCurrentPlayer()) && x < coordinates.x - 1)
                return true;
            else if((board[x][y] == GameManager.getCurrentPlayer()) && x >= coordinates.x - 1){
                this.board = temp.getState();
                return false;
            }
            this.board[x][y] = GameManager.getCurrentPlayer();
            y++;
        }
          this.board = temp.getState();
        return false; 
    }
    
    
    private boolean checkDiagonallyLeftUp(Point coordinates){
        CareTaker h = new CareTaker();
        h.setMemento(this.createMemento());
        Memento temp = h.getMemento();
        int y = coordinates.y - 1;
          for(int x = coordinates.x - 1; x >= 0 && y >= 0; x--){
            if(board[x][y] == 0){
                this.board = temp.getState();
                return false;
            }
            if((board[x][y] == GameManager.getCurrentPlayer()) && x < coordinates.x - 1)
                return true;
            else if((board[x][y] == GameManager.getCurrentPlayer()) && x >= coordinates.x - 1){
                this.board = temp.getState();
                return false;
            }
            this.board[x][y] = GameManager.getCurrentPlayer();
            y--;
        }
          this.board = temp.getState();
        return false; 
    }
    
    public boolean checkIfEmptySpot(Point coordinates){
        return ((board[coordinates.x][coordinates.y]==0));
    }
    
    public Memento createMemento(){
        return new Memento(board);
    }
    
    public void setMemento(Memento memento){
        board = memento.getState();
    }
}
