package ru.iokhin.tm;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

@Getter
@Setter
public final class Bootstrap implements IServiceLocator {

    @NotNull
    private Map<String, AbstractCommand> commandMap = new LinkedHashMap<>(0);

    @NotNull
    private final ProjectRepository projectRepository = new ProjectRepository();

    @NotNull
    private final TaskRepository taskRepository = new TaskRepository();

    @NotNull
    private final UserRepository userRepository = new UserRepository();

    @NotNull
    private final ProjectService projectService = new ProjectService(projectRepository);

    @NotNull
    private final TaskService taskService = new TaskService(taskRepository);

    @NotNull
    private final UserService userService = new UserService(userRepository);

    @Nullable
    private User currentUser;

    @Nullable
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(@Nullable User currentUser) {
        this.currentUser = currentUser;
    }

    private boolean isAuth() {
        return getCurrentUser() != null;
    }

    void init(Class[] CLASSES) {

        @NotNull
        User userAdmin = new User(RoleType.ADMIN, "admin", "admin");

        @NotNull
        User userUser = new User(RoleType.USER, "user", "user");

        this.userRepository.userMap.put(userAdmin.getUserId(), userAdmin);
        this.userRepository.userMap.put(userUser.getUserId(), userUser);

        for (Class commandClass : CLASSES) {
            AbstractCommand commandInstance = null;
            try {
                commandInstance = (AbstractCommand) commandClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (isAbstractCommand(commandInstance)) {
                commandRegister(commandInstance);
            }
        }

//        @NotNull
//        AbstractCommand projectCreateCommand = new ProjectCreateCommand(this);
//        @NotNull
//        AbstractCommand projectListCommand = new ProjectListCommand(this);
//        @NotNull
//        AbstractCommand projectRemoveCommand = new ProjectRemoveCommand(this);
//        @NotNull
//        AbstractCommand projectRemoveAllCommand = new ProjectRemoveAllCommand(this);
//        @NotNull
//        AbstractCommand projectEditCommand = new ProjectEditCommand(this);
//
//
//        @NotNull
//        AbstractCommand taskCreateCommand = new TaskCreateCommand(this);
//        @NotNull
//        AbstractCommand taskListCommand = new TaskListCommand(this);
//        @NotNull
//        AbstractCommand taskRemoveCommand = new TaskRemoveCommand(this);
//        @NotNull
//        AbstractCommand taskRemoveAllCommand = new TaskRemoveAllCommand(this);
//        @NotNull
//        AbstractCommand taskEditCommand = new TaskEditCommand(this);
//
//        @NotNull
//        AbstractCommand userAuthorization = new UserAuthorizationCommand(this);
//        @NotNull
//        AbstractCommand userEndSession = new UserEndSessionCommand(this);
//        @NotNull
//        AbstractCommand userPasswordChange = new UserPasswordChangeCommand(this);
//        @NotNull
//        AbstractCommand userRegistration = new UserRegistrationCommand(this);
//        @NotNull
//        AbstractCommand userProfileEdit = new UserProfileEditCommand(this);
//        @NotNull
//        AbstractCommand userList = new UserListCommand(this);
//
//        @NotNull
//        AbstractCommand help = new HelpCommand(this);
//        @NotNull
//        AbstractCommand exit = new ExitCommand(this);
//        @NotNull
//        AbstractCommand about = new AboutCommand(this);
//
//        this.commandMap.put(projectCreateCommand.name(), projectCreateCommand);
//        this.commandMap.put(projectListCommand.name(), projectListCommand);
//        this.commandMap.put(projectRemoveCommand.name(), projectRemoveCommand);
//        this.commandMap.put(projectRemoveAllCommand.name(), projectRemoveAllCommand);
//        this.commandMap.put(projectEditCommand.name(), projectEditCommand);
//
//        this.commandMap.put(taskCreateCommand.name(), taskCreateCommand);
//        this.commandMap.put(taskListCommand.name(), taskListCommand);
//        this.commandMap.put(taskRemoveCommand.name(), taskRemoveCommand);
//        this.commandMap.put(taskRemoveAllCommand.name(), taskRemoveAllCommand);
//        this.commandMap.put(taskEditCommand.name(), taskEditCommand);
//
//        this.commandMap.put(userAuthorization.name(), userAuthorization);
//        this.commandMap.put(userEndSession.name(), userEndSession);
//        this.commandMap.put(userPasswordChange.name(), userPasswordChange);
//        this.commandMap.put(userRegistration.name(), userRegistration);
//        this.commandMap.put(userProfileEdit.name(), userProfileEdit);
//        this.commandMap.put(userList.name(), userList);
//
//        this.commandMap.put(help.name(), help);
//        this.commandMap.put(exit.name(), exit);
//        this.commandMap.put(about.name(), about);

        System.out.println("***WELCOME TO TASK MANAGER***");

        @NotNull
        final Scanner scanner = new Scanner(System.in);

        @NotNull
        String input = "";

        while (!input.equals("exit")) {
            input = scanner.nextLine();
            AbstractCommand command = this.commandMap.get(input);
            execute(command);
        }
    }

    private void execute(@NotNull AbstractCommand command) {
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

    private void commandRegister(AbstractCommand abstractCommand) {
        abstractCommand.bootstrap = this;
        this.commandMap.put(abstractCommand.name(), abstractCommand);
    }

    private boolean isAbstractCommand(Object o){
        return o instanceof AbstractCommand;
    }
}
