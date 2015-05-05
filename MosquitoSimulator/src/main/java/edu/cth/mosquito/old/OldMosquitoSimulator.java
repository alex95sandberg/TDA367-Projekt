//package edu.cth.mosquito.old;
//
//import com.jme3.app.SimpleApplication;
//import com.jme3.bullet.BulletAppState;
//import com.jme3.bullet.PhysicsSpace;
//import com.jme3.input.KeyInput;
//import com.jme3.input.MouseInput;
//import com.jme3.input.controls.AnalogListener;
//import com.jme3.input.controls.KeyTrigger;
//import com.jme3.input.controls.MouseAxisTrigger;
//import com.jme3.input.controls.MouseButtonTrigger;
//import com.jme3.math.Vector3f;
//import edu.cth.mosquito.controller.Controller;
//import edu.cth.mosquito.core.Highscore;
//import edu.cth.mosquito.view.MosquitoSimulatorRenderer;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
///**
// * Sample 1 - how to get started with the most simple JME 3 application. Display
// * a blue 3D cube and view from all sides by moving the mouse and pressing the
// * WASD keys.
// */
//public class OldMosquitoSimulator extends SimpleApplication implements AnalogListener {
//    
//    //public static void main(String[] args) {
//    //    OldMosquitoSimulator app = new OldMosquitoSimulator();
//    //    app.start(); // start the game
//    //}
//    
//    private Controller controller;
//    private Highscore highscore;
//    public BulletAppState bulletAppState;
//    private Vector3f directionForward = new Vector3f();
//    private Vector3f directionLeft = new Vector3f();
//    private Vector3f directionUp = new Vector3f();
//    private boolean rotate = false;
//    private OldPlayer player;
//    private OldWorld world;
//    private MosquitoSimulatorRenderer msr;
//    
//    @Override
//    public void simpleInitApp(){
//
//        bulletAppState = new BulletAppState();
//        stateManager.attach(bulletAppState);
//        
//        // Disable the default flyby cam
//        flyCam.setEnabled(false);
//        initKeys();
//        player = new OldPlayer(cam);
//        world = new OldWorld(assetManager, getPhysicsSpace());
//        msr = new MosquitoSimulatorRenderer(assetManager);
//        highscore = new Highscore();
//        player.getPlayerNode().attachChild(msr.getMosquito());
//        controller = new Controller();
//        
//        rootNode.attachChild(world.getWorldNode());
//        rootNode.attachChild(player.getPlayerNode());
//
//    }
//    
//    public PhysicsSpace getPhysicsSpace(){
//        
//        return bulletAppState.getPhysicsSpace();
//        
//    }
//
//        @Override
//        public void onAnalog(String name, float value, float tpf) {
//            
//            if(inputManager.isCursorVisible())
//                inputManager.setCursorVisible(false);
//            
//            directionForward.set(cam.getDirection()).normalizeLocal();
//            directionLeft.set(cam.getLeft()).normalizeLocal();
//            directionUp.set(directionLeft).crossLocal(directionForward).normalizeLocal();
//            
//            if (name.equals("Forward")) {
//                directionForward.multLocal(5*tpf);
//                player.movePlayerNode(directionForward);
//            }
//            if (name.equals("Backward")) {
//                directionForward.multLocal(-5 * tpf);
//                player.movePlayerNode(directionForward);
//            }
//            if (name.equals("Right")) {
//                directionLeft.multLocal(-5*tpf);
//                player.movePlayerNode(directionLeft);
//              
//            }
//            if (name.equals("Left")) {
//                directionLeft.multLocal(5*tpf);
//                player.movePlayerNode(directionLeft);
//            }
//            
//            if(name.equals("Up")){
//                directionUp.multLocal(5 * tpf);
//                player.movePlayerNode(directionUp);
//            } 
//            if(name.equals("Down")){
//                directionUp.multLocal(-5 * tpf);
//                player.movePlayerNode(directionUp);
//            }
//            if (name.equals("rotateRight")) {
//                player.rotatePlayerNode(0, 5 * tpf, 0);
//            }
//            if (name.equals("rotateLeft")) {
//                player.rotatePlayerNode(0, -5 * tpf, 0);
//            }
//            if(name.equals("rotateUp")){               
//                player.rotatePlayerNode(+5 * tpf, 0, 0);
//            }
//            if(name.equals("rotateDown")){
//                player.rotatePlayerNode(-5 * tpf, 0, 0);
//            }
//            
//        }
//        
////         public void onAction(String name, boolean keyPressed, float tpf) {
////            //toggling rotation on or off
////            if (name.equals("toggleRotate") && keyPressed) {
////              rotate = true;
////              inputManager.setCursorVisible(false);
////            }
////            if (name.equals("toggleRotate") && !keyPressed) {
////              rotate = false;
////              inputManager.setCursorVisible(true);
////            }
////
////        }
//        
//        
//    
//    private void initKeys(){
//        inputManager.addMapping("Forward", new KeyTrigger(KeyInput.KEY_W));
//        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
//        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
//        inputManager.addMapping("Backward", new KeyTrigger(KeyInput.KEY_S));
//        inputManager.addMapping("Up", new KeyTrigger(KeyInput.KEY_LCONTROL));
//        inputManager.addMapping("Down", new KeyTrigger(KeyInput.KEY_SPACE));
//        inputManager.addMapping("toggleRotate", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
//        inputManager.addMapping("rotateRight", new MouseAxisTrigger(MouseInput.AXIS_X, true));
//        inputManager.addMapping("rotateLeft", new MouseAxisTrigger(MouseInput.AXIS_X, false));
//        inputManager.addMapping("rotateUp", new MouseAxisTrigger(MouseInput.AXIS_Y, true));
//        inputManager.addMapping("rotateDown", new MouseAxisTrigger(MouseInput.AXIS_Y, false));
//        
//        inputManager.addListener(this, "Left", "Forward", "Right", "Backward", "Up", "Down");
//        inputManager.addListener(this, "rotateRight", "rotateLeft","rotateUp", "rotateDown", "toggleRotate");
//       
//    }
//}
//
