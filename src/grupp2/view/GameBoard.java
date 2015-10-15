/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.view;

import grupp2.model.GameGrid;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 *
 * @author S142015
 */
public class GameBoard {
    private GridPane board;
    
    public GameBoard(){
        board = new GridPane();
        board.setAlignment(Pos.CENTER);

        Button[][] btn = new Button[GameGrid.getBoardSize()][GameGrid.getBoardSize()];

        for ( int i = 0; i < btn.length; i++) {
            for ( int j = 0; j < btn.length; j++) {
                btn[i][j] = new Button("");

                btn[i][j].setStyle("-fx-border-color: black;"
                        + "-fx-background-color: green");
                btn[i][j].setPrefSize(50, 50);

                board.add(btn[i][j], j, i+2);
            }
        }
    }
    
    public GridPane getGameBoardPane(){
        return board;
    }
    
    public void initializeObservers(){
        
    }
}
