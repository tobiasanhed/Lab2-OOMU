package grupp2.model;

import grupp2.controller.GameManager;
import grupp2.view.GameBoard;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This is the class that contains the methods that handle the human players
 * actions within the game.
 * @author Tobias
 */
public class HumanPlayer implements IPlayer {
    private String name;
    private int markerID;
    private Point draw;
    private boolean waitForUpdate;
    public HumanPlayer(){
        
    }
    
    public HumanPlayer(String name, int markerID){
        this.name = name;
        this.markerID = markerID;
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
        waitForUpdate = true;
        GameBoard.getInstance().attach(this);
        while(waitForUpdate){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return draw;
    }


    public void update() {
        this.draw = GameBoard.getInstance().getState();
        waitForUpdate = false;
    }
}
