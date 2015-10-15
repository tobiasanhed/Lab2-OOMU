/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author S142015
 */
public class GameFrame{
    /*5.2 Klass: GameFrame
GameFrame är en instans av en javafx.stage.Stage och finns uppe så länge programmet körs. 
    Den innehåller, bland annat, en instans av GameBoard samt knapparna ”Nytt parti” och ”Avsluta”.
    
5.3 Klass: GameBoard
GameBoard kan t.ex. vara av typen javafx.scene.layout.Pane och utgör den grafiska representationen av 
    spelplanen och används för att presentera den aktuella ställningen. 
    GameBoard fångar dessutom upp användarens (HumanPlayer) val av ruta då denne gör ett drag.*/
    private Button newGame;
    private Button endGame;
    private GameBoard graphicBoard;
    private Stage primaryStage;
    
    public GameFrame(Stage primaryStage){
        newGame = new Button("Nytt parti");
        endGame = new Button("Avsluta");
        graphicBoard = new GameBoard();
        this.primaryStage = primaryStage;
        
        
        BorderPane root = new BorderPane();
        
        HBox top = new HBox();
        top.setAlignment(Pos.CENTER);
        top.getChildren().addAll(newGame, endGame);
        
        root.setCenter(graphicBoard.getGameBoardPane());
        root.setTop(top);
        
        Scene scene = new Scene(root, 500, 500);
        
        this.primaryStage.setTitle("Othello Game");
        this.primaryStage.setScene(scene);
        this.primaryStage.show();

    }

    
}