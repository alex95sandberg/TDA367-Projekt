/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Anton
 */
public class Highscore {
    
    private ArrayList<Integer> highscores;
    
    public Highscore(){
        
        highscores = new ArrayList<>();
        
    }
    
    /**
     * Adds a score to the score list
     * @param newScore the score to be added
     */
    public void addScore(float newScore){
        
        highscores.add(Math.round(newScore));
        
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
     * A method to simplify the expression to get the length of the array.
     * @return the size of the highscore Array.
     */
    public int getSize(){
        
        return highscores.size();
        
    }
    
     /**
     * Sort the ArrayList to have the scores in a acending order
     */
    private void sortArray(){
        
        Collections.sort(highscores);
        
    }
    
}
