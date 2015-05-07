package edu.cth.mosquito.main;

import edu.cth.mosquito.controller.Controller;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.font.BitmapFont;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;
import edu.cth.mosquito.core.Highscore;
import edu.cth.mosquito.core.Player;
import edu.cth.mosquito.core.Position3D;
import edu.cth.mosquito.core.World;
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
    private World world;
    private MosquitoSimulatorRenderer msr;
    private GuiOverlay guiOverlay;
    private Vector3f directionForward = new Vector3f();
    private Vector3f directionLeft = new Vector3f();
    private Vector3f directionUp = new Vector3f();
    
    @Override
    public void simpleInitApp(){
        
        // Disable the default flyby cam
        flyCam.setEnabled(false);
        initKeys();
        guiOverlay = new GuiOverlay(assetManager);
        world = new World(10, 5, 15);
        player = new Player(world);
        highscore = new Highscore();
        controller = new Controller();
        msr = new MosquitoSimulatorRenderer(assetManager, cam);
        msr.renderRoom(world.getWidth(), world.getHeight());
        
        msr.renderPlaneRoom(world.getWidth(), world.getHeight(), world.getLength());
        
        
        
        rootNode.attachChild(msr.getRoomNode());
        
        rootNode.attachChild(msr.getMosquitoNode());
        
        initGUI();
        
        //Renders all the objects in world
        msr.renderWorldObjects(world.getObjects());       
        for(int i = 0; i < msr.getObjectNodes().size(); i++){
            rootNode.attachChild(msr.getObjectNodes().get(i));
        }

    }
    
    @Override
    public void simpleUpdate(float tpf) {
        msr.getMosquitoNode().setLocalTranslation(player.getPosition().getX(), 
                player.getPosition().getY(), player.getPosition().getZ());
        
        player.increaseScore(3 * tpf);
        player.decreaseEnergy(3 * tpf);
        guiOverlay.updateGUI(player.getEnergy(), player.getScore());
      
        
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
       
    }
      
    public void initGUI(){
        
        setDisplayStatView(false);  
        setDisplayFps(false);
        guiOverlay.setScoreTextPos(settings.getWidth()-guiOverlay.getScoreText().getLineWidth()-10,settings.getHeight()-10, 0f);
        guiOverlay.setEnergyTextPos(settings.getWidth()-guiOverlay.getEnergyText().getLineWidth()-10,(settings.getHeight()*0.9f), 0f);
      
        guiNode.attachChild(guiOverlay.getScoreText());
        guiNode.attachChild(guiOverlay.getEnergyText());
    
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
            msr.getMosquitoNode().rotate(0, -tpf, 0);
        }
        
        if (name.equals("rotateLeft")) {
            msr.getMosquitoNode().rotate(0, tpf, 0);
        }
        
        if(name.equals("rotateUp")){
            if (msr.getMosquitoNode().getLocalRotation().getX() > -0.70){
                msr.getMosquitoNode().rotate(-tpf, 0, 0);
            }
        }
        
        if(name.equals("rotateDown")){
            if (msr.getMosquitoNode().getLocalRotation().getX() < 0.70){
                msr.getMosquitoNode().rotate(tpf, 0, 0);
            }
        }
        
        if(name.equals("Reset")){
            System.out.println(msr.getMosquitoNode().getLocalRotation().getX());
            player.reset();
            msr.getMosquitoNode().setLocalRotation(Quaternion.IDENTITY);
        }

    }   
    
    private void initKeys(){
        inputManager.addMapping("Reset", new KeyTrigger(KeyInput.KEY_R));
        
        inputManager.addMapping("Forward", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Backward", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("Up", new KeyTrigger(KeyInput.KEY_LCONTROL));
        inputManager.addMapping("Down", new KeyTrigger(KeyInput.KEY_SPACE));
        
        inputManager.addMapping("rotateUp", new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addMapping("rotateDown", new KeyTrigger(KeyInput.KEY_DOWN));
        inputManager.addMapping("rotateLeft", new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addMapping("rotateRight", new KeyTrigger(KeyInput.KEY_RIGHT));
        
        inputManager.addMapping("toggleRotate", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addMapping("rotateLeft", new MouseAxisTrigger(MouseInput.AXIS_X, true));
        inputManager.addMapping("rotateRight", new MouseAxisTrigger(MouseInput.AXIS_X, false));
        inputManager.addMapping("rotateDown", new MouseAxisTrigger(MouseInput.AXIS_Y, true));
        inputManager.addMapping("rotateUp", new MouseAxisTrigger(MouseInput.AXIS_Y, false));
        
        inputManager.addListener(this, "Left", "Forward", "Right", "Backward", "Up", "Down", "Reset");
        inputManager.addListener(this, "rotateRight", "rotateLeft","rotateUp", "rotateDown", "toggleRotate");
       
    }
}

