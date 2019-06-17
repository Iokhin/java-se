package ru.iokhin.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.IService;
import ru.iokhin.tm.entityDTO.TaskDTO;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.Task;
import ru.iokhin.tm.entity.User;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface ITaskService extends IService<TaskDTO> {

    TaskDTO add(@NotNull final String userId, @NotNull final String projectId, @NotNull final String name) throws SQLException;

    TaskDTO edit(@NotNull final String userId, @NotNull final String id, @NotNull final String name);

    TaskDTO removeByUserId(@NotNull final String userId, @NotNull final String id);

    void removeAllByUserId(@NotNull final String userId);

    TaskDTO findOneByUserId(@NotNull final String userId, @NotNull final String id);

    Collection<TaskDTO> findAllByUserId(@NotNull final String userId);

    Collection<TaskDTO> findAllByProjectId(@NotNull final String userId, @NotNull final String projectId);

    void removeAllByProjectId(@NotNull final String userId, @NotNull final String projectId);

    Collection<TaskDTO> sortByUserId(@NotNull final String userId, @NotNull final String comparator);

    List<TaskDTO> findByPartOfNameOrDescription(@NotNull final String userId, @NotNull final String keyWord);

    Task getTaskFromDTO(@NotNull TaskDTO taskDTO);

    User getUser(@NotNull String userId);

    Project getProject(@NotNull String projectId);

    public void merge(@NotNull TaskDTO taskDTO);

}
