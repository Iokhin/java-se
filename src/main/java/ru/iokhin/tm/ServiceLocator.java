package ru.iokhin.tm;

import ru.iokhin.tm.service.ProjectService;
import ru.iokhin.tm.service.TaskService;
import ru.iokhin.tm.service.UserService;

public interface ServiceLocator {
    UserService getUserService();
    ProjectService getProjectService();
    TaskService getTaskService();
}

