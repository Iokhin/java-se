package ru.iokhin.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.IRepository;
import ru.iokhin.tm.entity.Task;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public interface ITaskRepository extends IRepository<Task> {

    Collection<Task> findAllByUserId(@NotNull final String userId);

    void removeAllByUserId(@NotNull final String userId);

    Task findOne(@NotNull final String parentId, @NotNull final String id);

    Task remove(@NotNull final String parentId, @NotNull final String id);

    Collection<Task> sortByUserId(@NotNull final String userId, Comparator<Task> comparator);

    List<Task> findByPartOfNameOrDescription(@NotNull final String userId, @NotNull final String keyWord);

}
