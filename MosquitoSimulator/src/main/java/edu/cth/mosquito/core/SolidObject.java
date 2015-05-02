/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

/**
 *
 * @author Alexander
 */
public class SolidObject {
    private Position3D position;
    private float width, height;
    
    public SolidObject(){
        position = new Position3D();
    }
    
    public SolidObject(Position3D position){
        this.position = position;
    }
    
    public SolidObject(float width, float height){
        this.width = width;
        this.height = height;
    }
    
    public SolidObject(Position3D position, float width, float height){
        this.position = position;
        this.width = width;
        this.height = height;
    }
    
    private Position3D getPosition(){
        return position;
    }
    
    private float getWidth(){
        return width;
    }
    
    private float getHeight(){
        return height;
    }
}
