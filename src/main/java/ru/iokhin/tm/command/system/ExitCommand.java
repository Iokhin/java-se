package ru.iokhin.tm.command.system;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

public final class ExitCommand extends AbstractCommand {

    public ExitCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public ExitCommand() {

    }

    @Override
    public final boolean security() {
        return false;
    }

    @Override
    public final String name() {
        return "exit";
    }

    @Override
    public final String description() {
        return "Exit the application";
    }

    @Override
    public final void execute() {
        System.exit(0);
    }
}
