package ru.iokhin.tm.command.projectCommand;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

public class ProjectCreateCommand extends AbstractCommand {

    private Scanner scanner = new Scanner(System.in);

    public ProjectCreateCommand(Bootstrap bootstrap, String name, String description) {
        super(bootstrap, name, description);
    }

    @Override
    public void execute() {
        System.out.println("ENTER NAME OF PROJECT TO CREATE");
        String name = scanner.nextLine();
        projectCreateCommand(name);
        System.out.println("OK");
    }

    private void projectCreateCommand(String name) {
        bootstrap.getProjectService().addProject(name, bootstrap.getCurrentUser());
    }
}
