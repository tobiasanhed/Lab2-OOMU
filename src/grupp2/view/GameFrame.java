/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.view;

import grupp2.controller.GameManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
    private static GameBoard graphicBoard = new GameBoard();
    private Stage primaryStage;
    private static Label resultLabel = new Label();
    private static int[] results;
    
    public GameFrame(Stage primaryStage){
        newGame = new Button("Nytt parti");
        endGame = new Button("Avsluta");
        this.primaryStage = primaryStage;
        
        
        BorderPane root = new BorderPane();
        BorderPane top = new BorderPane();
        Pane topLeft = new Pane();
        
        results = GameManager.getResult();
        resultLabel.setText("Player 1: " + results[0] + "\n" + "Player 2: " + results[1]);
        
        
        HBox topRight = new HBox();
        topRight.setAlignment(Pos.CENTER);
        topRight.getChildren().addAll(newGame, endGame);
        topLeft.getChildren().add(resultLabel);
        top.setLeft(topLeft);
        top.setRight(topRight);
        
        root.setCenter(GameFrame.graphicBoard.getGameBoardPane());
        root.setTop(top);
        
        Scene scene = new Scene(root, 500, 500);
        
        this.primaryStage.setTitle("Othello Game");
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        newGame.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                primaryStage.close();

                Platform.runLater(new Runnable(){

                    @Override
                    public void run() {
                        GameFrame graphicGame = new GameFrame(new Stage());
                    }
               
               });
               
               Thread newThread = new Thread(new GameManager());
               newThread.start();

            }
        });
        endGame.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                System.exit(1);
            }
            
        });

        
    }

    public static void updateBoard(){
        results = GameManager.getResult();
        
        Platform.runLater(new Runnable() { 
            @Override
            public void run() {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                resultLabel.setText("Player 1: " + results[0] + "\n" + "Player 2: " + results[1]);
                graphicBoard.drawGraphicBoard();
            }
        });
    }
    
    
}
    
