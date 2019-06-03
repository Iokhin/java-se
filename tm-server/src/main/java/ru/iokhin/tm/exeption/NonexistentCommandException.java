package ru.iokhin.tm.exeption;

public class NonexistentCommandException extends Throwable {
    public NonexistentCommandException() {
        super("NO SUCH COMMAND, ENTER:'help' TO LIST AVAILABLE COMMANDS");
    }
}
