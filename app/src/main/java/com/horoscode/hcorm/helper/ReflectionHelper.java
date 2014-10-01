package com.horoscode.hcorm.helper;

import android.database.Cursor;

import com.horoscode.hcorm.HCDatabase;
import com.horoscode.hcorm.HCModel;
import com.horoscode.hcorm.model.mmain;

import java.lang.reflect.Field;

/**
 * Created by Mac on 9/12/14.
 */
public class ReflectionHelper {
    public static String getFieldName(Field field){
        return field.getName().toString();
    }

    public static String getFieldValue(Field field){
        return getFieldValue(field.getName().toString());
    }

    public static String getFieldValue(String fieldName){
        String value                            =   null;
        try {
            Field field                         =   null;
            try {
                field                           =   HCDatabase.getModelCache().getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            value = field.get(HCDatabase.getModelCache()).toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static void setFieldValue(String fieldName, Object object){
        String value                            =   null;
        try {
            Field field                         =   null;
            try {
                field                           =   HCDatabase.getModelCache().getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            field.set(HCDatabase.getModelCache(), object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setFieldValue(Field field, Object object){
        try {
            field.set(HCDatabase.getModelCache(), object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
