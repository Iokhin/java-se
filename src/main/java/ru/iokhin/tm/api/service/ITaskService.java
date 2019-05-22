package ru.iokhin.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.IService;
import ru.iokhin.tm.entity.Task;
import ru.iokhin.tm.entity.User;

import java.util.Collection;

public interface ITaskService extends IService<Task> {

    Task add(@NotNull final User user, @NotNull final String projectId, @NotNull final String name);

    Task edit(@NotNull final User user, @NotNull final String id, @NotNull final String name);

    Task remove(@NotNull final User user, @NotNull final String id);

    void removeAllByUser(@NotNull final User user);

    Collection<Task> findAllByUser(@NotNull final User user);

    Collection<Task> findAllByProjectId(@NotNull final User user, @NotNull final String projectId);

    boolean removeAllByProjectId(@NotNull final User user, @NotNull final String projectId);

}
