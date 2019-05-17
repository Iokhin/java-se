package ru.iokhin.tm.command.project;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.service.ProjectService;

import java.util.Scanner;

public class ProjectEditCommand extends AbstractCommand {

    private Scanner scanner = new Scanner(System.in);

    public ProjectEditCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    private ProjectService projectService = bootstrap.getProjectService();

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
        System.out.println("ENTER ID OF PROJECT TO EDIT");
        String idEdit = scanner.nextLine();
        if (!bootstrap.getCurrentUser().getUserId().equals(projectService.getProjectById(idEdit).getUserId())) {
            System.out.println("NO ACCESS FOR THIS OPERATION");
            return;
        }
        System.out.println("ENTER NEW NAME OF PROJECT TO EDIT");
        String newName = scanner.nextLine();
        projectEditCommand(idEdit, newName);
        System.out.println("OK");
    }


    private void projectEditCommand(String id, String newName) {
        projectService.editProject(id, newName);
    }
}
