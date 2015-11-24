package com.moonstub.training.app.snakealpha;

import android.graphics.Rect;
import android.view.View;

import com.moonstub.training.app.snakealpha.framework.GameActivity;
import com.moonstub.training.app.snakealpha.framework.GameGraphics;
import com.moonstub.training.app.snakealpha.framework.GameImage;
import com.moonstub.training.app.snakealpha.input.TouchEvent;

public class MenuItem{//} extends View {

    //Rect bounds;
    int x,y;
    GameImage mPressedImage;
    GameImage mDefaultImage;
    boolean showDefaultImage = true;

    public MenuItem(GameActivity game, int top,int right){
        //super(game);
        x = top;
        y = right;
    }

    public void draw(GameGraphics g){
        if(showDefaultImage) {
            g.drawImage(mDefaultImage, x, y);
        }else {
            g.drawImage(mPressedImage,x,y);
        }
    }

    public boolean isTouched(){
        return showDefaultImage;
    }
    public void setShowDefaultImage(boolean truth){
        showDefaultImage = truth;
    }

    public boolean checkCollision(int x, int y){
        return getBounds().contains(x,y);
    }

    public void touched(TouchEvent.TouchEvents event){
        if(checkCollision(event.x, event.y)){
            setShowDefaultImage(event.type == TouchEvent.TouchEvents.TOUCH_UP || event.type == TouchEvent.TouchEvents.TOUCH_DRAGGED);
        }
    }

    public Rect getBounds(){
        return new Rect(x,y,x+mDefaultImage.getWidth(),y+mDefaultImage.getHeight());
    }
    public void LoadImagesFromFile(GameActivity game, String defaultFileName, String depressedFilename){
        mDefaultImage = game.getGameGraphics().newImage(defaultFileName, GameGraphics.ImageFormat.ARGB8888);
        mPressedImage = game.getGameGraphics().newImage(depressedFilename, GameGraphics.ImageFormat.ARGB8888);
    }

    public void setImages(GameImage defaultImage, GameImage depressedImage){
        mDefaultImage = defaultImage;
        mPressedImage = depressedImage;
    }

    public int centerX(){
        return getBounds().centerX();
    }
    public int centerY(){
        return getBounds().centerY();
    }

    public void setPressedImage(GameImage pressedImage){
        mPressedImage = pressedImage;
    }

    public void setDefaultImage(GameImage defaultImage){
        mDefaultImage = defaultImage;
    }
}
