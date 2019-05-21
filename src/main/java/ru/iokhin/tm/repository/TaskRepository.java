package ru.iokhin.tm.repository;

import ru.iokhin.tm.api.repository.ITaskRepository;
import ru.iokhin.tm.entity.Task;

import java.util.ArrayList;
import java.util.Collection;

public class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    @Override
    public Collection<Task> findAllByUserId(String userId) {
        Collection<Task> taskCollection = new ArrayList<>(0);
        for (Task task : findAll()) {
            if (task.getParentId().equals(userId))
                taskCollection.add(task);
        }
        return taskCollection;
    }

    @Override
    public void removeAllByUserId(String userId) {
        for (Task task : findAllByUserId(userId)) {
            remove(task.getId());
        }
    }

    @Override
    public Task findOne(String parentId, String id) {
        Task project = repository.get(id);
        return project.getParentId().equals(parentId) ? project : null;
    }

    @Override
    public Task remove(String parentId, String id) {
        Task task = repository.get(id);
        return task.getParentId().equals(parentId) ? repository.remove(task.getId()) : null;
    }

}
