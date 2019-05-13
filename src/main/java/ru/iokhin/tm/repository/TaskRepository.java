package ru.iokhin.tm.repository;

import ru.iokhin.tm.entity.Task;

import java.util.*;

public class TaskRepository {

    public Map<String ,Task> taskLinkedHashMap = new LinkedHashMap<>(0);

    public Task getTaskRepositoryItem(String id) {
        return taskLinkedHashMap.get(id);
    }

    public void  persistTaskRepositoryItem(Task task) {
        taskLinkedHashMap.put(task.getId(), task);
    }

    public void mergeTaskRepositoryItem(Task task) {
        taskLinkedHashMap.merge(task.getId(), task, (oldVal, newVal) -> new Task(oldVal.getProjectId(), newVal.getName(), oldVal.getId()));
    }

    public void removeTaskRepositoryItem(String id) {
        taskLinkedHashMap.remove(id);
    }

    public void removeAllTaskRepositoryItem() {
        taskLinkedHashMap.clear();
    }

    public void findOneTaskRepositoryItem(String name) {
        for (Task task : taskLinkedHashMap.values()) {
            if (task.getName().contains(name)) {
                System.out.println(task.toString());
                return;
            }
        }
    }

    public void findAllTaskRepositoryItem(String projectId) {
        int i = 0;
        for (Task task : taskLinkedHashMap.values()) {
            if (task.getProjectId().equals(projectId)) {
                System.out.println(++i + ". " + task.toString());
            }
        }
    }



}
