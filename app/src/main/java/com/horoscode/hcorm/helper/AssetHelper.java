package com.horoscode.hcorm.helper;

import com.horoscode.hcorm.HCDatabase;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Mac on 9/14/14.
 */
public class AssetHelper {
    public static InputStream fileToInputSteam(String fileName){
        InputStream inputFile = null;
        try {
            inputFile = HCDatabase.getContext().getAssets().open(fileName);
        }catch (Exception e){

        }
        return inputFile;
    }
}
