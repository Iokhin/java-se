package ru.iokhin.tm.exeption;

import org.jetbrains.annotations.NotNull;

public class AuthException extends Exception {
    public AuthException() {
        super("THIS COMMAND ALLOWS ONLY FOR AUTHORIZED USER, PLEASE LOGIN");
    }

    public AuthException(@NotNull final String message) {
        super(message);
    }
}
