package ru.iokhin.tm.service;

import ru.iokhin.tm.api.repository.IProjectRepository;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.util.StringValidator;

import java.util.Collection;

public class ProjectService extends AbstractService<Project, IProjectRepository> implements IProjectService {

    public ProjectService(IProjectRepository repository) {
        super(repository);
    }

    @Override
    public Project add(User user, String name) {
        StringValidator.validate(name);
        return repository.persist(new Project(user.getId(), name));
    }

    @Override
    public Project edit(User user, String id, String name) {
        StringValidator.validate(name, id);
        Project project = repository.findOne(user.getId(), id);
        if (project == null) return null;
        project.setName(name);
        return project;
    }

    @Override
    public Project remove(User user, String id) {
        StringValidator.validate(id);
        Project project = repository.findOne(user.getId(), id);
        if (project == null) return null;
        return repository.remove(user.getId(), id);
    }

    @Override
    public void removeAllByUser(User user) {
        repository.removeAllByUserId(user.getId());
    }

    @Override
    public Collection<Project> findAllByUser(User user) {
        return repository.findAllByUserId(user.getId());
    }
}
