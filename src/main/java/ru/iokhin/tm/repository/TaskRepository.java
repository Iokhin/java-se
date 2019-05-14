package ru.iokhin.tm.repository;

import ru.iokhin.tm.entity.Task;

import java.util.*;

public class TaskRepository implements TaskRepositoryInterface {

    public Map<String ,Task> taskLinkedHashMap = new LinkedHashMap<>(0);

    @Override
    public void add(Task task) {
        taskLinkedHashMap.put(task.getId(), task);
    }

    @Override
    public void list(String projectId) {
        int i = 0;
        for (Task task : taskLinkedHashMap.values()) {
            if (task.getProjectId().equals(projectId)) {
                System.out.println(++i + ". " + task.toString());
            }
        }
    }

    @Override
    public void merge(Task task) {
        taskLinkedHashMap.merge(task.getId(), task, (oldVal, newVal) -> new Task(oldVal.getProjectId(),
                newVal.getName(), oldVal.getId()));
    }

    @Override
    public void delete(String id) {
        taskLinkedHashMap.remove(id);
    }
}
