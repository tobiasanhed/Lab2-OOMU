/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.model;

import grupp2.controller.GameManager;
import grupp2.exceptions.InvalidMoveException;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * ComputerPlayer is the class which implements the interface Iplayer
 * and if either or both of the players are CPU:s one or two objects of this 
 * class will be created.
 * @author S142015
 */
public class ComputerPlayer implements IPlayer {
    private String name;
    private int markerID;
    private final boolean isComputer = true;

    public ComputerPlayer(){
        
    }
    /**
     * ComputerPlayer constructor creates the computer player based on input 
     * given by a human.
     * @param name this parameter is the name of the CPU based on input.
     * @param markerID this parameter is either value 1 or 2 depending on if the 
     * player starts as player 1 and is the black marker or player 2 and plays with
     * the white markers.
     */
    public ComputerPlayer(String name, int markerID){
        this.name = name;
        this.markerID = markerID;
    }
    
    @Override
    public boolean getIsComputer(){
        return isComputer;
    }
    
    @Override
    public void setName(String name){
        this.name = name;
    }
    
    @Override
    
    public void setMarkerID(int marker){
        this.markerID = marker;
    }
    
    @Override
    public String getName(){
        return name;
    }
    
    @Override
    public int getMarkerID(){
        return markerID;
    }
    
    @Override
    public Point getDraw(){
        ArrayList draws;
        Random rand = new Random();
        int drawIndex;
        Point draw;
        
        draws = GameManager.getPossibleDraws();
        if(draws.isEmpty())
            return null;
        else if(draws.size() == 1)
            return (Point)draws.get(0);
        else
            drawIndex = rand.nextInt(draws.size() - 1);
        
        return (Point)draws.get(drawIndex);
    }
}
