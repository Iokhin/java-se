package ru.iokhin.tm;

import ru.iokhin.tm.Command.*;
import ru.iokhin.tm.repository.ProjectRepository;
import ru.iokhin.tm.repository.TaskRepository;
import ru.iokhin.tm.service.ProjectService;
import ru.iokhin.tm.service.TaskService;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Bootstrap {

    private Map<String, AbstractCommand> commandMap = new LinkedHashMap<>(0);

    public ProjectRepository projectRepository = new ProjectRepository();
    public TaskRepository taskRepository = new TaskRepository();
    public ProjectService ps = new ProjectService(projectRepository);
    public TaskService ts = new TaskService(taskRepository);

    static void init() {

        Bootstrap bootstrap = new Bootstrap();

        AbstractCommand projectCreateCommand = new ProjectCreateCommand(bootstrap);
        AbstractCommand projectListCommand = new ProjectListCommand(bootstrap);
        AbstractCommand projectRemoveCommand = new ProjectRemoveCommand(bootstrap);
        AbstractCommand projectRemoveAllCommand = new ProjectRemoveAllCommand(bootstrap);
        AbstractCommand projectEditCommand = new ProjectEditCommand(bootstrap);

        AbstractCommand taskCreateCommand = new TaskCreateCommand(bootstrap);
        AbstractCommand taskListCommand = new TaskListCommand(bootstrap);
        AbstractCommand taskRemoveCommand = new TaskRemoveCommand(bootstrap);
        AbstractCommand taskRemoveAllCommand = new TaskRemoveAllCommand(bootstrap);
        AbstractCommand taskEditCommand = new TaskEditCommand(bootstrap);

        AbstractCommand help = new HelpCommand(bootstrap);
        AbstractCommand exit = new ExitCommand(bootstrap);

        bootstrap.commandMap.put(projectCreateCommand.getName(), projectCreateCommand);
        bootstrap.commandMap.put(projectListCommand.getName(), projectListCommand);
        bootstrap.commandMap.put(projectRemoveCommand.getName(), projectRemoveCommand);
        bootstrap.commandMap.put(projectRemoveAllCommand.getName(), projectRemoveAllCommand);
        bootstrap.commandMap.put(projectEditCommand.getName(), projectEditCommand);

        bootstrap.commandMap.put(taskCreateCommand.getName(), taskCreateCommand);
        bootstrap.commandMap.put(taskListCommand.getName(), taskListCommand);
        bootstrap.commandMap.put(taskRemoveCommand.getName(), taskRemoveCommand);
        bootstrap.commandMap.put(taskRemoveAllCommand.getName(), taskRemoveAllCommand);
        bootstrap.commandMap.put(taskEditCommand.getName(), taskEditCommand);

        bootstrap.commandMap.put(help.getName(), help);
        bootstrap.commandMap.put(exit.getName(), exit);

        System.out.println("***WELCOME TO TASK MANAGER***");
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals(bootstrap.commandMap.get("exit").getName())) {
            input = scanner.nextLine();
            for (Map.Entry<String, AbstractCommand> entry : bootstrap.commandMap.entrySet()) {
                if (input.equals(entry.getKey())) {
                    entry.getValue().execute();
                }
            }

        }
    }
}
