package ru.iokhin.tm.command.task;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

@NoArgsConstructor
public final class TaskCreateCommand extends AbstractCommand {

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
        return "task-create";
    }

    @Override
    public String description() {
        return "Create task for selected project";
    }

    @Override
    public void execute() {
        System.out.println("ENTER ID OF PROJECT TO CREATE TASK");
        System.out.println("PROJECTS LIST:");
        bootstrap.getProjectService().listProject(bootstrap.getCurrentUser().getUserId());
        String projectId = scanner.nextLine();
        if (bootstrap.getProjectService().getProjectById(projectId) == null) {
            System.out.println("NO SUCH PROJECT ID");
            return;
        }
        if (!isHaveAccess(bootstrap, projectId)) {
            System.out.println("NO ACCESS FOR THIS OPERATION");
            return;
        }
        System.out.println("ENTER NAME OF TASK TO CREATE");
        String taskName = scanner.nextLine();
        bootstrap.getTaskService().addTask(bootstrap.getCurrentUser().getUserId(), projectId, taskName);
        System.out.println("OK");
    }
}

