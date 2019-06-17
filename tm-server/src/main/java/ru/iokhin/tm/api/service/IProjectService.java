package ru.iokhin.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.entityDTO.ProjectDTO;
import ru.iokhin.tm.api.IService;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.enumerated.Status;
import ru.iokhin.tm.exeption.AuthException;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.Collection;

public interface IProjectService extends IService<ProjectDTO> {

    ProjectDTO add(@NotNull final String userId, @NotNull final String name) throws SQLException, AuthException;

    ProjectDTO edit(@NotNull final String userId, @NotNull final String id, @NotNull final String name) throws AuthException;

    ProjectDTO edit(@NotNull final String userId, @NotNull final String id, @NotNull final String name, @NotNull final Status status) throws AuthException;

    ProjectDTO removeByUserId(@NotNull final String userId, @NotNull final String id) throws Exception;

    void removeAllByUserId(@NotNull final String userId);

    Collection<ProjectDTO> findAllByUserId(@NotNull final String userId) throws AuthException;

    ProjectDTO findOneByUserId(@NotNull final String userId, @NotNull final String id) throws AuthException;

    Collection<ProjectDTO> sortByUserId(String userId, String comparator);

    Collection<ProjectDTO> findByPartOfNameOrDescription(@NotNull final String userId, @NotNull final String part);

    Project getProjectFromDTO(ProjectDTO projectDTO) throws AuthException;

    User getUser(@NotNull String userId) throws AuthException;

    void merge(@NotNull ProjectDTO projectDTO) throws AuthException;
}
