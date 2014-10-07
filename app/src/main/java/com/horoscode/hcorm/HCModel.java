package com.horoscode.hcorm;

import com.horoscode.hcorm.helper.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mac on 9/2/14.
 */
public class HCModel {

    public int id = -1;

    public HCModel() {
        HCDatabase.setModelCache(this);
        DatabaseHelper.checkDatabase();
        id = -1;
    }

    public static <T extends HCModel> List<T> all() {
        return DatabaseHelper.all();
    }

    public <T extends HCModel> T first() {
        return DatabaseHelper.first();
    }

    public int getId() {
        return id;
    }

    public void save() {
        HCDatabase.setModelCache(this);
        DatabaseHelper.save();
    }

    public void destroy() {
        DatabaseHelper.destroy();
    }
}
