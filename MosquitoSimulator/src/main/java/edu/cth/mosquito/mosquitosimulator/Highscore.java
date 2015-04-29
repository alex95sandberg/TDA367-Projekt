/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.mosquitosimulator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Alexander
 */
public class Highscore {
    
    private static ArrayList<Integer> highScores;
    private static final String fileName = "highscore.txt";
    private static PrintWriter ops;
    
    public Highscore(){
        
        highScores = new ArrayList<>();
        
    }
    
    /**
     * Adds a score to the score list
     * @param newScore the score to be added
     */
    public void addScore(int newScore){
        
        highScores.add(newScore);
        
        sortArray();
    }
    
    /**
     * Returns the high score arrayList
     * @return score list
     */
    public ArrayList<Integer> getHighscores(){
        
        return highScores;
        
    }
    
    /**
     * Loads the highscore.txt with the scores found in the ArrayList containing the scores
     */
    public void loadScoreFile(){
        
        try{
            
            ops = new PrintWriter(fileName);
            
            for(Integer a : highScores){
                
                ops.println(a.toString());
                
            }
            
            ops.close(); //Flushes data to the file
            
        } catch(FileNotFoundException e){
            
            System.out.println("File not found! " + e.getMessage());
            
        }
        
        
    }
    
    /**
     * Sort the ArrayList to have the scores in a acending order
     */
    private void sortArray(){
        
        Collections.sort(highScores);
        
    }
   /**
    * Loads the ArrayList with the saved scores in highscore.txt
    */
    public void loadHighscoreList(){
        
        try {
            
            for(String s : Files.readAllLines(Paths.get(fileName), Charset.defaultCharset())){
                
                Integer i = Integer.valueOf(s);
                highScores.add(i);
            
            }
            
        } catch (IOException e) {
            
            System.out.println("IOException! " + e.getMessage());
            
        }
        
    }
    
}
