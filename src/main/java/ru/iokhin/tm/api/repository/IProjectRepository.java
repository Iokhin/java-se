package ru.iokhin.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.IRepository;
import ru.iokhin.tm.entity.Project;

import java.util.Collection;

public interface IProjectRepository extends IRepository<Project> {

    Collection<Project> findAllByUserId(@NotNull final String userId);

    void removeAllByUserId(@NotNull final String userId);

    Project findOne(@NotNull final String parentId, @NotNull final String id);

    Project remove(@NotNull final String parentId, @NotNull final String id);

}
