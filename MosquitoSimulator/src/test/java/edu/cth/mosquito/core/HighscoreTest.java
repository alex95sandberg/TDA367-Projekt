/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

import junit.framework.TestCase;

/**
 *
 * @author Anton
 */
public class HighscoreTest extends TestCase {
    
    int[] highscores = new int[5];
    Highscore h = new Highscore();
    
    public HighscoreTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        highscores = h.getHighscore().clone();
        h.resetHighscore();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        
        h.resetHighscore();
        
        for (int i = 0;i < highscores.length; i++){
            h.addHighscore(highscores[i]);
        }
        
        
    }

    public void testAddHighscore() {
        
        Highscore h1 = new Highscore();
        
        for(int i = 0; i < 25; i++){
            
            if(i == 5){
                h1.addHighscore(50);
            } else {
                h1.addHighscore(i);
            }
            
        }
        
        assertTrue(h1.getHighscore()[4] == 21 && 
                    h1.getHighscore()[3] == 22 &&
                     h1.getHighscore()[2] == 23 &&
                      h1.getHighscore()[1] == 24 &&
                       h1.getHighscore()[0] == 50);
        
    }
    
    public void testResetHighscoreList(){
        
        Highscore h = new Highscore();
        
        for(int i = 0; i < 20; i++){
            
            h.addHighscore(i);
            
        }
        
        h.resetHighscore();
        
        assertTrue(h.getHighscore()[0] == 0 &&
                    h.getHighscore()[1] == 0 &&
                     h.getHighscore()[2] == 0 &&
                      h.getHighscore()[3] == 0 &&
                       h.getHighscore()[4] == 0);
        
    }

}
