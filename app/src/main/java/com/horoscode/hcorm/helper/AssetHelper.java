package com.horoscode.hcorm.helper;

import android.content.res.AssetManager;

import com.horoscode.hcorm.HCDatabase;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Created by Mac on 9/14/14.
 */
public class AssetHelper {
    public static InputStream fileToInputSteam(String fileName){
        InputStream inputFile       =   null;
        try {
            inputFile               =   getAssets().open(fileName);
        }catch (Exception e){

        }
        return inputFile;
    }

    private static AssetManager getAssets(){
        return HCDatabase.getContext().getAssets();
    }

    public static boolean isAssetsFileExist(){
        boolean isAssetsFileExist   =   false;
        try {
            isAssetsFileExist       =   Arrays.asList(getAssets().list("")).contains("myFile");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  isAssetsFileExist;
    }
}
