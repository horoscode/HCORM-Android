package com.horoscode.hcorm.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.horoscode.hcorm.HCDatabase;
import com.horoscode.hcorm.HCModel;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by Mac on 9/6/14.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    private String databaseName;
    private Context context;
    private static String databasePath;
    private static SQLiteDatabase db;
    private FileHelper fileHelper;

    public DatabaseHelper(){
        super(HCDatabase.getContext(), HCDatabase.getDatabaseName()+"."+HCDatabase.getDatabaseExtension(), null, HCDatabase.getDatabaseVersion());
        context = HCDatabase.getContext();
        databaseName = HCDatabase.getDatabaseName()+"."+HCDatabase.getDatabaseExtension();
        databasePath = HCDatabase.getDatabasePath();
        fileHelper  =   new FileHelper(databasePath);
        createDatabase();
    }

    private void createDatabase() {
        if(!fileHelper.exists()){
//            getReadableDatabase();
            writeDatabase();
        }
    }

    private void writeDatabase(){
        try {
            FileHelper.writeFile(databaseName, databasePath);
        }catch(Exception e){

        }
    }

    private static void openToWrite(){
        db 											=	SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public static long save(){
        openToWrite();
//        ContentValues values						=	new ContentValues();
        long sukses									=	-1;
//        Field[] fields                              =   HCDatabase.getModelCache().getClass().getFields();
//        HCModel modelCache                          =   HCDatabase.getModelCache();
//        String tableName                            =   modelCache.getTableName();
//        String id                                   =   String.valueOf(modelCache.getTableId());
//        for(int i=0; i<fields.length; i++){
//            if(!fields[i].getName().equals("id")){
//                values.put(ReflectionHelper.getFieldName(fields[i]), ReflectionHelper.getFieldValue(fields[i]));
//            }
//        }
//        if(id.equals("-1")){
//            sukses									=	db.insert(tableName, null, values);
//        }else{
//            values.put("id", id);
//            sukses									=	db.update(tableName, values, "id =?", new String[]{String.valueOf(id)});
//        }
//        closeDatabase();
        return sukses;
    }
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
        Field[] fields                              =   model.getClass().getFields();
        if (cursor.moveToFirst()) {
            do {
                ArrayList<Object> row				=	new ArrayList<Object>();
                for(int i=0; i<=fields.length; i++){
//                    field
                    row.add(cursor.getString(i));
                }
                data.add(row);
            } while (cursor.moveToNext());
        }
//        model.getClass().asSubclass()
//        return model;
    }

    public static void destroy() {
        openToWrite();
        db.delete(HCDatabase.getModelCache().getTableName(), " id = ?",  new String[]{String.valueOf(HCDatabase.getModelCache().getTableId())});
        closeDatabase();
    }

    private static void closeDatabase(){
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase arg0) { }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) { }
}
