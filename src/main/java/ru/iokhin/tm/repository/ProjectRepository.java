package ru.iokhin.tm.repository;

import ru.iokhin.tm.api.repository.IProjectRepository;
import ru.iokhin.tm.entity.Project;

import java.util.ArrayList;
import java.util.Collection;

public class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository {

    @Override
    public Collection<Project> findAllByUserId(String userId) {
        Collection<Project> projectCollection = new ArrayList<>(0);
        for (Project project : findAll()) {
            if (project.getParentId().equals(userId))
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

    @Override
    public Project findOne(String parentId, String id) {
        Project project = repository.get(id);
        return project.getParentId().equals(parentId) ? project : null;
    }

    @Override
    public Project remove(String parentId, String id) {
        Project project = repository.get(id);
        return project.getParentId().equals(parentId) ? repository.remove(project.getId()) : null;
    }

}
