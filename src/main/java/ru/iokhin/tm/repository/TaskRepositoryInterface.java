package ru.iokhin.tm.repository;

import ru.iokhin.tm.entity.Task;

public interface TaskRepositoryInterface {

    public void add(Task task);
    public void list(String projectId);
    public void merge(Task task);
    public void delete(String id);

}
