/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

import java.util.Random;
import junit.framework.TestCase;

/**
 *
 * @author Johan
 */
public class HumanTest extends TestCase {
    
    Random rnd = new Random();
    
    public HumanTest(String testName) {
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

    public void testDecreaseBlood() {
        Human h1 = new Human();
        
        float maxBlood = h1.getBlood();
        float bloodDiff = rnd.nextFloat();
        
        h1.decreaseBlood(bloodDiff);
        assertTrue(h1.getBlood() == maxBlood - bloodDiff);
    }

    public void testIncreaseBlood() {
        Human h1 = new Human();
        
        float maxBlood = h1.getBlood();
        float bloodDiff = rnd.nextFloat();
        
        h1.increaseBlood(bloodDiff);
        assertTrue(h1.getBlood() == maxBlood + bloodDiff);
    }
}
