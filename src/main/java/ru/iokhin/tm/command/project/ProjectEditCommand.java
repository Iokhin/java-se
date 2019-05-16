package ru.iokhin.tm.command.project;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

public class ProjectEditCommand extends AbstractCommand {

    private Scanner scanner = new Scanner(System.in);

    public ProjectEditCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "project-edit";
    }

    @Override
    public String description() {
        return "Edit project by ID";
    }

    @Override
    public void execute() {
        if (bootstrap.getCurrentUser() == null) {
            bootstrap.getCommandMap().get("user-login").execute();
            if (bootstrap.getCurrentUser() == null) return;
        }
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
}
