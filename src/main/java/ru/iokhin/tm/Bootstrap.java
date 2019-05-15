package ru.iokhin.tm;

import ru.iokhin.tm.command.*;
import ru.iokhin.tm.command.projectCommand.*;
import ru.iokhin.tm.command.taskCommand.*;
import ru.iokhin.tm.command.userCommand.*;
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

        AbstractCommand projectCreateCommand = new ProjectCreateCommand(bootstrap, "project-create", "project-create: Create new project.");
        AbstractCommand projectListCommand = new ProjectListCommand(bootstrap, "project-list", "project-list: Show all projects.");
        AbstractCommand projectRemoveCommand = new ProjectRemoveCommand(bootstrap, "project-remove", "project-remove: Remove selected project");
        AbstractCommand projectRemoveAllCommand = new ProjectRemoveAllCommand(bootstrap, "project-clear", "project-clear: Remove all projects");
        AbstractCommand projectEditCommand = new ProjectEditCommand(bootstrap, "project-edit", "project-edit: Edit selected project.");

        AbstractCommand taskCreateCommand = new TaskCreateCommand(bootstrap, "task-create", "task-create: Create new task.");
        AbstractCommand taskListCommand = new TaskListCommand(bootstrap, "task-list", "task-list: Show all tasks for chosen project.");
        AbstractCommand taskRemoveCommand = new TaskRemoveCommand(bootstrap, "task-remove", "task-remove: Remove selected task");
        AbstractCommand taskRemoveAllCommand = new TaskRemoveAllCommand(bootstrap, "task-clear", "task-clear: Remove all tasks for chosen project");
        AbstractCommand taskEditCommand = new TaskEditCommand(bootstrap, "task-edit", "task-edit: Edit selected task.");

        AbstractCommand userAuthorization = new UserAuthorizationCommand(bootstrap, "user-login", "user-login: Authorize user");
        AbstractCommand userEndSession = new UserEndSessionCommand(bootstrap, "user-logout", "user-logout: Log out current user");
        AbstractCommand userPasswordChange = new UserPasswordChangeCommand(bootstrap, "user-pass-change", "user-pass-change: Change the current user's password");
        AbstractCommand userRegistration = new UserRegistrationCommand(bootstrap, "user-registration", "user-registration: Register a new user");
        AbstractCommand userProfileEdit = new UserProfileEditCommand(bootstrap, "user-edit", "user-edit: Edit the current user's profile");
        AbstractCommand userList = new UserListCommand(bootstrap, "user-list", "user-list: List all users");

        AbstractCommand help = new HelpCommand(bootstrap, "help", "help: Show all commands.");
        AbstractCommand exit = new ExitCommand(bootstrap, "exit", "exit: Exit task manager.");
        AbstractCommand about = new AboutCommand(bootstrap, "about", "about: Show build info");

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
        bootstrap.commandMap.put(about.getName(), about);

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
