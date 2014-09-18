package com.horoscode.hcorm.helper;

import com.horoscode.hcorm.HCDatabase;

import java.lang.reflect.Field;

/**
 * Created by Mac on 9/12/14.
 */
public class ReflectionHelper {
    public static String getFieldName(Field field){
        return field.getName().toString();
    }

    public static String getFieldValue(Field field){
        String value                        =   null;
        try {
            try {
                field                       =   HCDatabase.getModelCache().getClass().getDeclaredField(field.getName().toString());
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
}
