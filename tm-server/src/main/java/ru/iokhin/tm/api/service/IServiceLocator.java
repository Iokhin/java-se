package ru.iokhin.tm.api.service;

import java.sql.Connection;

public interface IServiceLocator {

    IUserService getUserService();

    IProjectService getProjectService();

    ITaskService getTaskService();

    ITerminalService getTerminalService();

    ISessionService getSessionService();

    void setConnection(Connection connection);

}

