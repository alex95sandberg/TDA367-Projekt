/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.filehandler;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * An interface for filehandlers
 * 
 * @author Mosquito
 */
public interface IFileHandler {
    
    public void writeHighscore(int[] highScores) throws FileNotFoundException;
    
    public int[] readHighscore() throws IOException;
    
    public void deleteHighscores() throws FileNotFoundException;
    
}
