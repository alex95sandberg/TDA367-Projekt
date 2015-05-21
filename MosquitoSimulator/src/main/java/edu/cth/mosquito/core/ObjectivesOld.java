/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
package edu.cth.mosquito.core;

import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/*
/**
 *
 * @author rasmusdavidsson
 */

//blod från 2 olika personer
//blod ner till 0% på x antal
//behåll 100% energy i 15 sek
//få över 200 i score
/*
public class Objectives {
    
    private Random rand;
    private int randomNum;
    private int maximumObj;
    private int currentObj;
    private String objectiveText;
    private Player player;
    
    public Objectives(int currentObj, int maximumObj, Player player){         //int currentObj = nummer som inte ska komma igen.
        this.player = player;                                                 // 0 = alla kan komma.
        this.maximumObj = maximumObj;  
        randomNum = currentObj;
        
        try{
            newObjective(generateRandomNbr(currentObj,maximumObj));
        } 
        catch(IllegalArgumentException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(new JPanel(), e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public int generateRandomNbr(int notValidNbr, int max){
       while(notValidNbr == randomNum){
        rand = new Random();

        randomNum = rand.nextInt((max - 1) + 1) + 1;
        currentObj = randomNum;
       }
       return randomNum;
    
    }
    
    private void newObjective(int num){
        
        switch (num) {
            case 1:  objectiveText = "Gain more than 200 points.\nReward 50 points";
                     break;
            case 2:  objectiveText = "Gain more than 200 points.\nReward 50 points";
                     break;
            case 3:  objectiveText = "Gain more than 200 points.\nReward 50 points";
                     break;
            case 4:  objectiveText = "Gain more than 200 points.\nReward 50 points";
                     break;   
            default: throw new IllegalArgumentException("Objectives out of bounds!");
        }
        
        
    }
    
    public void objective1Reward(){
        
        player.increaseScore(player.getScore()+50);
    }
    
    public void objective2Reward(){
       
        
    }
    
    public void objective3Reward(){
    
        
    }
    
    public void objective4Reward(){
    
        
    }
    
    public String getObjectiveText(){
        
        
        return objectiveText;
    }
    
    public int getCurrentObjective(){
    
        return currentObj;
    }
    
}
*/
