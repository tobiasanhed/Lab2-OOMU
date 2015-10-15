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
public class GameGrid{

    private static int boardSize = 8;
    private static int whitePlayer = 2;
    private static int blackPlayer = 1;
    private int[][] board = new int[boardSize][boardSize];
    



    public int[][] getBoard() {
        return board;
    }

    public void setBoard(Point coordinates, int marker) {
        board[coordinates.x][coordinates.y] = marker;
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

    public void resetBoard() {
        this.initializeBoard();
    }

    public int getResult() {
        return 0;
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
        return false;
    }

    public ArrayList<Point> getPossibleMoves() {
        ArrayList<Point> possibleDraws = new ArrayList();
        Point coordinates = new Point();
        GameGrid temp = this;
        for (int i = 0; i < getBoardSize(); i++) {
            for (int j = 0; j < getBoardSize(); j++) {
                coordinates.setLocation(j, i);

                if (isPossibleMove(coordinates)) {
                    //this.board = temp.board;
                    possibleDraws.add(coordinates);

                }
            }
        }

        return possibleDraws;
    }
    
    public Memento createMemento() {
        return new Memento(this);
    }

    public void restoreMemento(Memento memento) {
        GameGrid haha;
        haha = memento.getState();
        this.setWholeBoard(haha.getBoard());
    }

    private boolean checkRight(Point coordinates) {
        //Kolla höger
        CareTaker caretaker = new CareTaker();
        GameGrid originator = new GameGrid();
        originator.setWholeBoard(this.getBoard());
        caretaker.addMemento(originator.createMemento());

        for (int i = coordinates.x + 1; i < getBoardSize(); i++) {
            if (board[i][coordinates.y] == 0) {
                originator.restoreMemento(caretaker.getMemento(0));
                return false;
            }
            if ((board[i][coordinates.y] == GameManager.getCurrentPlayer()) && i > coordinates.x + 1) {
                return true;
            } else if ((board[i][coordinates.y] == GameManager.getCurrentPlayer()) && i <= coordinates.x + 1) {
                originator.restoreMemento(caretaker.getMemento(0));
                return false;
            }
            setBoard(new Point(i, coordinates.y), GameManager.getCurrentPlayer());
        }
        originator.restoreMemento(caretaker.getMemento(0));
        return false;
    }

    private boolean checkLeft(Point coordinates) {
        CareTaker caretaker = new CareTaker();
        GameGrid originator = new GameGrid();
        originator.setWholeBoard(this.getBoard());
        caretaker.addMemento(originator.createMemento());

        for (int i = coordinates.x - 1; i >= 0; i--) {
            if (board[i][coordinates.y] == 0) {
                originator.restoreMemento(caretaker.getMemento(0));
                return false;
            }
            if ((board[i][coordinates.y] == GameManager.getCurrentPlayer()) && i < coordinates.x - 1) {

                return true;
            } else if ((board[i][coordinates.y] == GameManager.getCurrentPlayer()) && i >= coordinates.x - 1) {
                originator.restoreMemento(caretaker.getMemento(0));
                return false;
            }
            setBoard(new Point(i, coordinates.y), GameManager.getCurrentPlayer());
        }

        originator.restoreMemento(caretaker.getMemento(0));
        return false;
    }

    private boolean checkDown(Point coordinates) {
        CareTaker caretaker = new CareTaker();
        GameGrid originator = new GameGrid();
        originator.setWholeBoard(this.getBoard());
        caretaker.addMemento(originator.createMemento());

        for (int i = coordinates.y + 1; i < getBoardSize(); i++) {
            if (board[coordinates.x][i] == 0) {
                originator.restoreMemento(caretaker.getMemento(0));
                return false;
            }
            if ((board[coordinates.x][i] == GameManager.getCurrentPlayer()) && i > coordinates.y + 1) {
                return true;
            } else if ((board[coordinates.x][i] == GameManager.getCurrentPlayer()) && i <= coordinates.y + 1) {
                originator.restoreMemento(caretaker.getMemento(0));
                return false;
            }
            setBoard(new Point(coordinates.x, i), GameManager.getCurrentPlayer());
        }
        originator.restoreMemento(caretaker.getMemento(0));
        return false;
    }

    private boolean checkUp(Point coordinates) {
        CareTaker caretaker = new CareTaker();
        GameGrid originator = new GameGrid();
        originator.setWholeBoard(this.getBoard());
        caretaker.addMemento(originator.createMemento());
        
        for (int i = coordinates.y - 1; i >= 0; i--) {
            if (board[coordinates.x][i] == 0) {
                originator.restoreMemento(caretaker.getMemento(0));
                return false;
            }
            if ((board[coordinates.x][i] == GameManager.getCurrentPlayer()) && i < coordinates.y - 1) {
                return true;
            } else if ((board[coordinates.x][i] == GameManager.getCurrentPlayer()) && i >= coordinates.y - 1) {
                originator.restoreMemento(caretaker.getMemento(0));
                return false;
            }
            setBoard(new Point(coordinates.x, i), GameManager.getCurrentPlayer());
        }
        originator.restoreMemento(caretaker.getMemento(0));
        return false;
    }

    private boolean checkDiagonallyRightUp(Point coordinates) {
        CareTaker caretaker = new CareTaker();
        GameGrid originator = new GameGrid();
        originator.setWholeBoard(this.getBoard());
        caretaker.addMemento(originator.createMemento());

        int y = coordinates.y - 1;
        for (int x = coordinates.x + 1; x < getBoardSize() && y >= 0; x++) {
            if (board[x][y] == 0) {
                originator.restoreMemento(caretaker.getMemento(0));

                return false;
            }
            if ((board[x][y] == GameManager.getCurrentPlayer()) && x > coordinates.x + 1) {
                return true;
            } else if ((board[x][y] == GameManager.getCurrentPlayer()) && x <= coordinates.x + 1) {
                originator.restoreMemento(caretaker.getMemento(0));

                return false;
            }
            setBoard(new Point(x, y), GameManager.getCurrentPlayer());
            y--;

        }
        originator.restoreMemento(caretaker.getMemento(0));

        return false;
    }

    private boolean checkDiagonallyRightDown(Point coordinates) {
        CareTaker caretaker = new CareTaker();
        GameGrid originator = new GameGrid();
        originator.setWholeBoard(this.getBoard());
        caretaker.addMemento(originator.createMemento());

        int y = coordinates.y + 1;
        for (int x = coordinates.x + 1; x < getBoardSize() && y < getBoardSize(); x++) {
            if (board[x][y] == 0) {
                originator.restoreMemento(caretaker.getMemento(0));

                return false;
            }
            if ((board[x][y] == GameManager.getCurrentPlayer()) && x > coordinates.x + 1) {
                return true;
            } else if ((board[x][y] == GameManager.getCurrentPlayer()) && x <= coordinates.x + 1) {
                originator.restoreMemento(caretaker.getMemento(0));
                return false;
            }
            setBoard(new Point(x, y), GameManager.getCurrentPlayer());
            y++;
        }
        originator.restoreMemento(caretaker.getMemento(0));

        return false;
    }

    private boolean checkDiagonallyLeftDown(Point coordinates) {
        CareTaker caretaker = new CareTaker();
        GameGrid originator = new GameGrid();
        originator.setWholeBoard(this.getBoard());
        caretaker.addMemento(originator.createMemento());
        
        int y = coordinates.y + 1;
        for (int x = coordinates.x - 1; x >= 0 && y < getBoardSize(); x--) {
            if (board[x][y] == 0) {
                originator.restoreMemento(caretaker.getMemento(0));

                return false;
            }
            if ((board[x][y] == GameManager.getCurrentPlayer()) && x < coordinates.x - 1) {
                return true;
            } else if ((board[x][y] == GameManager.getCurrentPlayer()) && x >= coordinates.x - 1) {
                originator.restoreMemento(caretaker.getMemento(0));

                return false;
            }
            setBoard(new Point(x, y), GameManager.getCurrentPlayer());
            y++;
        }
        originator.restoreMemento(caretaker.getMemento(0));

        return false;
    }

    private boolean checkDiagonallyLeftUp(Point coordinates) {
        CareTaker caretaker = new CareTaker();
        GameGrid originator = new GameGrid();
        originator.setWholeBoard(this.getBoard());
        caretaker.addMemento(originator.createMemento());
        
        int y = coordinates.y - 1;
        for (int x = coordinates.x - 1; x >= 0 && y >= 0; x--) {
            if (board[x][y] == 0) {
                originator.restoreMemento(caretaker.getMemento(0));

                return false;
            }
            if ((board[x][y] == GameManager.getCurrentPlayer()) && x < coordinates.x - 1) {
                return true;
            } else if ((board[x][y] == GameManager.getCurrentPlayer()) && x >= coordinates.x - 1) {
                originator.restoreMemento(caretaker.getMemento(0));

                return false;
            }
            setBoard(new Point(x, y), GameManager.getCurrentPlayer());
            y--;
        }
        originator.restoreMemento(caretaker.getMemento(0));

        return false;
    }

    public boolean checkIfEmptySpot(Point coordinates) {
        return ((board[coordinates.x][coordinates.y] == 0));
    }


}
