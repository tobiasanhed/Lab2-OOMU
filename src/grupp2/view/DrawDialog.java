package grupp2.view;

import grupp2.model.IPlayer;
import javafx.scene.control.Alert;


/**
 * This is the dialog that is displayed when the game ends in a tie.
 * @author Rasmus
 */
public class DrawDialog implements IEndDialog{
    /**
     * This method shows a dialog that indicates that the game has ended in a draw.
     * @param player1 Represents player 1.
     * @param player2 Represents player 2.
     */
    @Override
    public void printResult(IPlayer player1, IPlayer player2){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("End of the game!");
        alert.setHeaderText("The game has ended.");
        alert.setContentText("It was a draw..");

        alert.showAndWait();
    }
}
