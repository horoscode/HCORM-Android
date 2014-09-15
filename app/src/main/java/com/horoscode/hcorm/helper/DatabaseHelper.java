package com.horoscode.hcorm.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.horoscode.hcorm.HCDatabase;
import com.horoscode.hcorm.HCModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by Mac on 9/6/14.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    private String databaseName = HCDatabase.getDatabaseName()+"."+HCDatabase.getDatabaseExtension();
    private Context context = HCDatabase.getContext();
    private static String databasePath = HCDatabase.getDatabasePath();
    private static SQLiteDatabase db;

    public DatabaseHelper(){
        super(HCDatabase.getContext(), HCDatabase.getDatabaseName()+"."+HCDatabase.getDatabaseExtension(), null, HCDatabase.getDatabaseVersion());
        createDatabase();
    }

    private void createDatabase() {
        boolean databaseExist 							=	checkDatabase();
        if(!databaseExist){
            getReadableDatabase();
            putDatabase();
        }
    }

    private boolean checkDatabase(){
        File databaseFile 							=	new File(HCDatabase.getDatabasePath());
        return databaseFile.exists();
    }

    private void putDatabase(){
        try{
            InputStream databaseFile				=	context.getAssets().open(databaseName);
            OutputStream databaseOutput 			=	new FileOutputStream(databasePath);
            byte[] buffer 							=	new byte[1024];
            int length;
            while ((length = databaseFile.read(buffer))>0){
                databaseOutput.write(buffer, 0, length);
            }
            databaseOutput.flush();
            databaseOutput.close();
            databaseFile.close();
        }
        catch(Exception e){
            Log.d("error", e.toString());
        }
    }

    public static long save(HCModel model, int tableId, String tableName){
        db 											=	SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READWRITE);
        ContentValues values						=	new ContentValues();
        long sukses									=	-1;
        Field[] fields                              =   model.getClass().getFields();
        String id                                   =   String.valueOf(tableId);

        for(int i=0; i<fields.length; i++){
            if(!fields[i].getName().equals("id")){
                try {
                    Field field = null;
                    try {
                        field = model.getClass().getDeclaredField(fields[i].getName().toString());
                        field.setAccessible(true);
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    Object value = field.get(model);
                    values.put(fields[i].getName(), value.toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        if(id.equals("-1")){
            sukses									=	db.insert(tableName, null, values);
        }else{
            values.put("id", id);
            sukses									=	db.update(tableName, values, "id =?", new String[]{String.valueOf(id)});
        }
        db.close();
        return sukses;
    }
//
//    public long update(String[][] input, String args[][]){
//        db 											=	SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
//        ContentValues values						=	new ContentValues();
//        long sukses									=	-1;
//        String selection							=	"";
//        String value[]								=	new String[args.length];
//        for(int i=0; i<args.length; i++){
//            if(i!=0){ selection					+=  " AND "; }
//            selection							+=	args[i][0] + " =?";
//        }
//        for(int i=0; i<value.length; i++){
//            value[i]							=	args[i][1];
//        }
//        for(int i=0; i<input.length; i++){
//            values.put(input[i][0], input[i][1]);
//        }
//
//        sukses										=	db.update(tableName, values, selection, value);
//
//        db.close();
//        return sukses;
//    }
//
//    public ArrayList<ArrayList<Object>> find(String column[], String args[][]){
//        ArrayList<ArrayList<Object>> data 			=	new ArrayList<ArrayList<Object>>();
//        SQLiteDatabase db 							=	SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
//        String selection							=	"";
//        String value[]								=	new String[args.length];
//        for(int i=0; i<args.length; i++){
//            if(i!=0){ selection					+=  " AND "; }
//            selection							+=	args[i][0] + " =?";
//        }
//
//        for(int i=0; i<value.length; i++){
//            value[i]							=	args[i][1];
//        }
//
//        Log.d("selection", value[0]);
//
//        Cursor cursor 							= 	db.query(tableName, column, selection, value, null, null, null, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                ArrayList<Object> row			=	new ArrayList<Object>();
//                for(int i=0; i<column.length; i++){
//                    row.add(cursor.getString(i));
//                }
//                data.add(row);
//            } while (cursor.moveToNext());
//        }
//
//        return data;
//    }
    public static void all(Class<? extends HCModel> model, String tableName){
        ArrayList<ArrayList<Object>> data 			=	new ArrayList<ArrayList<Object>>();
        String query 								=	"SELECT  * FROM " + tableName;
        SQLiteDatabase db 							=	SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor								=	db.rawQuery(query, null);
//        if (cursor.moveToFirst()) {
//            do {
//                ArrayList<Object> row				=	new ArrayList<Object>();
//                for(int i=0; i<=tableColumns.size(); i++){
//                    row.add(cursor.getString(i));
//                }
//                data.add(row);
//            } while (cursor.moveToNext());
//        }
//        model.getClass().asSubclass()
//        return model;
    }

    public static void destroy(HCModel model, int tableId, String tableName) {
        SQLiteDatabase db 							=	SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READWRITE);
        db.delete(tableName, " id = ?",  new String[]{String.valueOf(tableId)}); //selections args
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase arg0) { }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) { }
}
