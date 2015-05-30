/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

import edu.cth.mosquito.util.Position3D;

/**
 * Handles all the logic and data for the player
 * 
 * @author Mosquito
 */
public class Player {
    private World world;
    private Position3D pos;
    private float energy;
    private final float maxEnergy = 100;
    private float score;
    private Objectives objectives;
    private ObjectiveGenerator objGen = new ObjectiveGenerator();

    
    public Player(World world){
        this.energy = maxEnergy;
        this.pos = new Position3D();
        this.world  = world;
        generateNewObjective();
        
    }
    
    public Player(Position3D position, World world){
        this.energy = maxEnergy;
        this.pos = new Position3D(position);
        this.world = world;
        generateNewObjective();
    }
    
    public void move(Position3D distance){
        float tempX = pos.getX();
        float tempY = pos.getY();
        float tempZ = pos.getZ();
        
        //Checks if the player collides with the edges of the world
        //x och z
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
        //reset objectives
        objGen.resetObjectives();
        generateNewObjective();
        
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
    
    public Objectives getObjective(){
        return this.objectives;
        
    }
    
    private void generateNewObjective(){
        this.objectives = objGen.getnextObjective();
    }
    
    public void updateObjective(float tpf){
        if(getObjective() instanceof Objective1){
                if(getEnergy() >= 80){
                    getObjective().increaseProgress(tpf);
                    if(getObjective().getProgress() >= getObjective().getObjectiveGoal()){
                        increaseScore(getObjective().getObjectiveReward());
                        getObjective().setProgress(0);
                        generateNewObjective();
                    }
                       
                }else{
                    getObjective().setProgress(0);
                }
            }else if(getObjective() instanceof Objective2){
                if(getEnergy() <= 8){
                    getObjective().increaseProgress(tpf);
                    if(getObjective().getProgress() >= getObjective().getObjectiveGoal()){
                        
                        increaseScore(getObjective().getObjectiveReward());
                        getObjective().setProgress(0);
                        generateNewObjective();
                        
                    }
                       
                }else{
                    getObjective().setProgress(0);
                }
            }else if(getObjective() instanceof Objective3){
                if(Math.round(getEnergy()) >= 100){
                    getObjective().increaseProgress(tpf);
                    if(getObjective().getProgress() >= getObjective().getObjectiveGoal()){
                        
                        increaseScore(getObjective().getObjectiveReward());
                        getObjective().setProgress(0);
                        generateNewObjective();
                    }
                       
                }
            }else if(getObjective() instanceof Objective4){
                if(Math.round(getEnergy()) >= 70){
                    getObjective().increaseProgress(tpf);
                    if(getObjective().getProgress() >= getObjective().getObjectiveGoal()){
                        
                        increaseScore(getObjective().getObjectiveReward());
                        getObjective().setProgress(0);
                        generateNewObjective();
                    }
                       
                }
            }
    } 
}
