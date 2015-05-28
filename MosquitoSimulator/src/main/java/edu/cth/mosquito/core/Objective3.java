/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

import java.util.Timer;

/**
 *
 * @author rasmusdavidsson
 */



public class Objective3 implements Objectives {

    private static final int REWARD = 50;
    private static final String OBJECTIVETEXT = "Gain blood from two different humans, and keep over 70% of your energy.\nReward 50 points";
    
    public Objective3(){

    
    }
    @Override
    public int objectiveReward() {
         return REWARD;
    }

    @Override
    public void objectiveGoal() {

    }

    @Override
    public int getProgress() {
        return 1;
    }

    @Override
    public String getObjectiveText() {
        return OBJECTIVETEXT;
    }
}
