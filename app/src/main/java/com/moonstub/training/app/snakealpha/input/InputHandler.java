package com.moonstub.training.app.snakealpha.input;

import android.view.View.OnTouchListener;
import java.util.List;

public interface InputHandler extends OnTouchListener {

    boolean isDown(int pointer);

    int getX(int pointer);

    int getY(int pointer);

    List<TouchEvent.TouchEvents> getTouchEvents();
}
