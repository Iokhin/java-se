package ru.iokhin.tm.service;

public interface TaskServiceInterface {

    void addTask(String name, String projectId);
    void listTask(String projectId);
    void removeTask(String id);
    void clearTask(String projectId);
    void editTask(String id, String newName);

}
