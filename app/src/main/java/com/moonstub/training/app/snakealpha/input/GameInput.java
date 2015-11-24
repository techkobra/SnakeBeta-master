package com.moonstub.training.app.snakealpha.input;

import android.content.Context;
import android.view.View;

import com.moonstub.training.app.snakealpha.framework.GameActivity;
import com.moonstub.training.app.snakealpha.framework.RenderView;

import java.util.List;

/**
 * Created by Micah on 11/4/2015.
 */
public class GameInput implements TouchEvent {

    InputHandler mInputHandler;

    public GameInput(Context context, View view){
        mInputHandler = new GameInputHandler(view);
    }

    public GameInput(GameActivity gameActivity, RenderView renderView, float scaleX, float scaleY) {
        mInputHandler = new GameInputHandler(renderView, scaleX,scaleY);

    }

    public boolean isDown(int pointer){
        return mInputHandler.isDown(pointer);
    }

    public int getX(int pointer){
        return mInputHandler.getX(pointer);
    }

    public int getY(int pointer){
        return mInputHandler.getY(pointer);
    }

    public List<TouchEvents> getTouchEvents(){
        return mInputHandler.getTouchEvents();
    }

//    List<MotionEvent> mEventList;
//
//    public GameInput(GameActivity game, RenderView view){
//        view.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return false;
//            }
//        });
//    }


}
