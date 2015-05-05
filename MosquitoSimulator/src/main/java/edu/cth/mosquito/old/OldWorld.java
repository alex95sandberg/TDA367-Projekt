/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.old;

import edu.cth.mosquito.old.OldSolidObject;
import com.jme3.asset.AssetManager;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.collision.shapes.BoxCollisionShape;
import com.jme3.bullet.control.GhostControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import java.util.List;

/**
 *
 * @author Alexander
 */
public class OldWorld {
    
    private Node worldNode = new Node();
    private List<OldSolidObject> worldObjects;
    
    public OldWorld(AssetManager assetManager, PhysicsSpace physicsSpace){
           
        Box b = new Box(1, 1, 1); // create cube shape
        Material mat1 = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
        
        Geometry geom1 = new Geometry("Box", b);  // create cube geometry from the shape

        mat1.setColor("Color", ColorRGBA.Blue);   // set color of material to blue
        mat1.getAdditionalRenderState().setWireframe(true);
        geom1.setMaterial(mat1);                   // set the cube's material
        
        
        Box b2 = new Box(2, 2, 1);
        Material mat2 = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");
        
        Geometry geom2 = new Geometry("Box2", b2);

        mat2.setColor("Color", ColorRGBA.Gray);
        geom2.setMaterial(mat2); 
        geom2.setLocalTranslation(4, 0, 6);
        
        Geometry geom3 = new Geometry("Box3", b);  
        Material mat3 = new Material(assetManager,
                "Common/MatDefs/Misc/Unshaded.j3md");  
        mat3.setColor("Color", ColorRGBA.Green);  
        mat3.getAdditionalRenderState().setWireframe(true);
        geom3.setMaterial(mat3);                   
        geom3.setLocalTranslation(2, 0, 2);
        
        worldNode.attachChild(geom1);
        worldNode.attachChild(geom2);
        worldNode.attachChild(geom3);
    }
    
    public Node getWorldNode(){
        return worldNode;
    }
}