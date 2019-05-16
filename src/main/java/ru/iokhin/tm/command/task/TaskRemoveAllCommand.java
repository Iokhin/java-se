package ru.iokhin.tm.command.task;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

public class TaskRemoveAllCommand extends AbstractCommand {

    private Scanner scanner = new Scanner(System.in);

    public TaskRemoveAllCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

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
        if (bootstrap.getCurrentUser() == null) {
            bootstrap.getCommandMap().get("user-login").execute();
            if (bootstrap.getCurrentUser() == null) return;
        }
        System.out.println("ENTER ID OF PROJECT TO CLEAR TASKS");
        String projectIdTaskClear = scanner.nextLine();
        bootstrap.getTaskService().clearTask(projectIdTaskClear, bootstrap.getCurrentUser().getUserId());
        System.out.println("OK");
    }
}
