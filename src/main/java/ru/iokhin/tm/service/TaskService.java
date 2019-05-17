package ru.iokhin.tm.service;

import ru.iokhin.tm.api.ITaskService;
import ru.iokhin.tm.entity.Task;
import ru.iokhin.tm.repository.TaskRepository;

public class TaskService implements ITaskService {

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void addTask(String userId, String projectId, String name) {
        if (projectId == null && projectId.trim().isEmpty() && name == null && name.trim().isEmpty()) {
            System.out.println("Illegal argument");
            return;
        }
        taskRepository.add(new Task(userId, projectId, name));
    }

    @Override
    public void listTask(String projectId, String userId) {
        if (projectId == null && projectId.trim().isEmpty()) {
            System.out.println("Illegal argument");
            return;
        }
        taskRepository.list(projectId, userId);
    }

    @Override
    public void removeTask(String id) {
        if (id == null && id.trim().isEmpty()) {
            System.out.println("Illegal ID");
            return;
        }
        Task task;
        if ((task = taskRepository.findById(id)) == null) {
            System.out.println("NO TASK WITH SUCH ID");
            return;
        }
        taskRepository.delete(task.getId());
    }

    @Override
    public void clearTask(String projectId, String userId) {
        if (projectId == null && projectId.trim().isEmpty()) {
            System.out.println("Illegal argument");
            return;
        }
        for (Task task : taskRepository.taskLinkedHashMap.values()) {
            if (task.getUserId().equals(userId) && task.getProjectId().equals(projectId))
                removeTask(task.getId());
        }
    }


    @Override
    public void editTask(String id, String newName) {
        if (id == null && id.trim().isEmpty()) {
            System.out.println("Illegal ID");
            return;
        }
        if (newName == null && newName.trim().isEmpty()) {
            System.out.println("Illegal name");
            return;
        }
        Task task;
        if ((task = taskRepository.findById(id)) == null) {
            System.out.println("NO PROJECT WITH SUCH ID");
        }
        taskRepository.merge(new Task(newName, task.getId(), task.getUserId()));
    }

    private TaskRepository taskRepository;

    public Task getTaskById(String id) {
        return taskRepository.findById(id);
    }
}
