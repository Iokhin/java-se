package ru.iokhin.tm.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.entityDTO.ProjectDTO;
import ru.iokhin.tm.api.repository.IProjectRepository;
import ru.iokhin.tm.api.repository.IUserRepository;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.enumerated.Status;
import ru.iokhin.tm.repository.ProjectRepository;
import ru.iokhin.tm.repository.UserRepository;
import ru.iokhin.tm.util.StringValidator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectService extends AbstractService<ProjectDTO> implements IProjectService {

    public ProjectService(@NotNull EntityManagerFactory factory) {
        super(factory);
    }

    @Override
    public ProjectDTO add(@NotNull final String userId, @NotNull final String name) {
        StringValidator.validate(name);
        ProjectDTO project = new ProjectDTO(userId, name);
        merge(project);
        return project;
    }

    @Override
    public ProjectDTO edit(@NotNull final String userId, @NotNull final String id, @NotNull final String name) {
        StringValidator.validate(name, id, userId);
        @Nullable final ProjectDTO project = findOneByUserId(userId, id);
        if (project == null) return null;
        project.setName(name);
        merge(project);
        return project;
    }

    @Override
    public ProjectDTO edit(@NotNull String userId, @NotNull String id, @NotNull String name, @NotNull Status status) {
        StringValidator.validate(name, id, userId);
        @Nullable final ProjectDTO project = findOneByUserId(userId, id);
        if (project == null) return null;
        project.setName(name);
        project.setStatus(status);
        merge(project);
        return project;
    }

    @Override
    public ProjectDTO removeByUserId(@NotNull final String userId, @NotNull final String id) {
        StringValidator.validate(id, userId);
        @Nullable final ProjectDTO project = findOneByUserId(userId, id);
        if (project == null) return null;
        remove(project);
        return project;
    }

    @Override
    @SneakyThrows
    public void removeAllByUserId(@NotNull final String userId) {
        StringValidator.validate(userId);
        @NotNull final List<ProjectDTO> projects = findAllByUserId(userId);
        projects.forEach(this::remove);
    }

    @Override
    @SneakyThrows
    public List<ProjectDTO> findAllByUserId(@NotNull final String userId) {
        StringValidator.validate(userId);
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(em);
        @NotNull final List<ProjectDTO> projects = projectRepository.findAllByUserId(getUser(userId, em))
                .stream().map(Project::getProjectDTO)
                .collect(Collectors.toList());
        return projects;
    }

    @Override
    public ProjectDTO findOneByUserId(@NotNull final String userId, @NotNull final String id) {
        StringValidator.validate(userId, id);
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(em);
        @Nullable final Project project = projectRepository.findOneByUserId(getUser(userId, em), id);
        if (project == null) return null;
        return project.getProjectDTO();
    }

    @Override
    @SneakyThrows
    public List<ProjectDTO> sortByUserId(@NotNull final String userId, @NotNull final String parameter) {
        StringValidator.validate(userId, parameter);
        if ("order".equals(parameter)) return findAllByUserId(userId);
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(em);
        @Nullable final List<ProjectDTO> projects = projectRepository.sortByUserId(getUser(userId, em), parameter)
                .stream().map(Project::getProjectDTO).collect(Collectors.toList());
        return projects;
    }

    @Override
    @SneakyThrows
    public List<ProjectDTO> findByPartOfNameOrDescription(@NotNull final String userId, @NotNull final String part) {
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(em);
        @Nullable final List<ProjectDTO> projects = projectRepository
                .findByPartOfNameOrDescription(getUser(userId, em), part)
                .stream().map(Project::getProjectDTO).collect(Collectors.toList());
        return projects;
    }

    @Override
    public Project getProjectFromDTO(ProjectDTO projectDTO, EntityManager em) {
        Project project = new Project();
        project.setId(projectDTO.getId());
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setStatus(projectDTO.getStatus());
        project.setDateStart(projectDTO.getStartDate());
        project.setDateEnd(projectDTO.getEndDate());
        project.setUser(getUser(projectDTO.getParentId(), em));
        return project;
    }

    @Override
    public User getUser(@NotNull final String userId, @NotNull final EntityManager em) {
        @NotNull final IUserRepository userRepository = new UserRepository(em);
        @NotNull final User user = userRepository.findOne(userId);
        return user;
    }

    @Override
    public void persist(@NotNull ProjectDTO projectDTO) {
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(em);
        @NotNull final Project project = getProjectFromDTO(projectDTO, em);
        try {
            em.getTransaction().begin();
            projectRepository.persist(project);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void merge(@NotNull ProjectDTO projectDTO) {
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(em);
        @NotNull final Project project = getProjectFromDTO(projectDTO, em);
        try {
            em.getTransaction().begin();
            projectRepository.merge(project);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public ProjectDTO findOne(@NotNull String id) {
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(em);
        @Nullable final Project project = projectRepository.findOne(id);
        return project.getProjectDTO();
    }

    @Override
    public void remove(@NotNull ProjectDTO projectDTO) {
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(em);
        @NotNull final Project project = getProjectFromDTO(projectDTO, em);
        try {
            em.getTransaction().begin();
            projectRepository.remove(project);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
