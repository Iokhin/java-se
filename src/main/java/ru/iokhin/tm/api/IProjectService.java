package ru.iokhin.tm.api;

import ru.iokhin.tm.entity.User;

public interface IProjectService {

    public void addProject(String name, User user);
    public void listProject(String userId);
    public void removeProject(String id);
    public void clearProject();
    public void editProject(String id, String newName);

}
