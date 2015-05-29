/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.filehandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Mosquito
 */
public interface IFileHandler {
    
    public void writeHighscore(int[] highScores) throws FileNotFoundException;
    
    public int[] readHighscore() throws IOException;
    
    public void deleteHighscores() throws FileNotFoundException;
    
}
