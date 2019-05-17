package ru.iokhin.tm.command.project;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.service.ProjectService;

public class ProjectRemoveAllCommand extends AbstractCommand {

    public ProjectRemoveAllCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    private ProjectService projectService = bootstrap.getProjectService();

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
        projectService.clearProject();
    }
}
