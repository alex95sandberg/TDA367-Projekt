/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.core;

import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import com.jme3.scene.control.CameraControl;

/**
 *
 * @author Alexander
 */
public class OldPlayer {
    
    private CameraNode camNode;
    private Node playerNode = new Node();
    
    public OldPlayer(Camera cam){
        
        camNode = new CameraNode("Camera Node", cam);
        camNode.setControlDir(CameraControl.ControlDirection.SpatialToCamera);
        playerNode.attachChild(camNode);
        camNode.setLocalTranslation(new Vector3f(0, 0, -10));
        camNode.lookAt(playerNode.getLocalTranslation(), Vector3f.UNIT_Y); 
        
        playerNode.setLocalTranslation(-2, 0, -2);
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
