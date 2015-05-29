/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.filehandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Mosquito
 */
public class FileHandler implements IFileHandler{
    
    private static final String FILENAME = "highscore.txt";
    private PrintWriter ops;
    private int[] highscoreList = new int[5];
    
     /**
     * Loads the highscore.txt with the scores found in the ArrayList containing the scores
     * @throws FileNotFoundException if the file which to write to is not found
     */
    @Override
    public void writeHighscore(int[] highscores) throws FileNotFoundException {
            
        ops = new PrintWriter(FILENAME);
            
        for(int i = 0; i < highscores.length; i++){   
            ops.println(highscores[i]);  
        }
        
        ops.close();
        
    }
    
   /**
    * Loads and returns an ArrayList with the saved scores in the highscore file
    * @throws IOException
    */
    @Override
    public int[] readHighscore() throws IOException {
        
        int[] tempHighscore = new int[5];
        int i = 0;
        for(String s : Files.readAllLines(Paths.get(FILENAME), Charset.defaultCharset())){
            tempHighscore[i] = Integer.parseInt(s);
            i++;
        }
            
        return tempHighscore;
        
    }
    
    /**
     * A method thats prints a empty string to the highscore file 
     * thus removing the previous highscore from the file
     * @throws FileNotFoundException if the file which to write to is not found.
     */
    @Override
    public void deleteHighscores() throws FileNotFoundException {
        
        ops = new PrintWriter(FILENAME);
        
        ops.print("");
        
        ops.close();
        
    }
    
}
