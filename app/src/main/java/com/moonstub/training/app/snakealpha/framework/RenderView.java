package com.moonstub.training.app.snakealpha.framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Micah on 11/3/2015.
 */
public class RenderView extends SurfaceView implements Runnable {

    private Bitmap mBuffer;
    private GameActivity mGame;
    private Thread mainThread;
    private boolean mIsRunning;

    private SurfaceHolder mHolder;

    public RenderView(GameActivity game, Bitmap buffer) {
        super(game);
        mGame = game;
        mBuffer = buffer;
        mHolder = getHolder();
    }

    public void pause(){
        try {
            mIsRunning = false;
            mainThread.join();
        } catch (InterruptedException e) {
            Log.e("Error", "Error Stoping Thread", e);
        }
    }

    public void resume(){
        Log.v("log", "called Resume");
        mainThread = new Thread(this);
        mIsRunning = true;
        mainThread.start();
    }

    @Override
    public void run() {

        Thread currentThread = Thread.currentThread();
        Rect dst = new Rect();
        long currentNano = System.nanoTime();
        while(mIsRunning && currentThread == mainThread){

            if(!mHolder.getSurface().isValid()){continue;}

            float delta = (System.nanoTime() - currentNano) / 10000000.000f;
            currentNano = System.nanoTime();
            //Log.e("DELTA TIME: ", delta +"");
            mGame.getCurrentScreen().update(delta);
            mGame.getCurrentScreen().draw(delta);

            Canvas canvas = mHolder.lockCanvas();
            canvas.getClipBounds(dst);
            canvas.drawBitmap(mBuffer, null, dst, null);

            mHolder.unlockCanvasAndPost(canvas);

        }
    }
}
