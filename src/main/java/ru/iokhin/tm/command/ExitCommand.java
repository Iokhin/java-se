package ru.iokhin.tm.command;

import ru.iokhin.tm.Bootstrap;

public class ExitCommand extends AbstractCommand {

    public ExitCommand(Bootstrap bootstrap, String name, String description) {
        super(bootstrap, name, description);
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
