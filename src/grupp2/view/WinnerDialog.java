/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.view;

import grupp2.controller.GameManager;
import grupp2.model.IPlayer;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author S142015
 */
public class WinnerDialog implements IEndDialog{
   
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
