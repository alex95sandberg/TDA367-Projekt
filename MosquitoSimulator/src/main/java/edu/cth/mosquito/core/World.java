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
    private float width, height;
    private Player player;
    
    public World(float width, float height, Player player){
        this.width = width;
        this.height = height;
        this.player = player;
    }
    
    public float getWidth(){
        return width;
    }
    
    public float getHeight(){
        return height;
    }
    
}
