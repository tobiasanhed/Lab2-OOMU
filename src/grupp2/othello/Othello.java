/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.othello;

import grupp2.controller.GameManager;
import grupp2.view.GameFrame;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author S142015
 */
public class Othello extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Thread game = new Thread(new GameManager());
        game.start();
        GameFrame graphicGame = new GameFrame(primaryStage);
        
        /*
        SetUpGameDialog getPlayers = new SetUpGameDialog();
        getPlayers.getPlayers();
        */
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
