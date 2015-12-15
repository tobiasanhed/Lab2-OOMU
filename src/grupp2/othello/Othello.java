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
 * This is where the game starts.
 * @author Rasmus
 */
public class Othello extends Application {
    /**
     * This is where the GUI is launched.
     * @param primaryStage The primary graphics stage of the game.
     */
    @Override
    public void start(Stage primaryStage) {
        Thread game = new Thread(GameManager.getInstance());
        game.start();
        GameFrame.getInstance().drawFrame();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
