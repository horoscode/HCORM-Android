package com.horoscode.hcorm.query;

/**
 * Created by Mac on 9/6/14.
 */
public class Where {
    StringBuilder queryBuilder = new StringBuilder();

    public Where(String previousQuery){
        queryBuilder.append(previousQuery);
    }

    public String limit(int limit){
        return (queryBuilder.toString() + " LIMIT " + String.valueOf(limit));
    }
}