/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;


/**
 *
 * @author rasmusdavidsson
 */



public class Objective2 implements Objectives {

    private float progress = 0;
    private static final int GOAL = 15;
    private static final int REWARD = 120;
    private static final String OBJECTIVETEXT = "Stay below 8% for 15 seconds\nReward 120 points";
    
    public Objective2(){

    
    }
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
