/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.model;

import grupp2.controller.GameManager;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author S142015
 */
public class ComputerPlayer implements IPlayer {
    private String name;
    private int markerID;
    private final boolean isComputer = true;

    public ComputerPlayer(){
        
    }
    
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
