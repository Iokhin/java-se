package ru.iokhin.tm.command.system;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.config.Bootstrap;

import javax.inject.Inject;

@NoArgsConstructor
public final class HelpCommand extends AbstractCommand {

    @Inject
    private Bootstrap bootstrap;

    @Override
    public boolean security() {
        return false;
    }

    @Override
    public boolean admin() {
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
        for (@NotNull AbstractCommand abstractCommand : bootstrap.getCommandMap().values()) {
            System.out.println(abstractCommand.name() + ": " + abstractCommand.description());
        }
    }
}
