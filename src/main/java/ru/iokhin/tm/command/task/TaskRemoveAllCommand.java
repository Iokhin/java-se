package ru.iokhin.tm.command.task;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.service.ProjectService;
import ru.iokhin.tm.service.TaskService;

import java.util.Scanner;

public class TaskRemoveAllCommand extends AbstractCommand {

    private Scanner scanner = new Scanner(System.in);

    private ProjectService projectService = bootstrap.getProjectService();
    private TaskService taskService = bootstrap.getTaskService();

    public TaskRemoveAllCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "task-clear";
    }

    @Override
    public String description() {
        return "Remove all tasks for selected project";
    }

    @Override
    public void execute() {
        System.out.println("ENTER ID OF PROJECT TO CLEAR TASKS");
        String projectId = scanner.nextLine();
        if (!bootstrap.getCurrentUser().getUserId().equals(projectService.getProjectById(projectId).getUserId())) {
            System.out.println("NO ACCESS FOR THIS OPERATION");
            return;
        }
        taskService.clearTask(projectId, bootstrap.getCurrentUser().getUserId());
        System.out.println("OK");
    }
}
