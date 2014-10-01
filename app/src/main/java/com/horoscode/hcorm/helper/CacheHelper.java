package com.horoscode.hcorm.helper;

import com.horoscode.hcorm.HCModel;

/**
 * Created by Mac on 9/17/14.
 */
public class CacheHelper {
    private HCModel modelcache;

    public HCModel getModelcache() {
        return modelcache;
    }

    public void setModelcache(HCModel modelcache){
        this.modelcache = modelcache;
    }
}
