package ru.iokhin.tm.exception;

import org.jetbrains.annotations.NotNull;

public class ClientAuthException extends Exception {
    public ClientAuthException() {
        super("THIS COMMAND ALLOWS ONLY FOR AUTHORIZED USER, PLEASE LOGIN");
    }

    public ClientAuthException(@NotNull final String message) {
        super(message);
    }
}

