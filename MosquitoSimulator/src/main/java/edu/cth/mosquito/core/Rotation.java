/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

/**
 *
 * @author Johan
 */
public class Rotation {
    
    float x, y, z;
    float pi = 3.141592f;
    
    public Rotation(){
        x = 0;
        y = 0;
        z = 0;
    }
    
    public Rotation(float x, float y, float z){
        this.x = x;//%(2*pi);
        this.y = y;//%(2*pi);
        this.z = z;//%(2*pi);
    }
    
    public void rotate(float dx, float dy, float dz){
        this.x = (this.x + dx);//%(2*pi);
        this.y = (this.y + dy);//%(2*pi);
        this.z = (this.z + dz);//%(2*pi);
    }
    
    public float getXRotation(){
        return x;
    }
    
    public float getYRotation(){
        return y;
    }
    
    public float getZRotation(){
        return z;
    }
    
}
