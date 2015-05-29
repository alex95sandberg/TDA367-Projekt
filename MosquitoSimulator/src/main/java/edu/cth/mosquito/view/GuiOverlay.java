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
    private BitmapFont font;
    private BitmapText energyText;
    private BitmapText scoreText;
    private BitmapText objectiveText;
    private BitmapText progressText;
    private BitmapText rewardText;
    private BitmapText instructionText;
    private BillboardControl billboard;
    private Geometry geom, geom2;
    private Node barNode = new Node("energybar");
    private Geometry energygeom;
    private Geometry bloodgeom;
    private Node energybarNode = new Node("energybar");
    private Node bloodbarNode = new Node("bloodbar");
    private Camera energyCam;
    private Camera bloodCam;
    private Box bloodbarBox;
    private Box energybarBox;
     
     
    public GuiOverlay(AssetManager assetManager, Camera cam, RenderManager renderManager) {
        this.assetManager = assetManager;
        this.renderManager = renderManager;
        font = assetManager.loadFont("Interface/Fonts/Console.fnt");
        energyText = new BitmapText(font, false);
        scoreText = new BitmapText(font, false);
        objectiveText = new BitmapText(font, false);
        rewardText = new BitmapText(font, false);
        instructionText = new BitmapText(font, false);
        progressText = new BitmapText(font, false);

        //energy init
        energyText.setSize(font.getCharSet().getRenderedSize());      // font size
        energyText.setColor(ColorRGBA.White);                             // font color
        
        //score init
        scoreText.setSize(font.getCharSet().getRenderedSize());      // font size
        scoreText.setColor(ColorRGBA.White);                        // font color
        
        //objective init
        objectiveText.setSize(font.getCharSet().getRenderedSize());
        objectiveText.setColor(ColorRGBA.White);
        
        //progress init
        progressText.setSize(font.getCharSet().getRenderedSize());
        progressText.setColor(ColorRGBA.White);
        
        //reward init
        rewardText.setSize(font.getCharSet().getRenderedSize());
        rewardText.setColor(ColorRGBA.White);
        
        //intruction init
        instructionText.setSize(font.getCharSet().getRenderedSize());
        instructionText.setColor(ColorRGBA.White);
        instructionText.setText("Esc - Main menu \nP - Pause game \n\n"
                + "Q - Suck blood \nWASD - Move around \n"
                + "Shift/Space - Ascend/Descend \n"
                + "Arrowkeys - Rotate");
        
        //energybar init
        energybarBox = new Box( 0.15f, 0.2f, 0f); 
        energygeom = new Geometry("Box", energybarBox);    
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Yellow);
        energygeom.setMaterial(mat);
        energybarNode.attachChild(energygeom);
        energyCam = cam.clone();
        energyCam.setViewPort(0.8f, 1.05f, 0f, 1f);  
        energyCam.setLocation(new Vector3f(-0.1f, -0.1f, 1f));       
        final ViewPort view2 = renderManager.createMainView("energyview", energyCam); 
        view2.attachScene(energybarNode.getChild("Box"));  
        
        //bloodbar init       
        bloodbarBox = new Box( 1f, 0.1f, 0f); 
        bloodgeom = new Geometry("Box", bloodbarBox);    
        Material mat2 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat2.setColor("Color", ColorRGBA.Red);
        bloodgeom.setMaterial(mat2);
        bloodbarNode.attachChild(bloodgeom);
        bloodCam = cam.clone();
        bloodCam.setViewPort(0.422f, 0.622f, 0.3f, 0.5f);  
        bloodCam.setLocation(new Vector3f(0.3f, 0.6f,2f));       
        final ViewPort view3 = renderManager.createMainView("bloodview", bloodCam); 
        view3.attachScene(bloodbarNode.getChild("Box"));  
        

        //Creates the background for the energybar
        Material mat3 = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat3.setColor("Color", ColorRGBA.Black);
        geom2 = new Geometry("Box2", energybarBox);

        geom2.setMaterial(mat3);
        barNode.attachChild(geom2);
        geom2.setLocalScale(1.1f, 1.025f, 0);
        view2.attachScene(barNode.getChild("Box2"));
        barNode.updateGeometricState();
        
        updateGUI(100f,0f, 0f, 1);
        
    }
 
    public void updateGUI(float currentEnergy, float currentScore, float objectiveProgress, float objectiveGoal){
        
        setEnergyAmount(currentEnergy);
        setScoreAmount(currentScore);
        updateProgressText(objectiveProgress, objectiveGoal);
    
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
    
     //bloodbar
     
     
     public Node getBloodNode(){
     
         return bloodbarNode;
     }
     
     public void updateBloodbar(float x){
     
         bloodgeom.setLocalScale(x, 1, 1);
     }
     public Geometry getbloodGeom(){
     
         return bloodgeom;
     }
     
     public void resetUI(){
         energygeom.setLocalScale(1);
         energygeom.setLocalTranslation(0, 0, 0);
         bloodgeom.setLocalScale(1);
         bloodgeom.setLocalTranslation(0,0,0);
     }
     
     //

     
     //Energybar methods
     
     public Node getEnergyNode(){
     
         return energybarNode;
     }
     public void updateEnergybar(float y){

         if((y > 0 && energygeom.getLocalScale().getY() < 1) || (y < 0 && energygeom.getLocalScale().getY() > 0)){
            energygeom.setLocalScale(energygeom.getLocalScale().getX(), energygeom.getLocalScale().getY()+y, energygeom.getLocalScale().getZ());
            energygeom.setLocalTranslation(energygeom.getLocalTranslation().getX(), energygeom.getLocalTranslation().getY()+y/5, energygeom.getLocalTranslation().getZ());
         }
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
     
     public BitmapText getInstructionText(){
         return instructionText;
     }
     
     public void setInstructionTextPosition(float x, float y, float z){
         instructionText.setLocalTranslation(x, y, z);
     }
     
     public BitmapText getProgressText(){
         return progressText;
     }
     
     public void updateProgressText(float current, float goal){
         progressText.setText("Progress: " + Math.round(current*10f)/10f + "/" + goal);
     }
     
     public void setProgressTextPosition(float x, float y, float z){
         progressText.setLocalTranslation(x, y, z);
     }
}
