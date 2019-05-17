package ru.iokhin.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

public final class TaskListCommand extends AbstractCommand {

    public TaskListCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public TaskListCommand() {

    }

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public boolean security() {
        return true;
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

        if (!bootstrap.getCurrentUser().getUserId().equals(bootstrap.getProjectService().getProjectById(projectId).getUserId())) {
            System.out.println("NO ACCESS FOR THIS OPERATION");
            return;
        }
        System.out.println("TASKS LIST:");
        bootstrap.getTaskService().listTask(projectId, bootstrap.getCurrentUser().getUserId());
    }
}
