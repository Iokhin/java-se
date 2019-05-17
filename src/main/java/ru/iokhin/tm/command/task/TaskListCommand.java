package ru.iokhin.tm.command.task;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.service.ProjectService;
import ru.iokhin.tm.service.TaskService;

import java.util.Scanner;

public class TaskListCommand extends AbstractCommand {

    private Scanner scanner = new Scanner(System.in);

    public TaskListCommand(Bootstrap bootstrap) {
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
        return "task-list";
    }

    @Override
    public String description() {
        return "List all tasks for selected project";
    }

    @Override
    public void execute() {
        System.out.println("ENTER ID OF PROJECT TO LIST TASKS");
        String projectId = scanner.nextLine();
        if (!bootstrap.getCurrentUser().getUserId().equals(projectService.getProjectById(projectId).getUserId())) {
            System.out.println("NO ACCESS FOR THIS OPERATION");
            return;
        }
        System.out.println("TASKS LIST:");
        taskService.listTask(projectId, bootstrap.getCurrentUser().getUserId());
    }
}
