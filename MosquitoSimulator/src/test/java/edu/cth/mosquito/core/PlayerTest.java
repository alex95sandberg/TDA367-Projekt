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
public class PlayerTest extends TestCase {
    
    public PlayerTest(String testName) {
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

    public void testMove() {
        Player p1 = new Player(new Position3D(1, 1, 1));
        p1.move(new Position3D(1, -1, 0));
        assertTrue(p1.getPosition().getX() == 2 && 
                    p1.getPosition().getY() == 0 &&
                     p1.getPosition().getZ() == 1);
        
    }

    public void testDecreaseEnergy() {
        Player p2 = new Player();
        p2.decreaseEnergy(4.2f);
        p2.decreaseEnergy(30.0f);
        assertTrue(p2.getEnergy() == 65.8f);
    }

    public void testIncreaseEnergy() {
        Player p3 = new Player();
        p3.increaseEnergy(3.4f);
        p3.increaseEnergy(20.0f);
        assertTrue(p3.getEnergy() == 123.4f);
    }

    public void testIncreaseScore() {
        Player p4 = new Player();
        p4.increaseScore(3.45f);
        p4.increaseScore(10.0f);
        assertTrue(p4.getScore() == 13.45f);
    }
}
