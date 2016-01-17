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

    public InvalidMoveException(){
        
        
    }
}
