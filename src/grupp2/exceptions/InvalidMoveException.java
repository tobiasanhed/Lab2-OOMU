/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.exceptions;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author S142015
 */
public class InvalidMoveException extends Exception{
    
    public InvalidMoveException(String e){
        Platform.runLater(new Runnable(){
            

            @Override
            public void run() {
                Alert alert = new Alert(AlertType.WARNING);

                alert.setTitle("Warning Dialog");
                alert.setHeaderText("The move was invalid..");
                alert.setContentText(e);

                alert.showAndWait();            }
        });
        
    }
}
