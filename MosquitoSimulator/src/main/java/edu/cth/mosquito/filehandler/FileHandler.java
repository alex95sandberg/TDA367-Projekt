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
 * Reads and writes in highscore.txt
 * 
 * @author Mosquito
 */
public class FileHandler implements IFileHandler{
    
    private static final String FILENAME = "highscore.txt";
    private PrintWriter ops;
    
    //Writes the highscores to highscore.txt
     
    @Override
    public void writeHighscore(int[] highscores) throws FileNotFoundException {
            
        ops = new PrintWriter(FILENAME);
            
        for(int i = 0; i < highscores.length; i++){   
            ops.println(highscores[i]);  
        }
        
        ops.close();
        
    }
    
    //returns the content of highscore.txt
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
    
    //A method thats prints five "0" to the highscore file 
    //this removing the previous highscore from the file
     
    @Override
    public void deleteHighscores() throws FileNotFoundException {
        ops = new PrintWriter(FILENAME);
        
        for (int i = 0; i < 5; i++){
            ops.println("0");
        }
        
        ops.close();
    }
    
}
