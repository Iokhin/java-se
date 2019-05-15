package ru.iokhin.tm;

import ru.iokhin.tm.Command.*;
import ru.iokhin.tm.Command.ProjectCommand.*;
import ru.iokhin.tm.Command.TaskCommand.*;
import ru.iokhin.tm.Command.UserCommand.*;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.repository.ProjectRepository;
import ru.iokhin.tm.repository.TaskRepository;
import ru.iokhin.tm.repository.UserRepository;
import ru.iokhin.tm.service.ProjectService;
import ru.iokhin.tm.service.TaskService;
import ru.iokhin.tm.service.UserService;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Bootstrap implements ServiceLocator {

    private Map<String, AbstractCommand> commandMap = new LinkedHashMap<>(0);

    public ProjectRepository projectRepository = new ProjectRepository();
    public TaskRepository taskRepository = new TaskRepository();
    public ProjectService ps = new ProjectService(projectRepository);
    public TaskService ts = new TaskService(taskRepository);
    public UserRepository userRepository = new UserRepository();
    public UserService us = new UserService(userRepository);
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

        bootstrap.commandMap.put(userAuthorization.getName(), userAuthorization);
        bootstrap.commandMap.put(userEndSession.getName(), userEndSession);
        bootstrap.commandMap.put(userPasswordChange.getName(), userPasswordChange);
        bootstrap.commandMap.put(userRegistration.getName(), userRegistration);
        bootstrap.commandMap.put(userProfileEdit.getName(), userProfileEdit);
        bootstrap.commandMap.put(userList.getName(), userList);

        bootstrap.commandMap.put(help.getName(), help);
        bootstrap.commandMap.put(exit.getName(), exit);

        System.out.println("***WELCOME TO TASK MANAGER***");
        userRegistration.execute();
        userAuthorization.execute();
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

    @Override
    public UserService getUserService() {
        return us;
    }

    @Override
    public ProjectService getProjectService() {
        return ps;
    }

    @Override
    public TaskService getTaskService() {
        return ts;
    }
}
