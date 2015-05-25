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
        currentObj = 2;
        objectiveText = "";
    
    }
    @Override
    public int objectiveReward() {
         return 50;
    }

    @Override
    public void objectiveGoal() {
      
        
    }

    @Override
    public int getProgress() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
