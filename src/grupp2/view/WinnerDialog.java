/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author S142015
 */
public class WinnerDialog implements IEndDialog{
    
    @Override
    public void printResult(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Game Ended");
        alert.setHeaderText("The game has ended.");
        alert.setContentText(/*getWinner() + */" has won the game!");

        alert.showAndWait();

    }
}
