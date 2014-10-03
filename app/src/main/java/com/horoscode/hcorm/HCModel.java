package com.horoscode.hcorm;

import com.horoscode.hcorm.helper.DatabaseHelper;
import java.util.ArrayList;

/**
 * Created by Mac on 9/2/14.
 */
public class HCModel{

    protected int id                          =   -1;
//    protected String tablePrimaryKey        =   "id";

    public HCModel(){
        HCDatabase.setModelCache(this);
        DatabaseHelper.checkDatabase();
    }

    public int getId(){ return id; }

    public <T extends HCModel> ArrayList<T> all(){
        return DatabaseHelper.all();
    }

    public <T extends HCModel> T first(){
        return DatabaseHelper.first();
    }

    public void save(){
        HCDatabase.setModelCache(this);
        DatabaseHelper.save();
    }

    public void destroy(){
        DatabaseHelper.destroy();
    }
}
