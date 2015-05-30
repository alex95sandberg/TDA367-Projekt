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
 * Handles and generates all the objects in the world
 * 
 * @author Mosquito
 */
public class MosquitoSimulatorRenderer {
    
    private AssetManager assetManager;
    private Camera cam;
    private CameraNode camNode;
    private static final Node mosquitoNode = new Node();
    private static final Node roomNode = new Node();
    private static final List<Node> objectNodes = new ArrayList<>();
    private static final List<PointLight> lights = new ArrayList<>();
    
    private Spatial mosquito;
    private Spatial floor, wallN, wallS, wallW, wallE, roof;
    private Material floorMaterial, wallMaterial, roofMaterial;
    private Material humanMaterial, solidobjectMaterial;
    private PointLight lamp;
    private AmbientLight al;
    
    
    //Filenames
    //The zipped assets map
    private static final String ASSET_MAP = "assets.zip";
    
    //Spatials
    private static final String MOSQUITO_MODEL = "assets/mosquito.j3o";
    private static final String MALE_HUMAN_MODEL = "assets/human.j3o";
    private static final String FEMALE_HUMAN_MODEL = "assets/humanWoman.j3o";
    private static final String PLANE_MODEL = "assets/plane.j3o";
    
    //Materials
    private static final String LIGHTING_MATERIAL = "Common/MatDefs/Light/Lighting.j3md";
    private static final String UNSHADED_MATERIAL = "Common/MatDefs/Misc/Unshaded.j3md";
    
    //Textures
    private static final String HUMAN_TEXTURE = "assets/human.png";
    private static final String MOSQUITO_TEXTURE = "assets/orange.png";
    private static final String FLOOR_TEXTURE = "assets/floor.jpg";
    private static final String WALL_TEXTURE = "assets/wall.jpg";
    private static final String ROOF_TEXTURE = "assets/roof.png";
    
    
    
    
    //OKOMMENTERAD
    public MosquitoSimulatorRenderer(AssetManager assetManager, Camera cam){
        this.assetManager = assetManager;
        
        assetManager.registerLocator(ASSET_MAP, ZipLocator.class);
        this.cam = cam;
        this.renderMosquito();
        cameraSetup();
    }
    
    //OKOMMENTERAD
    private void cameraSetup(){
        camNode = new CameraNode("Camera Node", cam);
        camNode.setControlDir(CameraControl.ControlDirection.SpatialToCamera);
        mosquitoNode.attachChild(camNode);
        camNode.setLocalTranslation(new Vector3f(0, 0, -10));
        camNode.lookAt(mosquitoNode.getLocalTranslation(), Vector3f.UNIT_Y); 
        
        mosquitoNode.setLocalTranslation(0, 0, 0);
    }
    
    //OKOMMENTERAD
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
    
    //Creates the entire room
    public void renderRoom(float width, float height, float length){
        createMaterials();
        
        //Renders all 4 walls, roof and floor
        renderPlaneFloor(width, height, length);
        renderPlaneWallNorth(width, height, length);
        renderPlaneWallEast(width, height, length);
        renderPlaneWallWest(width, height, length);
        renderPlaneWallSouth(width, height, length);
        renderPlaneRoof(width, height, length);
        
    }
    
    //Creates all lightsources in the room
    public void createLights(float width, float height, float length){
        //Adds three "lamps" to the room
        addPointLight(0, height-1, 0);
        addPointLight(0, height-1, 50);
        addPointLight(0, height-1, -50);
        
        addAmbientLight(1f);
    }
    
    //Adds a lamp at the position (w,h,l)
    public void addPointLight(float width, float height, float length){
        lamp = new PointLight();
        lamp.setColor(ColorRGBA.White);
        lamp.setRadius(80f);
        lamp.setPosition(new Vector3f(width, height, length));
        lights.add(lamp);
    }
    
