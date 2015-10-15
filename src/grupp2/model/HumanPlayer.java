/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.model;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 *
 * @author S142015
 */
public class HumanPlayer implements IPlayer, Observer {
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
    public Point getDraw(){
        Scanner input = new Scanner(System.in);
        int value1, value2;
        
        System.out.println("Please enter a move as coordinates!");
        value1 = input.nextInt();
        value2 = input.nextInt();
        
        Point draw =new Point(value1, value2);
        
        return draw;
    }

    @Override
    public void update(Observable o, Object o1) {
        
    }
}
