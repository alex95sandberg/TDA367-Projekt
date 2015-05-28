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
import de.lessvoid.nifty.builder.ImageBuilder;
import de.lessvoid.nifty.builder.LayerBuilder;
import de.lessvoid.nifty.builder.PanelBuilder;
import de.lessvoid.nifty.builder.ScreenBuilder;
import de.lessvoid.nifty.builder.TextBuilder;
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
    private MenuController controller = this;
    
    public MenuController(List<Integer> highscore){
        
        this.highscore = highscore;
        
    }
    
    @Override
    public void initialize(AppStateManager asm, Application app){
        
        super.initialize(asm, app);
        ms = (MosquitoSimulator)app;
        
        ourScreen = new NiftyJmeDisplay(app.getAssetManager(), app.getInputManager(), app.getAudioRenderer(), app.getGuiViewPort());
       
        nifty = ourScreen.getNifty();
        
        app.getGuiViewPort().addProcessor(ourScreen);
        
        app.getAssetManager().registerLocator("assets.zip", ZipLocator.class);
        nifty.addScreen("start", new ScreenBuilder("NiftyScree"){{
            controller(controller);
            
            layer(new LayerBuilder("background"){{
                childLayoutCenter();
                
                image(new ImageBuilder(){{
                    
                    filename("assets/mosquitoTwo.jpg");
                    height("100%");
                    width("100%");
                
                }});
            
            }});
            
            layer(new LayerBuilder("foreground"){{
                childLayoutVertical();
                
                panel(new PanelBuilder("startHeading"){{
                    alignCenter();
                    childLayoutCenter();
                    width("75%");
                    height("25%");
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text("Mosquito Simulator");
                        font("assets/font.fnt");
                        
                    }});
                    
                }});
                
                panel(new PanelBuilder("startGame"){{
                    alignCenter();
                    childLayoutCenter();
                    width("75%");
                    height("25%");
                    visibleToMouse(true);
                    interactOnClick("startGame()");
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text("Start Game");
                        font("assets/font.fnt");
                        
                    }});
                    
                }});
                
                panel(new PanelBuilder("highscore"){{
                    alignCenter();
                    childLayoutCenter();
                    width("75%");
                    height("25%");
                    visibleToMouse(true);
                    interactOnClick("switchScreen(highscore)");
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text("Highscore");
                        font("assets/font.fnt");
                        
                    }});
                    
                }});
                
                panel(new PanelBuilder("exit"){{
                    alignCenter();
                    childLayoutCenter();
                    width("75%");
                    height("25%");
                    visibleToMouse(true);
                    interactOnClick("exitGame()");
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text("Exit game");
                        font("assets/font.fnt");
                        
                    }});
                    
                }});
                
            }});
        
        }}.build(nifty));
        
        nifty.addScreen("highscore", new ScreenBuilder("NiftyScree"){{
            controller(controller);
            
            layer(new LayerBuilder("background"){{
                childLayoutCenter();
                
                image(new ImageBuilder(){{
                    
                    filename("assets/mosquitoTwo.jpg");
                    height("100%");
                    width("100%");
                
                }});
            
            }});
            
            layer(new LayerBuilder("foreground"){{
                childLayoutVertical();
                
                panel(new PanelBuilder("highscoreText"){{
                    alignCenter();
                    childLayoutCenter();
                    width("75%");
                    height("14%");
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text("Highscore");
                        font("assets/font.fnt");
                        
                    }});
                    
                }});
                
                panel(new PanelBuilder("numberOne"){{
                    alignCenter();
                    childLayoutCenter();
                    width("75%");
                    height("14%");
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text("1. ${CALL.getPlayerScore(1)}");
                        font("assets/font.fnt");
                        
                    }});
                    
                }});
                
                panel(new PanelBuilder("numberTwo"){{
                    alignCenter();
                    childLayoutCenter();
                    width("75%");
                    height("14%");
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text("2. ${CALL.getPlayerScore(2)}");
                        font("assets/font.fnt");
                        
                    }});
                    
                }});
                
                panel(new PanelBuilder("numberThree"){{
                    alignCenter();
                    childLayoutCenter();
                    width("75%");
                    height("14%");
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text("3. ${CALL.getPlayerScore(3)}");
                        font("assets/font.fnt");
                        
                    }});
                    
                }});
                
                panel(new PanelBuilder("numberFour"){{
                    alignCenter();
                    childLayoutCenter();
                    width("75%");
                    height("14%");
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text("4. ${CALL.getPlayerScore(4)}");
                        font("assets/font.fnt");
                        
                    }});
                    
                }});
                
                panel(new PanelBuilder("numberFive"){{
                    alignCenter();
                    childLayoutCenter();
                    width("75%");
                    height("14%");
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text("5. ${CALL.getPlayerScore(5)}");
                        font("assets/font.fnt");
                        
                    }});
                    
                }});
                
                panel(new PanelBuilder("exiGame"){{
                    alignCenter();
                    childLayoutCenter();
                    width("75%");
                    height("14%");
                    visibleToMouse(true);
                    interactOnClick("switchScreen(start)");
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text("Back to menu");
                        font("assets/font.fnt");
                        
                    }});
                    
                }});
            }});
        
        }}.build(nifty));
        
        nifty.addScreen("pauseMenu", new ScreenBuilder("PauseMenu"){{
            controller(controller);
            
            layer(new LayerBuilder("background"){{
                childLayoutCenter();
                
                image(new ImageBuilder(){{
                    
                    filename("assets/mosquitoTwo.jpg");
                    height("100%");
                    width("100%");
                
                }});
            
            }});
            
            layer(new LayerBuilder("foreground"){{
                childLayoutVertical();
                
                panel(new PanelBuilder("pauseMenuHeading"){{
                    alignCenter();
                    childLayoutCenter();
                    width("75%");
                    height("50%");
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text("game is paused");
                        font("assets/font.fnt");
                        
                    }});
                    
                }});
                
                panel(new PanelBuilder("pauseMenuHeading2"){{
                    alignCenter();
                    childLayoutCenter();
                    width("75%");
                    height("25%");
                    interactOnClick("returnFromPause()");
                    visibleToMouse(true);
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text("Return to game");
                        font("assets/font.fnt");
                        
                    }});
                    
                }});
                
                panel(new PanelBuilder("pauseMenuHeading2"){{
                    alignCenter();
                    childLayoutCenter();
                    width("75%");
                    height("25%");
                    interactOnClick("exitGame()");
                    visibleToMouse(true);
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text("Exit game");
                        font("assets/font.fnt");
                        
                    }});
                    
                }});
            }});
        
        }}.build(nifty));
        
        nifty.addScreen("hud", new ScreenBuilder("ingameScreen"){{
            controller(controller);
        
            layer(new LayerBuilder("hud"){{
                childLayoutVertical();
                
            }});
            
        }}.build(nifty));
        
        
        
        nifty.gotoScreen("start");
        
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
        
        nifty.gotoScreen("hud");
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
    
    public void setHighscore(List<Integer> highscore){
        
        this.highscore = highscore;
        
    }
    
    public void returnFromPause(){
        
        nifty.gotoScreen("hud");
        ms.initGUI();
        ms.initAudio();
        ms.returnFromPause(true);
        
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
        } else if (size > 5){
            return String.valueOf(highscore.get(size-index));
        }
        
        return "-";
    }
    
}
