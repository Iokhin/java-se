package ru.iokhin.tm.api.repository;

import ru.iokhin.tm.api.IRepository;
import ru.iokhin.tm.entity.Project;

import java.util.Collection;

public interface IProjectRepository extends IRepository<Project> {

    Collection<Project> findAllByUserId(final String userId);

    void removeAllByUserId(final String userId);

    Project findOne(String parentId, String id);

    Project remove(String parentId, String id);

}
