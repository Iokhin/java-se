package ru.iokhin.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.IService;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.enumerated.Status;

import java.sql.SQLException;
import java.util.Collection;

public interface IProjectService extends IService<Project> {

    Project add(@NotNull final String userId, @NotNull final String name) throws SQLException;

    Project edit(@NotNull final String userId, @NotNull final String id, @NotNull final String name);

    Project edit(@NotNull final String userId, @NotNull final String id, @NotNull final String name, @NotNull final Status status);

    Project remove(@NotNull final String userId, @NotNull final String id);

    void removeAllByUserId(@NotNull final String userId);

    Collection<Project> findAllByUserId(@NotNull final String userId);

    Project findOne(@NotNull final String userId, @NotNull final String id);

    Collection<Project> sortByUserId(String userId, String comparator);

    Collection<Project> findByPartOfNameOrDescription(@NotNull final String userId, @NotNull final String part);
}
