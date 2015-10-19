/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.view;

import grupp2.model.IPlayer;

/**
 *
 * @author S142015
 */

/**
 * This is the interface that is implemented by the dialogs shown when the game ends.
 * The interface is implemented by WinnerDialog and DrawDialog.
 * @author Rasmus
 */
public interface IEndDialog {
    public void printResult(IPlayer player1, IPlayer player2);
}
