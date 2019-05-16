package ru.iokhin.tm.api;

public interface ITaskService {

    void addTask(String userId ,String name, String projectId);
    void listTask(String projectId, String userId);
    void removeTask(String id);
    void clearTask(String projectId, String userId);
    void editTask(String id, String newName);

}
