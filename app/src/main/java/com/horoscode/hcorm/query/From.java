package com.horoscode.hcorm.query;

/**
 * Created by Mac on 10/15/14.
 */
public class From {
    StringBuilder queryBuilder = new StringBuilder();

    public From(String previousQuery){
        queryBuilder.append(previousQuery);
    }

    public OrderBy orderBy(String orderStatement){
        queryBuilder.append(" ORDER BY " + orderStatement);
        return new OrderBy(queryBuilder.toString());
    }

    public Where where(String whereStatement){
        queryBuilder.append(" WHERE (" + whereStatement +")");
        return new Where(queryBuilder.toString());
    }

    public String limit(int limit){
        return (queryBuilder.toString() + " LIMIT " + String.valueOf(limit));
    }
}
