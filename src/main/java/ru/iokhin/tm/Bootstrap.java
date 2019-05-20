package ru.iokhin.tm;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.service.IServiceLocator;
import ru.iokhin.tm.command.*;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.Task;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.enumerated.RoleType;
import ru.iokhin.tm.repository.ProjectRepository;
import ru.iokhin.tm.repository.TaskRepository;
import ru.iokhin.tm.repository.UserRepository;
import ru.iokhin.tm.service.ProjectService;
import ru.iokhin.tm.service.ServiceLocator;
import ru.iokhin.tm.service.TaskService;
import ru.iokhin.tm.service.UserService;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

@Getter
@Setter
public final class Bootstrap {

    @NotNull
    private Map<String, AbstractCommand> commandMap = new LinkedHashMap<>(0);

//    public final IServiceLocator serviceLocator = new ServiceLocator();

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

    private boolean isAuth() {
        return getCurrentUser() != null;
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

    void generateTestData(){
        //Test data
        //---------

        @NotNull
        User userAdmin = new User(RoleType.ADMIN, "admin", "admin");

        @NotNull
        User userUser = new User(RoleType.USER, "user", "user");

        userRepository.userMap.put(userAdmin.getId(), userAdmin);
        userRepository.userMap.put(userUser.getId(), userUser);

        Project project1 = new Project("Project 1", userUser.getId());
        Project project2 = new Project("Project 2", userUser.getId());
        Project project3 = new Project("Project 3", userAdmin.getId());

        projectRepository.getRepositoryMap().put(project1.getId(), project1);
        projectRepository.getRepositoryMap().put(project2.getId(), project2);
        projectRepository.getRepositoryMap().put(project3.getId(), project3);

        Task task1 = new Task(project1.getUserId(), project1.getId(), "TASK 1 FOR PROJECT 1");
        Task task2 = new Task(project1.getUserId(), project1.getId(), "TASK 2 FOR PROJECT 1");
        Task task3 = new Task(project2.getUserId(), project2.getId(), "TASK 3 FOR PROJECT 2");
        Task task4 = new Task(project2.getUserId(), project2.getId(), "TASK 4 FOR PROJECT 2");
        Task task5 = new Task(project3.getUserId(), project3.getId(), "TASK 5 FOR PROJECT 3");

        taskRepository.getRepositoryMap().put(task1.getId(), task1);
        taskRepository.getRepositoryMap().put(task2.getId(), task2);
        taskRepository.getRepositoryMap().put(task3.getId(), task3);
        taskRepository.getRepositoryMap().put(task4.getId(), task4);
        taskRepository.getRepositoryMap().put(task5.getId(), task5);

        //---------
    }
}
