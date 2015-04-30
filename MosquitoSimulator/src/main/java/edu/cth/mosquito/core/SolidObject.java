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
    private float width, heigth;
    
    public SolidObject(){
        position = new Position3D();
    }
    
    public SolidObject(Position3D position){
        this.position = position;
    }
    
    public SolidObject(float width, float heigth){
        this.width = width;
        this.heigth = heigth;
    }
    
    public SolidObject(Position3D position, float width, float heigth){
        this.position = position;
        this.width = width;
        this.heigth = heigth;
    }
    
    private Position3D getPosition(){
        return position;
    }
    
    private float getWidth(){
        return width;
    }
    
    private float getHeight(){
        return heigth;
    }
}
