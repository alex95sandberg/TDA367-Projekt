/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author Anton
 */
public class FileHandler {
    
    private static final String fileName = "highscore.txt";
    private PrintWriter ops;
    
     /**
     * Loads the highscore.txt with the scores found in the ArrayList containing the scores
     * @throws FileNotFoundException if the file which to write to is not found
     */
    public void loadScoreFile(ArrayList<Integer> highScores) throws FileNotFoundException {
            
        ops = new PrintWriter(fileName);
            
        for(Integer a : highScores){
                
            ops.println(a.toString());
                
        }
            
        ops.close();
        
    }
    
   /**
    * Loads and returns an ArrayList with the saved scores in the highscore file
    * @throws IOException
    */
    public ArrayList<Integer> loadHighscoreList() throws IOException {
        
        ArrayList<Integer> tempHighscore = new ArrayList<>();
        
        for(String s : Files.readAllLines(Paths.get(fileName), Charset.defaultCharset())){
            
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
        
        ops = new PrintWriter(fileName);
        
        ops.print("");
        
        ops.close();
        
    }
    
}
