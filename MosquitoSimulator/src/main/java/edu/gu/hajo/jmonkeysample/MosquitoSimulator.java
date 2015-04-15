package edu.gu.hajo.jmonkeysample;

import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.math.ColorRGBA;
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
    
    Vector3f direction = new Vector3f();
    boolean rotate = false;
    Player player;
    
    
    @Override
    public void simpleInitApp(){
        initKeys();
        player = new Player(cam, assetManager);
        
        Box b = new Box(1, 1, 1); // create cube shape
        Geometry geom = new Geometry("Box", b);  // create cube geometry from the shape
        Material mat = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
        mat.setColor("Color", ColorRGBA.Blue);   // set color of material to blue
        geom.setMaterial(mat);                   // set the cube's material
        
        
        Geometry geom3 = new Geometry("Box3", b);  
        Material mat3 = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");  
        mat3.setColor("Color", ColorRGBA.Green);   
        geom3.setMaterial(mat3);                   
        geom3.setLocalTranslation(2, 0, 2);
        
        rootNode.attachChild(geom3);
        rootNode.attachChild(player.getPlayerNode());
        rootNode.attachChild(geom);              // make the cube appear in the scene
        
        
        // Disable the default flyby cam
        flyCam.setEnabled(false);
    }
    

        @Override
        public void onAnalog(String name, float value, float tpf) {
            
            direction.set(cam.getDirection()).normalizeLocal();
            
            if (name.equals("Forward")) {
              direction.multLocal(5*tpf);
              player.getPlayerNode().move(direction);
            }
            if (name.equals("Backward")) {
              direction.multLocal(-5 * tpf);
              player.getPlayerNode().move(direction);
            }
            if (name.equals("Right")) {
              direction.crossLocal(Vector3f.UNIT_Y).multLocal(5 * tpf);
              player.getPlayerNode().move(direction);
              
            }
            if (name.equals("Left")) {
              direction.crossLocal(Vector3f.UNIT_Y).multLocal(-5 * tpf);
              player.getPlayerNode().move(direction);
            }
            
            if(name.equals("Up")){
              direction.crossLocal(Vector3f.UNIT_X).multLocal(-5 * tpf);
              player.getPlayerNode().move(direction);
            } 
            if(name.equals("Down")){
              direction.crossLocal(Vector3f.UNIT_X).multLocal(5 * tpf);
              player.getPlayerNode().move(direction);
            }
            if (name.equals("rotateRight")) {
              player.getPlayerNode().rotate(0, 5 * tpf, 0);
            }
            if (name.equals("rotateLeft")) {
              player.getPlayerNode().rotate(0, -5 * tpf, 0);
            }
//            if(name.equals("rotateUp")){
//                targetNode.rotate(+5 * tpf, 0, 0);
//            }
//            if(name.equals("rotateDown")){
//                targetNode.rotate(-5 * tpf, 0, 0);
//            }
            
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

