package ru.iokhin.tm.Command;

import ru.iokhin.tm.Bootstrap;

import java.util.Scanner;

public class ProjectEditCommand extends AbstractCommand {

    private static final String name = "project-edit";
    private static final String description = "project-edit: Edit selected project.";

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
        System.out.println("ENTER ID OF PROJECT TO EDIT");
        String idEdit = scanner.nextLine();
        System.out.println("ENTER NEW NAME OF PROJECT TO EDIT");
        String newName = scanner.nextLine();
        projectEditCommand(idEdit, newName);
        System.out.println("OK");
    }

    private void projectEditCommand(String id, String newName) {
        bootstrap.getProjectService().editProject(id, newName);
    }

    public ProjectEditCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

}
