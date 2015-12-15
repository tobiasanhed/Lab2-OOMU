/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.view;

import grupp2.controller.GameManager;
import grupp2.model.IPlayer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * This is the class where the graphical user interface is implemented.
 * @author Tobias
 */
public class GameFrame extends Stage{
    private Button newGame = new Button("Nytt parti");
    private Button endGame = new Button("Avsluta");
    private GameBoard graphicBoard = new GameBoard();
    private Label resultLabel = new Label();
    private int[] results;
    private ArrayList<IPlayer> players;

    private static final GameFrame INSTANCE = new GameFrame();

    private GameFrame(){
    }

    public static GameFrame getInstance(){
        return INSTANCE;
    }

    public void drawFrame(){
        BorderPane root = new BorderPane();

        root.setCenter(graphicBoard.getGameBoardPane());
        root.setTop(initializeTop());

        Scene scene = new Scene(root, 500, 500);
        setScene(scene);
    }
    /**
     * This function sets up the top of the GUI, it consists of buttons, a resultlabel and the menu. 
     * @return A borderpane that makes up the top of the GUI.
     */
    private BorderPane initializeTop(){
        BorderPane top = new BorderPane();
        Pane topLeft = new Pane();
        
        players = GameManager.getInstance().getPlayers();
        results = GameManager.getInstance().getResult();
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
               
               Thread newThread = new Thread(GameManager.getInstance());
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
    
    /**
     * This function creates the menu of the GUI.
     * @return a menubar representing the menu of the GUI
     */
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
               
               Thread newThread = new Thread(GameManager.getInstance());
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
    public void updateBoard(){
        results = GameManager.getInstance().getResult();
        players = GameManager.getInstance().getPlayers();

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
    
    /**
     * This function enables the controller of the game to hide the game window.
     */
    public void hideGameWindow(){
        hide();
    }
    
    /**
     * This function enables the controller of the game to show the hidden game window.
     */
    public void showGameWindow(){
        show();
    }
}
    
