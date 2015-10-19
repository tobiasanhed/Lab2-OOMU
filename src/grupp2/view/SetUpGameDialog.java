/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.view;

import grupp2.model.ComputerPlayer;
import grupp2.model.IPlayer;
import java.util.ArrayList;
import java.util.Optional;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Pair;

/**
 *
 * @author S142015
 */
public class SetUpGameDialog {
    public ArrayList<IPlayer> getPlayers(){
        ArrayList<IPlayer> players = new ArrayList();
        
        Dialog<Pair<String, String>> playerDialog = new Dialog();
        playerDialog.setTitle("Game Setup");
        
        
        ButtonType loginButtonType = new ButtonType("OK", ButtonData.OK_DONE);
        playerDialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        
        GridPane pane = new GridPane();
        pane.setHgap(15);
        pane.setVgap(15);
        pane.setPadding(new Insets(20, 150, 10, 10));
        
        TextField player1 = new TextField();
        player1.setPromptText("Player 1");
        TextField player2 = new TextField();
        player1.setPromptText("Player 2");
        
        pane.add(player1, 0,0);
        pane.add(player2, 1, 1);
        
        playerDialog.getDialogPane().setContent(pane);
        
        /*
        
        
        playerDialog.setTitle("Game Setup");
        playerDialog.setHeaderText("Enter the name for the players and decide if it's a human or a computer player.");
        playerDialog.setContentText("Please enter your name:");
        playerDialog.getDialogPane().getChildren().add(textField);

        // Traditional way to get the response value.
        Optional<String> result = playerDialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your name: " + result.get());
        }

        // The Java 8 way to get the response value (with lambda expression).
        result.ifPresent(name -> System.out.println("Your name: " + name));
        
        IPlayer player1 = new ComputerPlayer();
        player1.setName(result.get());
        
        IPlayer player2 = new ComputerPlayer();
        player1.setName(result.get());
        
        players.add(player1);
        players.add(player2);
        
        */
        return players;
        
    }
}
