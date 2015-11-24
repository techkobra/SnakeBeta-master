package com.moonstub.training.app.snakealpha.Screens;

import android.util.Log;

import com.moonstub.training.app.snakealpha.framework.GameIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Micah on 11/9/2015.
 */

public class LoadLevel {

    InputStream mInputStream;
    GameIO mGameIO;
    String stringLevel = null;

    public LoadLevel(GameIO IO){
        mGameIO = IO;
    }

    public String loadFile(String file){
        InputStream inputStream = null;
        try {
            inputStream = mGameIO.readAsset(file);

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer string = new StringBuffer();

            String line;
            while((line = reader.readLine()) != null){
                Log.v("LINE", line);
                string.append(line);
            }

            Log.e("FILE TEXT", string.toString());
            stringLevel = string.toString();
            return string.toString();

        } catch (IOException e) {
            Log.e("LOG", "Unable to load file",e);
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                    //inputStream = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public List<Section> parseString(String s) {
        int x = 0;
        int y = 0;
        List<Section> levelList = new ArrayList<>();
        String[] splitString = s.split("#");
        for (int i = 0; i < splitString.length; i++){
            String [] splitStringComma = splitString[i].split(",");
            if(splitStringComma.length == 2){

                x = Integer.parseInt(splitStringComma[0]);
                y = Integer.parseInt(splitStringComma[1]);
                continue;
            }
            if(splitStringComma.length > 1) {

                //for(int j = 0;j<splitStringComma.length;j++){
                    for(int ii = 0; ii < y; ii++){
                        for(int jj = 0; jj < x; jj++){
                            if(Integer.parseInt(splitStringComma[(x*ii) + jj]) == 0){
                            levelList.add(new Section(jj,ii,Integer.parseInt(splitStringComma[(x*ii) + jj])));
                            //Log.e("LOC: " , "Index = "+ ( (ii*jj) + jj) + " :  X = " + jj + " : Y = " + ii + " String Loc = " + splitStringComma[(ii*jj) + jj]);

                            }
                        }
                    }
                    //Log.v("Split Split Split", splitStringComma[j]);
                //}
            }
        }
        return levelList;
    }
}
