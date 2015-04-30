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
    private Position3D pos;
    private float energy;
    private float score;
    
    public Player(){
        energy = 100;
        pos = new Position3D();
    }
    
    public Player(Position3D position){
        energy = 100;
        pos = new Position3D(position);
    }
    
    public void move(Position3D distance){
        pos.setPosition(pos.getX() + distance.getX(), pos.getY() + distance.getY(), pos.getZ() + distance.getZ());
    }
    
    public Position3D getPosition(){
        return pos;
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
