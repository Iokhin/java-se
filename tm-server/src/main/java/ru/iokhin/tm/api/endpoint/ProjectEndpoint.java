package ru.iokhin.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.DTO.ProjectDTO;
import ru.iokhin.tm.DTO.SessionDTO;
import ru.iokhin.tm.exeption.AuthException;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Collection;

@WebService
public interface ProjectEndpoint {

    ProjectDTO addProject(@WebParam(name = "session") @NotNull final SessionDTO session,
                          @WebParam(name = "name") @NotNull final String name) throws AuthException;

    ProjectDTO editProject(@WebParam(name = "session") @NotNull final SessionDTO session,
                           @WebParam(name = "id") @NotNull final String id,
                           @WebParam(name = "name") @NotNull final String name) throws AuthException;

    ProjectDTO removeProject(@WebParam(name = "session") @NotNull final SessionDTO session,
                             @WebParam(name = "id") @NotNull final String id) throws AuthException;

    void removeAllProjectByUserId(@WebParam(name = "session") @NotNull final SessionDTO session) throws AuthException;

    Collection<ProjectDTO> findAllProjectByUserId(@WebParam(name = "session") @NotNull final SessionDTO session) throws AuthException;

    ProjectDTO findProject(@WebParam(name = "session") @NotNull final SessionDTO session,
                           @WebParam(name = "id") @NotNull final String id) throws AuthException;

    Collection<ProjectDTO> sortProjectByUserId(@WebParam(name = "session") SessionDTO session,
                                               @WebParam(name = "comparator") String comparator) throws AuthException;

    Collection<ProjectDTO> findProjectByPartOfNameOrDescription(@WebParam(name = "session") @NotNull final SessionDTO session,
                                                                @WebParam(name = "part") @NotNull final String part) throws AuthException;

}
