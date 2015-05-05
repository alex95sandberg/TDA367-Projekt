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
    BitmapText hudText;
     
     
    public GuiOverlay(AssetManager assetManager) {
        this.assetManager = assetManager;
        font = assetManager.loadFont("Interface/Fonts/Console.fnt");
        hudText = new BitmapText(font, false);
        
        hudText.setSize(font.getCharSet().getRenderedSize());      // font size
        hudText.setColor(ColorRGBA.White);                             // font color
        hudText.setLocalTranslation(0f, hudText.getLineHeight(), 0f);   // position
        
    }
 
    
    public BitmapText getEnergyText(){
        return hudText;
    }
    
    public void setEnergyAmount(float amount){
        hudText.setText("Energy " + Math.round(amount) + "%");
        
    
    }
}
