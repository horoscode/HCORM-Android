package com.horoscode.hcorm;

import com.horoscode.hcorm.helper.DatabaseHelper;

import java.util.List;

/**
 * Created by Mac on 9/2/14.
 */
public class HCModel {

    public Integer id;

    public HCModel() {
        HCDatabase.setModelCache(this);
        DatabaseHelper.checkDatabase();
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

    public <T extends HCModel> T find(int primaryKeyValue) {
        return DatabaseHelper.find(primaryKeyValue);
    }

    public <T extends HCModel> T findBy(String condition) {
        return DatabaseHelper.findBy(condition);
    }

    public <T extends HCModel> T take() {
        return DatabaseHelper.take();
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
