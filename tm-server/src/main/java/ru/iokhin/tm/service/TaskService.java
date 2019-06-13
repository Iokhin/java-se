package ru.iokhin.tm.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.entityDTO.TaskDTO;
import ru.iokhin.tm.api.repository.IProjectRepository;
import ru.iokhin.tm.api.repository.ITaskRepository;
import ru.iokhin.tm.api.repository.IUserRepository;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.Task;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.repository.ProjectRepository;
import ru.iokhin.tm.repository.TaskRepository;
import ru.iokhin.tm.repository.UserRepository;
import ru.iokhin.tm.util.StringValidator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class TaskService extends AbstractService<TaskDTO> implements ITaskService {

    @Inject
    public TaskService(@NotNull EntityManagerFactory factory) {
        super(factory);
    }

    @Override
    public TaskDTO add(@NotNull final String userId, @NotNull final String projectId, @NotNull final String name) {
        StringValidator.validate(projectId, name, userId);
        TaskDTO task = new TaskDTO(userId, projectId, name);
        merge(task);
        return task;
    }

    @Override
    @SneakyThrows
    public TaskDTO edit(@NotNull final String userId, @NotNull final String id, @NotNull final String name) {
        StringValidator.validate(name, id, userId);
        @Nullable final TaskDTO task = findOneByUserId(userId, id);
        if (task == null) return null;
        task.setName(name);
        merge(task);
        return task;
    }

    @Override
    @SneakyThrows
    public TaskDTO removeByUserId(@NotNull final String userId, @NotNull final String id) {
        StringValidator.validate(id, userId);
        @NotNull final TaskDTO task = findOneByUserId(userId, id);
        remove(task);
        return task;
    }

    @Override
    public void removeAllByUserId(@NotNull final String userId) {
        StringValidator.validate(userId);
        @NotNull final List<TaskDTO> tasks = findAllByUserId(userId);
        tasks.forEach(this::remove);
    }

    @Override
    public TaskDTO findOneByUserId(@NotNull String userId, @NotNull String id) {
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        @Nullable final Task task = taskRepository.findOneByUserId(getUser(userId, em), id);
        return task.getTaskDTO();
    }

    @Override
    @SneakyThrows
    public List<TaskDTO> findAllByUserId(@NotNull final String userId) {
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        @Nullable final List<TaskDTO> tasks = taskRepository.findAllByUserId(getUser(userId, em))
                .stream().map(Task::getTaskDTO).collect(Collectors.toList());
        return tasks;
    }

    @Override
    public List<TaskDTO> findAllByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        @Nullable final List<TaskDTO> tasks = taskRepository.findAllByProjectId(getUser(userId, em),
                getProject(projectId, em)).stream().map(Task::getTaskDTO).collect(Collectors.toList());
        return tasks;
    }

    @Override
    public void removeAllByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        @Nullable final List<TaskDTO> tasks = findAllByProjectId(userId, projectId);
        if (tasks == null) return;
        tasks.forEach(this::remove);
    }

    @Override
    @SneakyThrows
    public Collection<TaskDTO> sortByUserId(@NotNull String userId, @NotNull String parameter) {
        StringValidator.validate(userId, parameter);
        if ("order".equals(parameter)) return findAllByUserId(userId);
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        @NotNull final List<TaskDTO> tasks = taskRepository.sortByUserId(getUser(userId, em), parameter).stream()
                .map(Task::getTaskDTO).collect(Collectors.toList());
        return tasks;
    }

    @Override
    public List<TaskDTO> findByPartOfNameOrDescription(@NotNull final String userId, @NotNull final String keyWord) {
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        @NotNull final List<TaskDTO> tasks = taskRepository.findByPartOfNameOrDescription(getUser(userId, em), keyWord)
                .stream().map(Task::getTaskDTO).collect(Collectors.toList());
        return tasks;
    }

    @Override
    public Task getTaskFromDTO(@NotNull TaskDTO taskDTO, @NotNull EntityManager em) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        task.setDateStart(taskDTO.getStartDate());
        task.setDateEnd(taskDTO.getEndDate());
        task.setProject(getProject(taskDTO.getProjectId(), em));
        task.setUser(getUser(taskDTO.getParentId(), em));
        return task;
    }


    @Override
    public User getUser(@NotNull String userId, @NotNull EntityManager em) {
        @NotNull final IUserRepository userRepository = new UserRepository(em);
        @NotNull final User user = userRepository.findOne(userId);
        return user;
    }

    @Override
    public Project getProject(@NotNull String projectId, @NotNull EntityManager em) {
        @NotNull final IProjectRepository projectRepository = new ProjectRepository(em);
        @NotNull final Project project = projectRepository.findOne(projectId);
        return project;
    }

    @Override
    public void persist(@NotNull TaskDTO taskDTO) {
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        @NotNull final Task task = getTaskFromDTO(taskDTO, em);
        if (task == null) System.out.println("NULL");
        try {
            em.getTransaction().begin();
            taskRepository.persist(task);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void merge(@NotNull TaskDTO taskDTO) {
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        @NotNull final Task task = getTaskFromDTO(taskDTO, em);
        try {
            em.getTransaction().begin();
            taskRepository.merge(task);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }


    @Override
    public TaskDTO findOne(@NotNull String id) {
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        return taskRepository.findOne(id).getTaskDTO();
    }

    @Override
    public void remove(@NotNull TaskDTO taskDTO) {
        @NotNull final EntityManager em = factory.createEntityManager();
        @NotNull final ITaskRepository taskRepository = new TaskRepository(em);
        @NotNull final Task task = getTaskFromDTO(taskDTO, em);
        try {
            em.getTransaction().begin();
            taskRepository.remove(task);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
