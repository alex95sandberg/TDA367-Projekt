/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

import java.util.ArrayList;

/**
 * Creating new objectives.
 * 
 * @author Mosquito
 * 
 * 
 */


public class ObjectiveGenerator {
    private int objIndex = -1;
    private final ArrayList<Objectives> objList = new ArrayList<Objectives>();
    private Objectives obj;
    
    public ObjectiveGenerator(){      
        objList.add(new Objective1());
        objList.add(new Objective2());
        objList.add(new Objective3());
        objList.add(new Objective4()); 
    }
    public Objectives getnextObjective(){
         if(objIndex < objList.size()-1){   
            objIndex++;
        }else{
            objIndex = 0;        
        }
        obj = objList.get(objIndex);
         
        return obj;
    }  
    
    public void resetObjectives(){
        objIndex = -1;
        
        for(int i = 0; i < objList.size(); i++){
            objList.get(i).setProgress(0);
        }
    }
}   
