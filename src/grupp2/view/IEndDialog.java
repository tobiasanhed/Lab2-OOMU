package grupp2.view;

import grupp2.model.IPlayer;

/**
 * This is the interface that is implemented by the dialogs shown when the game ends.
 * The interface is implemented by WinnerDialog and DrawDialog.
 * @author Rasmus
 */
public interface IEndDialog {
    void printResult(IPlayer player1, IPlayer player2, int [] result);
}
