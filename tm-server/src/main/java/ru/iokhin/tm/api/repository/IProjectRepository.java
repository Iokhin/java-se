package ru.iokhin.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.IRepository;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.User;

import java.util.List;

public interface IProjectRepository extends IRepository<Project> {

    Project findOneByUserId(@NotNull User user, @NotNull String id);

    List<Project> findAllByUserId(@NotNull User user);

    List<Project> findAll();

    void removeAllByUserId(@NotNull User user);

    void removeByUserId(@NotNull final User user, String id);

    List<Project> sortByUserId(@NotNull User user, @NotNull String parameter);

    List<Project> findByPartOfNameOrDescription(@NotNull User user, @NotNull String part);
}
