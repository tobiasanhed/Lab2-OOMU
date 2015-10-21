package grupp2.exceptions;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * InvalidMoveException handles the error occurring when a player picks a invalid
 * move or plays whenever he's not suposed to.
 * @author Thires
 */
public class InvalidMoveException extends Exception{
    
    /**
     * InvalidMoveException method creates an alert message in order to inform
     * the player(s) that the move is invalid.
     * @param e is the error message created by the program.
     */
    public InvalidMoveException(String e){
        Platform.runLater(new Runnable(){
            

            @Override
            public void run() {
                Alert alert = new Alert(AlertType.WARNING);

                alert.setTitle("Warning Dialog");
                alert.setHeaderText(e);
                alert.setContentText("Please enter an legal move..");

                alert.showAndWait();            }
        });
        
    }
}
