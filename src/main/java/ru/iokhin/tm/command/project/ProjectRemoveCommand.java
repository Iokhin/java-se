package ru.iokhin.tm.command.project;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

@NoArgsConstructor
public final class ProjectRemoveCommand extends AbstractCommand {

    @NotNull
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public boolean security() {
        return true;
    }

    private boolean isHaveAccess(Bootstrap bootstrap, String projectId) {

        @NotNull final String currentUserId = bootstrap.getCurrentUser().getId();
        @NotNull final String allowedUserId = bootstrap.getProjectService().findOne(projectId).getUserId();

        return currentUserId.equals(allowedUserId);
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
        bootstrap.getCommandMap().get("project-list").execute();
        System.out.println("ENTER ID OF PROJECT TO REMOVE");

        @NotNull
        String projectId = scanner.nextLine();
        if (bootstrap.getProjectService().findOne(projectId) == null) {
            System.out.println("NO SUCH PROJECT ID");
            return;
        }

        if (!isHaveAccess(bootstrap, projectId)) {
            System.out.println("NO ACCESS FOR THIS OPERATION");
            return;
        }
        projectRemoveCommand(projectId);
        System.out.println("OK");
    }

    private void projectRemoveCommand(@NotNull String id) {
        bootstrap.getProjectService().remove(id);
    }
}
