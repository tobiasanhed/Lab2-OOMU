/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.view;

import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 *
 * @author S143979
 */
public class ErrorDialog {
    
    public ErrorDialog(String e){
        Platform.runLater(new Runnable(){
            

            @Override
            public void run() {
                Alert alert = new Alert(Alert.AlertType.WARNING);

                alert.setTitle("Warning Dialog");
                alert.setHeaderText(e);
                alert.setContentText("Please enter an legal move..");

                alert.showAndWait();            }
        });
    }
    
}
