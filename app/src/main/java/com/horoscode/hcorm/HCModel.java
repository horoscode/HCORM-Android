package com.horoscode.hcorm;

import com.horoscode.hcorm.helper.DatabaseHelper;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Mac on 9/2/14.
 */
public class HCModel {

    protected int tableId = -1;
    protected String tableName;

    protected void all(){
        Class aClass = getClass();
        Field[] methods = aClass.getFields();
        String hasil = "";
        for(int i=0; i< methods.length; i++){
            try {
                Field field = null;
                try {
                    field = getClass().getDeclaredField(methods[i].getName().toString());
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                field.setAccessible(true);

                Object value = field.get(this);
                hasil += methods[i].getName().toString() + ": "+value.toString()+"\n";
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        hasil += "\n" + HCDatabase.getDatabaseName();
        DatabaseHelper.all(getClass(), tableName);
//        return hasil;
    }

    protected void save(){
        HCDatabase.setCache(this);
        DatabaseHelper.save();
    }

    public String getTableName(){ return tableName; }

    public int getTableId(){ return tableId; }

    protected void destroy(){
        DatabaseHelper.destroy();
    }
}
