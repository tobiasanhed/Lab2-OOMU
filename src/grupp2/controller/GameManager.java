/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupp2.controller;

import grupp2.model.GameGrid;

/**
 *
 * @author S142015
 */
public class GameManager {
    private int currentPlayer = 1;
    
    public void startGame(){
        GameGrid board = new GameGrid();
        printBoard(board);
    }
    
    public void printBoard(GameGrid board){
        for(int i = 0; i < board.getBoardSize(); i++){
            for(int j = 0; j < board.getBoardSize(); i++){
                System.out.print("|");
                System.out.print("  ");
                System.out.print("|");
            }
            System.out.print("\n");
        }
    }
}
