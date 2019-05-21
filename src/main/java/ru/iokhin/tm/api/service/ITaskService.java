package ru.iokhin.tm.api.service;

import ru.iokhin.tm.api.IService;
import ru.iokhin.tm.entity.Task;
import ru.iokhin.tm.entity.User;

import java.util.Collection;

public interface ITaskService extends IService<Task> {

    Task add(User user, String projectId, String name);

    Task edit(User user, String id, String name);

    Task remove(User user, String id);

    void removeAllByUser(User user);

    Collection<Task> findAllByUser(User user);

    Collection<Task> findAllByProjectId(User user, String projectId);

    void removeAllByProjectId(User user, String projectId);

}
