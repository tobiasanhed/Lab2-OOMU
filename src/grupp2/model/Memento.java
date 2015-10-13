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
public class Memento {
    private int[][] state;
    
    public Memento (int[][] state){
        this.state = state;
    }
    
    public int[][] getState(){
        return this.state;
    }
}
