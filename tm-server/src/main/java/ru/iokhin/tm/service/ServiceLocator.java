package ru.iokhin.tm.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.service.*;
import ru.iokhin.tm.repository.ProjectRepository;
import ru.iokhin.tm.repository.SessionRepository;
import ru.iokhin.tm.repository.TaskRepository;
import ru.iokhin.tm.repository.UserRepository;

import java.sql.Connection;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceLocator implements IServiceLocator {

    private Connection connection;

    @NotNull
    private ProjectRepository projectRepository;

    @NotNull
    private TaskRepository taskRepository;

    @NotNull
    private UserRepository userRepository;

    @NotNull
    private SessionRepository sessionRepository;

    @NotNull
    private ProjectService projectService;

    @NotNull
    private TaskService taskService;

    @NotNull
    private UserService userService;

    @NotNull
    private final TerminalService terminalService = new TerminalService();

    @NotNull
    private SessionService sessionService;

    public ServiceLocator(Connection connection) {
        this.connection = connection;
        this.projectRepository = new ProjectRepository(connection);
        this.taskRepository = new TaskRepository(connection);
        this.userRepository = new UserRepository(connection);
        this.sessionRepository = new SessionRepository(connection);
        this.projectService = new ProjectService(this.projectRepository);
        this.taskService = new TaskService(this.taskRepository);
        this.userService = new UserService(this.userRepository, this.projectRepository, this.taskRepository);
        this.sessionService = new SessionService(this.sessionRepository);
    }
}
