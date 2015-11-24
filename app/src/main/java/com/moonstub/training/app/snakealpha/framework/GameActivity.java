package com.moonstub.training.app.snakealpha.framework;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.moonstub.training.app.snakealpha.GameState;
import com.moonstub.training.app.snakealpha.input.GameInput;

public abstract class GameActivity extends AppCompatActivity {

    private static final String LOG_TAG = GameActivity.class.getSimpleName();

    private GameState mGameState = GameState.INIT;

    // Main Buffer this is what drawn by RenderView
    private Bitmap mGameBuffer;

    //GameLoop calls update and repaints the currentScreen
    private RenderView mRenderView;
    //Current Screen where the game play logic is stored and events are handled
    private GameScreen mCurrentScreen;
    //Stores Touch Events that screen will process
    private GameInput mGameInput;
    //Access to background music
    //private GameMusic mGameMusic;
    //Access to sound affects
    //private GameSound mGameSound;
    //Access to GameAudio mGameAudio
    //Provides Interface for in Game Image Creating
    private GameGraphics mGameGraphics;
    //Provides File Access
    private GameIO mGameIO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Will Cause Error if calling after call to super.
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Todo Lock Orientation to Landscape Mode if game Requires

        //Set Screen Dimensions
        int width = GameSettings.LAND_SCREEN_WIDTH;
        int height = GameSettings.LAND_SCREEN_HEIGHT;

        mGameBuffer = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        //TODO: Scale screen Dimensions to local screen Dimensions
        float scaleX;
        float scaleY;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getRealSize(size);
            scaleX = (float) width / size.x;
            scaleY = (float) height / size.y;
        } else {
            DisplayMetrics dmSize = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dmSize);
            scaleX = (float) width / dmSize.widthPixels;
            scaleY = (float) height / dmSize.heightPixels;
        }

        //Set RenderView -> GameLoop
        mRenderView = new RenderView(this, mGameBuffer);
        //Set Graphics Class
        mGameGraphics = new GameGraphics(getAssets(), mGameBuffer);
        // File IO Class used for reading and writing files (i.e. save files, game levels)
        mGameIO = new GameIO(this);
        //mGameAudio = new GameAudio(getAssets());
        //Gather simple inputs UP,DOWN,DRAG
        mGameInput = new GameInput(this, mRenderView, scaleX, scaleY);
        //Initialize First Screen
        mCurrentScreen = initializeScreen();

        //Set the Custom View
        setContentView(mRenderView);
    }

    protected abstract GameScreen initializeScreen();

    public GameScreen getCurrentScreen() {
        return mCurrentScreen;
    }

    public Bitmap getBufferGraphics() {
        return mGameBuffer;
    }

    public GameInput getGameInput(){
        return mGameInput;
    }

    public GameIO getGameIO(){
        return mGameIO;
    }

    public GameGraphics getGameGraphics(){
        return mGameGraphics;
    }

    public void setCurrentScreen(GameScreen screen){
        if (screen == null){
            throw new IllegalArgumentException("Screen is Empty");

        }
        //stop current screen start and set new Screen
        mCurrentScreen.pause();
        //Set all screen variables to null
        // Garbage Collect Items
        mCurrentScreen.dispose();
        screen.resume();
        screen.update(0);
        mCurrentScreen = screen;

    }

    @Override
    protected void onResume() {
        super.onResume();
        mCurrentScreen.resume();
        mRenderView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mRenderView.pause();
        mCurrentScreen.pause();

        if(isFinishing()){
            mCurrentScreen.dispose();
        }

    }

    @Override
    public void onBackPressed() {
        if(getCurrentScreen().onBackPressed()) {
            super.onBackPressed();
        }

    }

    public GameState getGameState(){
        return mGameState;
    }
    public void setGameState(GameState gameState){
        mGameState = gameState;
    }

}
