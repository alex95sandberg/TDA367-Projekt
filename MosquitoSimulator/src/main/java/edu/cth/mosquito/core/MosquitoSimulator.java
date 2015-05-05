package edu.cth.mosquito.core;

import edu.cth.mosquito.controller.Controller;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import edu.cth.mosquito.view.GuiOverlay;
import edu.cth.mosquito.view.MosquitoSimulatorRenderer;

public class MosquitoSimulator extends SimpleApplication implements AnalogListener {
    
    public static void main(String[] args) {
        MosquitoSimulator app = new MosquitoSimulator();
        app.start(); // start the game
    }
    
    private Controller controller;
    private Highscore highscore;
    private Player player;
    private MosquitoSimulatorRenderer msr;
    private GuiOverlay guiover;
    private Vector3f directionForward = new Vector3f();
    private Vector3f directionLeft = new Vector3f();
    private Vector3f directionUp = new Vector3f();
    
    
    @Override
    public void simpleInitApp(){
        
        // Disable the default flyby cam
        flyCam.setEnabled(false);
        initKeys();
        guiover = new GuiOverlay(assetManager);
        player = new Player();
        highscore = new Highscore();
        controller = new Controller();
        msr = new MosquitoSimulatorRenderer(assetManager, cam);
        rootNode.attachChild(msr.getMosquitoNode());

        rootNode.attachChild(msr.getRoomSpatial());
        

        

        ///TEMPORÄRT
        Box b = new Box(1, 1, 1); // create cube shape
        Material mat1 = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
        
        Geometry geom1 = new Geometry("Box", b);  // create cube geometry from the shape

        mat1.setColor("Color", ColorRGBA.Blue);   // set color of material to blue
        mat1.getAdditionalRenderState().setWireframe(true);
        geom1.setMaterial(mat1);                   // set the cube's material
        //TEMPORÄRT
        
        
        rootNode.attachChild(geom1);
        

    }
    
    @Override
    public void simpleUpdate(float tpf) {
        msr.getMosquitoNode().setLocalTranslation(player.getPosition().getX(), 
                player.getPosition().getY(), player.getPosition().getZ());
        updateEnergyText();
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
       
    }
      
      public void updateEnergyText(){
        setDisplayStatView(false);  
        setDisplayFps(false);
        guiover.setEnergyAmount(player.getEnergy());
        guiNode.attachChild(guiover.getEnergyText());
    
    }
      
    @Override
    public void onAnalog(String name, float value, float tpf) {

        if(inputManager.isCursorVisible())
            inputManager.setCursorVisible(false);

        directionForward.set(cam.getDirection()).normalizeLocal();
        directionLeft.set(cam.getLeft()).normalizeLocal();
        directionUp.set(directionLeft).crossLocal(directionForward).normalizeLocal();

        if (name.equals("Forward")) {
            directionForward.multLocal(5*tpf);
            player.move(new Position3D(directionForward.x, directionForward.y, directionForward.z));
        }
        if (name.equals("Backward")) {
            directionForward.multLocal(-5 * tpf);
            player.move(new Position3D(directionForward.x, directionForward.y, directionForward.z));
        }
        if (name.equals("Right")) {
            directionLeft.multLocal(-5*tpf);
            player.move(new Position3D(directionLeft.x, directionLeft.y, directionLeft.z));

        }
        if (name.equals("Left")) {
            directionLeft.multLocal(5*tpf);
            player.move(new Position3D(directionLeft.x, directionLeft.y, directionLeft.z));
        }

        if(name.equals("Up")){
            directionUp.multLocal(5 * tpf);
            player.move(new Position3D(directionUp.x, directionUp.y, directionUp.z));
        } 
        if(name.equals("Down")){
            directionUp.multLocal(-5 * tpf);
            player.move(new Position3D(directionUp.x, directionUp.y, directionUp.z));
        }
        if (name.equals("rotateRight")) {
            //player.rotatePlayerNode(0, 5 * tpf, 0);
        }
        
        if (name.equals("rotateLeft")) {
            //player.rotatePlayerNode(0, -5 * tpf, 0);
        }
        
        if(name.equals("rotateUp")){               
            //player.rotatePlayerNode(+5 * tpf, 0, 0);
        }
        
        if(name.equals("rotateDown")){
            //player.rotatePlayerNode(-5 * tpf, 0, 0);
        }

    }   
    
    private void initKeys(){
        inputManager.addMapping("Forward", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Backward", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("Up", new KeyTrigger(KeyInput.KEY_LCONTROL));
        inputManager.addMapping("Down", new KeyTrigger(KeyInput.KEY_SPACE));
        inputManager.addMapping("toggleRotate", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addMapping("rotateRight", new MouseAxisTrigger(MouseInput.AXIS_X, true));
        inputManager.addMapping("rotateLeft", new MouseAxisTrigger(MouseInput.AXIS_X, false));
        inputManager.addMapping("rotateUp", new MouseAxisTrigger(MouseInput.AXIS_Y, true));
        inputManager.addMapping("rotateDown", new MouseAxisTrigger(MouseInput.AXIS_Y, false));
        
        inputManager.addListener(this, "Left", "Forward", "Right", "Backward", "Up", "Down");
        inputManager.addListener(this, "rotateRight", "rotateLeft","rotateUp", "rotateDown", "toggleRotate");
       
    }
}

