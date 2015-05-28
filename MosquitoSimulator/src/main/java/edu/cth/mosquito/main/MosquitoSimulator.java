package edu.cth.mosquito.main;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.audio.AudioNode;
import com.jme3.bullet.BulletAppState;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.PointLight;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import edu.cth.mosquito.controller.MenuController;
import edu.cth.mosquito.core.Collision;
import edu.cth.mosquito.core.Highscore;
import edu.cth.mosquito.core.Human;
import edu.cth.mosquito.core.ObjectiveGenerator;
import edu.cth.mosquito.core.Objectives;
import edu.cth.mosquito.core.Player;
import edu.cth.mosquito.util.Position3D;
import edu.cth.mosquito.core.World;
import edu.cth.mosquito.view.GuiOverlay;
import edu.cth.mosquito.view.MosquitoSimulatorRenderer;

public class MosquitoSimulator extends SimpleApplication implements AnalogListener {
    
    public static void main(String[] args) {
        MosquitoSimulator app = new MosquitoSimulator();
        app.start(); // start the game
    }
    
    private Highscore highscore;
    private Player player;
    private World world;
    private MosquitoSimulatorRenderer msr;
    private GuiOverlay guiOverlay;
    private Vector3f directionForward = new Vector3f();
    private Vector3f directionLeft = new Vector3f();
    private Vector3f directionUp = new Vector3f();
    private BulletAppState bulletAppState;
    private Collision collision;
    private AudioNode audioMosquito;
    private MenuController menu;
    private boolean isRunning;
    private Objectives objectives;
    private ObjectiveGenerator objGen = new ObjectiveGenerator();
    
    @Override
    public void simpleInitApp(){
        initKeys();
        
        //Removes the stats in the lower left corner that are shown by default
        setDisplayStatView(false);  
        setDisplayFps(false);
        
        // Disable the default flyby cam
        flyCam.setEnabled(false);
        
        isRunning = true;
        
        msr = new MosquitoSimulatorRenderer(assetManager, cam);
        guiOverlay = new GuiOverlay(assetManager, cam, renderManager);

        world = new World(45, 10, 60);
        player = new Player(world);
        highscore = new Highscore();
        menu = new MenuController(highscore.getHighscore());
        
        stateManager.attach(menu);
        bulletAppState = new BulletAppState();
        stateManager.attach(bulletAppState);
                
        msr.renderRoom(world.getWidth(), world.getHeight(), world.getLength());
              
        rootNode.attachChild(msr.getRoomNode());
        rootNode.attachChild(msr.getMosquitoNode());
        
        msr.createLights(world.getWidth(), world.getHeight(), world.getLength());
        
        for (int i = 0; i < msr.getPointLights().size(); i++){
            rootNode.addLight((PointLight)msr.getPointLights().get(i));
        }
        rootNode.addLight(msr.getAmbientLight());

        
        //Renders all the objects in world
        msr.renderWorldObjects(world.getObjects());       
        for(int i = 0; i < msr.getObjectNodes().size(); i++){
            rootNode.attachChild(msr.getObjectNodes().get(i));
        }

        //Adds collision to all the objects in the world
        collision = new Collision(bulletAppState, msr.getMosquitoNode(), msr.getObjectNodes());
        
        //OBJ
        player.setNewObjective(objGen.getnextObjective());
        guiOverlay.setObjectiveText(player.getObjectiveText());
        
    }
    
