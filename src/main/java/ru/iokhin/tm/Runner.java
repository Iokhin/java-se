package ru.iokhin.tm;

import ru.iokhin.tm.project.Project;
import ru.iokhin.tm.project.ProjectsCollectionTools;

import java.util.*;

public class Runner {

    public static void main(String[] args) {
        System.out.println("***WELCOME TO TASK MANAGER***");
        Scanner scanner = new Scanner(System.in);
        Scanner scannerInt = new Scanner(System.in);
        String input = "";
        ProjectsCollectionTools projectsCollectionTools = new ProjectsCollectionTools();
        while (!input.equals("end")) {
            input = scanner.nextLine();
            switch (input) {
                case "project-create":
                    System.out.println("[PROJECT CREATE]");
                    System.out.println("ENTER NAME:");
                    projectsCollectionTools.addProject(scanner.nextLine());
                    break;
                case "project-list":
                    projectsCollectionTools.listProjects();
                    break;
                case "project-clear":
                    projectsCollectionTools.clearProjects();
                    break;
                case "project-remove":
                    System.out.println("REMOVE BY INDEX OR BY NAME? ENTER 'INDEX' FOR INDEX AND 'NAME' FOR NAME");
                    input = scanner.nextLine();
                    if (input.toLowerCase().equals("name")) {
                        System.out.println("ENTER THE NAME OF PROJECT");
                        projectsCollectionTools.removeProject(scanner.nextLine());
                    } else if (input.toLowerCase().equals("index")) {
                        System.out.println("ENTER THE INDEX OF PROJECT");
                        projectsCollectionTools.removeProject(scanner.nextInt());
                    } else {
                        System.out.println("WRONG COMMAND");
                    }
                    break;
                case "project-edit":
                    System.out.println("EDIT BY INDEX OR BY NAME? ENTER 'INDEX' FOR INDEX AND 'NAME' FOR NAME");
                    input = scanner.nextLine();
                    if (input.toLowerCase().equals("name")) {
                        System.out.println("ENTER THE NAME OF PROJECT");
                        String in1 = scanner.nextLine();
                        System.out.println("ENTER A NEW NAME OF THE PROJECT");
                        String in2 = scanner.nextLine();
                        projectsCollectionTools.editProject(in1, in2);
                    } else if (input.toLowerCase().equals("index")) {
                        System.out.println("ENTER THE INDEX OF PROJECT");
                        projectsCollectionTools.listProjects();
                        int in3 = scannerInt.nextInt();
                        System.out.println("ENTER A NEW NAME OF THE PROJECT");
                        String in4 = scanner.nextLine();
                        projectsCollectionTools.editProject(in3, in4);
                    } else {
                        System.out.println("WRONG COMMAND");
                    }
                    break;
                case "help":

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
                    break;

                case "task-manager":
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
                    while (!taskInput.equals("exit")) {
                        taskInput = scanner.nextLine();
                        switch (taskInput) {
                            case "task-create":
                                System.out.println("ENTER A NAME OF TASK TO ADD");
                                String name = scanner.nextLine();
                                project.tasksCollectionTools.addTask(name);
                                break;
                            case "task-list":
                                project.tasksCollectionTools.listTask();
                                break;
                            case "task-remove":
                                System.out.println("REMOVE BY INDEX OR BY NAME? ENTER 'INDEX' FOR INDEX AND 'NAME' FOR NAME");
                                taskInput = scanner.nextLine();
                                if (taskInput.toLowerCase().equals("name")) {
                                    System.out.println("ENTER THE NAME OF TASK");
                                    project.tasksCollectionTools.removeTask(scanner.nextLine());
                                } else if (taskInput.toLowerCase().equals("index")) {
                                    System.out.println("ENTER THE INDEX OF TASK");
                                    project.tasksCollectionTools.removeTask(scanner.nextInt());
                                } else {
                                    System.out.println("WRONG COMMAND");
                                }
                                break;
                            case "task-edit":
                                System.out.println("EDIT BY INDEX OR BY NAME? ENTER 'INDEX' FOR INDEX AND 'NAME' FOR NAME");
                                taskInput = scanner.nextLine();
                                if (taskInput.toLowerCase().equals("name")) {
                                    System.out.println("ENTER THE NAME OF TASK");
                                    String in1 = scanner.nextLine();
                                    System.out.println("ENTER A NEW NAME OF THE TASK");
                                    String in2 = scanner.nextLine();
                                    project.tasksCollectionTools.editTask(in1, in2);
                                } else if (taskInput.toLowerCase().equals("index")) {
                                    System.out.println("ENTER THE INDEX OF TASK");
                                    project.tasksCollectionTools.listTask();
                                    int in3 = scannerInt.nextInt();
                                    System.out.println("ENTER A NEW NAME OF THE TASK");
                                    String in4 = scanner.nextLine();
                                    project.tasksCollectionTools.editTask(in3, in4);
                                } else {
                                    System.out.println("WRONG COMMAND");
                                }
                                break;
                            case "task-clear":
                                project.tasksCollectionTools.clearTasks();
                                break;
                        }
                    }
            }
        }
    }
}



