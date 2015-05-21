/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

import java.util.Random;

/**
 *
 * @author rasmusdavidsson
 */
public interface Objectives {
    
    public final static int maximumObj= 4;

    
    public int objectiveReward();
    public void objectiveGoal();
    
}
