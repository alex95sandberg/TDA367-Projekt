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
public class Objective3Test extends TestCase {
    
    public Objective3Test(String testName) {
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
        Objective3 o3 = new Objective3();
        float testProgress = 0;
        
        for (int i = 0; i < 10; i++){
            float random = (float) (Math.random());
            testProgress += random;
            o3.increaseProgress(random);
        }
        
        assertTrue(o3.getProgress() == testProgress);
        
        o3.increaseProgress(o3.getObjectiveGoal() * 2);
        
        assertTrue(o3.getProgress() == o3.getObjectiveGoal());
    }
}
