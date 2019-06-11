package ru.iokhin.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.entityDTO.SessionDTO;
import ru.iokhin.tm.entityDTO.TaskDTO;
import ru.iokhin.tm.exeption.AuthException;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Collection;

@WebService
public interface TaskEndpoint {

    TaskDTO addTask(@WebParam(name = "session") @NotNull final SessionDTO session,
                    @WebParam(name = "projectId") @NotNull final String projectId,
                    @WebParam(name = "name") @NotNull final String name) throws AuthException;

    TaskDTO editTask(@WebParam(name = "session") @NotNull final SessionDTO session,
                     @WebParam(name = "id") @NotNull final String id,
                     @WebParam(name = "name") @NotNull final String name) throws AuthException;

    TaskDTO removeTask(@WebParam(name = "session") @NotNull final SessionDTO session,
                       @WebParam(name = "id") @NotNull final String id) throws AuthException;

    void removeAllTaskByUserId(@WebParam(name = "session") @NotNull final SessionDTO session) throws AuthException;

    Collection<TaskDTO> findAllTaskByUserId(@WebParam(name = "session") @NotNull final SessionDTO session) throws AuthException;

    Collection<TaskDTO> findAllTaskByProjectId(@WebParam(name = "session") @NotNull final SessionDTO session,
                                               @WebParam(name = "projectId") @NotNull final String projectId) throws AuthException;

    void removeAllByProjectId(@WebParam(name = "session") @NotNull final SessionDTO session,
                                 @WebParam(name = "projectId") @NotNull final String projectId) throws AuthException;

    Collection<TaskDTO> sortTaskByUserId(@WebParam(name = "session") @NotNull final SessionDTO session,
                                         @WebParam(name = "comparator") @NotNull final String comparator) throws AuthException;

    Collection<TaskDTO> findTaskByPartOfNameOrDescription(@WebParam(name = "session") @NotNull final SessionDTO session,
                                                          @WebParam(name = "keyWord") @NotNull final String keyWord) throws AuthException;

}
