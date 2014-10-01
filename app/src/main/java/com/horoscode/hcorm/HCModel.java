package com.horoscode.hcorm;

import com.horoscode.hcorm.helper.DatabaseHelper;
import java.util.ArrayList;

/**
 * Created by Mac on 9/2/14.
 */
public class HCModel{

    protected int tableId                   =   -1;
    protected String tablePrimaryKey        =   "id";

    public HCModel(){
        HCDatabase.setCache(this);
        DatabaseHelper.checkDatabase();
    }

    public int getTableId(){ return tableId; }

    public <T extends HCModel> ArrayList<T> all(){
        return DatabaseHelper.all();
    }

    public <T extends HCModel> T first(){
        return DatabaseHelper.first();
    }

    public void save(){
        HCDatabase.setCache(this);
        DatabaseHelper.save();
    }

    public void destroy(){
        DatabaseHelper.destroy();
    }
}
