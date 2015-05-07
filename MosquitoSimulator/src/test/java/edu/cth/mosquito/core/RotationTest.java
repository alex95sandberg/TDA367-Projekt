/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

import edu.cth.mosquito.util.Rotation;
import junit.framework.TestCase;

/**
 *
 * @author Johan
 */
public class RotationTest extends TestCase {
    
    float pi = 3.141592f;
    
    public RotationTest(String testName) {
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

    public void testRotate() {
        Rotation r1 = new Rotation(pi, pi, pi);
        
        r1.rotate(2*pi, 2*pi, 2*pi);
        
        System.out.println(r1.getXRotation());
        System.out.println(pi);
        
        assertTrue(r1.getXRotation() == pi);
    }
}
