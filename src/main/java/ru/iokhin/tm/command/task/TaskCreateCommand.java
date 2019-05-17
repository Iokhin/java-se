package ru.iokhin.tm.command.task;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.entity.Project;

import java.util.Scanner;

public final class TaskCreateCommand extends AbstractCommand {

    public TaskCreateCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    public TaskCreateCommand() {

    }

    @NotNull
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public boolean security() {
        return true;
    }

    @Override
    public String name() {
        return "task-create";
    }

    @Override
    public String description() {
        return "Create task for selected project";
    }

    @Override
    public void execute() {
        System.out.println("ENTER ID OF PROJECT TO CREATE TASK");
        System.out.println("PROJECTS LIST:");
        bootstrap.getProjectService().listProject(bootstrap.getCurrentUser().getUserId());
        String projectId = scanner.nextLine();
        if (!bootstrap.getCurrentUser().getUserId().equals(bootstrap.getProjectService().getProjectById(projectId).getUserId())) {
            System.out.println("NO ACCESS FOR THIS OPERATION");
            return;
        }
        for (Project project : bootstrap.getProjectService().getAllProjects().values()) {
            if (project.getId().equals(projectId)) {
                System.out.println("ENTER NAME OF TASK TO CREATE");
                String taskName = scanner.nextLine();
                bootstrap.getTaskService().addTask(bootstrap.getCurrentUser().getUserId(), projectId, taskName);
                System.out.println("OK");
                return;
            }
        }
        System.out.println("NO SUCH PROJECT ID");
    }
}

