/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

/**
 *
 * @author Alexander
 */
public class Human extends SolidObject{
    private float blood;
    
    public Human(){
        super();
        blood = 100;   
    }
    
    public Human(Position3D position){
        super(position);
        blood = 100;
    }
    
    public Human(float width, float height, float length){
        super(width, height, length);
        blood = 100;
    }
    
    public Human(Position3D position, float width, float height, float length){
        super(position, width, height, length);
        blood = 100;
    }
    
    public float getBlood(){
        return blood;
    }
    
    public void decreaseBlood(float amount){
        blood -= amount;
    }
    
    public void increaseBlood(float amount){
        blood += amount;
    }
}
