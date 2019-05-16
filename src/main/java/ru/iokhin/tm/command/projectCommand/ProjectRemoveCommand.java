package ru.iokhin.tm.command.projectCommand;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

public class ProjectRemoveCommand extends AbstractCommand {

    private Scanner scanner = new Scanner(System.in);

    public ProjectRemoveCommand(Bootstrap bootstrap, String name, String description) {
        super(bootstrap, name, description);
    }

    @Override
    public void execute() {
        if (bootstrap.getCurrentUser() == null) {
            bootstrap.getCommandMap().get("user-login").execute();
            if (bootstrap.getCurrentUser() == null) return;
        }
        bootstrap.getProjectService().listProject(bootstrap.getCurrentUser().getUserId());
        System.out.println("ENTER ID OF PROJECT TO REMOVE");
        String idRemove = scanner.nextLine();
        projectRemoveCommand(idRemove);
        System.out.println("OK");
    }


    private void projectRemoveCommand(String id) {
        bootstrap.getProjectService().removeProject(id);
    }
}
