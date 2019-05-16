package ru.iokhin.tm.command.taskCommand;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

public class TaskRemoveAllCommand extends AbstractCommand {

    private Scanner scanner = new Scanner(System.in);

    public TaskRemoveAllCommand(Bootstrap bootstrap, String name, String description) {
        super(bootstrap, name, description);
    }

    @Override
    public void execute() {
        if (bootstrap.getCurrentUser() == null) {
            bootstrap.getCommandMap().get("user-login").execute();
            if (bootstrap.getCurrentUser() == null) return;
        }
        System.out.println("ENTER ID OF PROJECT TO CLEAR TASKS");
        String projectIdTaskClear = scanner.nextLine();
        bootstrap.getTaskService().clearTask(projectIdTaskClear);
        System.out.println("OK");
    }
}
