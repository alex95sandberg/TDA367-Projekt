/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author Anton
 */
public class MenuState implements PropertyChangeListener {

    private boolean initGraphics = false;
    
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
