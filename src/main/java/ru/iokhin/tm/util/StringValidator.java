package ru.iokhin.tm.util;

public class StringValidator {
    public static boolean isValid(String ...strings){
        for (String string : strings) {
            if (string == null || string.trim().isEmpty())
                return false;
        }
        return true;
    }
}
