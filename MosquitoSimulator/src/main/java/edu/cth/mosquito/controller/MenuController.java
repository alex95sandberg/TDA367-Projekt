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
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import edu.cth.mosquito.main.MosquitoSimulator;
import java.util.List;

/**
 *
 * @author Anton
 */
public class MenuController extends AbstractAppState implements ScreenController{
    
    private MosquitoSimulator ms;
    private NiftyJmeDisplay ourScreen;
    private Nifty nifty;
    private List<Integer> highscore;
    
    public MenuController(List<Integer> highscore){
        
        this.highscore = highscore;
        
    }
    
    @Override
    public void initialize(AppStateManager asm, Application app){
        
        super.initialize(asm, app);
        ms = (MosquitoSimulator)app;
        
        app.getAssetManager().registerLocator("assets.zip", ZipLocator.class);
        ourScreen = new NiftyJmeDisplay(app.getAssetManager(), app.getInputManager(), app.getAudioRenderer(), app.getGuiViewPort());
       
        nifty = ourScreen.getNifty();
        
        app.getGuiViewPort().addProcessor(ourScreen);
        
        nifty.fromXml("assets/mainMenu.xml", "start", this);
        
    }

    public void cleanUp(){
        nifty.exit();
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
    
    public void startGame(){
        
        nifty.exit();
        ms.reset();
        ms.initGUI();
        ms.initAudio();
        
    }
    
    public void switchScreen(String id){
        
        nifty.gotoScreen(id);
        
    }
    
    public void exitGame(){
        
        ms.stop();
        
    }
    
    public String getPlayerScore(String stringIndex){
        
        int index = Integer.parseInt(stringIndex);
        int size = highscore.size();
        
        if(this.highscore.isEmpty()){
            return "-";
        } else if (size == 1 && index == 1){
            return String.valueOf(highscore.get(size-index));
        } else if (size == 2 && index <= 2){
            return String.valueOf(highscore.get(size-index));
        } else if (size == 3 && index <= 3){
            return String.valueOf(highscore.get(size-index));
        } else if (size == 4 && index <= 4){
            return String.valueOf(highscore.get(size-index));
        } else if (size == 5){
            return String.valueOf(highscore.get(size-index));
        }
        
        return "-";
    }
    
}
