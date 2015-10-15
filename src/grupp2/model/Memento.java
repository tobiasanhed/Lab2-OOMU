/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.model;

/**
 *
 * @author Rasmus
 */
public class Memento{
    private GameGrid state;
    
    public Memento (GameGrid state){
        this.state = state;
    }
    
    public GameGrid getState(){
        return (state);
    }
}
