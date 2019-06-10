package ru.iokhin.tm.exception;

public class ClientNonexistentCommandException extends Exception {
    public ClientNonexistentCommandException() {
        super("NO SUCH COMMAND");
    }
}
