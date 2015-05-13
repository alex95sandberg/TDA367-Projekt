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
    private BillboardControl billboard;
    Geometry geom;
    Node n = new Node("energybar");
     
     
    public GuiOverlay(AssetManager assetManager, Camera cam, RenderManager renderManager) {
        this.assetManager = assetManager;
        this.renderManager = renderManager;
        font = assetManager.loadFont("Interface/Fonts/Console.fnt");
        energyText = new BitmapText(font, false);
        scoreText = new BitmapText(font, false);
        bloodText = new BitmapText(font, false);
        
        //blood init
        bloodText.setSize(font.getCharSet().getRenderedSize());      // font size
        bloodText.setColor(ColorRGBA.White);
        
        //energy init
        energyText.setSize(font.getCharSet().getRenderedSize());      // font size
        energyText.setColor(ColorRGBA.White);                             // font color
        
        //score init
        scoreText.setSize(font.getCharSet().getRenderedSize());      // font size
        scoreText.setColor(ColorRGBA.White);                        // font color
        
        //energybar init
 
        Box b = new Box(new Vector3f(0, 0, 0), 0.2f, 0.2f, 3.6f);
        geom = new Geometry("Box", b);
        Material mat12 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat12.setColor("Color", ColorRGBA.Yellow);
        geom.setMaterial(mat12);
        n.attachChild(geom);
        Camera energyCam = cam.clone();
        energyCam.setViewPort(0.8f, 1.05f, 0f, 1f);       
        energyCam.setLocation(new Vector3f(-0.1f, -0.1f, 4.6f));       
        final ViewPort view2 = renderManager.createMainView("energyview", energyCam); 
        view2.attachScene(n.getChild("Box"));
        
        
       // cam2.setViewPort(0.8f, 1f, 0f, 1f);
        
       // cam2.setLocation(new Vector3f(-0.8f, -0.1f, 4.6f));

        

        ////view2.setBackgroundColor(ColorRGBA.randomColor().set(1, 1, 1, 1f));

       // view2.setClearDepth(true);


         //(Geometry)(n.getChild("healthbar")).getMesh()).updateGeometry(player.getEnergy() / 100 * 4, 0.2f);
        //cam2.setViewPort(speed, speed, speed, speed);
        
        
        
        
        
        updateGUI(100f,0f);
        setBloodAmount(100f);
        
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
    
     public void setBloodAmount(float amount){
         bloodText.setText("Blood: " + Math.round(amount) + "%");
     }
     
     public void setBloodTextPos(float x, float y, float z){
         bloodText.setLocalTranslation(x, y, z);
     }
     
     public BitmapText getBloodText(){
         return bloodText;
     }
     
    //All score methods ends here.
     
     //Energybar methods
     
     public Node getEnergyNode(){
     
         return n;
     }
     public void updateEnergybar(){

        //msr.getMosquitoNode().setUserData("health", 100f); kanske behöver??
        
        
        
        
         //Energy bar
       
        
        
        
        //rootNode.attachChild(msr.getMosquitoNode());
 
         
                                                                    //updateGeometry(player.getEnergy()/100, 3.0f)
        
        
    
    }
     
     //energybar methods end
}
