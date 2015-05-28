/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

import java.util.ArrayList;

/**
 *
 * @author rasmusdavidsson
 */
public class ObjectiveGenerator {
    private int objIndex = -1;
    private ArrayList<Objectives> objList = new ArrayList<Objectives>();
    private Objectives obj;
    
    public ObjectiveGenerator(){
        objList.add(new Objective1());
        objList.add(new Objective2());
        objList.add(new Objective3());
    
    }
    public Objectives getnextObjective(){
         if(objIndex < objList.size()){          
            objIndex++;
        }else{
            objIndex = 0;        
        }
        obj = objList.get(objIndex);
         
        return obj;
    }  
}   
