/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.mosquitosimulator;

/**
 *
 * @author Alexander
 */
public class Human extends newSolidObject{
    private float blood;
    
    public Human(){
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
