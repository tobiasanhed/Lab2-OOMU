/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.view;

import grupp2.controller.GameManager;
import grupp2.model.IPlayer;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * This is the dialog that is shown when the game ends. It displays which player won the game.
 * @author Rasmus
 */
public class WinnerDialog implements IEndDialog{
   /**
    * The winner of the game is printed here, the method printResult has the two
    * players of the game as parameters to determine whom of them is the winner
    * of the game.
    * @param player1 Represents player 1.
    * @param player2 Represents player 2.
    */
    @Override
    public void printResult(IPlayer player1, IPlayer player2){
        
        int [] result = new int[2];
               
        result = GameManager.getResult(); //Denna ska läggas till för att kunna ta in resultatet
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("End of the game!");
        alert.setHeaderText("The winner is...");
        if(result[0]>result[1])
            alert.setContentText(player1.getName());  //Här ska vi anropa player1.getName
        else
            alert.setContentText(player2.getName()); //Hör ska vi anropa player2.getName
        
        alert.showAndWait();
    }
    
}
