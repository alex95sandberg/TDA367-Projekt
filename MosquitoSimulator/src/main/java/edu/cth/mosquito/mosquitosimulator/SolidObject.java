/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.mosquitosimulator;

import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.control.GhostControl;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
 *
 * @author Alexander
 */
public class SolidObject {
    
    private Node solidObjectNode = new Node("SolidObject");
    private GhostControl ghost;
    
    public SolidObject(Vector3f size, PhysicsSpace physicsSpace){
        
        ghost = new GhostControl(new BoxCollisionShape(size));
        solidObjectNode.addControl(ghost);
        
        physicsSpace.add(ghost);
        physicsSpace.add(solidObjectNode);
        
    }
    
    public GhostControl getGhost(){
        return ghost;
    }
    
    public Node getNode(){
        return solidObjectNode;
    }
    
}
