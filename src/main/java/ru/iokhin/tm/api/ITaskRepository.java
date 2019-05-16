package ru.iokhin.tm.api;

import ru.iokhin.tm.entity.Task;

public interface ITaskRepository {

    void add(Task task);
    void list(String projectId, String userId);
    void merge(Task task);
    void delete(String id);
    Task findById(String id);

}
