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
        
        initObjects();
    }
    
    private void initObjects(){
        addSolidObject(1,1,1);
        addHuman(new Position3D(2,0,0),1,2,1);
        addHuman(new Position3D(-3,1,0),1,1,2);
    }
    
    private void addHuman(float width, float height, float length){
        objects.add(new Human(width,height,length));
    }
    
    private void addHuman(Position3D position, float width, float height, float length){
        objects.add(new Human(position,width,height,length));
    }
    
    private void addSolidObject(float width, float height, float length){
        objects.add(new SolidObject(width, height, length));
    }
    
    private void addSolidObject(Position3D position, float width, float height, float length){
        objects.add(new SolidObject(position, width, height, length));
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
    
    public List<SolidObject> getObjects(){
        return objects;
    }
    
}
