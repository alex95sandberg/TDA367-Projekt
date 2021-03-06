/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

/**
 * Handles the code for objective 3
 * 
 * @author Mosquito
 */



public class Objective3 implements Objectives {
    //progress is shown in seconds.
    private float progress = 0;
    private static final int GOAL = 10;
    private static final int REWARD = 90;
    private static final String OBJECTIVETEXT = "Hold your energy at 100% for a total of 10 seconds\nReward 90 points";
    
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
