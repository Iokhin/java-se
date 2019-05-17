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

    public boolean isAuth() {
        return getCurrentUser() != null;
    }

    void init() {

        User userAdmin = new User(RoleType.ADMIN, "admin", "admin");
        User userUser = new User(RoleType.USER, "user", "user");

        this.userRepository.userMap.put(userAdmin.getUserId(), userAdmin);
        this.userRepository.userMap.put(userUser.getUserId(), userUser);

        AbstractCommand projectCreateCommand = new ProjectCreateCommand(this);
        AbstractCommand projectListCommand = new ProjectListCommand(this);
        AbstractCommand projectRemoveCommand = new ProjectRemoveCommand(this);
        AbstractCommand projectRemoveAllCommand = new ProjectRemoveAllCommand(this);
        AbstractCommand projectEditCommand = new ProjectEditCommand(this);

        AbstractCommand taskCreateCommand = new TaskCreateCommand(this);
        AbstractCommand taskListCommand = new TaskListCommand(this);
        AbstractCommand taskRemoveCommand = new TaskRemoveCommand(this);
        AbstractCommand taskRemoveAllCommand = new TaskRemoveAllCommand(this);
        AbstractCommand taskEditCommand = new TaskEditCommand(this);

        AbstractCommand userAuthorization = new UserAuthorizationCommand(this);
        AbstractCommand userEndSession = new UserEndSessionCommand(this);
        AbstractCommand userPasswordChange = new UserPasswordChangeCommand(this);
        AbstractCommand userRegistration = new UserRegistrationCommand(this);
        AbstractCommand userProfileEdit = new UserProfileEditCommand(this);
        AbstractCommand userList = new UserListCommand(this);

        AbstractCommand help = new HelpCommand(this);
        AbstractCommand exit = new ExitCommand(this);
        AbstractCommand about = new AboutCommand(this);

        this.commandMap.put(projectCreateCommand.name(), projectCreateCommand);
        this.commandMap.put(projectListCommand.name(), projectListCommand);
        this.commandMap.put(projectRemoveCommand.name(), projectRemoveCommand);
        this.commandMap.put(projectRemoveAllCommand.name(), projectRemoveAllCommand);
        this.commandMap.put(projectEditCommand.name(), projectEditCommand);

        this.commandMap.put(taskCreateCommand.name(), taskCreateCommand);
        this.commandMap.put(taskListCommand.name(), taskListCommand);
        this.commandMap.put(taskRemoveCommand.name(), taskRemoveCommand);
        this.commandMap.put(taskRemoveAllCommand.name(), taskRemoveAllCommand);
        this.commandMap.put(taskEditCommand.name(), taskEditCommand);

        this.commandMap.put(userAuthorization.name(), userAuthorization);
        this.commandMap.put(userEndSession.name(), userEndSession);
        this.commandMap.put(userPasswordChange.name(), userPasswordChange);
        this.commandMap.put(userRegistration.name(), userRegistration);
        this.commandMap.put(userProfileEdit.name(), userProfileEdit);
        this.commandMap.put(userList.name(), userList);

        this.commandMap.put(help.name(), help);
        this.commandMap.put(exit.name(), exit);
        this.commandMap.put(about.name(), about);

        System.out.println("***WELCOME TO TASK MANAGER***");
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (!input.equals("exit")) {
            input = scanner.nextLine();
            AbstractCommand command = this.commandMap.get(input);
            execute(command);
        }
    }

    void execute(AbstractCommand command) {
        if (command == null)
            return;
        if (!command.security()) {
            command.execute();
        } else {
            if (this.isAuth())
                command.execute();
            else {
                this.commandMap.get("user-login").execute();
                if (this.isAuth())
                    command.execute();
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
