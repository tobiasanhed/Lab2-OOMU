package grupp2.model;

import java.awt.Point;


/**
 * This is the interface that is implemented by the players, the two classes that
 * implement this interface are computerplayer and humanplayer.
 * @author Thires
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
