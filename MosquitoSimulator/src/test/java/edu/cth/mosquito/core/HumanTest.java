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
public class HumanTest extends TestCase {
    
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
        h1.decreaseBlood(12.3f);
        assertTrue(h1.getBlood() == 87.7f);
    }

    public void testIncreaseBlood() {
        Human h1 = new Human();
        h1.increaseBlood(45.6f);
        assertTrue(h1.getBlood() == 145.6f);
    }
}
