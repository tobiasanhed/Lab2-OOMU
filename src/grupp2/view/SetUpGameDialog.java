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
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Text;

/**
 *
 * @author S142015
 */
public class SetUpGameDialog {
    public ArrayList<IPlayer> getPlayers(){
        ArrayList<IPlayer> players = new ArrayList();
        
        TextInputDialog playerDialog = new TextInputDialog("Player1");
        Text textField = new Text("Player2");
        
        
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
        
        
        return players;
        
    }
}
