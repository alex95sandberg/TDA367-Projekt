/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Johan
 */

public class World {
    
    private List<SolidObject> objects = new ArrayList<>();
    private float width, height, length;
    
    public World(float width, float height, float length){
        this.width = width;
        this.height = height;
        this.length = length;
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
