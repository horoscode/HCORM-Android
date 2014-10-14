package com.horoscode.hcorm.helper;

/**
 * Created by Mac on 10/14/14.
 */
public class StringHelper {
    public static String removeWhiteSpace(String str){
        return str.replaceAll("\\s+","");
    }
}
