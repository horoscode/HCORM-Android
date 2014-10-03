package com.horoscode.hcorm;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.horoscode.hcorm.helper.AssetHelper;
import com.horoscode.hcorm.helper.CacheHelper;
import com.horoscode.hcorm.helper.FileHelper;

/**
 * Created by Mac on 9/6/14.
 */
public class HCDatabase extends Application{

    private static String databaseName              = "";
    private static int databaseVersion;
    private static String databasePath              = "";
    private static String databaseExtension         = "";
    private static Context context;
    private static CacheHelper cacheHelper;

    public static Context getContext() {
        return context;
    }

    public void onCreate(){
        super.onCreate();
        HCDatabase.this.context                     = getApplicationContext();
        HCDatabase.this.cacheHelper                 = new CacheHelper();
    }

    public static void setDatabaseInfo(String databaseName, String databaseExtension,int databaseVersion,String databasePath){
        HCDatabase.databaseName                     = databaseName;
        HCDatabase.databaseExtension                = databaseExtension;
        HCDatabase.databaseVersion                  = databaseVersion;
        HCDatabase.databasePath                     = databasePath;
        if(!AssetHelper.isAssetsFileExist()){
            Log.e("Warning:","Can't find "+getDatabaseNameFile()+" in your assets folder");
        }
    }

    public static String getDatabaseNameFile(){
        return getDatabaseName() + "." + getDatabaseExtension();
    }

    public static String getDatabaseName() {
        return databaseName;
    }

    public static int getDatabaseVersion() {
        return databaseVersion;
    }

    public static String getDatabasePath() {
        if(databasePath.equals("")){
            databasePath                            = FileHelper.getAssetsPath()+"/"+ getDatabaseNameFile();
        }else{
            databasePath                            = FileHelper.getAssetsPath()+"/"+databasePath+"/"+ getDatabaseNameFile();
        }
        return databasePath;
    }

    public static String getDatabaseExtension() {
        return databaseExtension;
    }

    //Access Model Cache

    public static void setModelCache(HCModel model){
        cacheHelper.setModelcache(model);
    }

    public static HCModel getModelCache(){
        return cacheHelper.getModelcache();
    }
}