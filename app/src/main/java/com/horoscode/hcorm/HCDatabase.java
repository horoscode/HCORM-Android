package com.horoscode.hcorm;

import android.app.Application;
import android.content.Context;

/**
 * Created by Mac on 9/6/14.
 */
public class HCDatabase extends Application{

    private static String databaseName = "";
    private static String databaseVersion = "";
    private static String databasePath = "";
    private static String databaseExtension = "";

    public static void setDatabaseInfo(String databaseName, String databaseExtension,String databaseVersion,String databasePath){
        HCDatabase.databaseName = databaseName;
        HCDatabase.databaseExtension = databaseExtension;
        HCDatabase.databaseVersion = databaseVersion;
        HCDatabase.databasePath = databasePath;
    }

    public static String getDatabaseName() {
        return databaseName;
    }

    public static String getDatabaseVersion() {
        return databaseVersion;
    }

    public static String getDatabasePath() {
        return databasePath;
    }

    public static String getDatabaseExtension() {
        return databaseExtension;
    }
}