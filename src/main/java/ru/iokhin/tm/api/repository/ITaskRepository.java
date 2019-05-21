package ru.iokhin.tm.api.repository;

import ru.iokhin.tm.api.IRepository;
import ru.iokhin.tm.entity.Task;

import java.util.Collection;

public interface ITaskRepository extends IRepository<Task> {

    Collection<Task> findAllByUserId(final String userId);

    void removeAllByUserId(final String userId);

    Task findOne(String parentId, String id);

    Task remove(String parentId, String id);

}
