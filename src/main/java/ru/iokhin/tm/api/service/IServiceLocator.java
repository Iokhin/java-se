package ru.iokhin.tm.api.service;

public interface IServiceLocator {

    IUserService getUserService();

    IProjectService getProjectService();

    ITaskService getTaskService();

}

