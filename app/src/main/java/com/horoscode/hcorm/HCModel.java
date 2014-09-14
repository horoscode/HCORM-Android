package com.horoscode.hcorm;

import java.lang.reflect.Field;

/**
 * Created by Mac on 9/2/14.
 */
public class HCModel {

    protected int id;
    protected String tableName;

    protected String all(){
        Class aClass = getClass();
        Field[] methods = aClass.getFields();
        String hasil = "";
        for(int i=0; i< methods.length; i++){
            hasil += methods[i].getName().toString() + "\n";
        }
        hasil += "\n" + HCDatabase.getDatabaseName();
        return hasil;
    }

    protected void save(){
        
    }
}
