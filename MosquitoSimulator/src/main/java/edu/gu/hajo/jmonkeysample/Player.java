/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gu.hajo.jmonkeysample;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.control.CameraControl;
import com.jme3.scene.shape.Box;

/**
 *
 * @author Alexander
 */
public class Player {
    
    private CameraNode camNode;
    private Node playerNode = new Node();
    
    public Player(Camera cam, AssetManager assetManager){
        
        Mosquito mosquito = new Mosquito(assetManager);
        //create the camera Node
        camNode = new CameraNode("Camera Node", cam);
        //This mode means that camera copies the movements of the targetNode:
        camNode.setControlDir(CameraControl.ControlDirection.SpatialToCamera);
        //Attach the camNode to the targetNode:
        playerNode.attachChild(camNode);
        //Move camNode, e.g. behind and above the targetNode:
        camNode.setLocalTranslation(new Vector3f(0, 0, -10));
        //Rotate the camNode to look at the targetNode:
        camNode.lookAt(playerNode.getLocalTranslation(), Vector3f.UNIT_Y);
        
        Box b = new Box(1, 1, 1); // create cube shape
        Geometry geom = new Geometry("Box", b);  // create cube geometry from the shape
        Material mat = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
        mat.setColor("Color", ColorRGBA.Red);   // set color of material to blue
        geom.setMaterial(mat);                   // set the cube's material
        
        
        
        playerNode.setLocalTranslation(-2, 0, -2);
        playerNode.attachChild(mosquito.getSpatial());
          // make the cube appear in the scene
    }
    
    public void movePlayerNode(Vector3f direction){
        
        playerNode.move(direction);
        
    }
    
    public void rotatePlayerNode(float x, float y, float z){
        
        playerNode.rotate(x, y, z);
        
    }
    
    public Node getPlayerNode(){
        return playerNode;
    }
}
