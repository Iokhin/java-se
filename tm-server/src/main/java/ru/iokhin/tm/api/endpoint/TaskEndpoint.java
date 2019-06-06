package ru.iokhin.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.entity.Session;
import ru.iokhin.tm.entity.Task;
import ru.iokhin.tm.exeption.AuthException;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Collection;

@WebService
public interface TaskEndpoint {

    Task addTask(@WebParam(name = "session") @NotNull final Session session,
             @WebParam(name = "projectId") @NotNull final String projectId,
             @WebParam(name = "name") @NotNull final String name) throws AuthException;

    Task editTask(@WebParam(name = "session") @NotNull final Session session,
              @WebParam(name = "id") @NotNull final String id,
              @WebParam(name = "name") @NotNull final String name) throws AuthException;

    Task removeTask(@WebParam(name = "session") @NotNull final Session session,
                @WebParam(name = "id") @NotNull final String id) throws AuthException;

    void removeAllTaskByUserId(@WebParam(name = "session") @NotNull final Session session) throws AuthException;

    Collection<Task> findAllTaskByUserId(@WebParam(name = "session") @NotNull final Session session) throws AuthException;

    Collection<Task> findAllTaskByProjectId(@WebParam(name = "session") @NotNull final Session session,
                                        @WebParam(name = "projectId") @NotNull final String projectId) throws AuthException;

    void removeAllByProjectId(@WebParam(name = "session") @NotNull final Session session,
                                 @WebParam(name = "projectId") @NotNull final String projectId) throws AuthException;

    Collection<Task> sortTaskByUserId(@WebParam(name = "session") @NotNull final Session session,
                                  @WebParam(name = "comparator") @NotNull final String comparator) throws AuthException;

    Collection<Task> findTaskByPartOfNameOrDescription(@WebParam(name = "session") @NotNull final Session session,
                                                       @WebParam(name = "keyWord") @NotNull final String keyWord) throws AuthException;

}
