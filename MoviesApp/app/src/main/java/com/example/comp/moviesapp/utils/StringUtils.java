package com.example.comp.moviesapp.utils;

/**
 * Created by COMP on 20.11.2017..
 */

public class StringUtils {

    public static String isStringEmpty(String string) {
        return (string != null) ? string : "";
    }

    public static boolean checkIfStringNotEmpty(String text) {
        return text != null && !text.isEmpty();
    }
}
