package ru.iokhin.tm.service;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.iokhin.tm.entityDTO.TaskDTO;
import ru.iokhin.tm.api.repository.IProjectRepository;
import ru.iokhin.tm.api.repository.ITaskRepository;
import ru.iokhin.tm.api.repository.IUserRepository;
import ru.iokhin.tm.api.service.ITaskService;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.Task;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.util.StringValidator;

import java.util.List;
import java.util.stream.Collectors;

@Service("taskService")
@Scope("singleton")
public class TaskService extends AbstractService<TaskDTO> implements ITaskService {

    @NotNull
    private final IUserRepository userRepository;

    @NotNull
    private final IProjectRepository projectRepository;

    @NotNull
    private final ITaskRepository taskRepository;

    @Autowired
    public TaskService(@NotNull IUserRepository userRepository, @NotNull IProjectRepository projectRepository, @NotNull ITaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    @Transactional
    public TaskDTO add(@NotNull final String userId, @NotNull final String projectId, @NotNull final String name) {
        StringValidator.validate(projectId, name, userId);
        TaskDTO task = new TaskDTO(userId, projectId, name);
        merge(task);
        return task;
    }

    @Override
    @SneakyThrows
    @Transactional
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
    @Transactional
    public TaskDTO removeByUserId(@NotNull final String userId, @NotNull final String id) {
        StringValidator.validate(id, userId);
        @Nullable final TaskDTO task = findOneByUserId(userId, id);
        if (task == null) return null;
        remove(task);
        return task;
    }

    @Override
    @Transactional
    public void removeAllByUserId(@NotNull final String userId) {
        StringValidator.validate(userId);
        @Nullable final User user = getUser(userId);
        if (user == null) return;
        @Nullable final List<Task> tasks = taskRepository.findByUser(user);
        if (tasks == null) return;
        tasks.forEach(taskRepository::delete);
    }

    @Override
    @Transactional(readOnly = true)
    public TaskDTO findOneByUserId(@NotNull String userId, @NotNull String id) {
        @Nullable final Task task = taskRepository.findAnyByUserAndId(getUser(userId), id);
        if (task == null) return null;
        return task.getTaskDTO();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findAllByUserId(@NotNull String userId) {
        StringValidator.validate(userId);
        @Nullable final User user = getUser(userId);
        if (user == null) return null;
        @Nullable final List<Task> tasks = taskRepository.findByUser(user);
        if (tasks == null) return null;
        return tasks.stream().map(Task::getTaskDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findAllByProjectId(@NotNull String userId, @NotNull String projectId) {
        StringValidator.validate(userId);
        @Nullable final User user = getUser(userId);
        if (user == null) return null;
        @Nullable final Project project = getProject(projectId);
        if (project == null) return null;
        @Nullable final List<Task> tasks = taskRepository.findByUserAndProject(user, project);
        if (tasks == null) return null;
        return tasks.stream().map(Task::getTaskDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void removeAllByProjectId(@NotNull final String userId, @NotNull final String projectId) {
        @Nullable final List<TaskDTO> tasks = findAllByProjectId(userId, projectId);
        if (tasks == null) return;
        tasks.forEach(this::remove);
    }

    @Override
    @SneakyThrows
    @Transactional(readOnly = true)
    public List<TaskDTO> sortByUserId(@NotNull String userId, @NotNull String parameter) {
        StringValidator.validate(userId, parameter);
        @Nullable final User user = getUser(userId);
        if (user == null) return null;
        switch (parameter) {
            case "order":
                return findAllByUserId(userId);
            case "status":
                return taskRepository.sortByStatus(user).stream().map(Task::getTaskDTO).collect(Collectors.toList());
            case "dateStart":
                return taskRepository.sortByDateStart(user).stream().map(Task::getTaskDTO).collect(Collectors.toList());
            case "dateEnd":
                return taskRepository.sortByDateEnd(user).stream().map(Task::getTaskDTO).collect(Collectors.toList());
            default:
                throw new IllegalArgumentException("WRONG PARAMETER. VALID PARAMETERS: order, status, dateStart, dateEnd");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findByPartOfNameOrDescription(@NotNull final String userId, @NotNull final String keyWord) {
        StringValidator.validate(userId, keyWord);
        @Nullable final User user = getUser(userId);
        if (user == null) return null;
        @Nullable final List<Task> tasks = taskRepository.findByPartOfNameOrDescription(getUser(userId), keyWord);
        if (tasks == null) return null;
        return tasks.stream().map(Task::getTaskDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Task getTaskFromDTO(@NotNull TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setStatus(taskDTO.getStatus());
        task.setDateStart(taskDTO.getStartDate());
        task.setDateEnd(taskDTO.getEndDate());
        task.setProject(getProject(taskDTO.getProjectId()));
        task.setUser(getUser(taskDTO.getParentId()));
        return task;
    }


    @Override
    @Transactional(readOnly = true)
    public User getUser(@NotNull String userId) {
        @Nullable final User user = userRepository.findById(userId).orElse(null);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public Project getProject(@NotNull String projectId) {
        @Nullable final Project project = projectRepository.findById(projectId).orElse(null);
        return project;
    }

    @Override
    @Transactional
    public void persist(@NotNull TaskDTO taskDTO) {
        @NotNull final Task task = getTaskFromDTO(taskDTO);
        taskRepository.save(task);
    }

    @Override
    @Transactional
    public void merge(@NotNull TaskDTO taskDTO) {
        @NotNull final Task task = getTaskFromDTO(taskDTO);
        taskRepository.save(task);
    }


    @Override
    @Transactional(readOnly = true)
    public TaskDTO findOne(@NotNull String id) {
        @Nullable final Task task = taskRepository.findById(id).orElse(null);
        if (task == null) return null;
        return task.getTaskDTO();
    }

    @Override
    @Transactional
    public void remove(@NotNull TaskDTO taskDTO) {
        @Nullable final Task task = taskRepository.findById(taskDTO.getId()).orElse(null);
        if (task == null) return;
        taskRepository.delete(task);
    }
}
