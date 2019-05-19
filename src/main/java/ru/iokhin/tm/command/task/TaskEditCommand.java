package ru.iokhin.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

@NoArgsConstructor
public final class TaskEditCommand extends AbstractCommand {

    @NotNull
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public boolean security() {
        return true;
    }

    private boolean isHaveAccess(Bootstrap bootstrap, String taskId) {

        @NotNull final String currentUserId = bootstrap.getCurrentUser().getUserId();
        @NotNull final String allowedUserId = bootstrap.getTaskService().getTaskById(taskId).getUserId();

        return currentUserId.equals(allowedUserId);
    }


    @Override
    public String name() {
        return "task-edit";
    }

    @Override
    public String description() {
        return "Edit task by ID";
    }

    @Override
    public void execute() {
        System.out.println("ENTER ID OF PROJECT TO EDIT TASK");

        @NotNull
        String projectId = scanner.nextLine();
        if (bootstrap.getProjectService().getProjectById(projectId) == null) {
            System.out.println("NO SUCH PROJECT ID");
            return;
        }

        bootstrap.getTaskService().listTask(projectId, bootstrap.getCurrentUser().getUserId());
        System.out.println("ENTER ID OF TASK TO EDIT");

        @NotNull
        String taskId = scanner.nextLine();

        if (bootstrap.getTaskService().getTaskById(taskId) == null) {
            System.out.println("NO SUCH TASK ID");
            return;
        }

        if (!isHaveAccess(bootstrap, taskId)) {
            System.out.println("NO ACCESS FOR THIS OPERATION");
            return;
        }

        System.out.println("ENTER NEW NAME OF TASK TO EDIT");

        @NotNull
        String newTaskName = scanner.nextLine();

        bootstrap.getTaskService().editTask(taskId, newTaskName);
        System.out.println("OK");
    }
}

