/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

import com.jme3.bullet.collision.shapes.CollisionShape;

/**
 *
 * @author Anton
 */
public class RigidBody extends com.jme3.bullet.control.RigidBodyControl {
    
    private String name;
    
    public RigidBody(CollisionShape shape, String name){
        
        super(shape);
        this.name = name;
        
    }
    
    public RigidBody(float mass, String name){
        
        super(mass);
        this.name = name;
        
    }
    
    public RigidBody(CollisionShape shape, float mass, String name){
        
        super(shape, mass);
        this.name = name;
        
    }
    
    public String getName(){
        
        return this.name;
        
    }
    
    @Override
    public String toString(){
        
        return this.name;
        
    }
    
}
