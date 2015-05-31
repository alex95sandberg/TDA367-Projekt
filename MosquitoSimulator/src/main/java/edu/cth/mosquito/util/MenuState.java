/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.util;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * A observer class that checks for propertyChange. 
 * We use this to handle mouse input in the menu.
 * @author Mosquito
 */
public class MenuState implements PropertyChangeListener {

    private boolean initGraphics = false;
    private boolean resetScore = false;
    
    //When we get a propertyChange from an observed class thsi gets called.
    @Override
    public void propertyChange(PropertyChangeEvent evt){
        
        if(evt.getPropertyName().equals("startGame")  || evt.getPropertyName().equals("unPause")){
            initGraphics = true;
        } else if(evt.getPropertyName().equals("resetScore")){
            resetScore = true;
        }
        
    }
    
    public boolean okToInitGraphics(){
        return initGraphics;
    }
    
    public void graphicsInitailized(boolean flag){
        initGraphics = !flag;
    }
    
    public boolean resetScore(){
        return resetScore;
    }
    
    public void scoreIsReset(boolean flag){
        resetScore = !flag;
    }
}
