package ru.iokhin.tm.service;

import ru.iokhin.tm.api.repository.IAbstractRepository;
import ru.iokhin.tm.api.repository.ITaskRepository;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.entity.Task;
import ru.iokhin.tm.util.StringValidator;

import java.util.Collection;

public final class TaskService extends AbstractService<Task> implements ITaskService {

    public TaskService(IAbstractRepository<Task> repository) {
        super(repository);
    }

    @Override
    public Task add(String userId, String name, String projectId) {
        if (!StringValidator.isValid(userId, name, projectId)) {
            System.out.println("INVALID ARGUMENT");
            return null;
        }

        return repository.persist(new Task(userId, name, projectId));
    }

    @Override
    public Task edit(String id, String newName) {
        if (!StringValidator.isValid(id, newName)) {
            System.out.println("INVALID ARGUMENT");
            return null;
        }
        Task task = findOne(id);
        if (task == null) {
            System.out.println("NO SUCH TASK ID");
        }
        task.setName(newName);
        return task;
    }

    @Override
    public Collection<Task> findAllByUserId(String id) {
        if (!StringValidator.isValid(id)) {
            System.out.println("INVALID ARGUMENT");
            return null;
        }
        return ((ITaskRepository)repository).findAllByUserId(id);
    }

    @Override
    public void removeAllByUserId(String id) {
        if (!StringValidator.isValid(id)) {
            System.out.println("INVALID ARGUMENT");
            return;
        }
        ((ITaskRepository)repository).removeAllByUserId(id);
    }

    @Override
    public Collection<Task> findAllByProjectId(String id) {
        if (!StringValidator.isValid(id)) {
            System.out.println("INVALID ARGUMENT");
            return null;
        }
        return ((ITaskRepository)repository).findAllByProjectId(id);
    }

    @Override
    public void removeAllByProjectId(String id) {
        if (!StringValidator.isValid(id)) {
            System.out.println("INVALID ARGUMENT");
            return;
        }
        ((ITaskRepository)repository).removeAllByProjectId(id);
    }


//    @NotNull
//    private final TaskRepository taskRepository;
//
//    @Override
//    public void addTask(@NotNull String userId, @NotNull String projectId, @NotNull String name) {
//        if (!StringValidator.isValid(projectId, name)) {
//            System.out.println("Illegal argument");
//            return;
//        }
//        taskRepository.add(new Task(userId, projectId, name));
//    }
//
//    @Override
//    public void listTask(@NotNull String projectId, @NotNull String userId) {
//        if (!StringValidator.isValid(projectId)) {
//            System.out.println("Illegal argument");
//            return;
//        }
//        taskRepository.list(projectId, userId);
//    }
//
//    @Override
//    public void removeTask(@NotNull String id) {
//        if (!StringValidator.isValid(id)) {
//            System.out.println("Illegal argument");
//            return;
//        }
//
//        @NotNull
//        Task task = taskRepository.findById(id);
//
//        if (task == null) {
//            System.out.println("NO TASK WITH SUCH ID");
//            return;
//        }
//        taskRepository.delete(task.getId());
//    }
//
//    @Override
//    public void clearTask(@NotNull String projectId, @NotNull String userId) {
//        if (!StringValidator.isValid(projectId)) {
//            System.out.println("Illegal argument");
//            return;
//        }
//        for (@NotNull Task task : taskRepository.taskLinkedHashMap.values()) {
//            if (task.getUserId().equals(userId) && task.getProjectId().equals(projectId))
//                removeTask(task.getId());
//        }
//    }
//
//
//    @Override
//    public void editTask(String id, String newName) {
//        if (!StringValidator.isValid(id, newName)) {
//            System.out.println("Illegal argument");
//            return;
//        }
//        Task task = taskRepository.findById(id);
//        if (task == null) {
//            System.out.println("NO PROJECT WITH SUCH ID");
//        }
//        task.setName(newName);
//    }
//
//    public Task getTaskById(String id) {
//        return taskRepository.findById(id);
//    }
}
