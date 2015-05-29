/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

import edu.cth.mosquito.util.Position3D;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;

/**
 *
 * @author Alexander
 */
public class WorldTest extends TestCase {
    
    public WorldTest(String testName) {
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

    public void testResetHumans() {
    World w1 = new World(10,10,10);
    w1.addHuman(new Position3D());
    w1.addSolidObject(1, 2, 3);
    w1.addHuman(new Position3D());
    w1.addHuman(new Position3D());
    
    Human h1 = (Human)w1.getObjects().get(0);
    Human h2 = (Human)w1.getObjects().get(2);
    Human h3 = (Human)w1.getObjects().get(3);
    
    h1.decreaseBlood(50);
    h2.decreaseBlood(33);
    h3.decreaseBlood(100);
    
    w1.resetHumans();
    
    assertTrue(h1.getBlood() == 100 && h2.getBlood() == 100 && h3.getBlood() == 100);
    
    }

    public void testAddHuman_3args() {
        World w1 = new World(10, 10, 10);
        int initialSize = w1.getObjects().size();
        
        w1.addHuman(1, 1, 1);
        w1.addHuman(1, 1, 1);
        w1.addHuman(1, 1, 1);
        
        assertTrue(w1.getObjects().size() == initialSize + 3);
    }

    public void testAddHuman_4args() {
        World w1 = new World(10, 10, 10);
        int initialSize = w1.getObjects().size();
        
        w1.addHuman(new Position3D(0,0,0), 1, 1, 1);
        w1.addHuman(new Position3D(5,0,8), 1, 2, 1);
        w1.addHuman(new Position3D(7,0,-3), 1, 3, 1);
        w1.addHuman(new Position3D(-3,0,4), 1, 4, 1);
        
        assertTrue(w1.getObjects().size() == initialSize + 4);
    }

    public void testAddHuman_Position3D() {
        World w1 = new World(10, 10, 10);
        int initialSize = w1.getObjects().size();
        
        w1.addHuman(new Position3D(0, 0, 0));
        w1.addHuman(new Position3D(1, 1, 1));
        
        assertTrue(w1.getObjects().size() == initialSize + 2);
    }

    public void testAddSolidObject_3args() {
        World w1 = new World(10,10,10);
        int initialSize = w1.getObjects().size();
        
        w1.addSolidObject(0, 1, 2);
        w1.addSolidObject(0, 1, 2);
        w1.addSolidObject(0, 1, 2);
        
        assertTrue(w1.getObjects().size() == initialSize + 3);
    }

    public void testAddSolidObject_4args() {
        World w1 = new World(10,10,10);
        int initialSize = w1.getObjects().size();
        
        w1.addSolidObject(new Position3D(), 0, 1, 2);
        w1.addSolidObject(new Position3D(), 0, 1, 2);
        w1.addSolidObject(new Position3D(), 0, 1, 2);
        
        
        assertTrue(w1.getObjects().size() == initialSize + 3);
    }

}
