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

    
    public int getProgress();
    public int objectiveReward();
    public void objectiveGoal();
    public String getObjectiveText();
    
}
