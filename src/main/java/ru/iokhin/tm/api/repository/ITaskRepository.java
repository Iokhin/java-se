package ru.iokhin.tm.api.repository;

import ru.iokhin.tm.entity.Task;

import java.util.Collection;

public interface ITaskRepository {

    Collection<Task> findAllByUserId(final String id);

    void removeAllByUserId(final String id);

    Collection<Task> findAllByProjectId(final String id);

    void removeAllByProjectId(final String id);

}
