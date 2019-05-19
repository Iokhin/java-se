package ru.iokhin.tm;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.IServiceLocator;
import ru.iokhin.tm.command.*;
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

//    @Nullable
//    public User getCurrentUser() {
//        return currentUser;
//    }
//
//    public void setCurrentUser(@Nullable User currentUser) {
//        this.currentUser = currentUser;
//    }

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
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            if (isAbstractCommand(commandInstance)) {
                commandRegister(commandInstance);
            }
        }

        System.out.println("***WELCOME TO TASK MANAGER***");

        @NotNull final Scanner scanner = new Scanner(System.in);

        @NotNull
        String input = "";

        while (!input.equals("exit")) {
            input = scanner.nextLine();
            AbstractCommand command = this.commandMap.get(input);
            if (command == null) continue;
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

    private boolean isAbstractCommand(Object o) {
        return o instanceof AbstractCommand;
    }
}
