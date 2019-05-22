package ru.iokhin.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.IService;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.User;

import java.util.Collection;
import java.util.Comparator;

public interface IProjectService extends IService<Project> {

    Project add(@NotNull final User user, @NotNull final String name);

    Project edit(@NotNull final User user, @NotNull final String id, @NotNull final String name);

    Project remove(@NotNull final User user, @NotNull final String id);

    void removeAllByUser(@NotNull final User user);

    Collection<Project> findAllByUser(@NotNull final User user);

    Project findOne(@NotNull final User user, @NotNull final String id);

    Collection<Project> sortByUserId(User user, String comparator);

    Collection<Project> findByPartOfNameOrDescription(@NotNull final String userId, @NotNull final String part);
}
