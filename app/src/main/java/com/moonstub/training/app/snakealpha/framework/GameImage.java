package com.moonstub.training.app.snakealpha.framework;

import android.graphics.Bitmap;
import com.moonstub.training.app.snakealpha.framework.GameGraphics.ImageFormat;

public class GameImage {

    Bitmap mBitmap;
    GameGraphics.ImageFormat mImageFormat;

    public GameImage(Bitmap bitmap, ImageFormat format){
        mBitmap = bitmap;
        mImageFormat = format;
    }

    public int getWidth(){return mBitmap.getWidth();}
    public int getHeight(){return mBitmap.getHeight();}
    public ImageFormat getFormat(){return mImageFormat;}
    public void dispose(){mBitmap.recycle();}

    public Bitmap getImage() {
        return mBitmap;
    }
}
