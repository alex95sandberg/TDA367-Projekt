/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

import edu.cth.mosquito.util.Position3D;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains all the world objects and the size of the world
 * 
 * @author Mosquito
 */

public class World {
    
    private List<SolidObject> objects = new ArrayList<>();
    private float width, height, length;
    private SolidObject collidingObject;
    
    public World(float width, float height, float length){
        this.width = width;
        this.height = height;
        this.length = length;
        
        initObjects();
    }
    
    private void initObjects(){
        addHuman(new Position3D(30, -10, 50));
        addHuman(new Position3D(25, -10, 50));
        addHuman(new Position3D(22, -10, -36));
        addHuman(new Position3D(-32, -10, -12));
        addHuman(new Position3D(-38, -10, 40));
        addHuman(new Position3D(-17, -10, 20));
        addHuman(new Position3D(-8, -10, -50));
        addHuman(new Position3D(-28, -10, -37));
        addHuman(new Position3D(10, -10, 0));
        addHuman(new Position3D(14, -10, 34));        
    }
    
    public void resetHumans(){
        for(int i = 0; i < objects.size(); i++){
            if(objects.get(i) instanceof Human){
                ((Human)objects.get(i)).increaseBlood(100);
            }
        }
    }
    
    public void addHuman(float width, float height, float length){
        objects.add(new Human(width,height,length));
    }
    
    public void addHuman(Position3D position, float width, float height, float length){
        objects.add(new Human(position,width,height,length));
    }
    
    public void addHuman(Position3D position){
        objects.add(new Human(position, ((float)Math.random()+1), ((float)Math.random()+1), ((float)Math.random()+1)));
    }
    
    public void addSolidObject(float width, float height, float length){
        objects.add(new SolidObject(width, height, length));
    }
    
    public void addSolidObject(Position3D position, float width, float height, float length){
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
    
    public SolidObject getCollidingObject(){
        return collidingObject;
    }
    
    public void setCollidingObject(SolidObject collidingObject){
        this.collidingObject = collidingObject;
    }
    
}
