package ru.iokhin.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.IService;
import ru.iokhin.tm.entity.Task;
import ru.iokhin.tm.entity.User;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface ITaskService extends IService<Task> {

    Task add(@NotNull final String userId, @NotNull final String projectId, @NotNull final String name) throws SQLException;

    Task edit(@NotNull final String userId, @NotNull final String id, @NotNull final String name);

    Task remove(@NotNull final String userId, @NotNull final String id);

    void removeAllByUserId(@NotNull final String userId);

    Collection<Task> findAllByUserId(@NotNull final String userId);

    Collection<Task> findAllByProjectId(@NotNull final String userId, @NotNull final String projectId);

    boolean removeAllByProjectId(@NotNull final String userId, @NotNull final String projectId);

    Collection<Task> sortByUserId(@NotNull final String userId, @NotNull final String comparator);

    List<Task> findByPartOfNameOrDescription(@NotNull final String userId, @NotNull final String keyWord);

}
