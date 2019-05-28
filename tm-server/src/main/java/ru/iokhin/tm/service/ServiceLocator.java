package ru.iokhin.tm.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.service.*;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.entity.Session;
import ru.iokhin.tm.repository.ProjectRepository;
import ru.iokhin.tm.repository.SessionRepository;
import ru.iokhin.tm.repository.TaskRepository;
import ru.iokhin.tm.repository.UserRepository;

import java.util.Map;
@Getter
@AllArgsConstructor
public class ServiceLocator implements IServiceLocator {
    @NotNull
    private final Map<String, AbstractCommand> commandMap;

    @NotNull
    private final ProjectRepository projectRepository = new ProjectRepository();

    @NotNull
    private final TaskRepository taskRepository = new TaskRepository();

    @NotNull
    private final UserRepository userRepository = new UserRepository();

    @NotNull
    private final SessionRepository sessionRepository = new SessionRepository();

    @NotNull
    private final ProjectService projectService = new ProjectService(projectRepository);

    @NotNull
    private final TaskService taskService = new TaskService(taskRepository);

    @NotNull
    private final UserService userService = new UserService(userRepository);

    @NotNull
    private final TerminalService terminalService = new TerminalService();

    @NotNull
    private final SessionService sessionService = new SessionService(sessionRepository);

}
