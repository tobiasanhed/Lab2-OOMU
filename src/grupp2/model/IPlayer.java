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
    /**
     * Accessor method used to set the name of a player.
     * @param name The desired the name for the player.
     */
    public void setName(String name);
    /**
     * Accessor method used to set the color of a players pieces.
     * @param marker Will be either one or two, one is for black as black goes
     * first, which makes 2 indicate white colored pieces.
     */
    public void setMarkerID(int marker);
    /**
     * This method is used to determine wether or not  a player is a computer.
     * @return Returns true if a player is a computer player and false otherwise.
     */
    public boolean getIsComputer();
    /**
     * Accessor function used to get the name of a player.
     * @return Returns the name of the player as a String.
     */
    public String getName();
    /**
     * This method is used to determine if the player is player one, or two.
     * @return 1 for player 1, and 2 for player 2
     */
    public int getMarkerID();
    /**
     * This method returns the draw of a player as a Point, the x and y coordinate
     * comes from the GUI.
     * @return the players draw for his current turn.
     */
    public Point getDraw();
}
