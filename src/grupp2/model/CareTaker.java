/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.model;

import java.util.ArrayList;

/**
 *
 * @author Rasmus
 */
public class CareTaker {
    private ArrayList<Memento> savedStates = new ArrayList();
     
    public Memento getMemento(int index){
        return savedStates.get(index);
    }
    
    public void addMemento(Memento memento){
        savedStates.add(memento);
    }
}
