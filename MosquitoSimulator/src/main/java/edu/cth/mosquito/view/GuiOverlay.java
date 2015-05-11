/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.view;

import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.control.BillboardControl;
import com.jme3.scene.shape.Quad;

/**
 *
 * @author rasmusdavidsson
 */
public class GuiOverlay {
    
    private AssetManager assetManager;
    private Material guiMat;
    private BitmapFont font;
    private BitmapText energyText;
    private BitmapText scoreText;
    private Geometry energybar;
    private BillboardControl billboard;
     
     
    public GuiOverlay(AssetManager assetManager, Node mosqNode) {
        this.assetManager = assetManager;
        font = assetManager.loadFont("Interface/Fonts/Console.fnt");
        energyText = new BitmapText(font, false);
        scoreText = new BitmapText(font, false);
        
        
        
        //energy init
        energyText.setSize(font.getCharSet().getRenderedSize());      // font size
        energyText.setColor(ColorRGBA.White);                             // font color
        
        //score init
        scoreText.setSize(font.getCharSet().getRenderedSize());      // font size
        scoreText.setColor(ColorRGBA.White);                        // font color
        
        //energybar init
        guiMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        energybar = new Geometry("energybar", new Quad(0.1f, 0.4f));
        billboard = new BillboardControl();
        
        Material matC = guiMat.clone();
        matC.setColor("Color", ColorRGBA.Yellow);
        energybar.setMaterial(matC);
        
        energybar.move(-0.55f,-0.1f, -8.9999f);
        energybar.addControl(billboard);
        mosqNode.attachChild(energybar);
        
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
        scoreText.setText("Score: " + Math.round(amount) + "  ");
        
    
    }
    
     public void setScoreTextPos(float x, float y, float z){
         scoreText.setLocalTranslation(x, y, z);   // position
    }

    //All score methods ends here.
     
     //Energybar methods
     
     public void updateEnergybar(){

        //msr.getMosquitoNode().setUserData("health", 100f); kanske behöver??
        
        
        
        
         //Energy bar
       
        
        
        
        //rootNode.attachChild(msr.getMosquitoNode());
 
         
                                                                    //updateGeometry(player.getEnergy()/100, 3.0f)
        
        
    
    }
     
     //energybar methods
}
