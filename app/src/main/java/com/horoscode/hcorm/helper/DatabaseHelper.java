package com.horoscode.hcorm.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.horoscode.hcorm.HCDatabase;

import java.io.File;

/**
 * Created by Mac on 9/6/14.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    Context context;

    SQLiteDatabase db;

    public DatabaseHelper(Context context){
        super(context, HCDatabase.getDatabaseName(), null, HCDatabase.getDatabaseVersion());
        this.context								=	context;
        dbPath										=	context.getExternalFilesDir("assets").getAbsolutePath()+"/"+ HCDatabase.getDatabaseName() + HCDatabase.getDatabaseExtension();
        createDb();
    }

    private void createDb() {
        boolean dbExist 							=	checkDb();
        if(!dbExist){
            getReadableDatabase();
            putDb();
        }
    }

    private boolean checkDb(){
        File dbFile 								=	new File(dbPath);
        return dbFile.exists();

    }

    private void putDb(){
        try{
            InputStream myInput						=	context.getAssets().open(dbName);
            String outFileName 						=	dbPath ;

            OutputStream myOutput 					=	new FileOutputStream(outFileName);
            byte[] buffer 							=	new byte[1024];
            int length;
            while ((length = myInput.read(buffer))>0){
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }
        catch(Exception e){

        }
    }

//    public long save(String[] input){
//        db 											=	SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
//        ContentValues values						=	new ContentValues();
//        long sukses									=	-1;
//
//        for(int i=0; i<tableColumns.size(); i++){
//            values.put(tableColumns.get(i)[0], input[i]);
//        }
//
//        if(getId() == null){
//            sukses									=	db.insert(tableName, null, values);
//        }else{
//            values.put("id", getId());
//            sukses									=	db.update(tableName, values, "id =?", new String[]{getId()});
//        }
//        db.close();
//        return sukses;
//    }
//
//    public long saveId(String[] input){
//        db 											=	SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
//        ContentValues values						=	new ContentValues();
//        long sukses									=	-1;
//
//        for(int i=0; i<tableColumns.size(); i++){
//            values.put(tableColumns.get(i)[0], input[i]);
//        }
//
//        values.put("id", getId());
//        sukses									=	db.insert(tableName, null, values);
//        db.close();
//        return sukses;
//    }
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
//
//    public ArrayList<ArrayList<Object>> findQuery(String column[], String query){
//        ArrayList<ArrayList<Object>> data 			=	new ArrayList<ArrayList<Object>>();
//        SQLiteDatabase db 							=	SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
//        Cursor cursor								=	db.rawQuery(query, null);
//        if (cursor.moveToFirst()) {
//            do {
//                ArrayList<Object> row				=	new ArrayList<Object>();
//                for(int i=0; i<column.length; i++){
//                    row.add(cursor.getString(i));
//                }
//                data.add(row);
//            } while (cursor.moveToNext());
//        }
//        return data;
//    }
//
//    public ArrayList<ArrayList<Object>> findAll(){
//        ArrayList<ArrayList<Object>> data 			=	new ArrayList<ArrayList<Object>>();
//        String query 								=	"SELECT  * FROM " + tableName;
//        SQLiteDatabase db 							=	SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
//        Cursor cursor								=	db.rawQuery(query, null);
//        if (cursor.moveToFirst()) {
//            do {
//                ArrayList<Object> row				=	new ArrayList<Object>();
//                for(int i=0; i<=tableColumns.size(); i++){
//                    row.add(cursor.getString(i));
//                }
//                data.add(row);
//            } while (cursor.moveToNext());
//        }
//        return data;
//    }

//    public void delete() {
//        SQLiteDatabase db 							=	SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
//        db.delete(tableName, " id = ?",  new String[]{getId()}); //selections args
//        db.close();
//    }

    @Override
    public void onCreate(SQLiteDatabase arg0) { }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) { }
}
