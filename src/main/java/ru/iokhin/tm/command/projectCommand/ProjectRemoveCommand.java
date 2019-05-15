package ru.iokhin.tm.command.projectCommand;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

public class ProjectRemoveCommand extends AbstractCommand {

    private static final String name = "project-remove";
    private static final String description = "project-remove: Remove selected project";

    private Scanner scanner = new Scanner(System.in);

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
        System.out.println("ENTER ID OF PROJECT TO REMOVE");
        String idRemove = scanner.nextLine();
        projectRemoveCommand(idRemove);
        System.out.println("OK");
    }

    private void projectRemoveCommand(String id) {
        bootstrap.getProjectService().removeProject(id);
    }

    public ProjectRemoveCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

}
