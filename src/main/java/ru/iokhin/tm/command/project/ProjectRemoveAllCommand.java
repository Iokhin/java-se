package ru.iokhin.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.service.ProjectService;

public final class ProjectRemoveAllCommand extends AbstractCommand {

    public ProjectRemoveAllCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public ProjectRemoveAllCommand() {

    }

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
        projectRemoveAllCommand();
        System.out.println("OK");
    }


    private void projectRemoveAllCommand() {
        bootstrap.getProjectService().clearProject();
    }
}
