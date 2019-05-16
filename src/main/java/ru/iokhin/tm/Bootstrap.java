package ru.iokhin.tm;

import ru.iokhin.tm.api.IServiceLocator;
import ru.iokhin.tm.command.*;
import ru.iokhin.tm.command.project.*;
import ru.iokhin.tm.command.system.AboutCommand;
import ru.iokhin.tm.command.system.ExitCommand;
import ru.iokhin.tm.command.system.HelpCommand;
import ru.iokhin.tm.command.task.*;
import ru.iokhin.tm.command.user.*;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.enumerated.RoleType;
import ru.iokhin.tm.repository.ProjectRepository;
import ru.iokhin.tm.repository.TaskRepository;
import ru.iokhin.tm.repository.UserRepository;
import ru.iokhin.tm.service.ProjectService;
import ru.iokhin.tm.service.TaskService;
import ru.iokhin.tm.service.UserService;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Bootstrap implements IServiceLocator {

    private Map<String, AbstractCommand> commandMap = new LinkedHashMap<>(0);

    public ProjectRepository projectRepository = new ProjectRepository();
    public TaskRepository taskRepository = new TaskRepository();
    public ProjectService projectService = new ProjectService(projectRepository);
    public TaskService taskService = new TaskService(taskRepository);
    public UserRepository userRepository = new UserRepository();
    public UserService userService = new UserService(userRepository);
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    static void init() {

        Bootstrap bootstrap = new Bootstrap();

        User userAdmin = new User(RoleType.ADMIN, "admin", "admin");
        User userUser = new User(RoleType.USER, "user", "user");

        bootstrap.userRepository.userMap.put(userAdmin.getUserId(), userAdmin);
        bootstrap.userRepository.userMap.put(userUser.getUserId(), userUser);

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

        AbstractCommand userAuthorization = new UserAuthorizationCommand(bootstrap);
        AbstractCommand userEndSession = new UserEndSessionCommand(bootstrap);
        AbstractCommand userPasswordChange = new UserPasswordChangeCommand(bootstrap);
        AbstractCommand userRegistration = new UserRegistrationCommand(bootstrap);
        AbstractCommand userProfileEdit = new UserProfileEditCommand(bootstrap);
        AbstractCommand userList = new UserListCommand(bootstrap);

        AbstractCommand help = new HelpCommand(bootstrap);
        AbstractCommand exit = new ExitCommand(bootstrap);
        AbstractCommand about = new AboutCommand(bootstrap);

        bootstrap.commandMap.put(projectCreateCommand.name(), projectCreateCommand);
        bootstrap.commandMap.put(projectListCommand.name(), projectListCommand);
        bootstrap.commandMap.put(projectRemoveCommand.name(), projectRemoveCommand);
        bootstrap.commandMap.put(projectRemoveAllCommand.name(), projectRemoveAllCommand);
        bootstrap.commandMap.put(projectEditCommand.name(), projectEditCommand);

        bootstrap.commandMap.put(taskCreateCommand.name(), taskCreateCommand);
        bootstrap.commandMap.put(taskListCommand.name(), taskListCommand);
        bootstrap.commandMap.put(taskRemoveCommand.name(), taskRemoveCommand);
        bootstrap.commandMap.put(taskRemoveAllCommand.name(), taskRemoveAllCommand);
        bootstrap.commandMap.put(taskEditCommand.name(), taskEditCommand);

        bootstrap.commandMap.put(userAuthorization.name(), userAuthorization);
        bootstrap.commandMap.put(userEndSession.name(), userEndSession);
        bootstrap.commandMap.put(userPasswordChange.name(), userPasswordChange);
        bootstrap.commandMap.put(userRegistration.name(), userRegistration);
        bootstrap.commandMap.put(userProfileEdit.name(), userProfileEdit);
        bootstrap.commandMap.put(userList.name(), userList);

        bootstrap.commandMap.put(help.name(), help);
        bootstrap.commandMap.put(exit.name(), exit);
        bootstrap.commandMap.put(about.name(), about);

        System.out.println("***WELCOME TO TASK MANAGER***");
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals(bootstrap.commandMap.get("exit").name())) {
            input = scanner.nextLine();
            for (Map.Entry<String, AbstractCommand> entry : bootstrap.commandMap.entrySet()) {
                if (input.equals(entry.getKey())) {
                    entry.getValue().execute();
                }
            }

        }
    }

    @Override
    public UserService getUserService() {
        return userService;
    }

    @Override
    public ProjectService getProjectService() {
        return projectService;
    }

    @Override
    public TaskService getTaskService() {
        return taskService;
    }

    public Map<String, AbstractCommand> getCommandMap() {
        return commandMap;
    }
}
