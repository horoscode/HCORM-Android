package com.horoscode.hcorm.query;

/**
 * Created by Mac on 10/15/14.
 */
public class Select {
    StringBuilder queryBuilder = new StringBuilder("SELECT ");

    public Select(String columns){
        queryBuilder.append(columns);
    }

    public From from(String tableName){
        queryBuilder.append(" FROM " + tableName);
        return new From(queryBuilder.toString());
    }
}
