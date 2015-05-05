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
    private Rotation rot;
    private float energy;
    private float score;
    
    public Player(){
        energy = 100;
        pos = new Position3D();
        rot = new Rotation();
    }
    
    public Player(Position3D position){
        energy = 100;
        pos = new Position3D(position);
        rot = new Rotation();
    }
    
    public void move(Position3D distance){
        pos.setPosition(pos.getX() + distance.getX(), pos.getY() + distance.getY(), pos.getZ() + distance.getZ());
    }
    
    public Position3D getPosition(){
        return pos;
    }
    
    public void rotate(float x, float y, float z){
        rot.rotate(x, y, z);
    }
    
    public float getRotationX(){
        return rot.getXRotation();
    }
    
    public float getRotationY(){
        return rot.getYRotation();
    }
    
    public float getRotationZ(){
        return rot.getZRotation();
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
