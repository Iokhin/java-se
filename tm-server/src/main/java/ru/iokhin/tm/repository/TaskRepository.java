package ru.iokhin.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.repository.ITaskRepository;
import ru.iokhin.tm.entity.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    @Override
    public Collection<Task> findAllByUserId(@NotNull final String userId) {
        @NotNull final Collection<Task> taskCollection = new ArrayList<>(0);
        for (@NotNull Task task : findAll()) {
            if (task.getParentId().equals(userId))
                taskCollection.add(task);
        }
        return taskCollection;
    }

    @Override
    public void removeAllByUserId(@NotNull final String userId) {
        for (Task task : findAllByUserId(userId)) {
            remove(task.getId());
        }
    }

    @Override
    public Task findOne(@NotNull final String parentId, @NotNull final String id) {
        @Nullable final Task task = repository.get(id);
        if (task == null) return null;
        return task.getParentId().equals(parentId) ? task : null;
    }

    @Override
    public Task remove(@NotNull final String parentId, @NotNull final String id) {
        @Nullable final Task task = repository.get(id);
        if (task == null) return null;
        return task.getParentId().equals(parentId) ? repository.remove(task.getId()) : null;
    }

    @Override
    public Collection<Task> sortByUserId(@NotNull final String userId, @NotNull final Comparator<Task> comparator) {
        List<Task> taskList = new ArrayList<>(findAllByUserId(userId));
        taskList.sort(comparator);
        return taskList;
    }

    @Override
    public Collection<Task> findByPartOfNameOrDescription(@NotNull String userId, @NotNull String keyWord) {
        List<Task> taskList = new ArrayList<>(0);
        for (Task task : findAllByUserId(userId)) {
            if (task.getName().toLowerCase().contains(keyWord.toLowerCase()) ||
                    task.getDescription().toLowerCase().contains(keyWord.toLowerCase()))
                taskList.add(task);
        }
        return taskList;
    }

}
