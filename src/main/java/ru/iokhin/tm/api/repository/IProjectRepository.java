package ru.iokhin.tm.api.repository;

import ru.iokhin.tm.entity.Project;

import java.util.Collection;

public interface IProjectRepository {

    Collection<Project> findAllByUserId(final String userId);

    void removeAllByUserId(final String userId);

}
