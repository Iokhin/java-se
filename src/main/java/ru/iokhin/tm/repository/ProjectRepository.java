package ru.iokhin.tm.repository;

import lombok.Getter;
import ru.iokhin.tm.api.repository.IProjectRepository;
import ru.iokhin.tm.entity.Project;

import java.util.*;

@Getter
public final class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository {

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
            remove(project.getId());
        }
    }

    public Map<String, Project> getRepositoryMap() {
        return this.repositoryMap;
    }

}
