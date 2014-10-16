package com.horoscode.hcorm.helper;

import android.util.Log;

import com.horoscode.hcorm.HCDatabase;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

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

    public static boolean isFieldExist(String fieldName){
        try {
            if(HCDatabase.getModelCache().getClass().getDeclaredField(fieldName) != null){
                return true;
            }else{
                return false;
            }
        } catch (NoSuchFieldException e) {
            return false;
        }
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
            if(field.getType().getSimpleName().equals("Calendar")){
                SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                value = date_format.format(((GregorianCalendar) field.get(HCDatabase.getModelCache())).getTime());
            }else if(field.getType().equals(Number.class)){
                value = String.valueOf(((Number) field.get(HCDatabase.getModelCache())).longValue());
            }else{
                value = field.get(HCDatabase.getModelCache()).toString();
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static void setFieldValue(String fieldName, Object object){
        try {
            setFieldValue(HCDatabase.getModelCache().getClass().getField(fieldName), object);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void setFieldValue(Field field, Object object){
        try {
            if(field.getType().equals(boolean.class)){
                if(object.toString().equals(StringHelper.removeWhiteSpace("true"))){
                    object = true;
                }else{
                    object = false;
                }
            }else if(field.getType().equals(double.class)){
                object = Double.parseDouble(object.toString());
            }else if(field.getType().equals(Double.class)){
                object = Double.parseDouble(object.toString());
            }else if(field.getType().equals(int.class)){
                object = Integer.parseInt(object.toString());
            }else if(field.getType().equals(Integer.class)){
                object = Integer.parseInt(object.toString());
            }else if(field.getType().equals(Number.class)){
                final Object finalObject = object;
                object = new Number() {
                    @Override
                    public double doubleValue() {
                        return Double.parseDouble(finalObject.toString());
                    }

                    @Override
                    public float floatValue() {
                        return Float.parseFloat(finalObject.toString());
                    }

                    @Override
                    public int intValue() {
                        return Integer.parseInt(finalObject.toString());
                    }

                    @Override
                    public long longValue() {
                        return Long.parseLong(finalObject.toString());
                    }
                };
            }else if(field.getType().equals(Calendar.class)){
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Calendar calendar = Calendar.getInstance();
                try {
                    calendar.setTime(dateFormat.parse(object.toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                object = calendar;
            }
            field.set(HCDatabase.getModelCache(), object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