    @Override
    public void simpleUpdate(float tpf) {
        if(isRunning){
            
            //Updates the mosquito based on the position in player
            msr.getMosquitoNode().setLocalTranslation(player.getPosition().getX(), 
                    player.getPosition().getY(), player.getPosition().getZ());
                      
            player.increaseScore(3 * tpf);
            player.decreaseEnergy(3 * tpf);
            
            if(player.getEnergy() <= 0){
                highscore.addScore(player.getScore());
                showMenu("start");
                reset();
            }

            guiOverlay.updateGUI(player.getEnergy(), player.getScore());
            guiOverlay.updateEnergybar(-0.03f*tpf);


            //Increases the blood of humans over time
            for(int i = 0; i < world.getObjects().size(); i++){
                if(world.getObjects().get(i) instanceof Human){
                    ((Human)world.getObjects().get(i)).increaseBlood(1*tpf);
                }
            }
            
            if(collision.getCollidingNode() != null){

                //Checks for a colliding object and adds it
                for(int i = 0; i < msr.getObjectNodes().size(); i++){
                    if(collision.getCollidingNode().equals(msr.getObjectNodes().get(i))){
                        collision.setCollidingObject(world.getObjects().get(i));
                        break;
                    }
                }

                if(collision.getCollidingObject() instanceof Human)                    
                    guiOverlay.updateBloodbar(((Human)collision.getCollidingObject()).getBlood()/100);
                        

            }else{
                collision.setCollidingObject(null);
                
                //Hides the blood bar
                guiOverlay.getbloodGeom().setLocalScale(0);
            }
        }
        
            guiOverlay.getEnergyNode().updateGeometricState();
            guiOverlay.getEnergyNode().updateLogicalState(tpf);
            guiOverlay.getBloodNode().updateGeometricState();
            guiOverlay.getBloodNode().updateLogicalState(tpf);
    }

   
    
   
    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
       
    }
      
    public void initGUI(){
        
        inputManager.setCursorVisible(false);
        
        guiOverlay.setScoreTextPos(settings.getWidth()-guiOverlay.getScoreText().getLineWidth()-10,settings.getHeight()-10, 0f);
        guiOverlay.setEnergyTextPos(settings.getWidth()-guiOverlay.getEnergyText().getLineWidth()-10,(settings.getHeight()*0.9f), 0f);
        guiOverlay.setObjectiveTextPos(settings.getWidth()*0.05f, settings.getHeight()-10, 0f);
        guiOverlay.setRewardTextPos(settings.getWidth()/2-guiOverlay.getRewardText().getLineWidth()/2, settings.getHeight()/1.5f, 0f);
        guiOverlay.setInstructionTextPosition(10f, settings.getHeight() - 10f, 0f);
        
        guiNode.attachChild(guiOverlay.getObjectiveText());
        guiNode.attachChild(guiOverlay.getScoreText());
        guiNode.attachChild(guiOverlay.getEnergyText());
        guiNode.attachChild(guiOverlay.getRewardText());
        guiNode.attachChild(guiOverlay.getInstructionText());
    
    }
    
    public void initAudio(){
        assetManager.registerLocator("assets.zip", ZipLocator.class);
        audioMosquito = new AudioNode(assetManager, "assets/mosquitoSound.wav", false);
        audioMosquito.setPositional(false);
        audioMosquito.setLooping(true);
        audioMosquito.setVolume(2);
        rootNode.attachChild(audioMosquito);
        audioMosquito.play();
    }
    
    public void returnFromPause(boolean flag){       
        isRunning = flag;       
    }
    
    public void showMenu(String menuId){
        
        if(menuId.equals("start")){
            menu.setHighscore(highscore.getHighscore());           
        }       
        
        inputManager.setCursorVisible(true);
        menu.switchScreen(menuId);
        
        if(audioMosquito != null)
            audioMosquito.pause();
        
        guiNode.detachChild(guiOverlay.getObjectiveText());
        guiNode.detachChild(guiOverlay.getScoreText());
        guiNode.detachChild(guiOverlay.getEnergyText());
        guiNode.detachChild(guiOverlay.getRewardText());
        guiNode.detachChild(guiOverlay.getInstructionText());
    }
    
    public void reset(){
        
        player.reset();
        world.resetHumans();
        msr.getMosquitoNode().setLocalRotation(Quaternion.IDENTITY);
        guiOverlay.resetUI();
        isRunning = true;
        
    }
    
    private void movePlayer(Vector3f direction, float speed){
        
        direction.multLocal(speed);
        player.move(new Position3D(direction.x, direction.y, direction.z));
        
    }
    
    @Override
    public void stop(){
        super.stop();
        System.exit(0);
    }
    
    @Override
    public void onAnalog(String binding, float value, float tpf) {
      
        //The keys that can't be pressed while game is paused
        //Is not if else so that you can move in 2 directions at the same time
        if(isRunning){
            directionForward.set(cam.getDirection()).normalizeLocal();
            directionLeft.set(cam.getLeft()).normalizeLocal();
            directionUp.set(directionLeft).crossLocal(directionForward).normalizeLocal();

            if (binding.equals("Forward")) {
                movePlayer(directionForward, 5*tpf);
            }

            if (binding.equals("Backward")) {
                movePlayer(directionForward, -5*tpf);
            }
            
            if (binding.equals("Right")) {
                movePlayer(directionLeft, -5*tpf);
            }

            if (binding.equals("Left")) {
                movePlayer(directionLeft, 5*tpf);
            }

            if(binding.equals("Up")){
                movePlayer(directionUp, -5*tpf);
            }

            if(binding.equals("Down")){
                movePlayer(directionUp, 5*tpf);
            }
            
            if (binding.equals("rotateRight")) {
                msr.getMosquitoNode().rotate(0, -tpf, 0);
            }

            if (binding.equals("rotateLeft")) {
                msr.getMosquitoNode().rotate(0, tpf, 0);
            }

            if(binding.equals("SuckBlood") && collision.getCollidingObject() instanceof Human){
                Human temp = (Human)collision.getCollidingObject();
                if(temp.getBlood() >= 24*tpf){
                    temp.decreaseBlood(24*tpf);
                    player.increaseEnergy(15 * tpf);
                    guiOverlay.updateEnergybar(0.15f*tpf);
                    guiOverlay.updateBloodbar(-0.24f*tpf);
                }

            }
        }
        
        //The keys that can still be pressed while game is paused
        switch(binding){
            case "Pause":
                showMenu("pauseMenu");
                if(isRunning = true)
                    isRunning = false;
                break;
                
            case "Escape":
                highscore.addScore(player.getScore());
                showMenu("start");
                break;
                
            case "Reset":
                reset();
                break;
        }
    }   
    
    private void initKeys(){
        inputManager.deleteMapping(INPUT_MAPPING_EXIT);
        
        inputManager.addMapping("Reset", new KeyTrigger(KeyInput.KEY_R));
        inputManager.addMapping("SuckBlood", new KeyTrigger(KeyInput.KEY_Q));       
        inputManager.addMapping("Escape", new KeyTrigger(KeyInput.KEY_ESCAPE));       
        inputManager.addMapping("Pause", new KeyTrigger(KeyInput.KEY_P));
        
        inputManager.addMapping("Forward", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Backward", new KeyTrigger(KeyInput.KEY_S));
        inputManager.addMapping("Down", new KeyTrigger(KeyInput.KEY_LSHIFT));
        inputManager.addMapping("Up", new KeyTrigger(KeyInput.KEY_SPACE));
        
        inputManager.addMapping("rotateUp", new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addMapping("rotateDown", new KeyTrigger(KeyInput.KEY_DOWN));
        inputManager.addMapping("rotateLeft", new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addMapping("rotateRight", new KeyTrigger(KeyInput.KEY_RIGHT));
        
        inputManager.addListener(this, "Left", "Forward", "Right", "Backward", "Up", "Down", "Reset", "SuckBlood", "Escape", "Pause");
        inputManager.addListener(this, "rotateRight", "rotateLeft","rotateUp", "rotateDown", "toggleRotate");
       
    }
}

