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
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Anton
 */
public class Highscore {
    
    private List<Integer> highscores;
    private FileController file;
    private final JOptionPane errorMsg = new JOptionPane("Error!" +  "\nHighscorefile not found!", JOptionPane.ERROR_MESSAGE);
    private final JDialog errorDialog = errorMsg.createDialog("Failure");
    
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
            errorDialog.setAlwaysOnTop(true);
            errorDialog.setVisible(true);
        }
    }
    
    /**
     * Returns the high score arrayList
     * @return score list
     */
    public List<Integer> getHighscore(){
        
        try {
            this.highscores = file.getHighscores();
            
        } catch(IOException e){
            errorDialog.setAlwaysOnTop(true);
            errorDialog.setVisible(true);
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
    
    private void sortArray(){
        
        Collections.sort(highscores);
        
    }
    
    public void resetHighscore(){
        
        try {
            file.deleteHighscore();
        } catch(FileNotFoundException e){
            errorDialog.setAlwaysOnTop(true);
            errorDialog.setVisible(true);
        }
        
    }
    
}
