/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.model;

import java.awt.Point;

/**
 *
 * @author S142015
 */
public interface IPlayer {
    
    public void setName(String name);
    public void setMarkerID(int marker);
    public boolean getIsComputer();
    public String getName();
    public int getMarkerID();
    public Point getDraw();
}
