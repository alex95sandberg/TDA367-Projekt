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
     * @throws FileNotFoundException if the file is not found
     */
    public void loadScoreFile(ArrayList<Integer> highScores) throws FileNotFoundException {
            
        ops = new PrintWriter(fileName);
            
        for(Integer a : highScores){
                
            ops.println(a.toString());
                
        }
            
        ops.close(); //Flushes data to the file
        
    }
    
   /**
    * Loads the ArrayList with the saved scores in highscore.txt
    * @throws IOException
    */
    public ArrayList<Integer> loadHighscoreList() throws IOException {
        
        ArrayList<Integer> tempHighscore = new ArrayList<>();
        
        for(String s : Files.readAllLines(Paths.get(fileName), Charset.defaultCharset())){
            
            tempHighscore.add(Integer.parseInt(s));
        
        }
            
        return tempHighscore;
        
    }
    
}
