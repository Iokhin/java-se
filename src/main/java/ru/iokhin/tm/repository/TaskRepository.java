package ru.iokhin.tm.repository;

import ru.iokhin.tm.api.ITaskRepository;
import ru.iokhin.tm.entity.Task;

import java.util.*;

public class TaskRepository implements ITaskRepository {

    public Map<String ,Task> taskLinkedHashMap = new LinkedHashMap<>(0);

    @Override
    public void add(Task task) {
        taskLinkedHashMap.put(task.getId(), task);
    }

    @Override
    public void list(String projectId, String userId) {
        int i = 0;
        for (Task task : taskLinkedHashMap.values()) {
            if (task.getProjectId().equals(projectId) && task.getUserId().equals(userId)) {
                System.out.println(++i + ". " + task.toString());
            }
        }
    }

    @Override
    public void merge(Task task) {
        if (task == null) return;
        taskLinkedHashMap.put(task.getId(), task);
    }

    @Override
    public void delete(String id) {
        taskLinkedHashMap.remove(id);
    }

    @Override
    public Task findById(String id) {
        return taskLinkedHashMap.get(id);
    }
}
