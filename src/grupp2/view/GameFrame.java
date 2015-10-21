/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.view;

import grupp2.controller.GameManager;
import grupp2.model.IPlayer;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author S142015
 */

/**
 * This is the class where the graphical user interface is implemented.
 * @author Rasmus
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
    private static Stage primaryStage;
    private static Label resultLabel = new Label();
    private static int[] results;
    private static ArrayList<IPlayer> players;

    /**
     * This is where the buttons for the GUI is placed on the board and the Scene
     * for the game is set.
     * @param primaryStage The stage where the game board is shown.
     */
    public GameFrame(Stage primaryStage){
        this.primaryStage = primaryStage;

    }
    public void drawGraphic(){
        newGame = new Button("Nytt parti");
        endGame = new Button("Avsluta");
        
        
        BorderPane root = new BorderPane();
        
        
        root.setCenter(GameFrame.graphicBoard.getGameBoardPane());
        root.setTop(initializeTop());
        
        Scene scene = new Scene(root, 500, 500);
        
        this.primaryStage.setTitle("Othello Game");
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        
    }
    
    private BorderPane initializeTop(){
        BorderPane top = new BorderPane();
        Pane topLeft = new Pane();
        
        players = GameManager.getPlayers();
        results = GameManager.getResult();
        resultLabel.setText(": " + results[0] + "\n" + ": " + results[1]);
        
        
        HBox topRight = new HBox();
        topRight.setAlignment(Pos.CENTER);
        topRight.getChildren().addAll(newGame, endGame);
        topLeft.getChildren().add(resultLabel);
        top.setLeft(topLeft);
        top.setRight(topRight);
        top.setTop(initializeMenu());
        
        newGame.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                

               //primaryStage.close();
               graphicBoard.getGameBoardPane().getChildren().removeAll(graphicBoard.getGameBoardPane());
               
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
        return top;
    } 
    
    private MenuBar initializeMenu(){
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("Arkiv");
        Menu helpMenu = new Menu("Hjälp");
        MenuItem aboutGameMenuItem = new MenuItem("Om");
        MenuItem newGameMenuItem = new MenuItem("Nytt parti");
        MenuItem exitMenuItem = new MenuItem("Avsluta");
        fileMenu.getItems().addAll(newGameMenuItem, exitMenuItem);
        helpMenu.getItems().addAll(aboutGameMenuItem);
        menuBar.getMenus().addAll(fileMenu, helpMenu);

        newGameMenuItem.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
               //primaryStage.close();
               graphicBoard.getGameBoardPane().getChildren().removeAll(graphicBoard.getGameBoardPane());
               
               Thread newThread = new Thread(new GameManager());
               newThread.start();
            }
            
        });
        exitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(1);
            }
        });
        aboutGameMenuItem.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                Alert aboutDialog = new Alert(Alert.AlertType.INFORMATION);
                aboutDialog.setTitle("Om det här spelet");
                aboutDialog.setHeaderText("Det är är ett othello spel skapat av Thires Nilsson, Rasmus Lundquist\n"
                        + "& Tobias Ånhed. Skapat för SA-linjen på Högskolan i Borås.");
          
                aboutDialog.show();
                
            }
        
            
        });

        return menuBar;
    }
/**
 * The updateBoard method is used to update the GUI after one of the players has
 * played their turn.
 */
    public static void updateBoard(){
        results = GameManager.getResult();
        players = GameManager.getPlayers();

        Platform.runLater(new Runnable() { 
            @Override
            public void run() {
                
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                resultLabel.setText(players.get(0).getName() + ": " + results[0] + "\n" + players.get(1).getName() + ": " + results[1]);
                graphicBoard.drawGraphicBoard();
            }
        });
    }
    
    public static void hideGameWindow(){
        primaryStage.hide();
    }
    
    public static void showGameWindow(){
        primaryStage.show();
    }
    
    
}
    
