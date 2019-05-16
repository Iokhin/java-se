package ru.iokhin.tm.command.task;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

public class TaskListCommand extends AbstractCommand {

    private Scanner scanner = new Scanner(System.in);

    public TaskListCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

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
        if (bootstrap.getCurrentUser() == null) {
            bootstrap.getCommandMap().get("user-login").execute();
            if (bootstrap.getCurrentUser() == null) return;
        }
        System.out.println("ENTER ID OF PROJECT TO LIST TASKS");
        String projectIdTaskList = scanner.nextLine();
        System.out.println("TASKS LIST:");
        bootstrap.getTaskService().listTask(projectIdTaskList, bootstrap.getCurrentUser().getUserId());
    }
}
