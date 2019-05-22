package ru.iokhin.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.repository.IProjectRepository;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.util.ComparatorUtil;
import ru.iokhin.tm.util.StringValidator;

import java.util.Collection;
import java.util.Collections;

public class ProjectService extends AbstractService<Project, IProjectRepository> implements IProjectService {

    public ProjectService(IProjectRepository repository) {
        super(repository);
    }

    @Override
    public Project add(@NotNull final User user, @NotNull final String name) {
        StringValidator.validate(name);
        return repository.persist(new Project(user.getId(), name));
    }

    @Override
    public Project edit(@NotNull final User user, @NotNull final String id, @NotNull final String name) {
        StringValidator.validate(name, id);
        @Nullable final Project project = repository.findOne(user.getId(), id);
        if (project == null) return null;
        project.setName(name);
        return project;
    }

    @Override
    public Project remove(@NotNull final User user, @NotNull final String id) {
        StringValidator.validate(id);
        @Nullable final Project project = repository.findOne(user.getId(), id);
        if (project == null) return null;
        return repository.remove(user.getId(), id);
    }

    @Override
    public void removeAllByUser(@NotNull final User user) {
        repository.removeAllByUserId(user.getId());
    }

    @Override
    public Collection<Project> findAllByUser(@NotNull final User user) {
        return repository.findAllByUserId(user.getId());
    }

    @Override
    public Project findOne(@NotNull final User user, @NotNull final String id) {
        return repository.findOne(user.getId(), id);
    }

    @Override
    public Collection<Project> sortByUserId(User user, String comparator) {
        StringValidator.validate(user.getId(), comparator);
        if (comparator.equals("order")) return findAllByUser(user);
        if (ComparatorUtil.getProjectComparator(comparator) == null) return null;
        return repository.sortByUserId(user.getId(), ComparatorUtil.getProjectComparator(comparator));
    }

    @Override
    public Collection<Project> findByPartOfNameOrDescription(@NotNull String userId, @NotNull String part) {
        return repository.findByPartOfNameOrDescription(userId, part);
    }

}
