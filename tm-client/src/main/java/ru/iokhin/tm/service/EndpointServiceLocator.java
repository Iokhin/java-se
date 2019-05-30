package ru.iokhin.tm.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.IEndpointServiceLocator;
import ru.iokhin.tm.api.ITerminalService;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.*;

import java.util.Map;

@Getter
@RequiredArgsConstructor
public class EndpointServiceLocator implements IEndpointServiceLocator {

    @Nullable
    @Getter
    @Setter
    private Session session;

    @NotNull
    private final Map<String, AbstractCommand> commandMap;

    @NotNull
    private final ProjectEndpointBean projectEndpointBean = new ProjectEndpointBeanService().getProjectEndpointBeanPort();

    @NotNull
    private final TaskEndpointBean taskEndpointBean = new TaskEndpointBeanService().getTaskEndpointBeanPort();

    @NotNull
    private final UserEndpointBean userEndpointBean = new UserEndpointBeanService().getUserEndpointBeanPort();

    @NotNull
    private final SessionEndpointBean sessionEndpointBean = new SessionEndpointBeanService().getSessionEndpointBeanPort();

    @NotNull
    private final ITerminalService terminalService = new TerminalService();

    @NotNull
    @Override
    public ProjectEndpointBean getProjectEndpointBean() {
        return projectEndpointBean;
    }

    @NotNull
    @Override
    public TaskEndpointBean getTaskEndpointBean() {
        return taskEndpointBean;
    }

    @NotNull
    @Override
    public UserEndpointBean getUserEndpointBean() {
        return userEndpointBean;
    }

    @NotNull
    @Override
    public SessionEndpointBean getSessionEndpointBean() {
        return sessionEndpointBean;
    }

    @NotNull
    @Override
    public ITerminalService getTerminalService() {
        return terminalService;
    }

}
