/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

import edu.cth.mosquito.util.Position3D;

/**
 *
 * @author Alexander
 */
public class SolidObject {
    private Position3D position;
    private float width, height, length;
    
    public SolidObject(){
        position = new Position3D();
    }
    
    public SolidObject(Position3D position){
        this.position = position;
    }
    
    public SolidObject(float width, float height, float length){
        this.width = width;
        this.height = height;
        this.length = length;
        this.position = new Position3D();
    }
    
    public SolidObject(Position3D position, float width, float height, float length){
        this.position = position;
        this.width = width;
        this.height = height;
        this.length = length;
    }
    
    public Position3D getPosition(){
        return position;
    }
    
    public float getWidth(){
        return width;
    }
    
    public float getHeight(){
        return height;
    }
    
    public float getLength(){
        return length;
    }
}
