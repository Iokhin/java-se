package ru.iokhin.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

public final class TaskRemoveCommand extends AbstractCommand {

    public TaskRemoveCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public TaskRemoveCommand() {

    }

    @NotNull
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public boolean security() {
        return true;
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

        if (!bootstrap.getCurrentUser().getUserId().equals(bootstrap.getProjectService().getProjectById(projectId).getUserId())) {
            System.out.println("NO ACCESS FOR THIS OPERATION");
            return;
        }
        bootstrap.getTaskService().listTask(projectId, bootstrap.getCurrentUser().getUserId());
        System.out.println("ENTER ID OF TASk TO REMOVE");

        @NotNull
        String taskIdRemove = scanner.nextLine();

        bootstrap.getTaskService().removeTask(taskIdRemove);
        System.out.println("OK");
    }
}

