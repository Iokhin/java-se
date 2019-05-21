package ru.iokhin.tm.service.old;

import ru.iokhin.tm.api.repository.IProjectRepository;
import ru.iokhin.tm.api.service.IProjectService;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.service.AbstractService;
import ru.iokhin.tm.util.StringValidator;

import java.util.Collection;

public final class ProjectService extends AbstractService<Project, IProjectRepository> implements IProjectService {

    public ProjectService(IProjectRepository repository) {
        super(repository);
    }

    @Override
    public Project add(String name, User user) {
        StringValidator.validate(name);
        return repository.persist(new Project(name, user.getId()));
    }

    @Override
    public Project edit(String userId, String id, String name) {
        StringValidator.validate(userId, id, name);
        Project project = repository.findOne(id, userId);
        if (project == null) return null;
        project.setName(name);
        return project;
    }

    @Override
    public Project remove(String userId, String id) {
        StringValidator.validate(userId, id);
        Project project = repository.findOne(id, userId);
        if (project == null) return null;
        return repository.remove(id, userId);
    }

    @Override
    public Collection<Project> findAllByUserId(String userId) {
        StringValidator.validate(userId);
        return repository.findAllByUserId(userId);
    }

    @Override
    public void removeAllByUserId(String userId) {
        StringValidator.validate(userId);
        repository.removeAllByUserId(userId);
    }

    public Project findOne(String userId, String id) {
        StringValidator.validate(userId, id);
        return findOne(userId, id);
    }

//    @Override
//    public Project add(String name, User user) {
//        if (!StringValidator.isValid(name)) {
//            System.out.println("Illegal name");
//            return null;
//        }
//        return repository.persist(new Project(name, user.getId()));
//    }
//
//    @Override
//    public Project edit(String id, String name) {
//        if (!StringValidator.isValid(id, name)) {
//            System.out.println("Illegal argument");
//            return null;
//        }
//        Project project = repository.findOne(id);
//        if (project == null) {
//            System.out.println("NO PROJECT WITH SUCH ID");
//        }
//        project.setName(name);
//        return project;
//    }
//
//    @Override
//    public Collection<Project> findAllByUser(String id) {
//        if (!StringValidator.isValid(id)) {
//            System.out.println("Illegal argument");
//            return null;
//        }
//        return ((IProjectRepository) repository).findAllByUser(id);
//    }
//
//    @Override
//    public void removeAllByUserId(String id) {
//        if (!StringValidator.isValid(id)) {
//            System.out.println("Illegal argument");
//            return;
//        }
//        ((IProjectRepository) repository).removeAllByUserId(id);
//    }

}