    //Adds a light that lights up everything in the room equally
    public void addAmbientLight(float brightness){
        al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(brightness));
    }
    
    //Creates the materials for walls, roof and floor
    public void createMaterials(){
        //Sets the type of material
        floorMaterial = new Material(assetManager, LIGHTING_MATERIAL);
        wallMaterial = new Material(assetManager, LIGHTING_MATERIAL);
        roofMaterial = new Material(assetManager, LIGHTING_MATERIAL);
        humanMaterial = new Material(assetManager, LIGHTING_MATERIAL);
        solidobjectMaterial = new Material(assetManager, UNSHADED_MATERIAL);
        
        //Adds a texture to the materials
        Texture tf = assetManager.loadTexture(FLOOR_TEXTURE);
        floorMaterial.setTexture("DiffuseMap", tf);
        
        Texture tw = assetManager.loadTexture(WALL_TEXTURE);
        wallMaterial.setTexture("DiffuseMap", tw);
        
        Texture tr = assetManager.loadTexture(ROOF_TEXTURE);
        roofMaterial.setTexture("DiffuseMap", tr);
        
        Texture th = assetManager.loadTexture(HUMAN_TEXTURE);
        humanMaterial.setTexture("DiffuseMap", th);
        
        //TEMP
        solidobjectMaterial.setColor("Color", ColorRGBA.Blue);
    }   
    
    //Creates the floor
    public void renderPlaneFloor(float width, float height, float length){
        //Loads in the plane spatial
        assetManager.registerLocator(ASSET_MAP, ZipLocator.class);
        floor = assetManager.loadModel(PLANE_MODEL);
        
        //Scales the plane and gives it a position
        floor.scale(width, 1, length);
        floor.setLocalTranslation(0, -height, 0);
        
        floor.setMaterial(floorMaterial);
        
        roomNode.attachChild(floor);
    }
    
    //Creates the wall that is in front of you when the game starts
    public void renderPlaneWallNorth(float width, float height, float length){
        //Loads in the plane spatial
        assetManager.registerLocator(ASSET_MAP, ZipLocator.class);
        wallN = assetManager.loadModel(PLANE_MODEL);
        
        //Rotates the plane, scales it and gives it a position
        wallN.rotate((float)-Math.PI/2, 0, 0);
        wallN.scale(width, 1, height);
        wallN.setLocalTranslation(0, 0, length);
        
        wallN.setMaterial(wallMaterial);
        
        roomNode.attachChild(wallN);
    }
    
    //Creates the wall that is behind you when the game starts
    public void renderPlaneWallSouth(float width, float height, float length){
        //Loads in the plane spatial
        assetManager.registerLocator(ASSET_MAP, ZipLocator.class);
        wallS = assetManager.loadModel(PLANE_MODEL);
        
        //Rotates the plane, scales it and gives it a position
        wallS.rotate((float)Math.PI/2, 0, 0);
        wallS.scale(width, 1, height);
        wallS.setLocalTranslation(0, 0, -length);
        
        wallS.setMaterial(wallMaterial);
        
        roomNode.attachChild(wallS);
    }
    
    //Creates the wall that is to your right when the game starts
    public void renderPlaneWallEast(float width, float height, float length){
        //Loads in the plane spatial
        assetManager.registerLocator(ASSET_MAP, ZipLocator.class);
        wallE = assetManager.loadModel(PLANE_MODEL);
        
        //Rotates the plane, scales it and gives it a position
        wallE.rotate(0, 0, (float)-Math.PI/2);
        wallE.scale(height, 1, length);
        wallE.setLocalTranslation(-width, 0, 0);
        
        wallE.setMaterial(wallMaterial);
        
        roomNode.attachChild(wallE);
    }
    
    //Creates the wall that is to your left when the game starts
    public void renderPlaneWallWest(float width, float height, float length){
        //Loads in the plane spatial
        assetManager.registerLocator(ASSET_MAP, ZipLocator.class);
        wallW = assetManager.loadModel(PLANE_MODEL);
        
        //Rotates the plane, scales it and gives it a position
        wallW.rotate(0, 0, (float)Math.PI/2);
        wallW.scale(height, 1, length);
        wallW.setLocalTranslation(width, 0, 0);
        
        wallW.setMaterial(wallMaterial);
        
        roomNode.attachChild(wallW);
    }
    
    //Creates the roof
    public void renderPlaneRoof(float width, float height, float length){
        //Loads in the plane spatial
        assetManager.registerLocator(ASSET_MAP, ZipLocator.class);
        roof = assetManager.loadModel(PLANE_MODEL);
        
        //Rotates the plane, scales it and gives it a position
        roof.rotate((float)Math.PI, 0, 0);
        roof.scale(width, 1, length);
        roof.setLocalTranslation(0, height, 0);
        
        roof.setMaterial(roofMaterial);
        
        roomNode.attachChild(roof);
    }
    
    //OKOMMENTERAD
    public void renderWorldObjects(List<SolidObject> objects){
        assetManager.registerLocator(ASSET_MAP, ZipLocator.class);
        
        Node tempNode;
        
        for(int i = 0; i < objects.size(); i++){
            SolidObject object = objects.get(i);
            
            if(object instanceof Human){
                
                Human instance = (Human)object;
                
                tempNode = new Node("Human");
                
                final float rotation = (float)Math.random()*(float)Math.PI*2f;
                tempNode.rotate(0f, rotation, 0f);
                
                tempNode.attachChild(renderHuman(instance));
                
            }else{
                tempNode = new Node("Solid");
                
                tempNode.attachChild(renderSolidObject(object));
            }
            
            tempNode.setLocalTranslation(object.getPosition().getX(), 
                                            object.getPosition().getY(), 
                                               object.getPosition().getZ());
            objectNodes.add(tempNode);
        }
        
    }
    
    private Spatial renderHuman(Human human){
        Spatial h;
        
        h = getRandomGenderHuman();
        h.scale(human.getWidth(), human.getHeight(), human.getLength());
        
        h.setMaterial(humanMaterial);
        
        return h;
    }
    
    private Geometry renderSolidObject(SolidObject solidobject){
        Box b = new Box(solidobject.getWidth(), 
                            solidobject.getHeight(), 
                                solidobject.getLength());
        Geometry g = new Geometry("Box", b);
        g.setMaterial(solidobjectMaterial);
        
        return g;
    }
    
    //Returns a male or female human spatial by random
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
