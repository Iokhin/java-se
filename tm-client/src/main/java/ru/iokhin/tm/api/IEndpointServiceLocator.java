package ru.iokhin.tm.api;

import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.command.AbstractCommand;
import ru.iokhin.tm.endpoint.*;

import java.util.Map;

public interface IEndpointServiceLocator {

    ProjectEndpointBean getProjectEndpointBean();

    TaskEndpointBean getTaskEndpointBean();

    UserEndpointBean getUserEndpointBean();

    SessionEndpointBean getSessionEndpointBean();

    ITerminalService getTerminalService();

    SessionDTO getSession();

    void setSession(@Nullable final SessionDTO session);

    Map<String, AbstractCommand> getCommandMap();

}
