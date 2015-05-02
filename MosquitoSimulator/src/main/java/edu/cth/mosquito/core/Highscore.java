/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Alexander
 */
public class Highscore {
    
    private static ArrayList<Integer> highscores;
    
    public Highscore(){
        
        highscores = new ArrayList<>();
        
    }
    
    /**
     * Adds a score to the score list
     * @param newScore the score to be added
     */
    public void addScore(int newScore){
        
        highscores.add(newScore);
        System.out.print("blf");
        
        sortArray();
    }
    
    /**
     * A method that sets the highscore array to the input
     * @param highscoreList the list that should be the highscore list
     */
    public void setArray(ArrayList<Integer> highscoreList){
        
        highscores = highscoreList;
        
    }
    
    /**
     * Returns the high score arrayList
     * @return score list
     */
    public ArrayList<Integer> getHighscore(){
        
        return highscores;
        
    }
    
     /**
     * Sort the ArrayList to have the scores in a acending order
     */
    private void sortArray(){
        
        Collections.sort(highscores);
        
    }
    
}
