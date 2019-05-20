package ru.iokhin.tm.service;

import ru.iokhin.tm.api.repository.IAbstractRepository;
import ru.iokhin.tm.api.repository.IProjectRepository;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.util.StringValidator;

import java.util.Collection;

public final class ProjectService extends AbstractService<Project> implements IProjectService {

    public ProjectService(IAbstractRepository<Project> repository) {
        super(repository);
    }

    @Override
    public Project add(String name, User user) {
        if (!StringValidator.isValid(name)) {
            System.out.println("Illegal name");
            return null;
        }
        return repository.persist(new Project(name, user.getId()));
    }

    @Override
    public Project edit(String id, String name) {
        if (!StringValidator.isValid(id, name)) {
            System.out.println("Illegal argument");
            return null;
        }
        Project project = repository.findOne(id);
        if (project == null) {
            System.out.println("NO PROJECT WITH SUCH ID");
        }
        project.setName(name);
        return project;
    }

    @Override
    public Collection<Project> findAllByUserId(String id) {
        if (!StringValidator.isValid(id)) {
            System.out.println("Illegal argument");
            return null;
        }
        return ((IProjectRepository)repository).findAllByUserId(id);
    }

    @Override
    public void removeAllByUserId(String id) {
        if (!StringValidator.isValid(id)) {
            System.out.println("Illegal argument");
            return;
        }
        ((IProjectRepository)repository).removeAllByUserId(id);
    }

}
