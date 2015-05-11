/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.PhysicsCollisionObject;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.GhostControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anton
 */
public class Collision {
    
    private BulletAppState bulletAppState;
    private List<Node> solidObjectNodes;
    private List<RigidBody> rbArray;
    private GhostControl playerGhost;
    private Node mosquitoNode;
    private RigidBody rb;
    
    public Collision(BulletAppState bulletAppState, Node mosquitoNode, List<Node> solidObjectNodes){
        
        this.bulletAppState = bulletAppState;
        this.solidObjectNodes = solidObjectNodes;
        this.rbArray = new ArrayList<>();
        this.mosquitoNode = mosquitoNode;
        
        initCollision();
    }

    private void addGhostControl(){
        
        playerGhost = new GhostControl(new BoxCollisionShape(new Vector3f(0.4f,0.4f,0.4f)));
        mosquitoNode.addControl(playerGhost);
        
    }
    
    private void addControls(){
        
        for(Node a : solidObjectNodes){
            
            CollisionShape solidObjectShape = CollisionShapeFactory.createMeshShape(a);
            rb = new RigidBody(solidObjectShape, 0, a.getName());
            
            rbArray.add(rb);
            
        }
    }
    
    private void initCollision(){
        
        addGhostControl();
        addControls();
        addObjectsToPhysicsSpace();
        setPhysicsLocation();
        
    }
    
    private void addObjectsToPhysicsSpace(){
        
        getPhysicsSpace().add(playerGhost);
        
        for(RigidBodyControl a : rbArray){
            
            getPhysicsSpace().add(a);
            
        }
        
    }
    
    private void setPhysicsLocation(){
        
        playerGhost.setPhysicsLocation(mosquitoNode.getLocalTranslation());
        
        for(int i = 0; i < rbArray.size(); i++){
            
            rbArray.get(i).setPhysicsLocation(solidObjectNodes.get(i).getLocalTranslation());
            
        }
        
    }
    
    public String getColliding(){
        
        if(playerGhost.getOverlappingCount() == 1){
            
            for(PhysicsCollisionObject a : playerGhost.getOverlappingObjects()){
                
                if(a.getClass().equals(RigidBody.class)){
                    
                    return a.toString();
                    
                }
                
            }
            
        }
 
        return "Empty";
        
    }
    
    private PhysicsSpace getPhysicsSpace(){
        
        return bulletAppState.getPhysicsSpace();
        
    }
    
}
