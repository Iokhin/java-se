package ru.iokhin.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.IRepository;
import ru.iokhin.tm.entity.Project;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Comparator;

public interface IProjectRepository extends IRepository<Project> {

    Collection<Project> findAllByUserId(@NotNull final String userId) throws SQLException;

    void removeAllByUserId(@NotNull final String userId) throws SQLException;

    Project findOneByUserId(@NotNull final String parentId, @NotNull final String id);

    Project remove(@NotNull final String parentId, @NotNull final String id);

    Collection<Project> sortByUserId(String userId, Comparator<Project> comparator) throws SQLException;

    Collection<Project> findByPartOfNameOrDescription(@NotNull final String userId, @NotNull final String part) throws SQLException;

}
