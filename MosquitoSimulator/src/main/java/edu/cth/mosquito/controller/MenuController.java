/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.controller;

import com.jme3.app.Application;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.niftygui.NiftyJmeDisplay;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.controls.button.builder.ButtonBuilder;
import de.lessvoid.nifty.screen.DefaultScreenController;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import edu.cth.mosquito.main.MosquitoSimulator;

/**
 *
 * @author Anton
 */
public class MenuController extends AbstractAppState implements ScreenController{
    
    MosquitoSimulator ms;
    NiftyJmeDisplay ourScreen;
    Nifty nifty;
    AppStateManager asm;
    
    @Override
    public void initialize(AppStateManager asm, Application app){
        
        super.initialize(asm, app);
        ms = (MosquitoSimulator)app;
        
        ourScreen = new NiftyJmeDisplay(app.getAssetManager(), app.getInputManager(), app.getAudioRenderer(), app.getGuiViewPort());
       
        nifty = ourScreen.getNifty();
        
        app.getGuiViewPort().addProcessor(ourScreen);
        
        nifty.loadStyleFile("nifty-default-styles.xml");
        nifty.loadControlFile("nifty-default-controls.xml");
        
        nifty.addScreen("Screen_ID", new ScreenBuilder("Hello Nifty Screen"){{
            controller(new DefaultScreenController());
            
            layer(new LayerBuilder("Layer_ID"){{
                childLayoutVertical();
                
                
                panel(new PanelBuilder("Panel_ID"){{
                    childLayoutCenter();
                    
                    control(new ButtonBuilder("Button_ID", "HelloNifty"){{
                        alignCenter();
                        valignCenter();
                        height("35%");
                        width("15%");
                    }});
                }});
            }});
        }}.build(nifty));
        
        nifty.gotoScreen("Screen_ID");
    }

    public void cleanUp(){
        
    }
    
    @Override
    public void bind(Nifty nifty, Screen screen) {
        
    }

    @Override
    public void onStartScreen() {
        
    }

    @Override
    public void onEndScreen() {
        
    }
    

    
}
