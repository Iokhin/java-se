package ru.iokhin.tm.command;

import ru.iokhin.tm.Bootstrap;

public class ExitCommand extends AbstractCommand {

    private static final String name = "exit";
    private static final String description = "exit: Exit task manager.";

    public ExitCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
