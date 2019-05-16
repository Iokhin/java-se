package ru.iokhin.tm.command.taskCommand;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

public class TaskRemoveCommand extends AbstractCommand {

    private Scanner scanner = new Scanner(System.in);

    public TaskRemoveCommand(Bootstrap bootstrap, String name, String description) {
        super(bootstrap, name, description);
    }

    @Override
    public void execute() {
        if (bootstrap.getCurrentUser() == null) {
            bootstrap.getCommandMap().get("user-login").execute();
            if (bootstrap.getCurrentUser() == null) return;
        }
        System.out.println("ENTER ID OF PROJECT TO REMOVE TASK");
        String projectIdTaskRemove = scanner.nextLine();
        bootstrap.getTaskService().listTask(projectIdTaskRemove);
        System.out.println("ENTER ID OF TASk TO REMOVE");
        String taskIdRemove = scanner.nextLine();
        bootstrap.getTaskService().removeTask(taskIdRemove);
        System.out.println("OK");
    }
}

