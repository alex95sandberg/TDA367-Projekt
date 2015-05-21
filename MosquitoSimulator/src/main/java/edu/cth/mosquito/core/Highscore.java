/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

import edu.cth.mosquito.controller.FileController;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Anton
 */
public class Highscore {
    
    private ArrayList<Integer> highscores;
    private FileController file;
    
    public Highscore(){
        
        highscores = new ArrayList<>();
        
        file = new FileController();
    }
    
    /**
     * Adds a score to the score list
     * @param newScore the score to be added
     */
    public void addScore(float newScore){
        
        highscores.add(Math.round(newScore));
        
        sortArray();
        
        try{
            file.saveHighscoreToFile(highscores);
        } catch(FileNotFoundException e){
            System.out.println("Error! FileNotFoundException");
        }
    }
    
    /**
     * Returns the high score arrayList
     * @return score list
     */
    public ArrayList<Integer> getHighscore(){
        
        try {
            this.highscores = file.getHighscores();
        } catch(IOException e){
           System.out.println("Error: IOException");
        }
        
        sortArray();
        
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
    
    public void resetHighscore(){
        
        try {
            file.deleteHighscore();
        } catch(FileNotFoundException e){
            System.out.println("Error: FileNotFoundException");
        }
        
    }
    
}
