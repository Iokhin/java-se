package ru.iokhin.tm.api.service;

import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.User;

import java.util.Collection;

public interface IProjectService extends IAbstractService<Project> {

        Project add(final String name, User user);
        Project edit(final String id, final String name);
        Collection<Project> findAllByUserId(String id);
        void removeAllByUserId(String id);

}
