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
    private static final String FONTPATH = "assets/font.fnt";
    private static final String IMAGEPATH = "assets/mosquitoTwo.jpg";
    private static final String ASSETS = "assets.zip";
    
    private static final String START = "start";
    private static final String STARTGAME = "startGame";
    private static final String STARTGAMETEXT = "Start Game";
    private static final String STARTHEADING = "startHeading";
    
    private static final String NIFTYSCREEN = "NiftyScree";
    private static final String BACKGROUND = "background";
    private static final String FOREGROUND = "foreground";
    private static final String HUD = "hud";
    
    private static final String HEIGHT1 = "100%";
    private static final String WIDTH1 = "100%";
    private static final String HEIGHT2 = "25%";
    private static final String WIDTH2 = "75%";


    private static final String HIGHSCORE = "highscore";
    private static final String HIGHSCORETEXT = "Highscore";
    private static final String NBRONEHIGHSCORE = "numberOne";
    private static final String NBRTWOHIGHSCORE = "numberTwo";
    private static final String NBRTHREEHIGHSCORE = "numberThree";
    private static final String NBRFOURHIGHSCORE = "numberFour";
    private static final String NBRFIVEHIGHSCORE = "numberFive";
    
    private static final String EXIT = "exit";
    private static final String EXITINTERACT = "exitGame()";
    private static final String EXITGAMETEXT = "Exit Game";
    private static final String BACKTOMENUTEXT = "Back to menu";
    private static final String MOSQUITOSIMTEXT = "Mosquito Simulator";
    
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
        
        app.getAssetManager().registerLocator(ASSETS, ZipLocator.class);
        nifty.addScreen(START, new ScreenBuilder(NIFTYSCREEN){{
            controller(controller);
            
            layer(new LayerBuilder(BACKGROUND){{
                childLayoutCenter();
                
                image(new ImageBuilder(){{            
                    filename(IMAGEPATH);
                    height(HEIGHT1);
                    width(WIDTH1);
                
                }});
            }});
            
            layer(new LayerBuilder(FOREGROUND){{
                childLayoutVertical();
                
                panel(new PanelBuilder(STARTHEADING){{
                    alignCenter();
                    childLayoutCenter();
                    width(WIDTH2);
                    height(HEIGHT2);
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text(MOSQUITOSIMTEXT);
                        font(FONTPATH);
                        
                    }});
                }});
                
                panel(new PanelBuilder(STARTGAME){{
                    alignCenter();
                    childLayoutCenter();
                    width(WIDTH2);
                    height(HEIGHT2);
                    visibleToMouse(true);
                    interactOnClick(STARTGAME+"()");
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text(STARTGAMETEXT);
                        font(FONTPATH);
                        
                    }});
                }});
                
                panel(new PanelBuilder(HIGHSCORE){{
                    alignCenter();
                    childLayoutCenter();
                    width(WIDTH2);
                    height(HEIGHT2);
                    visibleToMouse(true);
                    interactOnClick("switchScreen(highscore)");
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text(HIGHSCORETEXT);
                        font(FONTPATH);
                        
                    }});
                }});
                
                panel(new PanelBuilder(EXIT){{
                    alignCenter();
                    childLayoutCenter();
                    width(WIDTH2);
                    height(HEIGHT2);
                    visibleToMouse(true);
                    interactOnClick(EXITINTERACT);
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text(EXITGAMETEXT);
                        font(FONTPATH);
                        
                    }});
                }});
            }});
        }}.build(nifty));
        
        nifty.addScreen(HIGHSCORE, new ScreenBuilder(NIFTYSCREEN){{
            controller(controller);
            
            layer(new LayerBuilder(BACKGROUND){{
                childLayoutCenter();
                
                image(new ImageBuilder(){{    
                    filename(IMAGEPATH);
                    height(HEIGHT1);
                    width(WIDTH1);
                
                }});
            }});
            
            layer(new LayerBuilder(FOREGROUND){{
                childLayoutVertical();
                
                panel(new PanelBuilder("highscoreText"){{
                    alignCenter();
                    childLayoutCenter();
                    width(WIDTH2);
                    height("14%");
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text(HIGHSCORETEXT);
                        font(FONTPATH);
                        
                    }});
                }});
                
                panel(new PanelBuilder(NBRONEHIGHSCORE){{
                    alignCenter();
                    childLayoutCenter();
                    width(WIDTH2);
                    height("14%");
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text("1. ${CALL.getPlayerScore(1)}");
                        font(FONTPATH);
                        
                    }});
                }});
                
                panel(new PanelBuilder(NBRTWOHIGHSCORE){{
                    alignCenter();
                    childLayoutCenter();
                    width(WIDTH2);
                    height("14%");
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text("2. ${CALL.getPlayerScore(2)}");
                        font(FONTPATH);
                        
                    }});
                }});
                
                panel(new PanelBuilder(NBRTHREEHIGHSCORE){{
                    alignCenter();
                    childLayoutCenter();
                    width(WIDTH2);
                    height("14%");
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text("3. ${CALL.getPlayerScore(3)}");
                        font(FONTPATH);
                        
                    }});
                }});
                
                panel(new PanelBuilder(NBRFOURHIGHSCORE){{
                    alignCenter();
                    childLayoutCenter();
                    width(WIDTH2);
                    height("14%");
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text("4. ${CALL.getPlayerScore(4)}");
                        font(FONTPATH);
                        
                    }});
                }});
                
                panel(new PanelBuilder(NBRFIVEHIGHSCORE){{
                    alignCenter();
                    childLayoutCenter();
                    width(WIDTH2);
                    height("14%");
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text("5. ${CALL.getPlayerScore(5)}");
                        font(FONTPATH);
                        
                    }});
                }});
                
                panel(new PanelBuilder("exiGame"){{
                    alignCenter();
                    childLayoutCenter();
                    width(WIDTH2);
                    height("14%");
                    visibleToMouse(true);
                    interactOnClick("switchScreen(start)");
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text(BACKTOMENUTEXT);
                        font(FONTPATH);
                        
                    }});
                }});
            }});
        }}.build(nifty));
        
        nifty.addScreen("pauseMenu", new ScreenBuilder("PauseMenu"){{
            controller(controller);
            
            layer(new LayerBuilder(BACKGROUND){{
                childLayoutCenter();
                
                image(new ImageBuilder(){{    
                    filename(IMAGEPATH);
                    height(HEIGHT1);
                    width(WIDTH1);
                
                }});
            
            }});
            
            layer(new LayerBuilder(FOREGROUND){{
                childLayoutVertical();
                
                panel(new PanelBuilder("pauseMenuHeading"){{
                    alignCenter();
                    childLayoutCenter();
                    width(WIDTH2);
                    height("50%");
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text("game is paused");
                        font(FONTPATH);
                        
                    }});
                    
                }});
                
                panel(new PanelBuilder("pauseMenuHeading2"){{
                    alignCenter();
                    childLayoutCenter();
                    width(WIDTH2);
                    height(HEIGHT2);
                    interactOnClick("returnFromPause()");
                    visibleToMouse(true);
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text("Return to game");
                        font(FONTPATH);
                        
                    }});
                    
                }});
                
                panel(new PanelBuilder("pauseMenuHeading2"){{
                    alignCenter();
                    childLayoutCenter();
                    width(WIDTH2);
                    height(HEIGHT2);
                    interactOnClick(EXITINTERACT);
                    visibleToMouse(true);
                    
                    text(new TextBuilder(){{
                        valignCenter();
                        alignCenter();
                        text(EXITGAMETEXT);
                        font(FONTPATH);
                        
                    }});
                    
                }});
            }});
        
        }}.build(nifty));
        
        nifty.addScreen(HUD, new ScreenBuilder("ingameScreen"){{
            controller(controller);
        
            layer(new LayerBuilder(HUD){{
                childLayoutVertical();
                
            }});
            
        }}.build(nifty));
        
        nifty.gotoScreen(START);
        
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
        
        nifty.gotoScreen(HUD);
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
        
        nifty.gotoScreen(HUD);
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
