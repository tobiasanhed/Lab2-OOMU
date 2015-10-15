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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author S142015
 */
public class GameBoard {
    private GridPane board;
    
    public GameBoard(){
        board = new GridPane();
        board.setAlignment(Pos.CENTER);
        board.setStyle("-fx-background-color: green");
        board.setGridLinesVisible(true);
        int [][] currentBoard = GameManager.getBoard();

        Button[][] btn = new Button[GameGrid.getBoardSize()][GameGrid.getBoardSize()];

        for (int i = 0; i < btn.length; i++) {
            for (int j = 0; j < btn.length; j++) {
                if (currentBoard[i][j] == 1) {
                    Circle whiteMarker = new Circle(20);
                    StackPane circlepane = new StackPane();
                    whiteMarker.setFill(Color.WHITE);
                    circlepane.getChildren().add(whiteMarker);
                    circlepane.setAlignment(Pos.CENTER);
                    board.add(circlepane, j, i);
                
                } else if (currentBoard[i][j] == 2) {
                    Circle blackMarker = new Circle(20);
                    StackPane circlepane = new StackPane();
                    blackMarker.setFill(Color.BLACK);
                    circlepane.getChildren().add(blackMarker);
                    circlepane.setAlignment(Pos.CENTER);
                    board.add(circlepane, j, i);
                } else {
                    btn[i][j] = new Button("");
                    btn[i][j].setOpacity(0);
                    btn[i][j].setPrefSize(50, 50);
                    btn[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            System.out.println("bajs");
                        }

                    });
                    board.add(btn[i][j], j, i);
                }

            }
        }
    }
    
    public GridPane getGameBoardPane(){
        return board;
    }
    
    public void initializeObservers(){
        
    }
}
