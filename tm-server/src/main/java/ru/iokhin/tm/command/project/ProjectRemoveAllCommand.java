package ru.iokhin.tm.command.project;

import lombok.NoArgsConstructor;
import ru.iokhin.tm.command.AbstractCommand;

@NoArgsConstructor
public final class ProjectRemoveAllCommand extends AbstractCommand {

    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "project-clear";
    }

    @Override
    public String description() {
        return "Remove all projects for current user";
    }

    @Override
    public void execute() {
        serviceLocator.getProjectService().removeAllByUser(serviceLocator.getUserService().getCurrentUser());
        System.out.println("OK");
    }
}
