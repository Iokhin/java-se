package ru.iokhin.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.api.service.IServiceLocator;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.Session;
import ru.iokhin.tm.enumerated.Status;
import ru.iokhin.tm.exeption.AuthException;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Collection;

@WebService
@NoArgsConstructor
public class ProjectEndpointBean implements ru.iokhin.tm.api.endpoint.ProjectEndpoint {
    @NotNull
    private IProjectService projectService;

    @NotNull
    private ISessionService sessionService;

    public ProjectEndpointBean(@NotNull final IServiceLocator serviceLocator) {
        this.projectService = serviceLocator.getProjectService();
        this.sessionService = serviceLocator.getSessionService();
    }

    @Override
    public Project add(@WebParam(name = "session") @NotNull final Session session,
                       @WebParam(name = "name") @NotNull final String name) throws AuthException {
        sessionService.validate(session);
        return projectService.add(session.getParentId(), name);
    }

    @Override
    public Project edit(@WebParam(name = "session") @NotNull final Session session,
                        @WebParam(name = "id") @NotNull final String id,
                        @WebParam(name = "name") @NotNull final String name) throws AuthException {
        sessionService.validate(session);
        return projectService.edit(session.getParentId(), id, name, Status.PROCCESSING);
    }

    @Override
    public Project remove(@WebParam(name = "session") @NotNull final Session session,
                          @WebParam(name = "id") @NotNull final String id) throws AuthException {
        sessionService.validate(session);
        return projectService.remove(session.getParentId(), id);
    }

    @Override
    public void removeAllByUserId(@WebParam(name = "session") @NotNull final Session session) throws AuthException {
        sessionService.validate(session);
        projectService.removeAllByUserId(session.getParentId());
    }

    @Override
    public Collection<Project> findAllByUserId(@WebParam(name = "session") @NotNull final Session session) throws AuthException {
        sessionService.validate(session);
        return projectService.findAllByUserId(session.getParentId());
    }

    @Override
    public Project findOne(@WebParam(name = "session") @NotNull final Session session,
                           @WebParam(name = "id") @NotNull final String id) throws AuthException {
        sessionService.validate(session);
        return projectService.findOne(session.getParentId(), id);
    }

    @Override
    public Collection<Project> sortByUserId(@WebParam(name = "session") @NotNull final Session session,
                                            @WebParam(name = "comparator") @NotNull final String comparator) throws AuthException {
        sessionService.validate(session);
        return projectService.sortByUserId(session.getParentId(), comparator);
    }

    @Override
    public Collection<Project> findByPartOfNameOrDescription(@WebParam(name = "session") @NotNull final Session session,
                                                             @WebParam(name = "part") @NotNull final String part) throws AuthException {
        sessionService.validate(session);
        return projectService.findByPartOfNameOrDescription(session.getParentId(), part);
    }
}
