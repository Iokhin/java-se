package ru.iokhin.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.repository.ITaskRepository;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.entity.Task;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.util.StringValidator;

import java.util.ArrayList;
import java.util.Collection;

public class TaskService extends AbstractService<Task, ITaskRepository> implements ITaskService {

    public TaskService(@NotNull final ITaskRepository repository) {
        super(repository);
    }

    @Override
    public Task add(@NotNull final User user, @NotNull final String projectId, @NotNull final String name) {
        StringValidator.validate(projectId, name);
        return repository.persist(new Task(user.getId(), projectId, name));
    }

    @Override
    public Task edit(@NotNull final User user, @NotNull final String id, @NotNull final String name) {
        StringValidator.validate(name, id);
        @Nullable final Task task = repository.findOne(user.getId(), id);
        if (task == null) return null;
        task.setName(name);
        return task;
    }

    @Override
    public Task remove(@NotNull final User user, @NotNull final String id) {
        StringValidator.validate(id);
        @Nullable final Task task = repository.findOne(user.getId(), id);
        if (task == null) return null;
        return repository.remove(user.getId(), id);
    }

    @Override
    public void removeAllByUser(@NotNull final User user) {
        repository.removeAllByUserId(user.getId());
    }

    @Override
    public Collection<Task> findAllByUser(@NotNull final User user) {
        return repository.findAllByUserId(user.getId());
    }

    @Override
    public Collection<Task> findAllByProjectId(@NotNull final User user, @NotNull final String projectId) {
        @NotNull final Collection<Task> tasks = new ArrayList<>(0);
        for (Task task : findAllByUser(user)) {
            if (task.getProjectId().equals(projectId))
                tasks.add(task);
        }
        return tasks;
    }

    @Override
    public boolean removeAllByProjectId(@NotNull final User user, @NotNull final String projectId) {
        @NotNull boolean flag = false;
        for (Task task : findAllByProjectId(user, projectId)) {
            repository.remove(user.getId(), task.getId());
            flag = true;
        }
        return flag;
    }


}
