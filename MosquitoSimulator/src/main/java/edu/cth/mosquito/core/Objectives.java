/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

/**
 *
 * @author rasmusdavidsson
 */
public interface Objectives {
    
    public float getProgress();
    public void setProgress(float amount);
    public void increaseProgress(float amount);
    public float getObjectiveReward();
    public float getObjectiveGoal();
    public String getObjectiveText();
    
}
