package ru.iokhin.tm.command.projectCommand;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

public class ProjectListCommand extends AbstractCommand {

    public ProjectListCommand(Bootstrap bootstrap, String name, String description) {
        super(bootstrap, name, description);
    }

    @Override
    public void execute() {
        if (bootstrap.getCurrentUser() == null) {
            bootstrap.getCommandMap().get("user-login").execute();
            if (bootstrap.getCurrentUser() == null) return;
        }
        System.out.println("PROJECTS LIST:");
        projectListCommand();
    }


    private void projectListCommand() {
        bootstrap.getProjectService().listProject(bootstrap.getCurrentUser().getUserId());
    }
}
