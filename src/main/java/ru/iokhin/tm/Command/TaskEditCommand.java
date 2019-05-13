package ru.iokhin.tm.Command;

import ru.iokhin.tm.Bootstrap;

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
        bootstrap.ts.listTask(projectIdTaskEdit);
        System.out.println("ENTER ID OF TASK TO EDIT");
        String taskIdEdit = scanner.nextLine();
        System.out.println("ENTER NEW NAME OF TASK TO EDIT");
        String newTaskName = scanner.nextLine();
        bootstrap.ts.editTask(taskIdEdit, newTaskName);
        System.out.println("OK");
    }

    public TaskEditCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }


}
