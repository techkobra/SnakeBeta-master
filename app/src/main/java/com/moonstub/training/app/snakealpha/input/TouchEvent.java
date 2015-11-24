package com.moonstub.training.app.snakealpha.input;

import java.util.List;

/**
 * Created by Micah on 11/4/2015.
 */
public interface TouchEvent {

    public static class TouchEvents {
        public static final int TOUCH_DOWN = 0;
        public static final int TOUCH_DRAGGED = 1;
        public static final int TOUCH_UP = 2;
        public int type;

        public int x, y;
        public int pointer;
    }

    boolean isDown(int pointer);

    int getX(int pointer);

    int getY(int pointer);

    List<TouchEvents> getTouchEvents();
}
