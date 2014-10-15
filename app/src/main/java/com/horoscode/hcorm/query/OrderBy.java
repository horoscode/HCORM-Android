package com.horoscode.hcorm.query;

/**
 * Created by Mac on 10/15/14.
 */
public class OrderBy {
    StringBuilder queryBuilder = new StringBuilder();

    public OrderBy(String previousQuery){
        queryBuilder.append(previousQuery);
    }

    public String limit(int limit){
        return (queryBuilder.toString() + " LIMIT " + String.valueOf(limit));
    }
}
