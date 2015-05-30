/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

import junit.framework.TestCase;

/**
 *
 * @author Johan
 */
public class Objective1Test extends TestCase {
    
    public Objective1Test(String testName) {
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
        Objective1 o1 = new Objective1();
        float testProgress = 0;
        
        for (int i = 0; i < 10; i++){
            float random = (float) (Math.random());
            testProgress += random;
            o1.increaseProgress(random);
        }
        
        assertTrue(o1.getProgress() == testProgress);
        
        o1.increaseProgress(o1.getObjectiveGoal() * 2);
        
        assertTrue(o1.getProgress() == o1.getObjectiveGoal());
    }
}
