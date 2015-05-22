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
    
    public HighscoreTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testAddScore() {
        
        Highscore h1 = new Highscore();
        
        for(int i = 0; i < 25; i++){
            
            if(i == 5){
                h1.addScore(50);
            } else {
                h1.addScore(i);
            }
            
        }
        
        assertTrue(h1.getSize() == 25 && h1.getHighscore().get(h1.getSize()-1) == 50);
        
    }
    
    public void testResetHighscoreList(){
        
        Highscore h = new Highscore();
        
        for(int i = 0; i < 20; i++){
            
            h.addScore(i);
            
        }
        
        h.resetHighscore();
        
        assertTrue(h.getHighscore().isEmpty());
        
    }

}
