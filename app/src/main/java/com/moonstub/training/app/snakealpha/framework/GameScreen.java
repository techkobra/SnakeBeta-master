package com.moonstub.training.app.snakealpha.framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.moonstub.training.app.snakealpha.GameState;

public abstract class GameScreen {

    public GameState mGameState;
    private int width;
    private int height;

    private GameActivity mGameActivity;
    private Bitmap mBackground;
    private Paint mPaint;
    private Canvas mCanvas;
    private GameGraphics mGameGraphics;

    public GameScreen(GameActivity game){
        mGameActivity = game;
        mGameGraphics = mGameActivity.getGameGraphics();
        width = mGameGraphics.getWidth();
        height = mGameGraphics.getHeight();
        mBackground = mGameActivity.getBufferGraphics();
        mPaint = new Paint();
        mCanvas = mGameGraphics.mCanvas;
        mGameState = GameState.INIT;
        init();


    }
    //LoadNeeded Assets;
    //Initialize needed Objects
    public abstract void init();
    //update objects
    public abstract void update(float delta);
    //draw objects
    public abstract void draw(float delta);
    //resume game screen
    public abstract void resume();
    // pause game screen
    public abstract void pause();
    //Set objects to null;
    public abstract void dispose();

    public GameActivity getGameActivity() {
        return mGameActivity;
    }

    public GameGraphics getGameGraphics(){
        return mGameGraphics;
    }

    public Bitmap getBackground() {
        return mBackground;
    }

    public Paint getPaint() {
        return mPaint;
    }

    public Canvas getCanvas() {
        return mCanvas;
    }

    public abstract boolean onBackPressed();
}
