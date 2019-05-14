package ru.iokhin.tm.Command;

import ru.iokhin.tm.Bootstrap;

import java.util.Scanner;

public class TaskRemoveAllCommand extends AbstractCommand {

    private static final String name = "task-clear";
    private static final String description = "task-clear: Remove all tasks for chosen project";

    private Scanner scanner = new Scanner(System.in);

    public TaskRemoveAllCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void execute() {
        System.out.println("ENTER ID OF PROJECT TO CLEAR TASKS");
        String projectIdTaskClear = scanner.nextLine();
        bootstrap.getTaskService().clearTask(projectIdTaskClear);
        System.out.println("OK");
    }
}
