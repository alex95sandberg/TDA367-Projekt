/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.view;

import com.jme3.asset.AssetManager;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.light.PointLight;
import com.jme3.material.Material;
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
    
    private AssetManager assetManager;
    private Camera cam;
    private CameraNode camNode;
    private Node mosquitoNode;
    private Node roomNode;
    private List<Node> objectNodes;
    private List<PointLight> lights;
    
    private Spatial mosquito;
    private Spatial room;
    private Spatial floor, wallN, wallS, wallW, wallE, roof;
    private Material floorMaterial, wallMaterial, roofMaterial;
    private PointLight lamp;
    
    public MosquitoSimulatorRenderer(AssetManager assetManager, Camera cam){
        this.assetManager = assetManager;
        
        assetManager.registerLocator("assets.zip", ZipLocator.class);
        this.cam = cam;
        mosquitoNode = new Node();
        roomNode = new Node();
        lights = new ArrayList<>();
        this.renderMosquito();
        cameraSetup();
    }
    
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
        
        Material m = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        Texture tf = assetManager.loadTexture("assets/orange.png");
        m.setTexture("DiffuseMap", tf);
        
        mosquito.setMaterial(m);
        mosquitoNode.attachChild(mosquito);
    }
    
    //Gamla rummet
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
        createMaterials();
        renderPlaneFloor(width, height, length);
        renderPlaneWallNorth(width, height, length);
        renderPlaneWallEast(width, height, length);
        renderPlaneWallWest(width, height, length);
        renderPlaneWallSouth(width, height, length);
        renderPlaneRoof(width, height, length);
        
    }
    
    public void createLights(float width, float height, float length){
        addLight(0, height-2, 0);
    }
    
    public void addLight(float width, float height, float length){
        PointLight lamp = new PointLight();
        lamp.setColor(ColorRGBA.White);
        lamp.setPosition(new Vector3f(width, height, length));
        lights.add(lamp);
    }
    
    public void createMaterials(){
        floorMaterial = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        wallMaterial = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        roofMaterial = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        
        //floorMaterial.setColor("Ambient", new ColorRGBA(1f, .96f, .56f, 1f));
        //wallMaterial.setColor("Ambient", new ColorRGBA(.01f, .66f, .62f, 1f));
        //roofMaterial.setColor("Ambient", new ColorRGBA(.98f, 1f, 1f, 1f)); 
        
        Texture tf = assetManager.loadTexture("assets/floor.jpg");
        floorMaterial.setTexture("DiffuseMap", tf);
        
        Texture tw = assetManager.loadTexture("assets/wall.jpg");
        wallMaterial.setTexture("DiffuseMap", tw);
        
        Texture tr = assetManager.loadTexture("assets/roof.jpg");
        roofMaterial.setTexture("DiffuseMap", tr);
    }   
    
    public void renderPlaneFloor(float width, float height, float length){
        assetManager.registerLocator("assets.zip", ZipLocator.class);
        floor = assetManager.loadModel("assets/plane.j3o");
        
        floor.scale(width, 1, length);
        floor.setLocalTranslation(0, -height, 0);
        
        floor.setMaterial(floorMaterial);
        
        roomNode.attachChild(floor);
    }
    
    public void renderPlaneWallNorth(float width, float height, float length){
        assetManager.registerLocator("assets.zip", ZipLocator.class);
        wallN = assetManager.loadModel("assets/plane.j3o");
        
        wallN.rotate((float)-Math.PI/2, 0, 0);
        wallN.scale(width, 1, height);
        wallN.setLocalTranslation(0, 0, length);
        
        wallN.setMaterial(wallMaterial);
        
        roomNode.attachChild(wallN);
    }
      
    public void renderPlaneWallSouth(float width, float height, float length){
        assetManager.registerLocator("assets.zip", ZipLocator.class);
        wallS = assetManager.loadModel("assets/plane.j3o");
        
        wallS.rotate((float)Math.PI/2, 0, 0);
        wallS.scale(width, 1, height);
        wallS.setLocalTranslation(0, 0, -length);
        
        wallS.setMaterial(wallMaterial);
        
        roomNode.attachChild(wallS);
    }
    
    public void renderPlaneWallEast(float width, float height, float length){
        assetManager.registerLocator("assets.zip", ZipLocator.class);
        wallE = assetManager.loadModel("assets/plane.j3o");
        
        wallE.rotate(0, 0, (float)-Math.PI/2);
        wallE.scale(height, 1, length);
        wallE.setLocalTranslation(-width, 0, 0);
        
        wallE.setMaterial(wallMaterial);
        
        roomNode.attachChild(wallE);
    }
    
    public void renderPlaneWallWest(float width, float height, float length){
        assetManager.registerLocator("assets.zip", ZipLocator.class);
        wallW = assetManager.loadModel("assets/plane.j3o");
        
        wallW.rotate(0, 0, (float)Math.PI/2);
        wallW.scale(height, 1, length);
        wallW.setLocalTranslation(width, 0, 0);
        
        wallW.setMaterial(wallMaterial);
        
        roomNode.attachChild(wallW);
    }
    
    public void renderPlaneRoof(float width, float height, float length){
        assetManager.registerLocator("assets.zip", ZipLocator.class);
        roof = assetManager.loadModel("assets/plane.j3o");
        
        roof.rotate((float)Math.PI, 0, 0);
        roof.scale(width, 1, length);
        roof.setLocalTranslation(0, height, 0);
        
        roof.setMaterial(roofMaterial);
        
        roomNode.attachChild(roof);
    }
    
    public void renderWorldObjects(List<SolidObject> objects){
        Box b;
        Material m;       
        Geometry g;
        objectNodes = new ArrayList<>();
        Node tempNode;
        Spatial h;
        
        assetManager.registerLocator("assets.zip", ZipLocator.class);
        for(int i = 0; i < objects.size(); i++){
            SolidObject object = objects.get(i);
            
            if(object instanceof Human){
                h = assetManager.loadModel("assets/human.j3o");
                m = new Material(assetManager,"Common/MatDefs/Light/Lighting.j3md");
                h.setMaterial(m);
                tempNode = new Node("Human");
                tempNode.attachChild(h);
                
            } else{
                m = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");
                m.setColor("Color", ColorRGBA.Blue);
                
                tempNode = new Node("Solid");
                
                b = new Box(object.getWidth(), object.getHeight(), object.getLength());
                g = new Geometry("Box", b);
                g.setMaterial(m);
                tempNode.attachChild(g);
            }
            
            
            
            
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

    public List getLights(){
        return lights;
    }
    
    private Vector2f Vector2f(double d, double d0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
