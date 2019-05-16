package ru.iokhin.tm.command.project;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

public class ProjectCreateCommand extends AbstractCommand {

    private Scanner scanner = new Scanner(System.in);

    public ProjectCreateCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "project-create";
    }

    @Override
    public String description() {
        return "Creates project";
    }

    @Override
    public void execute() {
        if (bootstrap.getCurrentUser() == null) {
            bootstrap.getCommandMap().get("user-login").execute();
            if (bootstrap.getCurrentUser() == null) return;
        }
        System.out.println("ENTER NAME OF PROJECT TO CREATE");
        String name = scanner.nextLine();
        projectCreateCommand(name);
        System.out.println("OK");
    }


    private void projectCreateCommand(String name) {
        bootstrap.getProjectService().addProject(name, bootstrap.getCurrentUser());
    }
}
