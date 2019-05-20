package ru.iokhin.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

@NoArgsConstructor
public final class TaskRemoveAllCommand extends AbstractCommand {

    @NotNull
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public boolean security() {
        return true;
    }

    private boolean isHaveAccess(Bootstrap bootstrap, String projectId) {

        @NotNull
        final String currentUserId = bootstrap.getCurrentUser().getId();
        @NotNull
        final String allowedUserId = bootstrap.getProjectService().findOne(projectId).getUserId();

        return currentUserId.equals(allowedUserId);
    }

    @Override
    public String name() {
        return "task-clear";
    }

    @Override
    public String description() {
        return "Remove all tasks for selected project";
    }

    @Override
    public void execute() {
        System.out.println("ENTER ID OF PROJECT TO CLEAR TASKS");

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
        bootstrap.getTaskService().removeAllByProjectId(projectId);
        System.out.println("OK");
    }
}
