/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

import edu.cth.mosquito.controller.FileController;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Handles the highscore data
 * 
 * @author Mosquito
 */
public class Highscore {
    
    private static int[] highscores;
    private static final FileController fileController = new FileController();
    private final JOptionPane errorMsg = new JOptionPane("Error!" +  "\nHighscorefile not found!", JOptionPane.ERROR_MESSAGE);
    private final JDialog errorDialog = errorMsg.createDialog("Failure");
    
    public Highscore(){
        highscores = new int[5];
    }
    
    /**
     * Adds a score to the score list
     * @param newScore the score to be added
     */
    public void sortNewScore(int newScore){
        
        if (newScore > highscores[4]){
            highscores[4] = newScore;
            
            if (newScore > highscores[3]){
                highscores[4] = highscores[3];
                highscores[3] = newScore;
                
                if (newScore > highscores[2]){
                    highscores[3] = highscores[2];
                    highscores[2] = newScore;
                    
                    if (newScore > highscores[1]){
                        highscores[2] = highscores[1];
                        highscores[1] = newScore;
                        
                        if (newScore > highscores[0]){
                            highscores[1] = highscores[0];
                            highscores[0] = newScore;
                        }
                    }
                }
            }
        }//end if
    
    }
    
    public void writeHighscore(int[] highscores){
        try {
                fileController.writeHighscore(highscores);
            } catch (FileNotFoundException e){
                errorDialog.setAlwaysOnTop(true);
                errorDialog.setVisible(true);
            }
    }
    
    public void addHighscore(int newScore){
        sortNewScore(newScore);
        writeHighscore(highscores);
    }
    /**
     * Returns the high score arrayList
     * @return score list
     */
    public int[] getHighscore(){
        try {
            highscores = fileController.readHighscore();
        } catch(IOException e){
            errorDialog.setAlwaysOnTop(true);
            errorDialog.setVisible(true);
        }
        
        return highscores;
    }
    
    /**
     * A method to simplify the expression to get the length of the array.
     * @return the size of the highscore Array.
     */
    public int getSize(){
        return highscores.length;
    }
    
    public void resetHighscore(){
        try {
            fileController.deleteHighscore();
        } catch(FileNotFoundException e){
            errorDialog.setAlwaysOnTop(true);
            errorDialog.setVisible(true);
        }
        
        for (int i = 0; i < highscores.length; i++){
            highscores[i] = 0;
        }
    }
    
}
