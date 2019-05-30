package ru.iokhin.tm.exception;

public class NonexistentCommandException extends Exception {
    public NonexistentCommandException() {
        super("NO SUCH COMMAND");
    }
}
