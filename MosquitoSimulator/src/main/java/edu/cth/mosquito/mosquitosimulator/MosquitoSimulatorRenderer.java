/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.mosquitosimulator;

import com.jme3.asset.AssetManager;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Spatial;

/**
 *
 * @author Alexander
 */
public class MosquitoSimulatorRenderer {
    
    public MosquitoSimulatorRenderer(AssetManager assetManager){
        this.assetManager = assetManager;
        this.renderMosquito();
    }
    private AssetManager assetManager;
    private Spatial mosquito;
    
    private void renderMosquito(){
        assetManager.registerLocator("assets.zip", ZipLocator.class);
        mosquito = assetManager.loadModel("assets/mosquito.j3o");
        mosquito.rotate(0f, (float)Math.PI/2, 0f);
        mosquito.scale(0.1f);
        mosquito.setLocalTranslation(0, -2, 0);
        
        Material m = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        m.setColor("Color", ColorRGBA.Orange);
        
        mosquito.setMaterial(m);
    }
    
    public Spatial getMosquito(){
            return mosquito;
    }
}
