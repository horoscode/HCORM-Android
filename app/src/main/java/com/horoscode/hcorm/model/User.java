package com.horoscode.hcorm.model;

import com.horoscode.hcorm.HCModel;

/**
 * Created by Mac on 10/5/14.
 */
public class User extends HCModel{
    public String username;
    public String password;
    public String primaryKey = "user_id";
    public int user_id;
}