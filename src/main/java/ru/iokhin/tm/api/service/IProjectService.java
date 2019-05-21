package ru.iokhin.tm.api.service;

import ru.iokhin.tm.api.IService;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.User;

import java.util.Collection;

public interface IProjectService extends IService<Project> {

    Project add(User user, String name);

    Project edit(User user, String id, String name);

    Project remove(User user, String id);

    void removeAllByUser(User user);

    Collection<Project> findAllByUser(User user);
}
