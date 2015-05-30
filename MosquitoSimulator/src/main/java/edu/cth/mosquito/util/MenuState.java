/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.util;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * A observer class that checks if its okay to initaite graphics
 * @author Mosquito
 */
public class MenuState implements PropertyChangeListener {

    private boolean initGraphics = false;
    
    //When we get a propertyChange from an observed class thsi gets called.
    @Override
    public void propertyChange(PropertyChangeEvent evt){
        
        if(evt.getPropertyName().equals("startGame")  || evt.getPropertyName().equals("unPause")){
            initGraphics = true;
        }
        
    }
    
    public boolean okToInitGraphics(){
        return initGraphics;
    }
    
    public void graphicsInitailized(boolean flag){
        initGraphics = !flag;
    }
    
}
