package ru.iokhin.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.iokhin.tm.api.repository.IProjectRepository;
import ru.iokhin.tm.entity.Project;

import java.util.*;

public class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository {

    @Override
    public Collection<Project> findAllByUserId(@NotNull final String userId) {
        @NotNull final Collection<Project> projectCollection = new ArrayList<>(0);
        for (Project project : findAll()) {
            if (project.getParentId().equals(userId))
                projectCollection.add(project);
        }
        return projectCollection;
    }

    @Override
    public void removeAllByUserId(@NotNull final String userId) {
        for (@NotNull Project project : findAllByUserId(userId)) {
            remove(project.getId(), userId);
        }
    }

    @Override
    public Project findOne(@NotNull final String parentId, @NotNull final String id) {
        @Nullable final Project project = repository.get(id);
        if (project == null) return null;
        return project.getParentId().equals(parentId) ? project : null;
    }

    @Override
    public Project remove(@NotNull final String parentId, @NotNull final String id) {
        @Nullable final Project project = repository.get(id);
        if (project == null) return null;
        return project.getParentId().equals(parentId) ? repository.remove(project.getId()) : null;
    }

    @Override
    public Collection<Project> sortByUserId(@NotNull final String userId, Comparator<Project> comparator) {
        List<Project> projectList = new ArrayList<>(findAllByUserId(userId));
        projectList.sort(comparator);
        return projectList;
    }

    @Override
    public Collection<Project> findByPartOfNameOrDescription(@NotNull final String userId, @NotNull final String part) {
        List<Project> projectList = new ArrayList<>(0);
        for (Project project : findAllByUserId(userId)) {
            if (project.getName().contains(part) || project.getDescription().contains(part)) {
                projectList.add(project);
            }
        }
        return projectList;
    }

}
