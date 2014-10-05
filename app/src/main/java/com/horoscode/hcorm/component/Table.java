package com.horoscode.hcorm.component;

import android.util.Log;

import com.horoscode.hcorm.HCDatabase;
import com.horoscode.hcorm.helper.ReflectionHelper;

/**
 * Created by Mac on 9/6/14.
 */
public class Table {
    private static String standardTableName(String modelName){
        String standardTableName    =   "";
        for(int i=0; i< modelName.length(); i++){
            if(Character.isUpperCase(modelName.charAt(i)) && i!=0){
                standardTableName   += "_";
            }
            standardTableName   += Character.toString(modelName.charAt(i)).toLowerCase();
        }
        return standardTableName+"s";
    }

    public static String getTableName(){
        String tableName = "";
        if(ReflectionHelper.isFieldExist("tableName")){
            tableName = ReflectionHelper.getFieldValue("tableName");
        }else{
            String className = HCDatabase.getModelCache().getClass().getSimpleName();
            if(Character.isUpperCase(className.charAt(0))) {
                tableName = standardTableName(className);
                Log.d("tableName", tableName);
            }else{
                Log.e("Warning", "You Must Follow Model Naming Convention or Override Model Naming Convention in " + className + " model");
            }
        }
        return tableName;
    }
}
