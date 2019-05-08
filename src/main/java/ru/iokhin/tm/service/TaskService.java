package ru.iokhin.tm.service;

import ru.iokhin.tm.entity.Task;
import ru.iokhin.tm.repository.TaskRepository;

public class TaskService {

    TaskRepository tr;

    public void addTask(String projectId, String name) {
        if (projectId != null && !projectId.trim().isEmpty() &&  name != null && !name.trim().isEmpty()) {
            tr.persistTaskRepositoryItem(new Task(projectId, name));
        }
        else System.out.println("Illegal argument");
    }

    public void listTask(String projectId ) {
        if (projectId != null && !projectId.trim().isEmpty()) {
            tr.findAllTaskRepositoryItem(projectId);
        }
        else System.out.println("Illegal argument");
    }

    public void removeTask(String id) {
        if (id != null && !id.trim().isEmpty()) {
            for (Task task : tr.taskLinkedHashMap.values()) {
                if (task.getId().equals(id)) {
                    tr.removeTaskRepositoryItem(task.getId());
                    return;
                }
            }
        }
        else System.out.println("Illegal argument");
    }

    public void clearTask(String projectId) {
        if (projectId != null && !projectId.trim().isEmpty()) {
            for (Task task : tr.taskLinkedHashMap.values()) {
                if (task.getProjectId().equals(projectId)) {
                    tr.removeTaskRepositoryItem(task.getId());
                }
            }
        }
        else System.out.println("Illegal argument");
    }

    public void editTask(String id, String newName) {
        if (id != null && !id.trim().isEmpty() && newName != null && !newName.trim().isEmpty()) {
            for (Task task : tr.taskLinkedHashMap.values()) {
                if (task.getId().equals(id)) {
                    Task newTask = new Task(task.getId(), newName);
                    tr.mergeTaskRepositoryItem(newTask);
                    return;
                }
            }
        }
        else System.out.println("Illegal argument");
    }

    public TaskService(TaskRepository tr) {
        this.tr = tr;
    }

}
