package ru.iokhin.tm;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.api.service.IServiceLocator;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.api.service.IUserService;
import ru.iokhin.tm.command.*;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.enumerated.RoleType;
import ru.iokhin.tm.exeption.AuthException;
import ru.iokhin.tm.service.*;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
public final class Bootstrap {

    @NotNull
    final IServiceLocator serviceLocator = new ServiceLocator();

    @NotNull
    private final Map<String, AbstractCommand> commandMap = new LinkedHashMap<>(0);

    private boolean isAuth() {
        return serviceLocator.getUserService().getCurrentUser() != null;
    }

    void init(Class[] CLASSES) {

        generateTestData();

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

        @NotNull String input = "";

        while (!input.equals("exit")) {
            input = serviceLocator.getTerminalService().nextLine();
            AbstractCommand command = this.commandMap.get(input);
            if (command == null) continue;
            try {
                execute(command);
            } catch (AuthException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void execute(@NotNull AbstractCommand command) throws AuthException {
        if (!command.security()) {
            command.execute();
        } else {
            if (this.isAuth())
                command.execute();
            else {
                throw new AuthException();
            }
        }
    }

    private void commandRegister(AbstractCommand abstractCommand) {
        abstractCommand.setBootstrap(this);
        abstractCommand.setServiceLocator(serviceLocator);
        this.commandMap.put(abstractCommand.name(), abstractCommand);
    }

    private boolean isAbstractCommand(Object o) {
        return o instanceof AbstractCommand;
    }

    private void generateTestData() {
        //Test data
        //---------

        @NotNull final IProjectService projectService = serviceLocator.getProjectService();
        @NotNull final ITaskService taskService = serviceLocator.getTaskService();
        @NotNull final IUserService userService = serviceLocator.getUserService();

        userService.add(RoleType.ADMIN, "admin", "admin");
        userService.add(RoleType.USER, "user", "user");

        projectService.add(userService.findByLogin("user"), "Project 1");
        projectService.add(userService.findByLogin("admin"), "Project 2");

        for (Project project : projectService.findAllByUser(userService.findByLogin("admin"))) {
            taskService.add(userService.findByLogin("admin"), project.getId(), "ADMIN TASK 5");
            taskService.add(userService.findByLogin("admin"), project.getId(), "ADMIN TASK 6");
            taskService.add(userService.findByLogin("admin"), project.getId(), "ADMIN TASK 7");
            taskService.add(userService.findByLogin("admin"), project.getId(), "ADMIN TASK 8");
        }

        for (Project project : projectService.findAllByUser(userService.findByLogin("user"))) {
            taskService.add(userService.findByLogin("user"), project.getId(), "USER TASK 1");
            taskService.add(userService.findByLogin("user"), project.getId(), "USER TASK 2");
            taskService.add(userService.findByLogin("user"), project.getId(), "USER TASK 3");
            taskService.add(userService.findByLogin("user"), project.getId(), "USER TASK 4");
        }

        //---------
    }
}
