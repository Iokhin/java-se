package ru.iokhin.tm.api.service;

import ru.iokhin.tm.entity.Task;

import java.util.Collection;

public interface ITaskService extends IAbstractService<Task> {

    Task add(String userId, String name, String projectId);

    Task edit(String id, String newName);

    Collection<Task> findAllByUserId(final String id);

    void removeAllByUserId(final String id);

    Collection<Task> findAllByProjectId(final String id);

    void removeAllByProjectId(final String id);

}
