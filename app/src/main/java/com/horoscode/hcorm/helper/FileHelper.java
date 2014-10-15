package com.horoscode.hcorm.helper;

import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Mac on 9/15/14.
 */
public class FileHelper{

    public static void writeDatabaseFile(String fileName, String databasePath){
        try {
            if(AssetHelper.isFileExistInAssets(fileName)){
                writeFile(fileToInputSteam(fileName), new FileOutputStream(databasePath));
            }else{
                Log.e("Warning:", "Can't find " + fileName + " in your assets folder");
            }
        } catch (IOException e) {
            Log.e("Warning", "Can't copy " + fileName +" database to " + databasePath +". Check your Write External Storage Permission");
        }
    }

    private static InputStream fileToInputSteam(String fileName) {
        InputStream inputFile = null;
        try {
            inputFile = AssetHelper.getAssets().open(fileName);
        } catch (IOException e) { }
        return inputFile;
    }

    public static boolean isFileExist(String filePath){
        File file                                   =   new File(filePath);
        if(file.exists()){
            return true;
        }else{
            return false;
        }
    }

    public static void writeFile(InputStream inputFile, OutputStream outputFile){
        try{
            byte[] buffer 							=	new byte[1024];
            int length;
            while ((length = inputFile.read(buffer))>0){
                outputFile.write(buffer, 0, length);
            }
            outputFile.flush();
            outputFile.close();
            inputFile.close();
        }
        catch(Exception e){ }
    }
}
