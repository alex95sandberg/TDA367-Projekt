/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.view;

import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.asset.AssetManager;

/**
 *
 * @author rasmusdavidsson
 */
public class GuiOverlay {
    
    private AssetManager assetManager;
    BitmapFont font;
    BitmapText energyText;
    BitmapText scoreText;
     
     
    public GuiOverlay(AssetManager assetManager) {
        this.assetManager = assetManager;
        font = assetManager.loadFont("Interface/Fonts/Console.fnt");
        energyText = new BitmapText(font, false);
        scoreText = new BitmapText(font, false);
        
        energyText.setSize(font.getCharSet().getRenderedSize());      // font size
        energyText.setColor(ColorRGBA.White);                             // font color
        
        
        scoreText.setSize(font.getCharSet().getRenderedSize());      // font size
        scoreText.setColor(ColorRGBA.White);                        // font color
        
        updateGUI(100f,10f);
        
        
    }
 
    public void updateGUI(float currentEnergy, float currentScore){
        
        
        setEnergyAmount(currentEnergy);
        setScoreAmount(currentScore);
    
    }
    
    //All energy methods starts here.
    public BitmapText getEnergyText(){
        return energyText;
    }
    
    public void setEnergyAmount(float amount){
        energyText.setText("Energy " + Math.round(amount) + "%");
        
    
    }

    public void setEnergyTextPos(float x, float y, float z){
         energyText.setLocalTranslation(x, y, z);   // position
    }
    //All Energy methods ends here.
    
    
    //All score methods start here.
    public BitmapText getScoreText(){
        
        return scoreText;
    }
    
    public void setScoreAmount(float amount){
        scoreText.setText("Score: " + Math.round(amount) + " ");
        
    
    }
    
     public void setScoreTextPos(float x, float y, float z){
         scoreText.setLocalTranslation(x, y, z);   // position
    }

    //All score methods ends here.
}
