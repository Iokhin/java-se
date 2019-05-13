package ru.iokhin.tm.Command;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.entity.User;

import java.util.Scanner;

public class ProjectCreateCommand extends AbstractCommand {

    private static final String name = "project-create";
    private static final String description = "project-create: Create new project.";

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
        System.out.println("ENTER NAME OF PROJECT TO CREATE");
        String name = scanner.nextLine();
        projectCreateCommand(name);
        System.out.println("OK");
    }

    private void projectCreateCommand(String name) {
        bootstrap.ps.addProject(name, bootstrap.getCurrentUser());
    }


    public ProjectCreateCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

}
