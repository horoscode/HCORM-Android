package com.horoscode.hcorm.helper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.horoscode.hcorm.HCDatabase;
import com.horoscode.hcorm.HCModel;
import com.horoscode.hcorm.component.Table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mac on 9/6/14.
 */
public class DatabaseHelper {

    private static String databaseName = HCDatabase.getDatabaseFileName();
    private static String databasePath = HCDatabase.getDatabasePath();
    private static HCModel modelCache = HCDatabase.getModelCache();
    private static Field[] fields = modelCache.getClass().getFields();
    private static String tableName, primaryKey;
    private static int id;
    private static SQLiteDatabase databaseAccessor;

    public static void checkDatabase() {
        if (!FileHelper.isFileExist(databasePath)) {
            writeDatabase();
        }
    }

    private static void writeDatabase() {
        try {
            FileHelper.writeDatabaseFile(databaseName, databasePath);
        } catch (Exception e) {
        }
    }

    public static long save() {
        openDatabase(true);
        ContentValues values = new ContentValues();
        long success = -1;
        for (int i = 0; i < fields.length; i++) {
            if (!fields[i].getName().equals("primaryKey") &&
                    !fields[i].getName().equals(primaryKey) &&
                    !fields[i].getName().equals("tableName") &&
                    !fields[i].getName().equals("id")) {
                values.put(ReflectionHelper.getFieldName(fields[i]), ReflectionHelper.getFieldValue(fields[i]));
            }
        }
        if (id == 0) {
            success = databaseAccessor.insert(tableName, null, values);
            if(success > 0){ last(); }
        } else {
            success = databaseAccessor.update(tableName, values, primaryKey + " = ?", new String[]{String.valueOf(id)});
        }
        closeDatabase();
        return success;
    }

    public static <T extends HCModel> T create(String...content) {
        openDatabase(true);
        ContentValues values = new ContentValues();
        for (int i = 0; i < content.length; i++) {
            String[] column = content[i].split("=");
            values.put(StringHelper.removeWhiteSpace(column[0]), StringHelper.removeWhiteSpace(column[1]));
        }
        if(databaseAccessor.insert(tableName, null, values) > 0){
            last();
        }else{
            StringBuilder message = new StringBuilder("Can't create record with ");
            for (int i = 0; i < content.length; i++) {
                String[] column = content[i].split("=");
                values.put(StringHelper.removeWhiteSpace(column[0]), StringHelper.removeWhiteSpace(column[1]));
                if(i != 0){
                    message.append(" AND ");
                }
                message.append(column[0] + " = " + column[1]);
            }
            Log.e("Warning", message + " argument.");
        }

        closeDatabase();
        return (T) modelCache;
    }

    public static <T extends HCModel> List<T> all() {
        openDatabase(false);
        List<T> models = new ArrayList<T>();
        String query = "SELECT * FROM " + tableName;
        Cursor cursor = databaseAccessor.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                try {
                    T model = (T) modelCache.getClass().newInstance();
                    for (int i = 0; i < cursor.getColumnCount(); i++) {
                        try {
                            Field column = model.getClass().getDeclaredField(cursor.getColumnName(i));
                            if(column.getType().getSimpleName().equals("String")){
                                ReflectionHelper.setFieldValue(column, cursor.getString(i));
                            }else{
                                ReflectionHelper.setFieldValue(column, Integer.parseInt(cursor.getString(i)));
                            }
                        } catch (Exception e) {
                        }
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

    public static <T extends HCModel> T first() {
        openDatabase(false);
        String query = "SELECT * FROM " + tableName + " ORDER BY " + primaryKey + " ASC LIMIT 1";
        Cursor cursor = databaseAccessor.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                try {
                    Field column = modelCache.getClass().getDeclaredField(cursor.getColumnName(i));
                    if(column.getType().getSimpleName().equals("String")){
                        ReflectionHelper.setFieldValue(column, cursor.getString(i));
                    }else{
                        ReflectionHelper.setFieldValue(column, Integer.parseInt(cursor.getString(i)));
                    }
                } catch (Exception e) {
                }
            }
        }
        return (T) modelCache;
    }

    public static <T extends HCModel> T last() {
        openDatabase(false);
        String query = "SELECT * FROM " + tableName + " ORDER BY " + primaryKey + " DESC LIMIT 1";
        Cursor cursor = databaseAccessor.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                try {
                    Field column = modelCache.getClass().getDeclaredField(cursor.getColumnName(i));
                    if(column.getType().getSimpleName().equals("String")){
                        ReflectionHelper.setFieldValue(column, cursor.getString(i));
                    }else{
                        ReflectionHelper.setFieldValue(column, Integer.parseInt(cursor.getString(i)));
                    }
                } catch (Exception e) {
                }
            }
        }
        return (T) modelCache;
    }

    public static long destroy() {
        long success = -1;
        openDatabase(true);
        if (id != -1) {
            success = databaseAccessor.delete(tableName, primaryKey + " = ?", new String[]{String.valueOf(id)});
            if(success > 0){
                Field[] modelFields = modelCache.getClass().getFields();
                for (int i=0; i<modelFields.length; i++){
                    if(modelFields[i].getType().getSimpleName().equals("String")){
                        ReflectionHelper.setFieldValue(modelFields[i], null);
                    }else{
                        ReflectionHelper.setFieldValue(modelFields[i], -1);
                    }
                }
            }else{
                Log.e("Warning", "Can't find data in " + tableName + " with " + primaryKey + " = " + String.valueOf(id));
            }
            closeDatabase();
        }else{
            Log.e("Warning", "Can't find data in " + tableName + " with " + primaryKey + " = " + String.valueOf(id));
        }
        return success;
    }

    private static void openDatabase(boolean writetable) {
        modelCache = HCDatabase.getModelCache();
        tableName = Table.getTableName();
        primaryKey = Table.getPrimaryKey();
        if(ReflectionHelper.isFieldExist(primaryKey)){
            id = Integer.parseInt(ReflectionHelper.getFieldValue(primaryKey));
        }else{
            if(primaryKey.equals("id")){
                id = modelCache.getId();
            }else{
                Log.e("Warning","Primary Key must defined with primary key override");
            }
        }

        try {
            if (writetable) {
                databaseAccessor = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READWRITE);
            } else {
                databaseAccessor = SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READONLY);
            }
        }catch(Exception e){
            Log.e("Warning","Can't open " + databasePath);
        }
    }

    private static void closeDatabase() {
        databaseAccessor.close();
    }
}
