/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.controller;

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
    private List<RigidBodyControl> rbArray;
    private GhostControl playerGhost;
    private Node mosquitoNode;
    private RigidBodyControl rb;
    
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
            rb = new RigidBodyControl(solidObjectShape, 0);
            
            a.addControl(rb);
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
    
    public boolean isColliding(){
        return playerGhost.getOverlappingCount() == 1;
    }
    
    public Node getCollidingNode(){
        
        if(isColliding()){
            
            for(PhysicsCollisionObject a : playerGhost.getOverlappingObjects()){
                
                if(a.getClass().equals(RigidBodyControl.class)){
                     
                    return (Node)a.getUserObject();
                    
                }
                
            }
            
        }
 
        return null;
        
    }
    
    private PhysicsSpace getPhysicsSpace(){
        
        return bulletAppState.getPhysicsSpace();
        
    }
    
}