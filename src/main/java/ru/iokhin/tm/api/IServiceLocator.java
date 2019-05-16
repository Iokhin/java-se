package ru.iokhin.tm.api;

import ru.iokhin.tm.service.ProjectService;
import ru.iokhin.tm.service.TaskService;
import ru.iokhin.tm.service.UserService;

public interface IServiceLocator {
    UserService getUserService();
    ProjectService getProjectService();
    TaskService getTaskService();
}

