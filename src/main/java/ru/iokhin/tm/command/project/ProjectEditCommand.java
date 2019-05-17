package ru.iokhin.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

public final class ProjectEditCommand extends AbstractCommand {

    public ProjectEditCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public ProjectEditCommand() {

    }

    private final Scanner scanner = new Scanner(System.in);

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

        @NotNull
        String idEdit = scanner.nextLine();

        if (!bootstrap.getCurrentUser().getUserId().equals(bootstrap.getProjectService().getProjectById(idEdit).getUserId())) {
            System.out.println("NO ACCESS FOR THIS OPERATION");
            return;
        }
        System.out.println("ENTER NEW NAME OF PROJECT TO EDIT");

        @NotNull
        String newName = scanner.nextLine();

        projectEditCommand(idEdit, newName);
        System.out.println("OK");
    }


    private void projectEditCommand(@NotNull String id, @NotNull String newName) {
        bootstrap.getProjectService().editProject(id, newName);
    }
}
