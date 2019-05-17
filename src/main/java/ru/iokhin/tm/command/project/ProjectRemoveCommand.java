package ru.iokhin.tm.command.project;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

public final class ProjectRemoveCommand extends AbstractCommand {

    public ProjectRemoveCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public ProjectRemoveCommand() {

    }

    @NotNull
    private final Scanner scanner = new Scanner(System.in);

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

        @NotNull
        String idRemove = scanner.nextLine();

        if (!bootstrap.getCurrentUser().getUserId().equals(bootstrap.getProjectService().getProjectById(idRemove).getUserId())) {
            System.out.println("NO ACCESS FOR THIS OPERATION");
            return;
        }
        projectRemoveCommand(idRemove);
        System.out.println("OK");
    }

    private void projectRemoveCommand(@NotNull String id) {
        bootstrap.getProjectService().removeProject(id);
    }
}
