package ru.iokhin.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.DTO.ProjectDTO;
import ru.iokhin.tm.api.IService;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.enumerated.Status;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.Collection;

public interface IProjectService extends IService<ProjectDTO> {

    ProjectDTO add(@NotNull final String userId, @NotNull final String name) throws SQLException;

    ProjectDTO edit(@NotNull final String userId, @NotNull final String id, @NotNull final String name);

    ProjectDTO edit(@NotNull final String userId, @NotNull final String id, @NotNull final String name, @NotNull final Status status);

    ProjectDTO removeByUserId(@NotNull final String userId, @NotNull final String id);

    void removeAllByUserId(@NotNull final String userId);

    Collection<ProjectDTO> findAllByUserId(@NotNull final String userId);

    ProjectDTO findOneByUserId(@NotNull final String userId, @NotNull final String id);

    Collection<ProjectDTO> sortByUserId(String userId, String comparator);

    Collection<ProjectDTO> findByPartOfNameOrDescription(@NotNull final String userId, @NotNull final String part);

    Project getProjectFromDTO(ProjectDTO projectDTO, EntityManager em);

    User getUser(@NotNull String userId, @NotNull EntityManager em);
}
