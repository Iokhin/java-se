package ru.iokhin.tm.exeption;

public class AuthException extends Exception {
    public AuthException() {
        super("THIS COMMAND ALLOWS ONLY FOR AUTHORIZED USER, PLEASE LOGIN");
    }
}
