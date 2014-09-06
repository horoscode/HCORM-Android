package com.horoscode.hcorm;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Mac on 9/6/14.
 */
public class database extends Application{

    private String HCORM_DB_NAME = "";
    private String HCORM_DB_version = "";

    public database(){
//        try {
//            ApplicationInfo ai = getPackageManager().getApplicationInfo(activity.getPackageName(), PackageManager.GET_META_DATA);
//            Bundle bundle = ai.metaData;
//            String myApiKey = bundle.getString("HCORM_DB_NAME");
//        } catch (PackageManager.NameNotFoundException e) {
//            Log.e(TAG, "Failed to load meta-data, NameNotFound: " + e.getMessage());
//        } catch (NullPointerException e) {
//            Log.e(TAG, "Failed to load meta-data, NullPointer: " + e.getMessage());
//        }

    }

}
