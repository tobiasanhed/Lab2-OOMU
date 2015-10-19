/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.model;

import grupp2.controller.GameManager;
import grupp2.exceptions.InvalidMoveException;
import java.awt.Point;
import java.util.Scanner;

/**
 *
 * @author S142015
 */
public class HumanPlayer implements IPlayer {
    private String name;
    private int markerID;
    private final boolean isComputer = false;
    
    public HumanPlayer(){
        
    }
    
    public HumanPlayer(String name, int markerID){
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
    public Point getDraw() throws InvalidMoveException{
        
        Point draw =GameManager.getCoord(); 
        
        if(!GameManager.isPossibleDraw(draw))
            throw new InvalidMoveException("Illegal Draw");
        
        return draw;
    }

}
