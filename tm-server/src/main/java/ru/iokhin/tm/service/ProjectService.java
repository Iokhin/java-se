package ru.iokhin.tm.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.repository.IProjectRepository;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.enumerated.Status;
import ru.iokhin.tm.util.ComparatorUtil;
import ru.iokhin.tm.util.StringValidator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

@RequiredArgsConstructor
public class ProjectService extends AbstractService<Project, IProjectRepository> implements IProjectService {

//    public ProjectService(IProjectRepository repository) {
//        super(repository);
//    }

    @NotNull
    private final SqlSessionFactory sqlSessionFactory;

    @Override
    public Project add(@NotNull final String userId, @NotNull final String name) throws SQLException {
        StringValidator.validate(name);
        return repository.persist(new Project(userId, name));
    }

    @Override
    public Project edit(@NotNull final String userId, @NotNull final String id, @NotNull final String name) {
        StringValidator.validate(name, id);
        @Nullable final Project project = repository.findOneByUserId(userId, id);
        if (project == null) return null;
        project.setName(name);
        return project;
    }

    @Override
    public Project edit(@NotNull String userId, @NotNull String id, @NotNull String name, @NotNull Status status) {
        StringValidator.validate(name, id, userId);
        @Nullable final Project project = repository.findOneByUserId(userId, id);
        if (project == null) return null;
        project.setName(name);
        project.setStatus(status);
        repository.merge(project);
        return project;
    }

    @Override
    public Project remove(@NotNull final String userId, @NotNull final String id) {
        StringValidator.validate(id);
        @Nullable final Project project = repository.findOneByUserId(userId, id);
        if (project == null) return null;
        return repository.remove(userId, id);
    }

    @Override
    @SneakyThrows
    public void removeAllByUserId(@NotNull final String userId) {
        repository.removeAllByUserId(userId);
    }

    @Override
    @SneakyThrows
    public Collection<Project> findAllByUserId(@NotNull final String userId) {
        return repository.findAllByUserId(userId);
    }

    @Override
    public Project findOne(@NotNull final String userId, @NotNull final String id) {
        return repository.findOneByUserId(userId, id);
    }

    @Override
    @SneakyThrows
    public Collection<Project> sortByUserId(@NotNull final String userId, @NotNull final String comparator) {
        StringValidator.validate(userId, comparator);
        if (comparator.equals("order")) return findAllByUserId(userId);
        if (ComparatorUtil.getProjectComparator(comparator) == null) return null;
        return repository.sortByUserId(userId, ComparatorUtil.getProjectComparator(comparator));
    }

    @Override
    @SneakyThrows
    public Collection<Project> findByPartOfNameOrDescription(@NotNull final String userId, @NotNull final String part) {
        return repository.findByPartOfNameOrDescription(userId, part);
    }

}
