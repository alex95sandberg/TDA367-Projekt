/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.util;

/**
 * A class that handles a position in 3-dimensions
 * 
 * @author Mosquito
 */
public class Position3D {
    private float x, y, z;
    
    public Position3D(){
        x = 0;
        y = 0;
        z = 0;
    }
    
    public Position3D(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public Position3D(Position3D position){
        this.x = position.getX();
        this.y = position.getY();
        this.z = position.getZ();
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
        
    public float getZ(){
        return z;
    }
    
    public void setX(float x){
        this.x = x;
    }
    
    public void setY(float y){
        this.y = y;
    }
        
    public void setZ(float z){
        this.z = z;
    }
    
    public void setPosition(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public void setPosition(Position3D position){
        this.x = position.getX();
        this.y = position.getY();
        this.z = position.getZ();
    }
    
}
