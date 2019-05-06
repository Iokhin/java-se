package ru.iokhin.tm;

import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.tools.ProjectsCollectionTools;

import java.util.*;

public class Runner {

    private final static String PROJECT_CREATE = "project-create";
    private final static String PROJECT_LIST = "project-list";
    private final static String PROJECT_EDIT = "project-edit";
    private final static String PROJECT_REMOVE = "project-remove";
    private final static String PROJECT_CLEAR = "project-clear";

    private final static String TASK_MANAGER = "task-manager";
    private final static String TASK_CREATE = "task-create";
    private final static String TASK_LIST = "task-list";
    private final static String TASK_EDIT = "task-edit";
    private final static String TASK_REMOVE = "task-remove";
    private final static String TASK_CLEAR = "task-clear";

    private final static String HELP = "help";
    private final static String EXIT = "exit";
    private final static String END = "end";

    private static void help() {
        System.out.println("help: Show all commands.");
        System.out.println("project-create: Create new project.");
        System.out.println("project-list: Show all projects.");
        System.out.println("project-edit: Edit selected project.");
        System.out.println("project-remove: Remove selected project");
        System.out.println("project-clear: Remove all projects");

        System.out.println("task-manager: Chose project to manage tasks");
        System.out.println("task-create: Create new task.");
        System.out.println("task-list: Show all tasks for chosen project.");
        System.out.println("task-edit: Edit selected task.");
        System.out.println("task-remove: Remove selected task");
        System.out.println("task-clear: Remove all tasks for chosen project");
        System.out.println("exit: Exit task manager for current project");

        System.out.println("end: End the program");
    }

    public static void main(String[] args) {
        System.out.println("***WELCOME TO TASK MANAGER***");
        Scanner scanner = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);
        String input = "";
        ProjectsCollectionTools projectsCollectionTools = new ProjectsCollectionTools();
        while (!input.equals(END)) {
            input = scanner.nextLine();
            switch (input) {
                case PROJECT_CREATE:
                    projectsCollectionTools.addProject();
                    break;
                case PROJECT_LIST:
                    projectsCollectionTools.listProjects();
                    break;
                case PROJECT_CLEAR:
                    projectsCollectionTools.clearProjects();
                    break;
                case PROJECT_REMOVE:
                    projectsCollectionTools.removeProject();
                    break;
                case PROJECT_EDIT:
                    projectsCollectionTools.editProject();
                    break;
                case HELP:
                    Runner.help();
                    break;
                case TASK_MANAGER:
                    String taskInput = "";
                    Project project = null;
                    System.out.println("CHOSE PROJECT TO MANAGE");
                    projectsCollectionTools.listProjects();
                    int item = scannerInt.nextInt() - 1;
                    if (item < projectsCollectionTools.projectsCollection.size() && item >= 0) {
                        project = projectsCollectionTools.projectsCollection.get(item);
                        System.out.println(project.getName() + " WAS CHOSEN");
                    } else {
                        System.out.println("NO PROJECT WITH SUCH INDEX");
                        break;
                    }
                    while (!taskInput.equals(EXIT)) {
                        taskInput = scanner.nextLine();
                        switch (taskInput) {
                            case TASK_CREATE:
                                project.tasksCollectionTools.addTask();
                                break;
                            case TASK_LIST:
                                project.tasksCollectionTools.listTask();
                                break;
                            case TASK_REMOVE:
                                project.tasksCollectionTools.removeTask();
                                break;
                            case TASK_EDIT:
                                project.tasksCollectionTools.editTask();
                                break;
                            case TASK_CLEAR:
                                project.tasksCollectionTools.clearTasks();
                                break;
                        }
                    }
            }
        }
    }
}



