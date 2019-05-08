package ru.iokhin.tm;

import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.repository.ProjectRepository;
import ru.iokhin.tm.repository.TaskRepository;
import ru.iokhin.tm.service.ProjectService;
import ru.iokhin.tm.service.TaskService;

import java.util.Scanner;

public class Bootstrap {

    private final static String PROJECT_CREATE = "project-create";
    private final static String PROJECT_LIST = "project-list";
    private final static String PROJECT_EDIT = "project-edit";
    private final static String PROJECT_REMOVE = "project-remove";
    private final static String PROJECT_CLEAR = "project-clear";

    private final static String TASK_CREATE = "task-create";
    private final static String TASK_LIST = "task-list";
    private final static String TASK_EDIT = "task-edit";
    private final static String TASK_REMOVE = "task-remove";
    private final static String TASK_CLEAR = "task-clear";

    private final static String HELP = "help";
    private final static String EXIT = "exit";

    private static void help() {
        System.out.println("help: Show all commands.");

        System.out.println("project-create: Create new project.");
        System.out.println("project-list: Show all projects.");
        System.out.println("project-edit: Edit selected project.");
        System.out.println("project-remove: Remove selected project");
        System.out.println("project-clear: Remove all projects");

        System.out.println("task-create: Create new task.");
        System.out.println("task-list: Show all tasks for chosen project.");
        System.out.println("task-edit: Edit selected task.");
        System.out.println("task-remove: Remove selected task");
        System.out.println("task-clear: Remove all tasks for chosen project");

        System.out.println("exit: Exit task manager");
    }

    public void init() {
        System.out.println("***WELCOME TO TASK MANAGER***");
        Scanner scanner = new Scanner(System.in);
        String input = "";
        ProjectRepository projectRepository = new ProjectRepository();
        TaskRepository taskRepository = new TaskRepository();
        ProjectService ps = new ProjectService(projectRepository);
        TaskService ts = new TaskService(taskRepository);
        while (!input.equals(EXIT)) {
            input = scanner.nextLine();
            label:
            switch (input) {
                case PROJECT_CREATE:
                    System.out.println("ENTER NAME OF PROJECT TO CREATE");
                    String name = scanner.nextLine();
                    ps.addProject(name);
                    System.out.println("OK");
                    break;
                case PROJECT_LIST:
                    System.out.println("PROJECTS LIST:");
                    ps.listProject();
                    break;
                case PROJECT_CLEAR:
                    ps.clearProject();
                    System.out.println("OK");
                    break;
                case PROJECT_REMOVE:
                    System.out.println("ENTER ID OF PROJECT TO REMOVE");
                    String idRemove = scanner.nextLine();
                    ps.removeProject(idRemove);
                    System.out.println("OK");
                    break;
                case PROJECT_EDIT:
                    System.out.println("ENTER ID OF PROJECT TO EDIT");
                    String idEdit = scanner.nextLine();
                    System.out.println("ENTER NEW NAME OF PROJECT TO EDIT");
                    String newName = scanner.nextLine();
                    ps.editProject(idEdit, newName);
                    System.out.println("OK");
                    break;
                case TASK_CREATE:
                    System.out.println("ENTER ID OF PROJECT TO CREATE TASK");
                    System.out.println("PROJECTS LIST:");
                    ps.listProject();
                    String projectId = scanner.nextLine();
                    for (Project project : projectRepository.projectLinkedHashMap.values()) {
                        if (project.getId().equals(projectId)) {
                            System.out.println("ENTER NAME OF TASK TO CREATE");
                            String taskName = scanner.nextLine();
                            ts.addTask(projectId, taskName);
                            System.out.println("OK");
                            break label;
                        }
                    }
                    System.out.println("NO SUCH PROJECT ID");
                    break;
                case TASK_LIST:
                    System.out.println("ENTER ID OF PROJECT TO LIST TASKS");
                    String projectIdTaskList = scanner.nextLine();
                    System.out.println("TASKS LIST:");
                    ts.listTask(projectIdTaskList);
                    break;
                case TASK_REMOVE:
                    System.out.println("ENTER ID OF PROJECT TO REMOVE TASK");
                    String projectIdTaskRemove = scanner.nextLine();
                    ts.listTask(projectIdTaskRemove);
                    System.out.println("ENTER ID OF TASk TO REMOVE");
                    String taskIdRemove = scanner.nextLine();
                    ts.removeTask(taskIdRemove);
                    System.out.println("OK");
                    break;
                case TASK_EDIT:
                    System.out.println("ENTER ID OF PROJECT TO EDIT TASK");
                    String projectIdTaskEdit = scanner.nextLine();
                    ts.listTask(projectIdTaskEdit);
                    System.out.println("ENTER ID OF TASK TO EDIT");
                    String taskIdEdit = scanner.nextLine();
                    System.out.println("ENTER NEW NAME OF TASK TO EDIT");
                    String newTaskName = scanner.nextLine();
                    ts.editTask(taskIdEdit, newTaskName);
                    System.out.println("OK");
                    break;
                case TASK_CLEAR:
                    System.out.println("ENTER ID OF PROJECT TO CLEAR TASKS");
                    String projectIdTaskClear = scanner.nextLine();
                    ts.clearTask(projectIdTaskClear);
                    System.out.println("OK");
                    break;
                case HELP:
                    Bootstrap.help();
                    break;
            }
        }
    }
}
