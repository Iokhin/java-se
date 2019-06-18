package ru.iokhin.tm.endpoint;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.iokhin.tm.entityDTO.SessionDTO;
import ru.iokhin.tm.entityDTO.TaskDTO;
import ru.iokhin.tm.api.endpoint.TaskEndpoint;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.api.service.ITaskService;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Collection;

@Controller
@WebService
@NoArgsConstructor
public class TaskEndpointBean implements TaskEndpoint {
    @NotNull
    private ITaskService taskService;

    @NotNull
    private ISessionService sessionService;

    @Autowired
    public TaskEndpointBean(@NotNull final ITaskService taskService, @NotNull final ISessionService sessionService) {
        this.taskService = taskService;
        this.sessionService = sessionService;
    }

    @Override
    @SneakyThrows
    public TaskDTO addTask(@WebParam(name = "session") @NotNull final SessionDTO session,
                           @WebParam(name = "projectId") @NotNull final String projectId,
                           @WebParam(name = "name") @NotNull final String name) {
        sessionService.validate(session);
        return taskService.add(session.getParentId(), projectId, name);
    }

    @Override
    @SneakyThrows
    public TaskDTO editTask(@WebParam(name = "session") @NotNull final SessionDTO session,
                            @WebParam(name = "id") @NotNull final String id,
                            @WebParam(name = "name") @NotNull final String name) {
        sessionService.validate(session);
        return taskService.edit(session.getParentId(), id, name);
    }

    @Override
    @SneakyThrows
    public TaskDTO removeTask(@WebParam(name = "session") @NotNull final SessionDTO session,
                              @WebParam(name = "id") @NotNull final String id) {
        sessionService.validate(session);
        return taskService.removeByUserId(session.getParentId(), id);
    }

    @Override
    @SneakyThrows
    public void removeAllTaskByUserId(@WebParam(name = "session") @NotNull final SessionDTO session) {
        sessionService.validate(session);
        taskService.removeAllByUserId(session.getParentId());
    }

    @Override
    @SneakyThrows
    public Collection<TaskDTO> findAllTaskByUserId(@WebParam(name = "session") @NotNull final SessionDTO session) {
        sessionService.validate(session);
        return taskService.findAllByUserId(session.getParentId());
    }

    @Override
    @SneakyThrows
    public Collection<TaskDTO> findAllTaskByProjectId(@WebParam(name = "session") @NotNull final SessionDTO session,
                                                      @WebParam(name = "projectId") @NotNull final String projectId) {
        sessionService.validate(session);
        return taskService.findAllByProjectId(session.getParentId(), projectId);
    }

    @Override
    @SneakyThrows
    public void removeAllByProjectId(@WebParam(name = "session") @NotNull final SessionDTO session,
                                     @WebParam(name = "projectId") @NotNull final String projectId) {
        sessionService.validate(session);
        taskService.removeAllByProjectId(session.getParentId(), projectId);
    }

    @Override
    @SneakyThrows
    public Collection<TaskDTO> sortTaskByUserId(@WebParam(name = "session") @NotNull final SessionDTO session,
                                                @WebParam(name = "comparator") @NotNull final String comparator) {
        sessionService.validate(session);
        return taskService.sortByUserId(session.getParentId(), comparator);
    }

    @Override
    @SneakyThrows
    public Collection<TaskDTO> findTaskByPartOfNameOrDescription(@WebParam(name = "session") @NotNull final SessionDTO session,
                                                                 @WebParam(name = "keyWord") @NotNull final String keyWord) {
        sessionService.validate(session);
        return taskService.findByPartOfNameOrDescription(session.getParentId(), keyWord);
    }
}
