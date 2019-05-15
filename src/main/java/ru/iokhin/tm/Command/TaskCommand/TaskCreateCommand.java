package ru.iokhin.tm.Command.TaskCommand;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.Command.AbstractCommand;
import ru.iokhin.tm.entity.Project;

import java.util.Scanner;

public class TaskCreateCommand extends AbstractCommand {

    private static final String name = "task-create";
    private static final String description = "task-create: Create new task.";

    private Scanner scanner = new Scanner(System.in);

    public TaskCreateCommand(Bootstrap bootstrap) {
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
        System.out.println("ENTER ID OF PROJECT TO CREATE TASK");
        System.out.println("PROJECTS LIST:");
        bootstrap.getProjectService().listProject(bootstrap.getCurrentUser().getUserId());
        String projectId = scanner.nextLine();
        for (Project project : bootstrap.projectRepository.projectLinkedHashMap.values()) {
            if (project.getId().equals(projectId)) {
                System.out.println("ENTER NAME OF TASK TO CREATE");
                String taskName = scanner.nextLine();
                bootstrap.getTaskService().addTask(projectId, taskName);
                System.out.println("OK");
                return;
            }
        }
        System.out.println("NO SUCH PROJECT ID");
    }
}
