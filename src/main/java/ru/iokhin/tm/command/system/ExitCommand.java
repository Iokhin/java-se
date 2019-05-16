package ru.iokhin.tm.command.system;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

public class ExitCommand extends AbstractCommand {

    public ExitCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public boolean security() {
        return false;
    }

    @Override
    public String name() {
        return "exit";
    }

    @Override
    public String description() {
        return "Exit the application";
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
