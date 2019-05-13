package ru.iokhin.tm.Command;

import ru.iokhin.tm.Bootstrap;

public class ProjectListCommand extends AbstractCommand {

    private static final String name = "project-list";
    private static final String description = "project-list: Show all projects.";

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
        System.out.println("PROJECTS LIST:");
        projectListCommand();
    }

    private void projectListCommand() {
        bootstrap.ps.listProject(bootstrap.getCurrentUser().getUserId());
    }

    public ProjectListCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

}
