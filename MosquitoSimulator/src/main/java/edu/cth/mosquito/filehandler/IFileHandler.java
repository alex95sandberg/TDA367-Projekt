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
    
    public void loadScoreFile(List<Integer> highScores) throws FileNotFoundException;
    
    public List<Integer> loadHighscoreList() throws IOException;
    
    public void deleteHighscores() throws FileNotFoundException;
    
}
