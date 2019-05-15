package ru.iokhin.tm.command.taskCommand;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.entity.Project;

import java.util.Scanner;

public class TaskCreateCommand extends AbstractCommand {

    private Scanner scanner = new Scanner(System.in);

    public TaskCreateCommand(Bootstrap bootstrap, String name, String description) {
        super(bootstrap, name, description);
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
