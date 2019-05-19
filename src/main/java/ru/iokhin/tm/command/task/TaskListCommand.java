package ru.iokhin.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

@NoArgsConstructor
public final class TaskListCommand extends AbstractCommand {

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
        return "task-list";
    }

    @Override
    public String description() {
        return "List all tasks for selected project";
    }

    @Override
    public void execute() {
        System.out.println("ENTER ID OF PROJECT TO LIST TASKS");

        @NotNull
        String projectId = scanner.nextLine();
        if (bootstrap.getProjectService().getProjectById(projectId) == null) {
            System.out.println("NO SUCH PROJECT ID");
            return;
        }

        assert bootstrap.getCurrentUser() != null;
        if (!isHaveAccess(bootstrap, projectId)) {
            System.out.println("NO ACCESS FOR THIS OPERATION");
            return;
        }
        System.out.println("TASKS LIST:");
        bootstrap.getTaskService().listTask(projectId, bootstrap.getCurrentUser().getUserId());
    }
}
