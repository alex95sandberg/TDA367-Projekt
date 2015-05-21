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
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.control.BillboardControl;
import com.jme3.scene.shape.Box;

/**
 *
 * @author rasmusdavidsson
 */
public class GuiOverlay {
    
    private AssetManager assetManager;
    private RenderManager renderManager;
    private Material guiMat;
    private BitmapFont font;
    private BitmapText energyText;
    private BitmapText scoreText;
    private BitmapText bloodText;
    private BitmapText objectiveText;
    private BitmapText rewardText;
    private BillboardControl billboard;
    Geometry geom;
    Node barNode = new Node("energybar");
    private Camera energyCam;
    private Box barBox;
     
     
    public GuiOverlay(AssetManager assetManager, Camera cam, RenderManager renderManager) {
        this.assetManager = assetManager;
        this.renderManager = renderManager;
        font = assetManager.loadFont("Interface/Fonts/Console.fnt");
        energyText = new BitmapText(font, false);
        scoreText = new BitmapText(font, false);
        bloodText = new BitmapText(font, false);
        objectiveText = new BitmapText(font, false);
        rewardText = new BitmapText(font, false);
        
        //blood init
        bloodText.setSize(font.getCharSet().getRenderedSize());      // font size
        bloodText.setColor(ColorRGBA.White);
        
        //energy init
        energyText.setSize(font.getCharSet().getRenderedSize());      // font size
        energyText.setColor(ColorRGBA.White);                             // font color
        
        //score init
        scoreText.setSize(font.getCharSet().getRenderedSize());      // font size
        scoreText.setColor(ColorRGBA.White);                        // font color
        
        //objective init
        objectiveText.setSize(font.getCharSet().getRenderedSize());
        objectiveText.setColor(ColorRGBA.White);
        
        //reward init
        rewardText.setSize(font.getCharSet().getRenderedSize());
        rewardText.setColor(ColorRGBA.White);
        
        //energybar init
 
        barBox = new Box( 0.15f, 0.2f, 0f); 
        geom = new Geometry("Box", barBox);    
        Material mat12 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat12.setColor("Color", ColorRGBA.Yellow);
        geom.setMaterial(mat12);
        barNode.attachChild(geom);
        energyCam = cam.clone();
        energyCam.setViewPort(0.8f, 1.05f, 0f, 1f);  
        energyCam.setLocation(new Vector3f(-0.1f, -0.1f, 1f));       
        final ViewPort view2 = renderManager.createMainView("energyview", energyCam); 
        view2.attachScene(barNode.getChild("Box"));  
        
        updateGUI(100f,0f,"");
        setBloodAmount(100f);
        
    }
 
    public void updateGUI(float currentEnergy, float currentScore, String objText){
        
        setEnergyAmount(currentEnergy);
        setScoreAmount(currentScore);
        setObjectiveText(objText);
    
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
    
     //blood start
     
     public void setBloodAmount(float amount){
         bloodText.setText("Blood: " + Math.round(amount) + "%");
     }
     
     public void setBloodTextPos(float x, float y, float z){
         bloodText.setLocalTranslation(x, y, z);
     }
     
     public BitmapText getBloodText(){
         return bloodText;
     }
     
     //blood end
     
     
     //Energybar methods
     
     public Node getEnergyNode(){
     
         return barNode;
     }
     public void updateEnergybar(float y){

         if((y > 0 && geom.getLocalScale().getY() < 1) || (y < 0 && geom.getLocalScale().getY() > 0)){
            geom.setLocalScale(geom.getLocalScale().getX(), geom.getLocalScale().getY()+y, geom.getLocalScale().getZ());
            geom.setLocalTranslation(geom.getLocalTranslation().getX(), geom.getLocalTranslation().getY()+y/5, geom.getLocalTranslation().getZ());
         }
     }
     
     public void resetUI(){
         geom.setLocalScale(1);
     }
     
     //energybar methods end
     
     
     //Objectives methods
     public BitmapText getObjectiveText(){
        return objectiveText;
    }
    
     public void setObjectiveText(String text){
        objectiveText.setText("Objectives :"+"\n"+text);
        
    
    }

     public void setObjectiveTextPos(float x, float y, float z){
         objectiveText.setLocalTranslation(x, y, z);   // position
    }
     
     
     public void setRewardText(String text){
         rewardText.setText(text);
         
     }
     
     public void setRewardTextPos(float x, float y, float z){
         rewardText.setLocalTranslation(x, y, z);   // position
    }
     
     public BitmapText getRewardText(){
     
         return rewardText;
     }
     
     
     //objectives methods end
}
