package com.horoscode.hcorm.helper;

import android.content.res.AssetManager;
import android.util.Log;

import com.horoscode.hcorm.HCDatabase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Created by Mac on 9/14/14.
 */
public class AssetHelper {
    public static AssetManager getAssets() {
        return HCDatabase.getContext().getAssets();
    }

    public static String getAssetsPath(){
        return HCDatabase.getContext().getExternalFilesDir("assets").getAbsolutePath();
    }

    public static boolean isFileExistInAssets(String fileName){
        try {
            if (Arrays.asList(getAssets().list("")).contains(fileName)) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }
}
