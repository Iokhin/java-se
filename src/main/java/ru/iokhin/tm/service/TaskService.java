package ru.iokhin.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.ITaskService;
import ru.iokhin.tm.entity.Task;
import ru.iokhin.tm.repository.TaskRepository;

public final class TaskService implements ITaskService {

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @NotNull
    private final TaskRepository taskRepository;

    @Override
    public void addTask(@NotNull String userId, @NotNull String projectId, @NotNull String name) {
        if (projectId.trim().isEmpty() && name.trim().isEmpty()) {
            System.out.println("Illegal argument");
            return;
        }
        taskRepository.add(new Task(userId, projectId, name));
    }

    @Override
    public void listTask(@NotNull String projectId, @NotNull String userId) {
        if (projectId.trim().isEmpty()) {
            System.out.println("Illegal argument");
            return;
        }
        taskRepository.list(projectId, userId);
    }

    @Override
    public void removeTask(@NotNull String id) {
        if (id.trim().isEmpty()) {
            System.out.println("Illegal ID");
            return;
        }

        @NotNull
        Task task = taskRepository.findById(id);

        if (task == null) {
            System.out.println("NO TASK WITH SUCH ID");
            return;
        }
        taskRepository.delete(task.getId());
    }

    @Override
    public void clearTask(@NotNull String projectId, @NotNull String userId) {
        if (projectId.trim().isEmpty()) {
            System.out.println("Illegal argument");
            return;
        }
        for (@NotNull Task task : taskRepository.taskLinkedHashMap.values()) {
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

    public Task getTaskById(String id) {
        return taskRepository.findById(id);
    }
}
