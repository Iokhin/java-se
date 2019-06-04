package ru.iokhin.tm.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.repository.IProjectRepository;
import ru.iokhin.tm.api.service.*;
//import ru.iokhin.tm.repository.ProjectRepository;
//import ru.iokhin.tm.repository.SessionRepository;
//import ru.iokhin.tm.repository.TaskRepository;
//import ru.iokhin.tm.repository.UserRepository;

import java.sql.Connection;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceLocator implements IServiceLocator {

    private Connection connection;

//    @NotNull
//    private ProjectRepository projectRepository;
//
//    @NotNull
//    private TaskRepository taskRepository;

//    @NotNull
//    private UserRepository userRepository;

//    @NotNull
//    private SessionRepository sessionRepository;

    @NotNull
    private IProjectService projectService;

    @NotNull
    private ITaskService taskService;

    @NotNull
    private IUserService userService;

    @NotNull
    private final TerminalService terminalService = new TerminalService();

    @NotNull
    private ISessionService sessionService;

    public ServiceLocator(@NotNull final ISessionService sessionService, @NotNull final IUserService userService,
                          @NotNull final IProjectService projectService, @NotNull final ITaskService taskService) {
//        this.connection = connection;
//        this.projectRepository = new ProjectRepository(connection);
//        this.taskRepository = new TaskRepository(connection);
//        this.userRepository = new UserRepository(connection);
//        this.sessionRepository = new SessionRepository(connection);
        this.projectService = projectService;
        this.taskService = taskService;
        this.userService = userService;
        this.sessionService = sessionService;
    }
}
