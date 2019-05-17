package ru.iokhin.tm.command.task;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.util.Scanner;

public class TaskEditCommand extends AbstractCommand {

    private Scanner scanner = new Scanner(System.in);

    public TaskEditCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

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
        String projectIdTaskEdit = scanner.nextLine();
        bootstrap.getTaskService().listTask(projectIdTaskEdit, bootstrap.getCurrentUser().getUserId());
        System.out.println("ENTER ID OF TASK TO EDIT");
        String taskIdEdit = scanner.nextLine();
        System.out.println("ENTER NEW NAME OF TASK TO EDIT");
        String newTaskName = scanner.nextLine();
        bootstrap.getTaskService().editTask(taskIdEdit, newTaskName);
        System.out.println("OK");
    }
}

