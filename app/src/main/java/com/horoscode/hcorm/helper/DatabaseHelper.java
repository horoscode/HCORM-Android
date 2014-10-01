package com.horoscode.hcorm.helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.horoscode.hcorm.HCDatabase;
import com.horoscode.hcorm.HCModel;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by Mac on 9/6/14.
 */
public class DatabaseHelper{

    private static String databaseName              =   HCDatabase.getDatabaseName()+"."+HCDatabase.getDatabaseExtension();
    private static String databasePath              =   HCDatabase.getDatabasePath();
    private static HCModel modelCache               =   HCDatabase.getModelCache();
    private static Field[] fields                   =   modelCache.getClass().getFields();
    private static String tableName;
    private static int tableId;
    private static SQLiteDatabase databaseAccessor;
    private static FileHelper fileHelper            =   new FileHelper(databasePath);

    public static void checkDatabase() {
        if(!fileHelper.exists()){
            writeDatabase();
        }
    }

    private static void writeDatabase(){
        try {
            FileHelper.writeFile(databaseName, databasePath);
        }catch(Exception e){

        }
    }

    public static long save(){
        openDatabase(true);
        ContentValues values						=	new ContentValues();
        long success								=	-1;
        for(int i=0; i<fields.length; i++){
            if(!fields[i].getName().equals("id") && !fields[i].getName().equals("tableName")){
                values.put(ReflectionHelper.getFieldName(fields[i]), ReflectionHelper.getFieldValue(fields[i]));
            }
        }

        if(tableId==-1){
            success									=	databaseAccessor.insert(tableName, null, values);
        }else{
            success									=	databaseAccessor.update(tableName, values, "id = ?", new String[]{String.valueOf(tableId)});
        }

        closeDatabase();
        return success;
    }

    public static <T extends HCModel> ArrayList<T> all(){
        openDatabase(false);
        ArrayList<T> models                         =   new ArrayList<T>();
        String query 								=	"SELECT * FROM " + tableName;
        Cursor cursor								=	databaseAccessor.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                try {
                    T model                         =   (T) modelCache.getClass().newInstance();
                    for(int i=0; i<cursor.getColumnCount(); i++){
                        try {
                            Field kolom         =   model.getClass().getDeclaredField(cursor.getColumnName(i));
                            ReflectionHelper.setFieldValue(kolom,cursor.getString(i));
                        }catch(Exception e){}
                    }
                    models.add(model);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } while (cursor.moveToNext());
        }
        return models;
    }

    public static <T extends HCModel> T first(){
        openDatabase(false);
        String query 								=	"SELECT * FROM " + tableName;
        Cursor cursor								=	databaseAccessor.rawQuery(query, null);
        T models                                    =   (T) modelCache;
        if (cursor.moveToFirst()) {
            for(int i=0; i<cursor.getColumnCount(); i++){
                if(!cursor.getColumnName(i).equals("id")){
                    try {
                        Field kolom             =   models.getClass().getDeclaredField(cursor.getColumnName(i));
                        ReflectionHelper.setFieldValue(kolom,cursor.getString(i));
                    }catch(Exception e){}
                }
            }
        }
        return models;
    }

    public static long destroy() {
        long success								=	-1;
        int tableId                                 =   HCDatabase.getModelCache().getTableId();
        if(tableId!=-1){
            openDatabase(true);
            databaseAccessor.delete(tableName, " id = ?",  new String[]{String.valueOf(tableId)});
            closeDatabase();
        }
        return success;
    }

    private static void openDatabase(boolean write){
        modelCache                                 =   HCDatabase.getModelCache();
        try {
            tableName                              =   ReflectionHelper.getFieldValue("tableName");
        }catch (Exception e){

        }
        tableId                                    =   modelCache.getTableId();
        if(write){
            databaseAccessor                       =   SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READWRITE);
        }else{
            databaseAccessor                       =   SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READONLY);
        }
    }

    private static void closeDatabase(){
        databaseAccessor.close();
    }
}
