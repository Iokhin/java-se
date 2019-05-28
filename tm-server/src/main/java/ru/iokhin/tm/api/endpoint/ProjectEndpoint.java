package ru.iokhin.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.Session;
import ru.iokhin.tm.enumerated.Status;
import ru.iokhin.tm.exeption.AuthException;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Collection;

@WebService
public interface ProjectEndpoint {

    Project add(@WebParam(name = "session") @NotNull final Session session,
                @WebParam(name = "name") @NotNull final String name) throws AuthException;

    Project edit(@WebParam(name = "session") @NotNull final Session session,
                 @WebParam(name = "id") @NotNull final String id,
                 @WebParam(name = "name") @NotNull final String name,
                 @WebParam(name = "status") @NotNull final Status status) throws AuthException;

    Project remove(@WebParam(name = "session") @NotNull final Session session,
                   @WebParam(name = "id") @NotNull final String id) throws AuthException;

    void removeAllByUserId(@WebParam(name = "session") @NotNull final Session session) throws AuthException;

    Collection<Project> findAllByUserId(@WebParam(name = "session") @NotNull final Session session) throws AuthException;

    Project findOne(@WebParam(name = "session") @NotNull final Session session,
                    @WebParam(name = "id") @NotNull final String id) throws AuthException;

    Collection<Project> sortByUserId(@WebParam(name = "session") Session session,
                                     @WebParam(name = "comparator") String comparator) throws AuthException;

    Collection<Project> findByPartOfNameOrDescription(@WebParam(name = "session") @NotNull final Session session,
                                                      @WebParam(name = "part") @NotNull final String part) throws AuthException;

}
