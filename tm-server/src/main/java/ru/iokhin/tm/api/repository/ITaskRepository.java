package ru.iokhin.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.IRepository;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.Task;
import ru.iokhin.tm.entity.User;

import java.util.List;

public interface ITaskRepository extends IRepository<Task> {

    Task findOneByUserId(@NotNull User user, @NotNull String id);

    List<Task> findAllByUserId(@NotNull User user);

    List<Task> findAllByProjectId(@NotNull User user, @NotNull Project project);

    Integer removeByUserId(@NotNull User user, @NotNull String id);

    Integer removeAllByUserId(@NotNull User user);

    void removeAllByProjectId(@NotNull User user, @NotNull Project project);

    List<Task> sortByUserId(@NotNull User user, @NotNull String parameter);

    List<Task> findByPartOfNameOrDescription(@NotNull User user, @NotNull String keyWord);

}
