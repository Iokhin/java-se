package ru.iokhin.tm;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.api.service.IServiceLocator;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.api.service.IUserService;
import ru.iokhin.tm.command.*;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.enumerated.RoleType;
import ru.iokhin.tm.exeption.AuthException;
import ru.iokhin.tm.exeption.NonexistentCommandException;
import ru.iokhin.tm.service.*;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
final class Bootstrap {
    @NotNull
    private final Map<String, AbstractCommand> commandMap = new LinkedHashMap<>(0);

    @NotNull
    private final IServiceLocator serviceLocator = new ServiceLocator(commandMap);

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
            try {
                execute(command);
            } catch (AuthException | IllegalArgumentException | NonexistentCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void execute(@Nullable AbstractCommand command) throws AuthException, NonexistentCommandException {
        if (command == null) throw new NonexistentCommandException();
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

        userService.add(RoleType.ADMIN, "3afe899e-ee58-4543-8076-48af7f1abd71", "admin", "admin");
        userService.add(RoleType.USER, "7cfe899e-ee58-3290-8076-48af7f1abd66", "user", "user");

        projectService.add(userService.findByLogin("user").getId(), "Project 1");
        projectService.add(userService.findByLogin("admin").getId(), "Project 2");

        for (Project project : projectService.findAllByUserId(userService.findByLogin("admin").getId())) {
            taskService.add(userService.findByLogin("admin"), project.getId(), "ADMIN TASK 5");
            taskService.add(userService.findByLogin("admin"), project.getId(), "ADMIN TASK 6");
            taskService.add(userService.findByLogin("admin"), project.getId(), "ADMIN TASK 7");
            taskService.add(userService.findByLogin("admin"), project.getId(), "ADMIN TASK 8");
        }

        for (Project project : projectService.findAllByUserId(userService.findByLogin("user").getId())) {
            taskService.add(userService.findByLogin("user"), project.getId(), "USER TASK 1");
            taskService.add(userService.findByLogin("user"), project.getId(), "USER TASK 2");
            taskService.add(userService.findByLogin("user"), project.getId(), "USER TASK 3");
            taskService.add(userService.findByLogin("user"), project.getId(), "USER TASK 4");
        }
        //---------
    }
}
