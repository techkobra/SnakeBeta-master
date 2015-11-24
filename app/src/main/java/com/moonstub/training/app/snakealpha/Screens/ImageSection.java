package com.moonstub.training.app.snakealpha.Screens;

import android.graphics.Rect;

import com.moonstub.training.app.snakealpha.framework.GameGraphics;
import com.moonstub.training.app.snakealpha.framework.GameImage;
import com.moonstub.training.app.snakealpha.framework.GameSettings;

import java.util.ArrayList;

/**
 * Created by Micah on 11/8/2015.
 */
public class ImageSection extends Section{
    GameImage sectionImage;

    public ImageSection(GameImage image) {
        sectionImage = image;
    }

    public void draw(GameGraphics g){
        g.drawScaledImage(sectionImage,x,y, GameSettings.GRID_SIZE,GameSettings.GRID_SIZE);
    }

    public Rect bounds(){
        return new Rect(x,y,x+sectionImage.getWidth(),y+sectionImage.getHeight());
    }

    public void setX(int value){
        x = value;
    }

    public void setY(int value){
        y = value;
    }

    public void setPoint(int valueX, int valueY){
        x = valueX;
        y = valueY;
    }

    public boolean checkImageList(ArrayList<ImageSection> sectionList) {
        int len = sectionList.size();
        for (int i = 0; i < len; i++) {
            if (x == sectionList.get(i).x && y == sectionList.get(i).y) {
                return true;
            }
        }
        return false;
    }

}
