package ru.iokhin.tm.util;

public final class StringValidator {
    public static void validate(String ...strings) {
        for (String string : strings) {
            if (string == null || string.trim().isEmpty())
                throw new IllegalArgumentException("INVALID PARAMETERS");
        }
    }

    public static void main(String[] args) {
        validate("");
    }
}
