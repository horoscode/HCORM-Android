package com.horoscode.hcorm.helper;

import android.util.Log;

import com.horoscode.hcorm.HCDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Mac on 9/15/14.
 */
public class FileHelper extends File{

    public FileHelper(String path) {
        super(path);
    }

    public static boolean isFileExist(File file){
        return file.exists();
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
        catch(Exception e){
            Log.d("Error:", e.toString());
        }
    }

    public static void writeFile(String fileName, String databasePath){
        try{
            InputStream inputFile                   =   AssetHelper.fileToInputSteam(fileName);
            OutputStream outputFile                 =   new FileOutputStream(databasePath);
            byte[] buffer 							=	new byte[1024];
            int length;
            while ((length = inputFile.read(buffer))>0){
                outputFile.write(buffer, 0, length);
            }
            outputFile.flush();
            outputFile.close();
            inputFile.close();
        }
        catch(Exception e){
            Log.d("Error:", e.toString());
        }
    }
}
