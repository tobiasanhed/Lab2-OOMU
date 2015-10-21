/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.view;

import grupp2.controller.GameManager;
import grupp2.model.GameGrid;
import java.awt.Point;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


/**
 * In this class is the game board and methods used to display the pieces on the board
 * and to draw the actual game board.
 * @author Thires
 */
public class GameBoard{
    private GridPane board;
    private int [][] currentBoard = GameManager.getBoard();
    private Button[][] btn = new Button[GameGrid.getBoardSize()][GameGrid.getBoardSize()];

/**
 * This is the method that initializes the GUI and puts the starting pieces onto the board.
 */
    public GameBoard(){
        board = new GridPane();
        board.setAlignment(Pos.CENTER);
        board.setStyle("-fx-background-color: green");
        board.setGridLinesVisible(true);


        for (int i = 0; i < btn.length; i++) {
            for (int j = 0; j < btn.length; j++) {
                if (currentBoard[i][j] == 2) {
                    writeWhiteMarker(i,j);
                } else if (currentBoard[i][j] == 1) {
                    writeBlackMarker(i,j);
                } else {
                    btn[i][j] = new Button("");
                    btn[i][j].setOpacity(100);
                    btn[i][j].setStyle("-fx-color: green");
                    btn[i][j].setPrefSize(50, 50);
                    Point draw = new Point(j, i);
                    btn[i][j].setOnKeyPressed(new EventHandler<KeyEvent>(){
                        
                        @Override
                        public void handle(KeyEvent event) {
                            Button temp = (Button)event.getSource();
                            if(event.getCode() == KeyCode.ENTER && temp.isFocused() && !GameManager.getIsComputerPlayer())
                                GameManager.setCoord(draw);
                                
                        }
                        
                    });
                    btn[i][j].setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event){
                            if(!GameManager.getIsComputerPlayer())
                                GameManager.setCoord(draw);
                        }

                    });
                    board.add(btn[i][j], j, i);
                }

            }
        }
    }
    /**
     * The drawGraphicBoard method is used after the game board has been updated
     * to display changes made to the state of the game board.
     */
    public void drawGraphicBoard(){
        currentBoard = GameManager.getBoardNotifier();
        
        for (int i = 0; i < btn.length; i++) {
            for (int j = 0; j < btn.length; j++) {
                if (currentBoard[i][j] == 2) {
                    writeWhiteMarker(i,j);
                } else if (currentBoard[i][j] == 1) {
                    writeBlackMarker(i,j);
                } else {
                    btn[i][j] = new Button("");
                    btn[i][j].setOpacity(0);
                    btn[i][j].setPrefSize(50, 50);
                    Point draw = new Point(j, i);
                    btn[i][j].setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            
                            GameManager.setCoord(draw);
                        }

                    });
                    board.add(btn[i][j], j, i);
                }

            }
        }
    }
    /**
     * This method return the current state of the board.
     * @return the current board state.
     */
    public GridPane getGameBoardPane(){
        return board;
    }
    /**
     * This method draws a white marker in the cell of the board where the white
     * player has made their move.
     * @param i x coordinate of the board.
     * @param j y coordinate of the board.
     */
    public void writeBlackMarker(int i, int j){
                    Circle blackMarker = new Circle(20);
                    StackPane circlepane = new StackPane();
                    blackMarker.setFill(Color.BLACK);
                    circlepane.getChildren().add(blackMarker);
                    circlepane.setAlignment(Pos.CENTER);
                    board.add(circlepane, i, j);
    }
      /**
     * This method draws a white marker in the cell of the board where the black
     * player has made their move.
     * @param i x coordinate of the board.
     * @param j y coordinate of the board.
     */
    public void writeWhiteMarker(int i, int j){
                    Circle whiteMarker = new Circle(20);
                    StackPane circlepane = new StackPane();
                    whiteMarker.setFill(Color.WHITE);
                    circlepane.getChildren().add(whiteMarker);
                    circlepane.setAlignment(Pos.CENTER);
                    board.add(circlepane, i, j);
    }

}
