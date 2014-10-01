package com.horoscode.hcorm;

import com.horoscode.hcorm.helper.DatabaseHelper;
import com.horoscode.hcorm.model.mmain;
import com.horoscode.hcorm.helper.ReflectionHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mac on 9/2/14.
 */
public class HCModel {

    protected int tableId                   =   -1;

    public HCModel(){
        HCDatabase.setCache(this);
        DatabaseHelper.checkDatabase();
    }

    public int getTableId(){ return tableId; }

    protected <T extends HCModel> ArrayList<T> all(){
        return DatabaseHelper.all((T) this);
    }

    protected <T extends HCModel> T first(){
        return DatabaseHelper.first((T) this);
    }

    protected void save(){
        HCDatabase.setCache(this);
        DatabaseHelper.save();
    }

    protected void destroy(){
        DatabaseHelper.destroy();
    }
}
