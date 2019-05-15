package ru.iokhin.tm.Command.ProjectCommand;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.Command.AbstractCommand;

public class ProjectRemoveAllCommand extends AbstractCommand {

    private static final String name = "project-clear";
    private static final String description = "project-clear: Remove all projects";

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
        projectRemoveAllCommand();
        System.out.println("OK");
    }

    private void projectRemoveAllCommand() {
        bootstrap.getProjectService().clearProject();
    }

    public ProjectRemoveAllCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

}