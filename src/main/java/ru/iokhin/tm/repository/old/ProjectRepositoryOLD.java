package ru.iokhin.tm.repository.old;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.repository.IProjectRepository;
import ru.iokhin.tm.entity.Project;

import java.util.*;

@Getter
public final class ProjectRepositoryOLD extends AbstractRepositoryOLD<Project> implements IProjectRepository {

    @Override
    public Project findOne(@NotNull final String id, @NotNull final String userId) {
        Project project = repositoryMap.get(id);
        return project.getUserId().equals(userId) ? project : null;
    }

    @Override
    public Project remove(String id, String userId) {
        Project project = findOne(id, userId);
        return project != null ? repositoryMap.remove(project.getId()) : null;
    }

    @Override
    public Collection<Project> findAllByUserId(String userId) {
        Collection<Project> projectCollection = new ArrayList<>(0);
        for (Project project : findAll()) {
            if (project.getUserId().equals(userId))
                projectCollection.add(project);
        }
        return projectCollection;
    }

    @Override
    public void removeAllByUserId(String userId) {
        for (Project project : findAllByUserId(userId)) {
            remove(project.getId(), userId);
        }
    }

}
