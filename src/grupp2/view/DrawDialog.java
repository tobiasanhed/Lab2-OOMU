/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.view;

import javafx.scene.control.Alert;

/**
 *
 * @author S142015
 */
public class DrawDialog implements IEndDialog{
    @Override
    public void printResult(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Ended");
        alert.setHeaderText("The game has ended.");
        alert.setContentText("It was a draw..");

        alert.showAndWait();
    }
}
