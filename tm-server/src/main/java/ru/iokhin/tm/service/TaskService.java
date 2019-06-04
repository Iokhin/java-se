package ru.iokhin.tm.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.repository.ITaskRepository;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.entity.Task;
import ru.iokhin.tm.util.ComparatorUtil;
import ru.iokhin.tm.util.StringValidator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TaskService extends AbstractService<Task, ITaskRepository> implements ITaskService {

    public TaskService(@NotNull final ITaskRepository repository) {
        super(repository);
    }

    @Override
    public Task add(@NotNull final String userId, @NotNull final String projectId, @NotNull final String name) throws SQLException {
        StringValidator.validate(projectId, name);
        return repository.persist(new Task(userId, projectId, name));
    }

    @Override
    @SneakyThrows
    public Task edit(@NotNull final String userId, @NotNull final String id, @NotNull final String name) {
        StringValidator.validate(name, id, userId);
        @Nullable final Task task = repository.findOneByUserId(userId, id);
        if (task == null) return null;
        task.setName(name);
        repository.merge(task);
        return task;
    }

    @Override
    @SneakyThrows
    public Task remove(@NotNull final String userId, @NotNull final String id) {
        StringValidator.validate(id);
        @Nullable final Task task = repository.findOneByUserId(userId, id);
        if (task == null) return null;
        return repository.remove(userId, id);
    }

    @Override
    public void removeAllByUserId(@NotNull final String userId) {
        repository.removeAllByUserId(userId);
    }

    @Override
    @SneakyThrows
    public Collection<Task> findAllByUserId(@NotNull final String userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public Collection<Task> findAllByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        @NotNull final Collection<Task> tasks = new ArrayList<>(0);
        for (Task task : findAllByUserId(userId)) {
            if (task.getProjectId().equals(projectId))
                tasks.add(task);
        }
        return tasks;
    }

    @Override
    public boolean removeAllByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        @NotNull boolean flag = false;
        for (Task task : findAllByProjectId(userId, projectId)) {
            repository.remove(userId, task.getId());
            flag = true;
        }
        return flag;
    }

    @Override
    @SneakyThrows
    public Collection<Task> sortByUserId(@NotNull String userId, @NotNull String comparator) {
        StringValidator.validate(userId, comparator);
        if (comparator.equals("order")) return repository.findAllByUserId(userId);
        if (ComparatorUtil.getTaskComparator(comparator) == null) return null;
        return repository.sortByUserId(userId, ComparatorUtil.getTaskComparator(comparator));
    }

    @Override
    public List<Task> findByPartOfNameOrDescription(@NotNull final String userId, @NotNull final String keyWord) {
        return repository.findByPartOfNameOrDescription(userId, keyWord);
    }


    @Override
    public Connection getConnection() {
        return repository.getConnection();
    }
}
