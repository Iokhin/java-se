package ru.iokhin.tm.command.system;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

public final class HelpCommand extends AbstractCommand {

    public HelpCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public HelpCommand() {

    }

    @Override
    public boolean security() {
        return false;
    }

    @Override
    public String name() {
        return "help";
    }

    @Override
    public String description() {
        return "Show description of all commands";
    }

    @Override
    public void execute() {
        helpCommand();
    }

    private void helpCommand() {
        for (AbstractCommand abstractCommand : bootstrap.getCommandMap().values()) {
            System.out.println(abstractCommand.name() + ": " + abstractCommand.description());
        }
    }
}
