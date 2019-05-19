package ru.iokhin.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

@NoArgsConstructor
public final class TaskRemoveCommand extends AbstractCommand {


    @NotNull
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
        return "task-remove";
    }

    @Override
    public String description() {
        return "Remove task by ID";
    }

    @Override
    public void execute() {
        System.out.println("ENTER ID OF PROJECT TO REMOVE TASK");

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
        bootstrap.getTaskService().listTask(projectId, bootstrap.getCurrentUser().getUserId());
        System.out.println("ENTER ID OF TASk TO REMOVE");

        @NotNull
        String taskId = scanner.nextLine();
        if (bootstrap.getTaskService().getTaskById(taskId) == null) {
            System.out.println("NO SUCH TASK ID");
            return;
        }

        bootstrap.getTaskService().removeTask(taskId);
        System.out.println("OK");
    }
}

