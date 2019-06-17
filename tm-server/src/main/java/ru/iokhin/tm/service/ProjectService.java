package ru.iokhin.tm.service;

import lombok.SneakyThrows;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.entityDTO.ProjectDTO;
import ru.iokhin.tm.api.repository.IProjectRepository;
import ru.iokhin.tm.api.repository.IUserRepository;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.enumerated.Status;
import ru.iokhin.tm.exeption.AuthException;
import ru.iokhin.tm.util.StringValidator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProjectService extends AbstractService<ProjectDTO> implements IProjectService {

    @NotNull
    private final IProjectRepository projectRepository;

    @NotNull
    private final IUserRepository userRepository;

    @Inject
    public ProjectService(IProjectRepository projectRepository, IUserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public ProjectDTO add(@NotNull final String userId, @NotNull final String name) throws AuthException {
        StringValidator.validate(name);
        ProjectDTO project = new ProjectDTO(userId, name);
        merge(project);
        return project;
    }

    @Override
    @Transactional
    public ProjectDTO edit(@NotNull final String userId, @NotNull final String id, @NotNull final String name) throws AuthException {
        StringValidator.validate(name, id, userId);
        @Nullable final Project project = projectRepository.findAnyByUserAndId(getUser(userId), id);
        if (project == null) return null;
        project.setName(name);
        projectRepository.save(project);
        return project.getProjectDTO();
    }

    @Override
    @Transactional
    public ProjectDTO edit(@NotNull String userId, @NotNull String id, @NotNull String name, @NotNull Status status)
            throws AuthException {
        StringValidator.validate(name, id, userId);
        @Nullable final Project project = projectRepository.findAnyByUserAndId(getUser(userId), id);
        if (project == null) throw new AuthException("PROJECT NOT FOUND");
        project.setName(name);
        project.setStatus(status);
        projectRepository.save(project);
        return project.getProjectDTO();
    }

    @Override
    @Transactional
    public ProjectDTO removeByUserId(@NotNull final String userId, @NotNull final String id) throws AuthException {
        StringValidator.validate(id, userId);
        @Nullable final Project project = projectRepository.findAnyByUserAndId(getUser(userId), id);
        if (project == null) throw new AuthException("PROJECT NOT FOUND");
        @NotNull final ProjectDTO projectDTO = project.getProjectDTO();
        remove(projectDTO);
        return projectDTO;
    }

    @Override
    @SneakyThrows
    @Transactional
    public void removeAllByUserId(@NotNull final String userId) {
        StringValidator.validate(userId);
        User user = getUser(userId);
        if (user == null) throw new AuthException("USER NOT FOUND");
        @NotNull final List<Project> projects = projectRepository.findByUser(user);
        if (projects == null) throw new AuthException("PROJECTS NOT FOUND");
        for (Project project : projects) {
            projectRepository.remove(project);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProjectDTO> findAllByUserId(@NotNull final String userId) throws AuthException {
        StringValidator.validate(userId);
        @Nullable final User user = userRepository.findBy(userId);
        if (user == null) throw new AuthException("USER NOT FOUND");
        @Nullable final List<Project> projects = projectRepository.findByUser(user);
        if (projects == null) throw new AuthException("PROJECTS NOT FOUND");
        return projects.stream().map(Project::getProjectDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectDTO findOneByUserId(@NotNull final String userId, @NotNull final String id) throws AuthException {
        StringValidator.validate(userId, id);
        @Nullable final User user = userRepository.findBy(userId);
        if (user == null) throw new AuthException("USER NOT FOUND");
        @Nullable final Project project = projectRepository.findAnyByUserAndId(getUser(userId), id);
        if (project == null) return null;
        return project.getProjectDTO();
    }

    @Override
    @SneakyThrows
    @Transactional(readOnly = true)
    public List<ProjectDTO> sortByUserId(@NotNull String userId, @NotNull String parameter) {
        StringValidator.validate(userId, parameter);
        @Nullable final User user = getUser(userId);
        if (user == null) return null;
        switch (parameter) {
            case "order":
                return findAllByUserId(userId);
            case "status":
                return projectRepository.sortByStatus(user).stream().map(Project::getProjectDTO).collect(Collectors.toList());
            case "dateStart":
                return projectRepository.sortByDateStart(user).stream().map(Project::getProjectDTO).collect(Collectors.toList());
            case "dateEnd":
                return projectRepository.sortByDateEnd(user).stream().map(Project::getProjectDTO).collect(Collectors.toList());
            default:
                throw new IllegalArgumentException("WRONG PARAMETER. VALID PARAMETERS: order, status, dateStart, dateEnd");
        }
    }

    @Override
    @SneakyThrows
    @Transactional(readOnly = true)
    public List<ProjectDTO> findByPartOfNameOrDescription(@NotNull final String userId, @NotNull final String part) {
        @Nullable final List<Project> projects = projectRepository.findByPartOfNameOrDescription(getUser(userId), part);
        if (projects == null) throw new AuthException("PROJECTS NOT FOUND");
        return projects.stream().map(Project::getProjectDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Project getProjectFromDTO(@NotNull final ProjectDTO projectDTO) throws AuthException {
        Project project = new Project();
        project.setId(projectDTO.getId());
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setStatus(projectDTO.getStatus());
        project.setDateStart(projectDTO.getStartDate());
        project.setDateEnd(projectDTO.getEndDate());
        project.setUser(getUser(projectDTO.getParentId()));
        return project;
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(@NotNull final String userId) throws AuthException {
        @Nullable final User user = userRepository.findBy(userId);
        if (user == null) throw new AuthException("USER IS NULL");
        return user;
    }

    @Override
    @Transactional
    public void persist(@NotNull final ProjectDTO projectDTO) throws AuthException {
        @NotNull final Project project = getProjectFromDTO(projectDTO);
        projectRepository.save(project);
    }

    @Override
    @Transactional
    public void merge(@NotNull final ProjectDTO projectDTO) throws AuthException {
        @NotNull final Project project = getProjectFromDTO(projectDTO);
        projectRepository.save(project);
    }

    @Override
    @Transactional(readOnly = true)
    public ProjectDTO findOne(@NotNull final String id) {
        @Nullable final Project project = projectRepository.findBy(id);
        if (project == null) return null;
        return project.getProjectDTO();
    }

    @Override
    @Transactional
    public void remove(@NotNull final ProjectDTO projectDTO) throws AuthException {
        @Nullable final Project project = projectRepository.findBy(projectDTO.getId());
        if (project == null) throw new AuthException("PROJECT IS NULL");
        projectRepository.remove(project);
    }
}
