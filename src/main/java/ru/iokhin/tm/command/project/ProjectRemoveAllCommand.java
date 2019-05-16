package ru.iokhin.tm.command.project;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

public class ProjectRemoveAllCommand extends AbstractCommand {

    public ProjectRemoveAllCommand(Bootstrap bootstrap) {
        super(bootstrap);
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
        if (bootstrap.getCurrentUser() == null) {
            bootstrap.getCommandMap().get("user-login").execute();
            if (bootstrap.getCurrentUser() == null) return;
        }
        projectRemoveAllCommand();
        System.out.println("OK");
    }


    private void projectRemoveAllCommand() {
        bootstrap.getProjectService().clearProject();

    }
}
