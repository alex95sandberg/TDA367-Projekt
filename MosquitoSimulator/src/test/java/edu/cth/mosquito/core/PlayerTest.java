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
        
        Player p1 = new Player(new Position3D(x1, y1, z1), new World(10, 10, 10));
        
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
        float energyDiff = rnd.nextFloat() * 10;
        
        p3.decreaseEnergy(max/2);
        p3.increaseEnergy(energyDiff);
        assertTrue(p3.getEnergy() == max/2 + energyDiff);
    }

    public void testIncreaseScore() {
        Player p4 = new Player(new World(0,0,0));
        
        float start = p4.getScore();
        float scoreDiff = rnd.nextFloat();
        
        p4.increaseScore(scoreDiff);
        assertTrue(p4.getScore() == start + scoreDiff);
    }
    
    public void testReset(){
        Player p5 = new Player(new World(10, 10, 10));
        
        p5.decreaseEnergy(50f);
        p5.increaseScore(100);
        p5.move(new Position3D(1,2,3));
        
        p5.reset();
        assertTrue(p5.getEnergy() == p5.getMaxEnergy() &&
                    p5.getPosition().getX() == 0 &&
                     p5.getPosition().getY() == 0 &&
                      p5.getPosition().getZ() == 0 &&
                       p5.getScore() == 0);
    }
    
    public void testWorldPositiveLimit(){
        Player p6 = new Player(new World(10, 7, 5));
        
        for (int i = 0; i < 30; i++){
            p6.move(new Position3D(1, 1, 1));
            assertTrue(p6.getPosition().getX() < 10.1f &&
                        p6.getPosition().getY() < 7.1f &&
                         p6.getPosition().getZ() < 5.1f);
        }
    }
    
    public void testWorldNegativeLimit(){
        Player p7 = new Player(new World(10, 7, 5));
        
        for (int i = 0; i < 30; i++){
            p7.move(new Position3D(-1, -1, -1));
            assertTrue(p7.getPosition().getX() > -10.1f &&
                        p7.getPosition().getY() > -7.1f &&
                         p7.getPosition().getZ() > -5.1f);
        }
    }
}
