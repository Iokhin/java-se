package ru.iokhin.tm.command.system;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.config.Bootstrap;

@Component
@NoArgsConstructor
public final class HelpCommand extends AbstractCommand {

    @Autowired
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
