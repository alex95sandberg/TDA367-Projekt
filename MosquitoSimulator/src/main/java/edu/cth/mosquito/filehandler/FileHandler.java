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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mosquito
 */
public class FileHandler implements IFileHandler{
    
    private static final String FILENAME = "highscore.txt";
    private PrintWriter ops;
    
     /**
     * Loads the highscore.txt with the scores found in the ArrayList containing the scores
     * @throws FileNotFoundException if the file which to write to is not found
     */
    public void loadScoreFile(List<Integer> highScores) throws FileNotFoundException {
            
        ops = new PrintWriter(FILENAME);
            
        for(Integer a : highScores){
                
            ops.println(a.toString());
                
        }
            
        ops.close();
        
    }
    
   /**
    * Loads and returns an ArrayList with the saved scores in the highscore file
    * @throws IOException
    */
    public List<Integer> loadHighscoreList() throws IOException {
        
        List<Integer> tempHighscore = new ArrayList<>();
        
        for(String s : Files.readAllLines(Paths.get(FILENAME), Charset.defaultCharset())){
            
            tempHighscore.add(Integer.parseInt(s));
        
        }
            
        return tempHighscore;
        
    }
    
    /**
     * A method thats prints a empty string to the highscore file 
     * thus removing the previous highscore from the file
     * @throws FileNotFoundException if the file which to write to is not found.
     */
    public void deleteHighscores() throws FileNotFoundException {
        
        ops = new PrintWriter(FILENAME);
        
        ops.print("");
        
        ops.close();
        
    }
    
}
