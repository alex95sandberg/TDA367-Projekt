/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

/**
 *
 * @author Alexander
 */
public class Player {
    private World world;
    private Position3D pos;
    private Rotation rot;
    private float energy;
    private float score;
    
    public Player(World world){
        this.energy = 100;
        this.pos = new Position3D();
        this.rot = new Rotation();
        this.world  = world;
        
    }
    
    public Player(Position3D position, World world){
        this.energy = 100;
        this.pos = new Position3D(position);
        this.rot = new Rotation();
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
    
    public void rotate(float x, float y, float z){
        rot.rotate(x, y, z);
    }
    
    public void reset(){
        energy = 100;
        score = 0;
        pos = new Position3D();
        rot = new Rotation();
    }
    
    public Rotation getRotation(){
        return rot;
    }
    
    public float getEnergy(){
        return energy;
    }
    
    public void decreaseEnergy(float amount){
        energy -= amount;
    }
    
    public void increaseEnergy(float amount){
        energy += amount;
    }
    
    public float getScore(){
        return score;
    }
    
    public void increaseScore(float amount){
        score += amount;
    }
}
