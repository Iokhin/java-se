package ru.iokhin.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.endpoint.TaskEndpoint;
import ru.iokhin.tm.api.service.IServiceLocator;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.entity.Session;
import ru.iokhin.tm.entity.Task;
import ru.iokhin.tm.exeption.AuthException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Collection;
import java.util.List;

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
    public Task addTask(@WebParam(name = "session") @NotNull final Session session,
                    @WebParam(name = "projectId") @NotNull final String projectId,
                    @WebParam(name = "name") @NotNull final String name) throws AuthException {
        sessionService.validate(session);
        return taskService.add(session.getParentId(), projectId, name);
    }

    @Override
    public Task edit(@WebParam(name = "session") @NotNull final Session session,
                     @WebParam(name = "id") @NotNull final String id,
                     @WebParam(name = "name") @NotNull final String name) throws AuthException {
        sessionService.validate(session);
        return taskService.edit(session.getParentId(), id, name);
    }

    @Override
    public Task remove(@WebParam(name = "session") @NotNull final Session session,
                       @WebParam(name = "id") @NotNull final String id) throws AuthException {
        sessionService.validate(session);
        return taskService.remove(session.getParentId(), id);
    }

    @Override
    public void removeAllByUserId(@WebParam(name = "session") @NotNull final Session session) throws AuthException {
        sessionService.validate(session);
        taskService.removeAllByUserId(session.getParentId());
    }

    @Override
    public Collection<Task> findAllByUserId(@WebParam(name = "session") @NotNull final Session session) throws AuthException {
        sessionService.validate(session);
        return taskService.findAllByUserId(session.getParentId());
    }

    @Override
    public Collection<Task> findAllByProjectId(@WebParam(name = "session") @NotNull final Session session,
                                               @WebParam(name = "projectId") @NotNull final String projectId) throws AuthException {
        sessionService.validate(session);
        return taskService.findAllByProjectId(session.getParentId(), projectId);
    }

    @Override
    public boolean removeAllByProjectId(@WebParam(name = "session") @NotNull final Session session,
                                        @WebParam(name = "projectId") @NotNull final String projectId) throws AuthException {
        sessionService.validate(session);
        return taskService.removeAllByProjectId(session.getParentId(), projectId);
    }

    @Override
    public Collection<Task> sortTaskByUserId(@WebParam(name = "session") @NotNull final Session session,
                                         @WebParam(name = "comparator") @NotNull final String comparator) throws AuthException {
        sessionService.validate(session);
        return taskService.sortByUserId(session.getParentId(), comparator);
    }

    @Override
    public Collection<Task> findByPartOfNameOrDescriptionTask(@WebParam(name = "session") @NotNull final Session session,
                                                        @WebParam(name = "keyWord") @NotNull final String keyWord) throws AuthException {
        sessionService.validate(session);
        return taskService.findByPartOfNameOrDescription(session.getParentId(), keyWord);
    }
}
