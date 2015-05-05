/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.view;

import com.jme3.asset.AssetManager;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.CameraControl;

/**
 *
 * @author Alexander
 */
public class MosquitoSimulatorRenderer {
    
    public MosquitoSimulatorRenderer(AssetManager assetManager, Camera cam){
        this.assetManager = assetManager;
        this.cam = cam;
        mosquitoNode = new Node();
        this.renderMosquito();
        this.renderRoom();
        cameraSetup();
    }
    private AssetManager assetManager;
    private Camera cam;
    private Spatial mosquito;
    private Spatial room;
    private CameraNode camNode;
    private Node mosquitoNode;
    
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
    
    private void renderRoom(){
        assetManager.registerLocator("assets.zip", ZipLocator.class);
        room = assetManager.loadModel("assets/room.j3o");
        room.scale(10,4,10);
        room.setLocalTranslation(0, 0, 0);
        
        Material m = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        m.setColor("Color", ColorRGBA.Yellow);
        m.getAdditionalRenderState().setWireframe(true);
        
        room.setMaterial(m);
    }
    
    public Node getMosquitoNode(){
            return mosquitoNode;
    }
    
    public Spatial getRoomSpatial(){
        return room;
    }
    
    public void renderEnergybar(){
        
    }
}
