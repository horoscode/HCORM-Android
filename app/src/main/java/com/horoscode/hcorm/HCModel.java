package com.horoscode.hcorm;

import android.util.Log;

import com.horoscode.hcorm.helper.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Mac on 9/2/14.
 */
public class HCModel {

    public int id;

    public HCModel() {
        HCDatabase.setModelCache(this);
        DatabaseHelper.checkDatabase();
        id = -1;
    }

    public <T extends HCModel> List<T> all() {
        return DatabaseHelper.all();
    }

    public <T extends HCModel> T first() {
        return DatabaseHelper.first();
    }

    public <T extends HCModel> T last() {
        return DatabaseHelper.last();
    }

    public int getId() {
        return id;
    }

    public long save() {
        HCDatabase.setModelCache(this);
        return DatabaseHelper.save();
    }

    public <T extends HCModel> T create(String... content){
        HCDatabase.setModelCache(this);
        return DatabaseHelper.create(content);
    }

    public void destroy() {
        HCDatabase.setModelCache(this);
        DatabaseHelper.destroy();
    }
}
