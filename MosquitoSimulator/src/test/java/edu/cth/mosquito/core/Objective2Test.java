/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;

/**
 *
 * @author Johan
 */
public class Objective2Test extends TestCase {
    
    public Objective2Test(String testName) {
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
        Objective2 o2 = new Objective2();
        float testProgress = 0;
        
        for (int i = 0; i < 10; i++){
            float random = (float) (Math.random());
            testProgress += random;
            o2.increaseProgress(random);
        }
        
        assertTrue(o2.getProgress() == testProgress);
        
        o2.increaseProgress(o2.getObjectiveGoal() * 2);
        
        assertTrue(o2.getProgress() == o2.getObjectiveGoal());
    }
}
