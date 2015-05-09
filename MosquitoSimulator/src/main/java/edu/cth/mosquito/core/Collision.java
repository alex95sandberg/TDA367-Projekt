/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.GhostControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import java.util.ArrayList;

/**
 *
 * @author Anton
 */
public class Collision {
    
    private BulletAppState bulletAppState;
    private ArrayList<Node> solidObjectNodes;
    private ArrayList<RigidBodyControl> rbcArray;
    private GhostControl playerGhost;
    private RigidBodyControl rbc;
    private Node mosquitoNode;
    
    public Collision(BulletAppState bulletAppState, Node mosquitoNode, ArrayList<Node> solidObjectNodes){
        
        this.bulletAppState = bulletAppState;
        this.solidObjectNodes = solidObjectNodes;
        this.rbcArray = new ArrayList<>();
        this.mosquitoNode = mosquitoNode;
        
        initCollision();
    }
    
    private void addGhostControl(){
        
        playerGhost = new GhostControl(new BoxCollisionShape(new Vector3f(0.3f,0.3f,0.3f)));
        mosquitoNode.addControl(playerGhost);
        
    }
    
    private void addControls(){
        
        for(Node a : solidObjectNodes){
        
            CollisionShape solidObjectShape = CollisionShapeFactory.createMeshShape(a);
            rbc = new RigidBodyControl(solidObjectShape, 0);
            
            rbcArray.add(rbc);
            
        }
    }
    
    private void initCollision(){
        
        addGhostControl();
        addControls();
        addToPhysicsSpace();
        
    }
    
    private void addToPhysicsSpace(){
        
        getPhysicsSpace().add(playerGhost);
        
        for(RigidBodyControl a : rbcArray){
            
            getPhysicsSpace().add(a);
            
        }
        
    }
    
    public boolean isColliding(){
        
        if(playerGhost.getOverlappingCount() > 0){
            return true;
        }
 
        return false;
        
    }
    
    private PhysicsSpace getPhysicsSpace(){
        
        return bulletAppState.getPhysicsSpace();
        
    }
    
}
