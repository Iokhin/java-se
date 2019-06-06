package ru.iokhin.tm.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.service.*;

import java.sql.Connection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceLocator implements IServiceLocator {

    private Connection connection;

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
        this.projectService = projectService;
        this.taskService = taskService;
        this.userService = userService;
        this.sessionService = sessionService;
    }
}
