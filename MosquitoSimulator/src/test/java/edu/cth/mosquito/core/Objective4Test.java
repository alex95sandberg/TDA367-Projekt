/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;

/**
 *
 * @author Mosquito
 */
public class Objective4Test extends TestCase {
    
    public Objective4Test(String testName) {
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

    public void testIncreaseProgress() {
        Objective4 o4 = new Objective4();
        float testProgress = 0;
        
        for (int i = 0; i < 10; i++){
            float random = (float) (Math.random());
            testProgress += random;
            o4.increaseProgress(random);
        }
        
        assertTrue(o4.getProgress() == testProgress);
        
        o4.increaseProgress(o4.getObjectiveGoal() * 2);
        
        assertTrue(o4.getProgress() == o4.getObjectiveGoal());
    }
}
