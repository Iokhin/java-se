package ru.iokhin.tm.command.taskCommand;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

public class TaskListCommand extends AbstractCommand {

    private Scanner scanner = new Scanner(System.in);

    public TaskListCommand(Bootstrap bootstrap, String name, String description) {
        super(bootstrap, name, description);
    }

    @Override
    public void execute() {
        System.out.println("ENTER ID OF PROJECT TO LIST TASKS");
        String projectIdTaskList = scanner.nextLine();
        System.out.println("TASKS LIST:");
        bootstrap.getTaskService().listTask(projectIdTaskList);
    }
}
