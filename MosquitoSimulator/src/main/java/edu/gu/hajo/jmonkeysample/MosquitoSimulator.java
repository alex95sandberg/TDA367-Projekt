package edu.gu.hajo.jmonkeysample;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
 * Sample 1 - how to get started with the most simple JME 3 application. Display
 * a blue 3D cube and view from all sides by moving the mouse and pressing the
 * WASD keys.
 */
public class MosquitoSimulator extends SimpleApplication implements AnalogListener {
    
    public static void main(String[] args) {
        MosquitoSimulator app = new MosquitoSimulator();
        app.start(); // start the game
    }
    
    public BulletAppState bulletAppState;
    private Vector3f directionForward = new Vector3f();
    private Vector3f directionLeft = new Vector3f();
    private Vector3f directionUp = new Vector3f();
    private boolean rotate = false;
    private Player player;
    private World world;
    private MosquitoSimulatorRenderer msr;
    
    @Override
    public void simpleInitApp(){

        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
        
        // Disable the default flyby cam
        flyCam.setEnabled(false);
        initKeys();
        player = new Player(cam);
        world = new World(assetManager, getPhysicsSpace());
        msr = new MosquitoSimulatorRenderer(assetManager);
        player.getPlayerNode().attachChild(msr.getMosquito());
        
        rootNode.attachChild(world.getWorldNode());
        rootNode.attachChild(player.getPlayerNode());
        
    }
    
    public PhysicsSpace getPhysicsSpace(){
        
        return bulletAppState.getPhysicsSpace();
        
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
                player.movePlayerNode(directionForward);
            }
            if (name.equals("Backward")) {
                directionForward.multLocal(-5 * tpf);
                player.movePlayerNode(directionForward);
            }
            if (name.equals("Right")) {
                directionLeft.multLocal(-5*tpf);
                player.movePlayerNode(directionLeft);
              
            }
            if (name.equals("Left")) {
                directionLeft.multLocal(5*tpf);
                player.movePlayerNode(directionLeft);
            }
            
            if(name.equals("Up")){
                directionUp.multLocal(5 * tpf);
                player.movePlayerNode(directionUp);
            } 
            if(name.equals("Down")){
                directionUp.multLocal(-5 * tpf);
                player.movePlayerNode(directionUp);
            }
            if (name.equals("rotateRight")) {
                player.rotatePlayerNode(0, 5 * tpf, 0);
            }
            if (name.equals("rotateLeft")) {
                player.rotatePlayerNode(0, -5 * tpf, 0);
            }
            if(name.equals("rotateUp")){               
                player.rotatePlayerNode(+5 * tpf, 0, 0);
            }
            if(name.equals("rotateDown")){
                player.rotatePlayerNode(-5 * tpf, 0, 0);
            }
            
        }
        
//         public void onAction(String name, boolean keyPressed, float tpf) {
//            //toggling rotation on or off
//            if (name.equals("toggleRotate") && keyPressed) {
//              rotate = true;
//              inputManager.setCursorVisible(false);
//            }
//            if (name.equals("toggleRotate") && !keyPressed) {
//              rotate = false;
//              inputManager.setCursorVisible(true);
//            }
//
//        }
        
        
    
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

