/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.controller;

import edu.cth.mosquito.filehandler.FileHandler;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author Anton
 */
public class Controller {
    
    private FileHandler fileHandler;
    
    public Controller(){
        
        fileHandler = new FileHandler();
        
    }
    
    public void saveHighscoreToFile(ArrayList<Integer> highscore) throws FileNotFoundException {
        
        fileHandler.loadScoreFile(highscore);
        
    }
    
    public ArrayList<Integer> getHighscores() throws IOException {
        
       return fileHandler.loadHighscoreList();
        
    }
    
    public void deleteHighscore() throws FileNotFoundException {
        
        fileHandler.deleteHighscores();
        
    }
}
