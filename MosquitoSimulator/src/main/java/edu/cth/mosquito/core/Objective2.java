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



public class Objective2 implements Objectives {

    public int currentObj;
    private String objectiveText;
    
    public Objective2(){
        currentObj = 1;
        objectiveText = "Gain blood from two different humans, and keep over 70% of your energy.\nReward 50 points";
    
    }
    @Override
    public int objectiveReward() {
         return 50;
    }

    @Override
    public void objectiveGoal() {
      
        
        
    }
}
