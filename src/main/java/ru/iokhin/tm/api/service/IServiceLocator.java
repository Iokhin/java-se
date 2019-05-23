package ru.iokhin.tm.api.service;

import ru.iokhin.tm.command.AbstractCommand;

import java.util.Map;

public interface IServiceLocator {

    IUserService getUserService();

    IProjectService getProjectService();

    ITaskService getTaskService();

    ITerminalService getTerminalService();

    Map<String, AbstractCommand> getCommandMap();

}

