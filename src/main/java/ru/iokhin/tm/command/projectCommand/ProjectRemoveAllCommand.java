package ru.iokhin.tm.command.projectCommand;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

public class ProjectRemoveAllCommand extends AbstractCommand {

    public ProjectRemoveAllCommand(Bootstrap bootstrap, String name, String description) {
        super(bootstrap, name, description);
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
