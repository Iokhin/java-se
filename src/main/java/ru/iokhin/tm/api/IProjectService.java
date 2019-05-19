package ru.iokhin.tm.api;

import ru.iokhin.tm.entity.User;

public interface IProjectService {

    void addProject(String name, User user);

    void listProject(String userId);

    void removeProject(String id);

    void clearProject();

    void editProject(String id, String newName);

}
