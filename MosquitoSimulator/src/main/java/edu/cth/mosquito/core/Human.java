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
    private float maxBlood = 100;
    
    public Human(){
        super();
        blood = maxBlood;   
    }
    
    public Human(Position3D position){
        super(position);
        blood = maxBlood;
    }
    
    public Human(float width, float height, float length){
        super(width, height, length);
        blood = maxBlood;
    }
    
    public Human(Position3D position, float width, float height, float length){
        super(position, width, height, length);
        blood = maxBlood;
    }
    
    public float getBlood(){
        return blood;
    }
    
    public void decreaseBlood(float amount){
        blood -= amount;
        
        if(blood < 0)
            blood = 0;
    }
    
    public void increaseBlood(float amount){
        blood += amount;
        
        if(blood > maxBlood)
            blood = maxBlood;
    }
}
