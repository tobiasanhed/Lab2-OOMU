package grupp2.model;

import grupp2.controller.GameManager;
import java.awt.Point;

/**
 * This is the class that contains the methods that handle the human players
 * actions within the game.
 * @author Tobias
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
    public Point getDraw(){
        
        return GameManager.getInstance().getCoord();
    }

}
