package ru.iokhin.tm.command.task;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

public class TaskRemoveCommand extends AbstractCommand {

    private Scanner scanner = new Scanner(System.in);

    public TaskRemoveCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

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
        if (bootstrap.getCurrentUser() == null) {
            bootstrap.getCommandMap().get("user-login").execute();
            if (bootstrap.getCurrentUser() == null) return;
        }
        System.out.println("ENTER ID OF PROJECT TO REMOVE TASK");
        String projectIdTaskRemove = scanner.nextLine();
        bootstrap.getTaskService().listTask(projectIdTaskRemove, bootstrap.getCurrentUser().getUserId());
        System.out.println("ENTER ID OF TASk TO REMOVE");
        String taskIdRemove = scanner.nextLine();
        bootstrap.getTaskService().removeTask(taskIdRemove);
        System.out.println("OK");
    }
}

