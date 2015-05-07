/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.view;

import com.jme3.asset.AssetManager;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.CameraControl;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;
import edu.cth.mosquito.core.Human;
import edu.cth.mosquito.core.SolidObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alexander
 */
public class MosquitoSimulatorRenderer {
    
    public MosquitoSimulatorRenderer(AssetManager assetManager, Camera cam){
        this.assetManager = assetManager;
        
        assetManager.registerLocator("assets.zip", ZipLocator.class);
        this.cam = cam;
        mosquitoNode = new Node();
        this.renderMosquito();
        cameraSetup();
    }
    private AssetManager assetManager;
    private Camera cam;
    private Spatial mosquito;
    private Spatial room;
    private Spatial floor, wallN, wallS, wallW, wallE, roof;
    private CameraNode camNode;
    private Node mosquitoNode;
    private Node roomNode = new Node();
    private List<Node> objectNodes;
    
    private void cameraSetup(){
        camNode = new CameraNode("Camera Node", cam);
        camNode.setControlDir(CameraControl.ControlDirection.SpatialToCamera);
        mosquitoNode.attachChild(camNode);
        camNode.setLocalTranslation(new Vector3f(0, 0, -10));
        camNode.lookAt(mosquitoNode.getLocalTranslation(), Vector3f.UNIT_Y); 
        
        mosquitoNode.setLocalTranslation(0, 0, 0);
    }
    
    private void renderMosquito(){
        assetManager.registerLocator("assets.zip", ZipLocator.class);
        mosquito = assetManager.loadModel("assets/mosquito.j3o");
        mosquito.rotate(0f, (float)Math.PI/2, 0f);
        mosquito.scale(0.1f);
        mosquito.setLocalTranslation(0, 0, 0);
        
        Material m = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        m.setColor("Color", ColorRGBA.Orange);
        
        mosquito.setMaterial(m);
        mosquitoNode.attachChild(mosquito);
    }
    
    public void renderRoom(float width, float height){
        assetManager.registerLocator("assets.zip", ZipLocator.class);
        room = assetManager.loadModel("assets/room.j3o");
        room.scale(width,height,width);
        room.setLocalTranslation(0, 0, 0);
        
        Material m = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        m.setColor("Color", ColorRGBA.Yellow);
        m.getAdditionalRenderState().setWireframe(true);
        //Texture txt = assetManager.loadTexture("assets/lloyd.jpg");
        //m.setTexture("ColorMap", txt);
        
        room.setMaterial(m);
    }
    
    public void renderPlaneRoom(float width, float height, float length){
        renderPlaneFloor(width, height, length);
        renderPlaneWallNorth(width, height, length);
        renderPlaneWallEast(width, height, length);
        renderPlaneWallWest(width, height, length);
        renderPlaneWallSouth(width, height, length);
        renderPlaneRoof(width, height, length);
    }
    
    public void renderPlaneFloor(float width, float height, float length){
        assetManager.registerLocator("assets.zip", ZipLocator.class);
        floor = assetManager.loadModel("assets/plane.j3o");
        
        floor.scale(width, 1, length);
        floor.setLocalTranslation(0, -height, 0);
        
        Material m = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //m.setColor("Color", ColorRGBA.Green);
        Texture txt = assetManager.loadTexture("assets/floor.jpg");
        m.setTexture("ColorMap", txt); 
        
        floor.setMaterial(m);
        
        roomNode.attachChild(floor);
    }
    
    public void renderPlaneWallNorth(float width, float height, float length){
        assetManager.registerLocator("assets.zip", ZipLocator.class);
        wallN = assetManager.loadModel("assets/plane.j3o");
        
        wallN.rotate((float)-Math.PI/2, 0, 0);
        wallN.scale(width, 1, height);
        wallN.setLocalTranslation(0, 0, length);
        
        Material m = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //m.setColor("Color", ColorRGBA.Red);
        
        Texture txt = assetManager.loadTexture("assets/wall.jpg");
        m.setTexture("ColorMap", txt);
        
        wallN.setMaterial(m);
        
        roomNode.attachChild(wallN);
    }
      
