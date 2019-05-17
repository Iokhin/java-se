package ru.iokhin.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

public final class TaskRemoveAllCommand extends AbstractCommand {

    public TaskRemoveAllCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public TaskRemoveAllCommand() {

    }

    @NotNull
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public boolean security() {
        return true;
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

        if (!bootstrap.getCurrentUser().getUserId().equals(bootstrap.getProjectService().getProjectById(projectId).getUserId())) {
            System.out.println("NO ACCESS FOR THIS OPERATION");
            return;
        }
        bootstrap.getTaskService().clearTask(projectId, bootstrap.getCurrentUser().getUserId());
        System.out.println("OK");
    }
}
