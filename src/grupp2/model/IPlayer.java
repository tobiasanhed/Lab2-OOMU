/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.model;

import grupp2.exceptions.InvalidMoveException;
import java.awt.Point;

/**
 *
 * @author S142015
 */
/**
 * This is the interface that is implemented by the players, the two classes that
 * implement this interface are computerplayer and humanplayer.
 * @author Rasmus
 */
public interface IPlayer {
    
    public void setName(String name);
    public void setMarkerID(int marker);
    public boolean getIsComputer();
    public String getName();
    public int getMarkerID();
    public Point getDraw();
}
