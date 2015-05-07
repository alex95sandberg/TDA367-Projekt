/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

import edu.cth.mosquito.util.Position3D;
import java.util.Random;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;

/**
 *
 * @author Johan
 */
public class PlayerTest extends TestCase {
    
    Random rnd = new Random();
    
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
        float x1 = rnd.nextFloat();
        float y1 = rnd.nextFloat();
        float z1 = rnd.nextFloat();
        
        Player p1 = new Player(new Position3D(x1, y1, z1), new World(0,0,0));
        
        float x2 = rnd.nextFloat();
        float y2 = rnd.nextFloat();
        float z2 = rnd.nextFloat();
        
        p1.move(new Position3D(x2, y2, z2));
        
        assertTrue(p1.getPosition().getX() == x1+x2 && 
                    p1.getPosition().getY() == y1+y2 &&
                     p1.getPosition().getZ() == z1+z2);
        
    }

    public void testDecreaseEnergy() {
        Player p2 = new Player(new World(0,0,0));
        
        float max = p2.getEnergy();
        float energyDiff = rnd.nextFloat();
        
        p2.decreaseEnergy(energyDiff);
        assertTrue(p2.getEnergy() == max - energyDiff);
    }

    public void testIncreaseEnergy() {
        Player p3 = new Player(new World(0,0,0));
        
        float max = p3.getEnergy();
        float energyDiff = rnd.nextFloat();
        
        p3.increaseEnergy(energyDiff);
        assertTrue(p3.getEnergy() == max + energyDiff);
    }

    public void testIncreaseScore() {
        Player p4 = new Player(new World(0,0,0));
        
        float start = p4.getScore();
        float scoreDiff = rnd.nextFloat();
        
        p4.increaseScore(scoreDiff);
        assertTrue(p4.getScore() == start + scoreDiff);
    }
}
