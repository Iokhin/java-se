package ru.iokhin.tm.Command;

import ru.iokhin.tm.Bootstrap;

import java.util.Scanner;

public class TaskListCommand extends AbstractCommand {

    private static final String name = "task-list";
    private static final String description = "task-list: Show all tasks for chosen project.";

    private Scanner scanner = new Scanner(System.in);

    public TaskListCommand(Bootstrap bootstrap) {
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
        System.out.println("ENTER ID OF PROJECT TO LIST TASKS");
        String projectIdTaskList = scanner.nextLine();
        System.out.println("TASKS LIST:");
        bootstrap.getTaskService().listTask(projectIdTaskList);
    }
}