    public void renderPlaneWallSouth(float width, float height, float length){
        assetManager.registerLocator("assets.zip", ZipLocator.class);
        wallS = assetManager.loadModel("assets/plane.j3o");
        
        wallS.rotate((float)Math.PI/2, 0, 0);
        wallS.scale(width, 1, height);
        wallS.setLocalTranslation(0, 0, -length);
        
        Material m = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //m.setColor("Color", ColorRGBA.Blue);
        Texture txt = assetManager.loadTexture("assets/wall.jpg");
        m.setTexture("ColorMap", txt);
        
        wallS.setMaterial(m);
        
        roomNode.attachChild(wallS);
    }
    
    public void renderPlaneWallEast(float width, float height, float length){
        assetManager.registerLocator("assets.zip", ZipLocator.class);
        wallE = assetManager.loadModel("assets/plane.j3o");
        
        wallE.rotate(0, 0, (float)-Math.PI/2);
        wallE.scale(height, 1, length);
        wallE.setLocalTranslation(-
                width, 0, 0);
        
        Material m = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //m.setColor("Color", ColorRGBA.Yellow);
        Texture txt = assetManager.loadTexture("assets/wall.jpg");
        m.setTexture("ColorMap", txt);
        
        wallE.setMaterial(m);
        
        roomNode.attachChild(wallE);
    }
    
    public void renderPlaneWallWest(float width, float height, float length){
        assetManager.registerLocator("assets.zip", ZipLocator.class);
        wallW = assetManager.loadModel("assets/plane.j3o");
        
        wallW.rotate(0, 0, (float)Math.PI/2);
        wallW.scale(height, 1, length);
        wallW.setLocalTranslation(width, 0, 0);
        
        Material m = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //m.setColor("Color", ColorRGBA.LightGray);
        Texture txt = assetManager.loadTexture("assets/wall.jpg");
        m.setTexture("ColorMap", txt);
        
        wallW.setMaterial(m);
        
        roomNode.attachChild(wallW);
    }
    
    public void renderPlaneRoof(float width, float height, float length){
        assetManager.registerLocator("assets.zip", ZipLocator.class);
        roof = assetManager.loadModel("assets/plane.j3o");
        
        roof.rotate((float)Math.PI, 0, 0);
        roof.scale(width, 1, length);
        roof.setLocalTranslation(0, height, 0);
        
        Material m = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        //m.setColor("Color", ColorRGBA.Cyan);
        Texture txt = assetManager.loadTexture("assets/roof.jpg");
        m.setTexture("ColorMap", txt);
        
        roof.setMaterial(m);
        
        roomNode.attachChild(roof);
    }
    
    public void renderWorldObjects(List<SolidObject> objects){
        Box b;
        Material m;       
        Geometry g;
        objectNodes = new ArrayList<>();
        
        for(int i = 0; i < objects.size(); i++){
            SolidObject object = objects.get(i);
            
            if(object instanceof Human){
                m = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");
                Texture txt2 = assetManager.loadTexture("assets/lloyd.jpg");
        
                m.setTexture("ColorMap", txt2);
                
            }else{
                m = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");
                m.setColor("Color", ColorRGBA.Blue);
            }
            
            b = new Box(object.getWidth(), object.getHeight(), object.getLength());
            g = new Geometry("Box", b);
            g.setMaterial(m);
            
            Node tempNode = new Node();
            tempNode.attachChild(g);
            tempNode.setLocalTranslation(object.getPosition().getX(), object.getPosition().getY(), object.getPosition().getZ());
            objectNodes.add(tempNode);
        }
        
    }
    
    public List<Node> getObjectNodes(){
        return objectNodes;
    }
    
    public Node getMosquitoNode(){
        return mosquitoNode;
    }
    
    public Spatial getRoomSpatial(){
        return room;
    }
    
    public Node getRoomNode(){
        return roomNode;
    }

    private Vector2f Vector2f(double d, double d0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
