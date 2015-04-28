/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.mosquitosimulator;

/**
 *
 * @author Alexander
 */
public class newSolidObject {
    private Position3D position;
    private float width, heigth;
    
    public newSolidObject(){
        position = new Position3D();
    }
    
    public newSolidObject(Position3D position){
        this.position = position;
    }
    
    public newSolidObject(float width, float heigth){
        this.width = width;
        this.heigth = heigth;
    }
    
    public newSolidObject(Position3D position, float width, float heigth){
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
    
    private float getHeigth(){
        return heigth;
    }
}
