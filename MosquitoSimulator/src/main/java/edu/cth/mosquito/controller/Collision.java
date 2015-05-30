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
 * This class takes the player Node and all the object Nodes we want to detect collision with.
 * Then does all of the necessary initiations to be able to detect collision between the player 
 * and the objects.
 * @author Mosquito
 */
public class Collision {
    
    private final BulletAppState bulletAppState;
    private final List<Node> solidObjectNodes;
    private final Node mosquitoNode;
    private final List<RigidBodyControl> rbArray;
    private GhostControl playerGhost;
    private RigidBodyControl rb;
    
    public Collision(final BulletAppState bulletAppState, final Node mosquitoNode, final List<Node> solidObjectNodes){
        
        this.bulletAppState = bulletAppState;
        this.solidObjectNodes = solidObjectNodes;
        this.rbArray = new ArrayList<>();
        this.mosquitoNode = mosquitoNode;
        
        initCollision();
    }
    
    //Adds a ghostControl to the player Node. It's this Control that we use 
    //to detect collision between the player and the objects.
    private void addGhostControl(){
        
        playerGhost = new GhostControl(new BoxCollisionShape(new Vector3f(0.4f,0.4f,0.4f)));
        mosquitoNode.addControl(playerGhost);
        
    }
    
    //We take all of the object nodes and make collisionShapes, then we take those and make
    //RigidBodyControls. It is these objects that playerGhost detect collision with. 
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
    
    //Its in the physicsSpace we check for collision
    //and its here where we add all of the objects to it.
    private void addObjectsToPhysicsSpace(){
        
        getPhysicsSpace().add(playerGhost);
        
        for(RigidBodyControl a : rbArray){
            
            getPhysicsSpace().add(a);
            
        }
        
    }
    
    //Here we take the objects initial position and set their PhysicsSpace
    //position equal to it. 
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
