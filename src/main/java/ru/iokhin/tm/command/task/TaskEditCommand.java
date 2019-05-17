package ru.iokhin.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

public final class TaskEditCommand extends AbstractCommand {

    public TaskEditCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public TaskEditCommand() {

    }

    @NotNull
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public boolean security() {
        return true;
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
        String projectIdTaskEdit = scanner.nextLine();

        bootstrap.getTaskService().listTask(projectIdTaskEdit, bootstrap.getCurrentUser().getUserId());
        System.out.println("ENTER ID OF TASK TO EDIT");

        @NotNull
        String taskIdEdit = scanner.nextLine();

        if (!bootstrap.getCurrentUser().getUserId().equals(bootstrap.getTaskService().getTaskById(taskIdEdit).getUserId())) {
            System.out.println("NO ACCESS FOR THIS OPERATION");
            return;
        }
        System.out.println("ENTER NEW NAME OF TASK TO EDIT");

        @NotNull
        String newTaskName = scanner.nextLine();

        bootstrap.getTaskService().editTask(taskIdEdit, newTaskName);
        System.out.println("OK");
    }
}

