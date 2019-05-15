package ru.iokhin.tm.repository;

import ru.iokhin.tm.entity.Task;

public interface TaskRepositoryInterface {

    void add(Task task);
    void list(String projectId);
    void merge(Task task);
    void delete(String id);

}
