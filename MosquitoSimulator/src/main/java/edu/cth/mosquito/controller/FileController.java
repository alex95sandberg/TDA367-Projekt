/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.controller;

import edu.cth.mosquito.filehandler.FileHandler;
import edu.cth.mosquito.filehandler.FileHandlerFactory;
import edu.cth.mosquito.filehandler.IFileHandler;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * 
 * @author Anton
 */
public class FileController {
    
    private IFileHandler fileHandler;
    private static final FileHandlerFactory factory = new FileHandlerFactory();
    
    public FileController(){
        fileHandler = factory.getFileHandler("FileHandler");
    }
    
    public void saveHighscoreToFile(List<Integer> highscore) throws FileNotFoundException {
        fileHandler.loadScoreFile(highscore);
        
    }
    
    public List<Integer> getHighscores() throws IOException {
       return fileHandler.loadHighscoreList();
    }
    
    public void deleteHighscore() throws FileNotFoundException {
        fileHandler.deleteHighscores();
    }
}
