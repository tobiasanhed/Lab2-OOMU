/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.view;

import grupp2.controller.GameManager;
import grupp2.model.ComputerPlayer;
import grupp2.model.IPlayer;
import java.util.ArrayList;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;

/**
 *
 * @author S142015
 */
public class SetUpGameDialog {
    public ArrayList<IPlayer> getPlayers(){
        ArrayList<IPlayer> playerArray = new ArrayList();
        
        final ToggleGroup playerOneGroup = new ToggleGroup();
        final ToggleGroup playerTwoGroup = new ToggleGroup();
        
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);

        GridPane gridpane = new GridPane();
        Scene scene = new Scene(gridpane, 400, 270);
        gridpane.setPadding(new Insets(18));
        gridpane.setHgap(15);
        gridpane.setVgap(15);
        
        Label playerName1 = new Label("Player one name:");
        playerName1.setStyle("-fx-font: 20px Tahoma; -fx-stroke: black; -fx-stroke-width:1;");
        gridpane.add(playerName1, 0, 1);
        TextField playerOneTf = new TextField();
        gridpane.add(playerOneTf, 0, 2);
        
        Text instruction = new Text("Please choose type of player:");
        gridpane.add(instruction, 0, 3);
        
        RadioButton playerOneCPU = new RadioButton("Computer player");
        RadioButton playerOneHuman = new RadioButton("Human player");
        
        playerOneCPU.setToggleGroup(playerOneGroup);
        playerOneCPU.setSelected(true);
        gridpane.add(playerOneCPU, 0, 4);
        
        playerOneHuman.setToggleGroup(playerOneGroup);
        gridpane.add(playerOneHuman, 0, 5);
        
        Label playerName2 = new Label("Player two name:");
        playerName2.setStyle("-fx-font: 20px Tahoma; -fx-stroke: black; -fx-stroke-width:1;");
        gridpane.add(playerName2, 1, 1);
        TextField playerTwoTf = new TextField();
        gridpane.add(playerTwoTf, 1, 2);
        
        RadioButton playerTwoCPU = new RadioButton("Computer player");
        RadioButton playerTwoHuman = new RadioButton("Human player");
        
        playerTwoCPU.setToggleGroup(playerTwoGroup);
        playerTwoCPU.setSelected(true);
        gridpane.add(playerTwoCPU, 1, 4);
        
        playerTwoHuman.setToggleGroup(playerTwoGroup);
        gridpane.add(playerTwoHuman, 1, 5);
        
        Button btn = new Button("I'm ready!");
        gridpane.add(btn, 0, 6);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            //Här ska vi skapa player objekten tror jag
                            //If radiobutton-val är human skapa en sån annars skapa en CPU
                            //kör getText på tf som gäller för playerOne och använd get name för att sätta namnet på spelaren
                            //Lägg till playerArray
                            //Upprepa ovan för spelare nummer två
                            //Returnera playerArrayen
                        }

                    });
        
        
        dialog.setScene(scene);
        dialog.show(); //Denna ska bort
        
        return playerArray;
    }
}
