/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.view;

import com.jme3.asset.AssetManager;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.light.AmbientLight;
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
    private Spatial floor, wallN, wallS, wallW, wallE, roof;
    private Material floorMaterial, wallMaterial, roofMaterial;
    private PointLight lamp;
    private AmbientLight al;
    
    
    //Filenames
    private static final String ASSET_MAP = "assets.zip";
    
    private static final String MOSQUITO_MODEL = "assets/mosquito.j3o";
    private static final String MALE_HUMAN_MODEL = "assets/human.j3o";
    private static final String FEMALE_HUMAN_MODEL = "assets/humanWoman.j3o";
    private static final String PLANE_MODEL = "assets/plane.j3o";
    
    private static final String LIGHTING_MATERIAL = "Common/MatDefs/Light/Lighting.j3md";
    private static final String UNSHADED_MATERIAL = "Common/MatDefs/Misc/Unshaded.j3md";
    
    private static final String MOSQUITO_TEXTURE = "assets/orange.png";
    private static final String FLOOR_TEXTURE = "assets/floor.jpg";
    private static final String WALL_TEXTURE = "assets/wall.jpg";
    private static final String ROOF_TEXTURE = "assets/roof.png";
    
    
    
    
    
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
        assetManager.registerLocator(ASSET_MAP, ZipLocator.class);
        mosquito = assetManager.loadModel(MOSQUITO_MODEL);
        mosquito.rotate(0f, (float)Math.PI/2, 0f);
        mosquito.scale(0.1f);
        mosquito.setLocalTranslation(0, -1, 0);
        
        Material m = new Material(assetManager, LIGHTING_MATERIAL);
        Texture tf = assetManager.loadTexture(MOSQUITO_TEXTURE);
        m.setTexture("DiffuseMap", tf);
        
        mosquito.setMaterial(m);
        mosquitoNode.attachChild(mosquito);
    }
    
    //Skapar hela rummet
    public void renderRoom(float width, float height, float length){
        createMaterials();
        renderPlaneFloor(width, height, length);
        renderPlaneWallNorth(width, height, length);
        renderPlaneWallEast(width, height, length);
        renderPlaneWallWest(width, height, length);
        renderPlaneWallSouth(width, height, length);
        renderPlaneRoof(width, height, length);
        
    }
    
    //Skapar alla ljuskällor i rummet
    public void createLights(float width, float height, float length){
        //Lägger till tre "lampor" i taket
        addPointLight(0, height-1, 0);
        addPointLight(0, height-1, 50);
        addPointLight(0, height-1, -50);
        
        //Lägger till ett ljus som lyser på allt i rummet
        addAmbientLight(1f);
    }
    
    //Lägger till en lampa på positionen (w,h,l)
    public void addPointLight(float width, float height, float length){
        lamp = new PointLight();
        lamp.setColor(ColorRGBA.White);
        lamp.setRadius(80f);
        lamp.setPosition(new Vector3f(width, height, length));
        lights.add(lamp);
    }
    
    //Lägger till ljus med styrkan brightness i hela rummet
    public void addAmbientLight(float brightness){
        al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(brightness));
    }
    
    //Skapar materialet för väggar, tak och golv
    public void createMaterials(){
        floorMaterial = new Material(assetManager, LIGHTING_MATERIAL);
        wallMaterial = new Material(assetManager, LIGHTING_MATERIAL);
        roofMaterial = new Material(assetManager, LIGHTING_MATERIAL);
        
        Texture tf = assetManager.loadTexture(FLOOR_TEXTURE);
        floorMaterial.setTexture("DiffuseMap", tf);
        
        Texture tw = assetManager.loadTexture(WALL_TEXTURE);
        wallMaterial.setTexture("DiffuseMap", tw);
        
        Texture tr = assetManager.loadTexture(ROOF_TEXTURE);
        roofMaterial.setTexture("DiffuseMap", tr);
    }   
    
    public void renderPlaneFloor(float width, float height, float length){
        assetManager.registerLocator(ASSET_MAP, ZipLocator.class);
        floor = assetManager.loadModel(PLANE_MODEL);
        
        floor.scale(width, 1, length);
        floor.setLocalTranslation(0, -height, 0);
        
        floor.setMaterial(floorMaterial);
        
        roomNode.attachChild(floor);
    }
    
    public void renderPlaneWallNorth(float width, float height, float length){
        assetManager.registerLocator(ASSET_MAP, ZipLocator.class);
        wallN = assetManager.loadModel(PLANE_MODEL);
        
        wallN.rotate((float)-Math.PI/2, 0, 0);
        wallN.scale(width, 1, height);
        wallN.setLocalTranslation(0, 0, length);
        
        wallN.setMaterial(wallMaterial);
        
        roomNode.attachChild(wallN);
    }
      
    public void renderPlaneWallSouth(float width, float height, float length){
        assetManager.registerLocator(ASSET_MAP, ZipLocator.class);
        wallS = assetManager.loadModel(PLANE_MODEL);
        
        wallS.rotate((float)Math.PI/2, 0, 0);
        wallS.scale(width, 1, height);
        wallS.setLocalTranslation(0, 0, -length);
        
        wallS.setMaterial(wallMaterial);
        
        roomNode.attachChild(wallS);
    }
    
    public void renderPlaneWallEast(float width, float height, float length){
        assetManager.registerLocator(ASSET_MAP, ZipLocator.class);
        wallE = assetManager.loadModel(PLANE_MODEL);
        
        wallE.rotate(0, 0, (float)-Math.PI/2);
        wallE.scale(height, 1, length);
        wallE.setLocalTranslation(-width, 0, 0);
        
        wallE.setMaterial(wallMaterial);
        
        roomNode.attachChild(wallE);
    }
    
    public void renderPlaneWallWest(float width, float height, float length){
        assetManager.registerLocator(ASSET_MAP, ZipLocator.class);
        wallW = assetManager.loadModel(PLANE_MODEL);
        
        wallW.rotate(0, 0, (float)Math.PI/2);
        wallW.scale(height, 1, length);
        wallW.setLocalTranslation(width, 0, 0);
        
        wallW.setMaterial(wallMaterial);
        
        roomNode.attachChild(wallW);
    }
    
    public void renderPlaneRoof(float width, float height, float length){
        assetManager.registerLocator(ASSET_MAP, ZipLocator.class);
        roof = assetManager.loadModel(PLANE_MODEL);
        
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
        final float rotation = (float)Math.random()*(float)Math.PI*2f;
        
        assetManager.registerLocator(ASSET_MAP, ZipLocator.class);
        for(int i = 0; i < objects.size(); i++){
            SolidObject object = objects.get(i);
            
            if(object instanceof Human){
                
                Human instance = (Human)object;
                
                h = getRandomGenderHuman();
                h.scale(instance.getWidth(), instance.getHeight(), instance.getLength());
                              
                m = new Material(assetManager,LIGHTING_MATERIAL);
                h.setMaterial(m);
                tempNode = new Node("Human");
                tempNode.rotate(0f, rotation, 0f);
                tempNode.attachChild(h);
                
            }else{
                m = new Material(assetManager,
                UNSHADED_MATERIAL);
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
    
    private Spatial getRandomGenderHuman(){
        if (Math.random() > 0.5){
             return assetManager.loadModel(MALE_HUMAN_MODEL);
        } else {
             return assetManager.loadModel(FEMALE_HUMAN_MODEL);
        }
    }
    
    public List<Node> getObjectNodes(){
        return objectNodes;
    }
    
    public Node getMosquitoNode(){
        return mosquitoNode;
    }
    
    public Node getRoomNode(){
        return roomNode;
    }

    public List getPointLights(){
        return lights;
    }
    
    public AmbientLight getAmbientLight(){
        return al;
    }
}
