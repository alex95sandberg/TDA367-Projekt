/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cth.mosquito.controller;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
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
import de.lessvoid.nifty.elements.Element;
import de.lessvoid.nifty.elements.render.TextRenderer;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 *
 * @author Anton
 */
public class MenuController extends AbstractAppState implements ScreenController{
    
    private SimpleApplication rootApp;
    private NiftyJmeDisplay ourScreen;
    private Nifty nifty;
    private List<Integer> highscore;
    private MenuController controller = this;
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
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
    
    private static final String NBRONE = "nbrOne";
    private static final String NBRTWO = "nbrTwo";
    private static final String NBRTHREE = "nbrThree";
    private static final String NBRFOUR = "nbrFour";
    private static final String NBRFIVE = "nbrFive";
    
    private static final String EXIT = "exit";
    private static final String EXITINTERACT = "exitGame()";
    private static final String EXITGAMETEXT = "Exit Game";
    private static final String BACKTOMENUTEXT = "Back to menu";
    private static final String MOSQUITOSIMTEXT = "Mosquito Simulator";
    
    private Element placeOne;
    private Element placeTwo;
    private Element placeThree;
    private Element placeFour;
    private Element placeFive;
    
    public MenuController(List<Integer> highscore){
        
        this.highscore = highscore;
        
    }
    
    @Override
    public void initialize(AppStateManager asm, Application app){
        
        super.initialize(asm, app);
        rootApp = (SimpleApplication)app;
        
        ourScreen = new NiftyJmeDisplay(app.getAssetManager(), app.getInputManager(), app.getAudioRenderer(), app.getGuiViewPort());
       
        nifty = ourScreen.getNifty();
        
        app.getGuiViewPort().addProcessor(ourScreen);
        
        app.getAssetManager().registerLocator(ASSETS, ZipLocator.class);
        nifty.addScreen(START, new ScreenBuilder("startScreen"){{
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
        
        nifty.addScreen(HIGHSCORE, new ScreenBuilder("highscoreScreen"){{
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
                
                panel(new PanelBuilder(){{
                    alignCenter();
                    childLayoutCenter();
                    width(WIDTH2);
                    height("14%");
                    
                    text(new TextBuilder(){{
                        id(NBRONE);
                        valignCenter();
                        alignCenter();
                        text("");
                        font(FONTPATH);
                        
                    }});
                }});
                
                panel(new PanelBuilder(){{
                    alignCenter();
                    childLayoutCenter();
                    width(WIDTH2);
                    height("14%");
                    
                    text(new TextBuilder(){{
                        id(NBRTWO);
                        valignCenter();
                        alignCenter();
                        text("");
                        font(FONTPATH);
                        
                    }});
                }});
                
                panel(new PanelBuilder(){{
                    alignCenter();
                    childLayoutCenter();
                    width(WIDTH2);
                    height("14%");
                    
                    text(new TextBuilder(){{
                        id(NBRTHREE);
                        valignCenter();
                        alignCenter();
                        text("");
                        font(FONTPATH);
                        
                    }});
                }});
                
                panel(new PanelBuilder(){{
                    alignCenter();
                    childLayoutCenter();
                    width(WIDTH2);
                    height("14%");
                    
                    text(new TextBuilder(){{
                        id(NBRFOUR);
                        valignCenter();
                        alignCenter();
                        text("");
                        font(FONTPATH);
                        
                    }});
                }});
                
                panel(new PanelBuilder(){{
                    alignCenter();
                    childLayoutCenter();
                    width(WIDTH2);
                    height("14%");
                    
                    text(new TextBuilder(){{
                        id(NBRFIVE);
                        valignCenter();
                        alignCenter();
                        text("");
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
        
        nifty.addScreen("pauseMenu", new ScreenBuilder("pauseMenu"){{
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

    @Override
    public void cleanup(){
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
    
    private void initElements(){
        placeOne = nifty.getCurrentScreen().findElementByName(NBRONE);
        placeTwo = nifty.getCurrentScreen().findElementByName(NBRTWO);
        placeThree = nifty.getCurrentScreen().findElementByName(NBRTHREE);
        placeFour = nifty.getCurrentScreen().findElementByName(NBRFOUR);
        placeFive = nifty.getCurrentScreen().findElementByName(NBRFIVE);
    }
    
    public void startGame(){
        
        nifty.gotoScreen(HUD);
        pcs.firePropertyChange("startGame", 0, 1);
        
    }
    
    public void switchScreen(String id){
        
        nifty.gotoScreen(id);
        
    }
    
    public void exitGame(){
        
        rootApp.stop();
        
    }
    
    public void setHighscore(List<Integer> highscore){
        
        this.highscore = highscore;
        
    }
    
    public void returnFromPause(){
        
        nifty.gotoScreen(HUD);
        pcs.firePropertyChange("unPause", 0, 1);
        
    }
    
    public String getPlayerScore(int index){
        
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
    
    private void setHighscoreText(){
        placeOne.getRenderer(TextRenderer.class).setText("1. " + getPlayerScore(1));
        placeTwo.getRenderer(TextRenderer.class).setText("2. " + getPlayerScore(2));
        placeThree.getRenderer(TextRenderer.class).setText("3. " + getPlayerScore(3));
        placeFour.getRenderer(TextRenderer.class).setText("4. " + getPlayerScore(4));
        placeFive.getRenderer(TextRenderer.class).setText("5. " + getPlayerScore(5));
    }
    
    @Override
    public void update(float tpf){
        
        if(nifty.getCurrentScreen().getScreenId().equals("highscoreScreen")){
            initElements();
            setHighscoreText();
        }
        
    }
    
    public void addObserver(PropertyChangeListener observer){
        pcs.addPropertyChangeListener(observer);
        
    }
    
    public void removeObserver(PropertyChangeListener observer){
        pcs.removePropertyChangeListener(observer);
    }
}
