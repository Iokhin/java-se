package ru.iokhin.tm.command.taskCommand;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

public class TaskEditCommand extends AbstractCommand {

    private static final String name = "task-edit";
    private static final String description = "task-edit: Edit selected task.";

    private Scanner scanner = new Scanner(System.in);

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
        System.out.println("ENTER ID OF PROJECT TO EDIT TASK");
        String projectIdTaskEdit = scanner.nextLine();
        bootstrap.getTaskService().listTask(projectIdTaskEdit);
        System.out.println("ENTER ID OF TASK TO EDIT");
        String taskIdEdit = scanner.nextLine();
        System.out.println("ENTER NEW NAME OF TASK TO EDIT");
        String newTaskName = scanner.nextLine();
        bootstrap.getTaskService().editTask(taskIdEdit, newTaskName);
        System.out.println("OK");
    }

    public TaskEditCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }


}
