/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

/**
 *
 * @author rasmusdavidsson
 */



public class Objective4 implements Objectives {

    private float progress = 0;
    private static final int GOAL = 60;
    private static final int REWARD = 90;
    private static final String OBJECTIVETEXT = "Collide all four walls within one minute\nReward 90 points";

    
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
