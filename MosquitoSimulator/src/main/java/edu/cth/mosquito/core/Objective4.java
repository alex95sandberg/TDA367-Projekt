/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

/**
 * Handles the code for objective 4
 * 
 * @author Mosquito
 */



public class Objective4 implements Objectives {

    private float progress = 0;
    private static final int GOAL = 50;
    private static final int REWARD = 100;
    private static final String OBJECTIVETEXT = "Collide with all four walls within 50 seconds\nReward 100 points";

    
    @Override
    public float getObjectiveReward() {
         return REWARD;
    }

    @Override
    public float getObjectiveGoal() {

        return GOAL;
    }

    @Override
    public float getProgress() {  
        return progress;
    }

    @Override
    public String getObjectiveText() {
        return OBJECTIVETEXT;
    }

    @Override
    public void increaseProgress(float amount) {
      progress += amount;  
      if(progress >= GOAL)
          progress = GOAL;
          
    }

    @Override
    public void setProgress(float amount) {
        
        progress = amount;
    }
}
