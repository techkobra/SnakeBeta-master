package com.moonstub.training.app.snakealpha.Screens;

import com.moonstub.training.app.snakealpha.GameAssets;
import com.moonstub.training.app.snakealpha.GameState;
import com.moonstub.training.app.snakealpha.framework.GameActivity;
import com.moonstub.training.app.snakealpha.framework.GameGraphics;
import com.moonstub.training.app.snakealpha.framework.GameScreen;
import com.moonstub.training.app.snakealpha.input.TouchEvent;

import java.util.ArrayList;

public class LoadingScreen extends GameScreen {
    float timeElapsed = 0;

    public LoadingScreen(GameActivity game) {
        super(game);
    }

    @Override
    public void init() {
        //Load assets Used in Next Game Screen
        GameAssets.SplashImage = getGameGraphics()
                .newImage("full_splash.png", GameGraphics.ImageFormat.ARGB8888);
        GameAssets.PlayBtnMenuDefault = getGameGraphics()
                .newImage("menu_play_default.png", GameGraphics.ImageFormat.ARGB8888);
        GameAssets.PlayBtnMenuPressed = getGameGraphics()
                .newImage("menu_play_pressed.png", GameGraphics.ImageFormat.ARGB8888);
        mGameState = GameState.RUNNING;
    }

    @Override
    public void update(float delta) {
        ArrayList<TouchEvent.TouchEvents> events = (ArrayList<TouchEvent.TouchEvents>) getGameActivity()
                .getGameInput().getTouchEvents();

        switch (mGameState){
            case INIT:
                updateInit();
                break;
            case PAUSED:
                updatePaused(events);
                break;
            case RESUME:
                updateResume(events);
                break;
            case RUNNING:
                updateRunning(events, delta);
                break;
            case  NEXT:
                updateNext();
                break;
            default:
                throw new IllegalArgumentException("GameState Error");
        }

    }

    private void updateNext() {
        getGameActivity().setCurrentScreen(new MainMenuScreen(getGameActivity()));
    }

    private void updateRunning(ArrayList<TouchEvent.TouchEvents> events, float delta) {
        //Artificially Slow Down Game to Fake network load time
        //TODO add fade in animations for future builds
        if(timeElapsed >= 0) {
            mGameState = GameState.NEXT;
        }
        timeElapsed += delta;
    }

    private void updateResume(ArrayList<TouchEvent.TouchEvents> events) {

    }

    private void updatePaused(ArrayList<TouchEvent.TouchEvents> events) {

    }

    private void updateInit() {

    }

    @Override
    public void draw(float delta) {

        getGameGraphics().clearScreen(0);
        getGameGraphics().drawImage(GameAssets.SplashImage, 0, 0);

    }

    @Override
    public void resume() {
    if(mGameState == GameState.PAUSED){
        mGameState = GameState.RESUME;
    }
    }

    @Override
    public void pause() {
        if(mGameState == GameState.PAUSED){
            mGameState = GameState.RESUME;
        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean onBackPressed() {
        return true;
    }
}
