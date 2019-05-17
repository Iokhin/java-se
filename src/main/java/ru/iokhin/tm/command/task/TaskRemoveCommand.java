package ru.iokhin.tm.command.task;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.service.ProjectService;
import ru.iokhin.tm.service.TaskService;

import java.util.Scanner;

public class TaskRemoveCommand extends AbstractCommand {

    private Scanner scanner = new Scanner(System.in);

    public TaskRemoveCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    private ProjectService projectService = bootstrap.getProjectService();
    private TaskService taskService = bootstrap.getTaskService();

    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "task-remove";
    }

    @Override
    public String description() {
        return "Remove task by ID";
    }

    @Override
    public void execute() {
        System.out.println("ENTER ID OF PROJECT TO REMOVE TASK");
        String projectId = scanner.nextLine();
        if (!bootstrap.getCurrentUser().getUserId().equals(projectService.getProjectById(projectId).getUserId())) {
            System.out.println("NO ACCESS FOR THIS OPERATION");
            return;
        }
        taskService.listTask(projectId, bootstrap.getCurrentUser().getUserId());
        System.out.println("ENTER ID OF TASk TO REMOVE");
        String taskIdRemove = scanner.nextLine();
        taskService.removeTask(taskIdRemove);
        System.out.println("OK");
    }
}

