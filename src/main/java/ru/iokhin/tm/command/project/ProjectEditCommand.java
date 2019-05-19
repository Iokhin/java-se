package ru.iokhin.tm.command.project;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

@NoArgsConstructor
public final class ProjectEditCommand extends AbstractCommand {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public boolean security() {
        return true;
    }

    private boolean isHaveAccess(Bootstrap bootstrap, String projectId) {

        @NotNull
        final String currentUserId = bootstrap.getCurrentUser().getUserId();
        @NotNull
        final String allowedUserId = bootstrap.getProjectService().getProjectById(projectId).getUserId();

        return currentUserId.equals(allowedUserId);
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
        String projectId = scanner.nextLine();
        if (bootstrap.getProjectService().getProjectById(projectId) == null) {
            System.out.println("NO SUCH PROJECT ID");
            return;
        }

        if (!isHaveAccess(bootstrap, projectId)) {
            System.out.println("NO ACCESS FOR THIS OPERATION");
            return;
        }
        System.out.println("ENTER NEW NAME OF PROJECT TO EDIT");

        @NotNull
        String newName = scanner.nextLine();

        projectEditCommand(projectId, newName);
        System.out.println("OK");
    }


    private void projectEditCommand(@NotNull String id, @NotNull String newName) {
        bootstrap.getProjectService().editProject(id, newName);
    }
}
