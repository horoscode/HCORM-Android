package com.horoscode.hcorm;

/**
 * Created by Mac on 9/7/14.
 */
public class HCORM{

    public static void initWithDatabaseName(String databaseName){
        initWithDatabaseNameAndDatabaseExtension(databaseName, "db");
    }

    public static void initWithDatabaseNameAndDatabaseExtension(String databaseName, String databaseExtension){
        initWithDatabaseNameAndDatabaseExtensionAndDatabaseVersion(databaseName, databaseExtension, 1);
    }

    public static void initWithDatabaseNameAndDatabaseVersion(String databaseName, int databaseVersion){
        initWithDatabaseNameAndDatabaseVersionAndDatabasePath(databaseName, databaseVersion, "data");
    }

    public static void initWithDatabaseNameAndDatabasePath(String databaseName, String databasePath){
        initWithDatabaseNameAndDatabaseExtensionAndDatabasePath(databaseName, "db", databasePath);
    }

    public static void initWithDatabaseNameAndDatabaseExtensionAndDatabaseVersion(String databaseName, String databaseExtension, int databaseVersion){
        initWithDatabaseNameAndDatabaseExtensionAndDatabaseVersionAndDatabasePath(databaseName, databaseExtension, databaseVersion, "data");
    }

    public static void initWithDatabaseNameAndDatabaseExtensionAndDatabasePath(String databaseName, String databaseExtension, String databasePath){
        initWithDatabaseNameAndDatabaseExtensionAndDatabaseVersionAndDatabasePath(databaseName, databaseExtension, 1, databasePath);
    }

    public static void initWithDatabaseNameAndDatabaseVersionAndDatabasePath(String databaseName, int databaseVersion, String databasePath){
        initWithDatabaseNameAndDatabaseExtensionAndDatabaseVersionAndDatabasePath(databaseName, "db", databaseVersion, databasePath);
    }

    public static void initWithDatabaseNameAndDatabaseExtensionAndDatabaseVersionAndDatabasePath(String databaseName, String databaseExtension,int databaseVersion,String databasePath){
        HCDatabase.setDatabaseInfo(databaseName, databaseExtension, databaseVersion, databasePath);
    }
}
