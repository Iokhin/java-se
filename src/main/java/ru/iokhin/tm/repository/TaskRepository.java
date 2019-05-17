package ru.iokhin.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.ITaskRepository;
import ru.iokhin.tm.entity.Task;

import java.util.*;

public final class TaskRepository implements ITaskRepository {

    @NotNull
    public Map<String, Task> taskLinkedHashMap = new LinkedHashMap<>(0);

    @Override
    public void add(@NotNull Task task) {
        taskLinkedHashMap.put(task.getId(), task);
    }

    @Override
    public void list(@NotNull String projectId, @NotNull String userId) {

        @NotNull
        int i = 0;

        for (@NotNull Task task : taskLinkedHashMap.values()) {
            if (task.getProjectId().equals(projectId) && task.getUserId().equals(userId)) {
                System.out.println(++i + ". " + task.toString());
            }
        }
    }

    @Override
    public void merge(@NotNull Task task) {
        taskLinkedHashMap.put(task.getId(), task);
    }

    @Override
    public void delete(@NotNull String id) {
        taskLinkedHashMap.remove(id);
    }

    @Override
    public Task findById(@NotNull String id) {
        return taskLinkedHashMap.get(id);
    }
}
