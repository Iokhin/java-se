package ru.iokhin.tm.repository.old;

import ru.iokhin.tm.api.repository.ITaskRepository;
import ru.iokhin.tm.entity.Task;

import java.util.*;

public final class TaskRepositoryOLD extends AbstractRepositoryOLD<Task> implements ITaskRepository {

    @Override
    public Collection<Task> findAllByUserId(String id) {
        Collection<Task> taskCollection = new ArrayList<>(0);
        for (Task task : findAll()) {
            if (task.getUserId().equals(id))
                taskCollection.add(task);
        }
        return taskCollection;
    }

    @Override
    public void removeAllByUserId(String id) {
        for (Task task : findAllByUserId(id)) {
            remove(task.getId());
        }
    }

    @Override
    public Collection<Task> findAllByProjectId(String id) {
        Collection<Task> taskCollection = new ArrayList<>(0);
        for (Task task : findAll()) {
            if (task.getProjectId().equals(id))
                taskCollection.add(task);
        }
        return taskCollection;
    }

    @Override
    public void removeAllByProjectId(String id) {
        for (Task task : findAllByProjectId(id)) {
            remove(task.getId());
        }
    }

    public Map<String, Task> getRepositoryMap() {
        return this.repositoryMap;
    }
}
