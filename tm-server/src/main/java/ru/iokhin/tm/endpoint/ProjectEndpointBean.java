package ru.iokhin.tm.endpoint;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.entityDTO.ProjectDTO;
import ru.iokhin.tm.entityDTO.SessionDTO;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.api.service.IServiceLocator;
import ru.iokhin.tm.api.service.ISessionService;
import ru.iokhin.tm.enumerated.Status;

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
    @SneakyThrows
    public ProjectDTO addProject(@WebParam(name = "session") @NotNull final SessionDTO session,
                                 @WebParam(name = "name") @NotNull final String name) {
        sessionService.validate(session);
        return projectService.add(session.getParentId(), name);
    }

    @Override
    @SneakyThrows
    public ProjectDTO editProject(@WebParam(name = "session") @NotNull final SessionDTO session,
                                  @WebParam(name = "id") @NotNull final String id,
                                  @WebParam(name = "name") @NotNull final String name) {
        sessionService.validate(session);
        return projectService.edit(session.getParentId(), id, name, Status.PROCCESSING);
    }

    @Override
    @SneakyThrows
    public ProjectDTO removeProject(@WebParam(name = "session") @NotNull final SessionDTO session,
                                    @WebParam(name = "id") @NotNull final String id) {
        sessionService.validate(session);
        return projectService.removeByUserId(session.getParentId(), id);
    }

    @Override
    @SneakyThrows
    public void removeAllProjectByUserId(@WebParam(name = "session") @NotNull final SessionDTO session) {
        sessionService.validate(session);
        projectService.removeAllByUserId(session.getParentId());
    }

    @Override
    @SneakyThrows
    public Collection<ProjectDTO> findAllProjectByUserId(@WebParam(name = "session") @NotNull final SessionDTO session) {
        sessionService.validate(session);
        return projectService.findAllByUserId(session.getParentId());
    }

    @Override
    @SneakyThrows
    public ProjectDTO findProject(@WebParam(name = "session") @NotNull final SessionDTO session,
                                  @WebParam(name = "id") @NotNull final String id) {
        sessionService.validate(session);
        return projectService.findOneByUserId(session.getParentId(), id);
    }

    @Override
    @SneakyThrows
    public Collection<ProjectDTO> sortProjectByUserId(@WebParam(name = "session") @NotNull final SessionDTO session,
                                                      @WebParam(name = "comparator") @NotNull final String comparator) {
        sessionService.validate(session);
        return projectService.sortByUserId(session.getParentId(), comparator);
    }

    @Override
    @SneakyThrows
    public Collection<ProjectDTO> findProjectByPartOfNameOrDescription(@WebParam(name = "session") @NotNull final SessionDTO session,
                                                                       @WebParam(name = "part") @NotNull final String part) {
        sessionService.validate(session);
        return projectService.findByPartOfNameOrDescription(session.getParentId(), part);
    }
}
