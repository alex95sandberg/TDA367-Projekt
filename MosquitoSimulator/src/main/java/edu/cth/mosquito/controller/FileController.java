/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.controller;

import edu.cth.mosquito.filehandler.FileHandlerFactory;
import edu.cth.mosquito.filehandler.IFileHandler;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 
 * 
 * @author Mosquito
 */
public class FileController {
    
    private IFileHandler fileHandler;
    private static final FileHandlerFactory factory = new FileHandlerFactory();
    
    public FileController(){
        fileHandler = factory.getFileHandler("FileHandler");
    }
    
    public void writeHighscore(int[] highscore) throws FileNotFoundException {
        fileHandler.writeHighscore(highscore);
    }
    
    public int[] readHighscore() throws IOException {
       return fileHandler.readHighscore();
    }
    
    public void deleteHighscore() throws FileNotFoundException {
        fileHandler.deleteHighscores();
    }
}
