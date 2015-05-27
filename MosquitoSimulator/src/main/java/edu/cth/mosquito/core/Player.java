/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

import edu.cth.mosquito.util.Position3D;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Alexander
 */
public class Player {
    private World world;
    private Position3D pos;
    private float energy;
    private final float maxEnergy = 100;
    private float score;
    private Objectives objectives;
    private ArrayList<Objectives> objList;
    private Random rand;
    private Objectives currentObj;
    private int indexObj;
    private Objectives testrandobj;

    
    public Player(World world){
        this.energy = maxEnergy;
        this.pos = new Position3D();
        this.world  = world;
        
    }
    
    public Player(Position3D position, World world){
        this.energy = maxEnergy;
        this.pos = new Position3D(position);
        this.world = world;
    }
    
    public void move(Position3D distance){
        float tempX = pos.getX();
        float tempY = pos.getY();
        float tempZ = pos.getZ();
        
        if(pos.getX() < world.getWidth() && distance.getX() > 0)
            tempX += distance.getX();
        else if(pos.getX() > -world.getWidth() && distance.getX() < 0)
            tempX += distance.getX();
        
        if(pos.getY() < world.getHeight() && distance.getY() > 0)
            tempY += distance.getY();
        else if(pos.getY() > -world.getHeight() && distance.getY() < 0)
            tempY += distance.getY();
        
        if(pos.getZ() < world.getLength() && distance.getZ() > 0)
            tempZ += distance.getZ();
        else if(pos.getZ() > -world.getLength() && distance.getZ() < 0)
            tempZ += distance.getZ();
        
        pos.setPosition(tempX, tempY, tempZ);
    }
    
    public Position3D getPosition(){
        return pos;
    }
    
    public void reset(){
        energy = maxEnergy;
        score = 0;
        pos = new Position3D();
    }
    
    public float getEnergy(){
        return energy;
    }
    
    public float getMaxEnergy(){
        return maxEnergy;
    }
    
    public void decreaseEnergy(float amount){
        energy -= amount;
        
        if(energy < 0)
            energy = 0;
    }
    
    public void increaseEnergy(float amount){
        energy += amount;
        
        if(energy > 100)
            energy = 100;
             
    }
    
    public float getScore(){
        return score;
    }
    
    public void increaseScore(float amount){
        score += amount;
    }
    
    //OBJ!!---------//
     public void setNewObjective(Objectives objectives){
        rand = new Random();
        //indexObj = rand.nextInt(objList.size());
        System.out.println(objList.size());
        if(currentObj == testrandobj){
            //setNewObjective(object);
        } else{
            currentObj = testrandobj;
        }
    }

    public Objectives getObjectives(){
        return objectives;
    }
    
    public void startObjective(){
        
        objectives.objectiveGoal();
    }
}
