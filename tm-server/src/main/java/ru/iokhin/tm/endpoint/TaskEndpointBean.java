package ru.iokhin.tm.endpoint;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.endpoint.TaskEndpoint;
import ru.iokhin.tm.api.service.IServiceLocator;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.entity.Session;
import ru.iokhin.tm.entity.Task;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Collection;

@WebService
@NoArgsConstructor
public class TaskEndpointBean implements TaskEndpoint {
    @NotNull
    private ITaskService taskService;

    @NotNull
    private ISessionService sessionService;

    public TaskEndpointBean(@NotNull final IServiceLocator serviceLocator) {
        taskService = serviceLocator.getTaskService();
        sessionService = serviceLocator.getSessionService();
    }

    @Override
    @SneakyThrows
    public Task addTask(@WebParam(name = "session") @NotNull final Session session,
                        @WebParam(name = "projectId") @NotNull final String projectId,
                        @WebParam(name = "name") @NotNull final String name) {
        sessionService.validate(session);
        return taskService.add(session.getParentId(), projectId, name);
    }

    @Override
    @SneakyThrows
    public Task editTask(@WebParam(name = "session") @NotNull final Session session,
                     @WebParam(name = "id") @NotNull final String id,
                     @WebParam(name = "name") @NotNull final String name) {
        sessionService.validate(session);
        return taskService.edit(session.getParentId(), id, name);
    }

    @Override
    @SneakyThrows
    public Task removeTask(@WebParam(name = "session") @NotNull final Session session,
                       @WebParam(name = "id") @NotNull final String id) {
        sessionService.validate(session);
        return taskService.removeByUserId(session.getParentId(), id);
    }

    @Override
    @SneakyThrows
    public void removeAllTaskByUserId(@WebParam(name = "session") @NotNull final Session session) {
        sessionService.validate(session);
        taskService.removeAllByUserId(session.getParentId());
    }

    @Override
    @SneakyThrows
    public Collection<Task> findAllTaskByUserId(@WebParam(name = "session") @NotNull final Session session) {
        sessionService.validate(session);
        return taskService.findAllByUserId(session.getParentId());
    }

    @Override
    @SneakyThrows
    public Collection<Task> findAllTaskByProjectId(@WebParam(name = "session") @NotNull final Session session,
                                               @WebParam(name = "projectId") @NotNull final String projectId) {
        sessionService.validate(session);
        return taskService.findAllByProjectId(session.getParentId(), projectId);
    }

    @Override
    @SneakyThrows
    public void removeAllByProjectId(@WebParam(name = "session") @NotNull final Session session,
                                        @WebParam(name = "projectId") @NotNull final String projectId) {
        sessionService.validate(session);
        taskService.removeAllByProjectId(session.getParentId(), projectId);
    }

    @Override
    @SneakyThrows
    public Collection<Task> sortTaskByUserId(@WebParam(name = "session") @NotNull final Session session,
                                             @WebParam(name = "comparator") @NotNull final String comparator) {
        sessionService.validate(session);
        return taskService.sortByUserId(session.getParentId(), comparator);
    }

    @Override
    @SneakyThrows
    public Collection<Task> findTaskByPartOfNameOrDescription(@WebParam(name = "session") @NotNull final Session session,
                                                              @WebParam(name = "keyWord") @NotNull final String keyWord) {
        sessionService.validate(session);
        return taskService.findByPartOfNameOrDescription(session.getParentId(), keyWord);
    }
}
