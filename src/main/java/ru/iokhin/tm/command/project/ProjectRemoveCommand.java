package ru.iokhin.tm.command.project;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

public class ProjectRemoveCommand extends AbstractCommand {

    private Scanner scanner = new Scanner(System.in);

    public ProjectRemoveCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "project-remove";
    }

    @Override
    public String description() {
        return "Remove project by ID";
    }

    @Override
    public void execute() {
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
