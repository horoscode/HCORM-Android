package com.horoscode.hcorm;

import android.app.Application;
import android.content.Context;

import com.horoscode.hcorm.helper.DatabaseHelper;

/**
 * Created by Mac on 9/6/14.
 */
public class HCDatabase extends Application{

    private static String databaseName = "";
    private static int databaseVersion;
    private static String databasePath = "";
    private static String databaseExtension = "";
    private static Context context;

    public static Context getContext() {
        return context;
    }

    public void onCreate(){
        super.onCreate();
        HCDatabase.this.context = getApplicationContext();
    }

    public static void setDatabaseInfo(String databaseName, String databaseExtension,int databaseVersion,String databasePath){
        HCDatabase.databaseName = databaseName;
        HCDatabase.databaseExtension = databaseExtension;
        HCDatabase.databaseVersion = databaseVersion;
        HCDatabase.databasePath = databasePath;
    }

    public static String getDatabaseName() {
        return databaseName;
    }

    public static int getDatabaseVersion() {
        return databaseVersion;
    }

    public static String getDatabasePath() {
        return context.getExternalFilesDir("assets").getAbsolutePath()+"/"+ databaseName + "." + databaseExtension;
    }

    public static String getDatabaseExtension() {
        return databaseExtension;
    }
}