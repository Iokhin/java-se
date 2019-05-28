package ru.iokhin.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.repository.IProjectRepository;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.enumerated.Status;
import ru.iokhin.tm.util.ComparatorUtil;
import ru.iokhin.tm.util.StringValidator;

import java.util.Collection;

public class ProjectService extends AbstractService<Project, IProjectRepository> implements IProjectService {

    public ProjectService(IProjectRepository repository) {
        super(repository);
    }

    @Override
    public Project add(@NotNull final String userId, @NotNull final String name) {
        StringValidator.validate(name);
        return repository.persist(new Project(userId, name));
    }

    @Override
    public Project edit(@NotNull final String userId, @NotNull final String id, @NotNull final String name) {
        StringValidator.validate(name, id);
        @Nullable final Project project = repository.findOne(userId, id);
        if (project == null) return null;
        project.setName(name);
        return project;
    }

    @Override
    public Project edit(@NotNull String userId, @NotNull String id, @NotNull String name, @NotNull Status status) {
        StringValidator.validate(name, id);
        @Nullable final Project project = repository.findOne(userId, id);
        if (project == null) return null;
        project.setName(name);
        project.setStatus(status);
        return project;
    }

    @Override
    public Project remove(@NotNull final String userId, @NotNull final String id) {
        StringValidator.validate(id);
        @Nullable final Project project = repository.findOne(userId, id);
        if (project == null) return null;
        return repository.remove(userId, id);
    }

    @Override
    public void removeAllByUserId(@NotNull final String userId) {
        repository.removeAllByUserId(userId);
    }

    @Override
    public Collection<Project> findAllByUserId(@NotNull final String userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public Project findOne(@NotNull final String userId, @NotNull final String id) {
        return repository.findOne(userId, id);
    }

    @Override
    public Collection<Project> sortByUserId(@NotNull final String userId, @NotNull final String comparator) {
        StringValidator.validate(userId, comparator);
        if (comparator.equals("order")) return findAllByUserId(userId);
        if (ComparatorUtil.getProjectComparator(comparator) == null) return null;
        return repository.sortByUserId(userId, ComparatorUtil.getProjectComparator(comparator));
    }

    @Override
    public Collection<Project> findByPartOfNameOrDescription(@NotNull final String userId, @NotNull final String part) {
        return repository.findByPartOfNameOrDescription(userId, part);
    }

}
