package ru.iokhin.tm.service;

import ru.iokhin.tm.api.IProjectService;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.repository.ProjectRepository;

public class ProjectService implements IProjectService {

    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void addProject(String name, User user) {
        if (name == null && name.trim().isEmpty()) {
            System.out.println("Illegal name");
            return;
        }
        projectRepository.add(new Project(name, user.getUserId()));
    }

    @Override
    public void listProject(String userId) {
        projectRepository.list(userId);
    }

    @Override
    public void removeProject(String id) {
        if (id == null && id.trim().isEmpty()) {
            System.out.println("Illegal ID");
            return;
        }
        Project project;
        if ((project = projectRepository.findById(id)) == null) {
            System.out.println("NO PROJECT WITH SUCH ID");
        } else projectRepository.delete(project.getId());
    }

    @Override
    public void clearProject() {
        projectRepository.clear();
    }

    @Override
    public void editProject(String id, String newName) {
        if (id == null && id.trim().isEmpty()) {
            System.out.println("Illegal ID");
            return;
        }
        if (newName == null && newName.trim().isEmpty()) {
            System.out.println("Illegal name");
            return;
        }
        Project project;
        if ((project = projectRepository.findById(id)) == null) {
            System.out.println("NO PROJECT WITH SUCH ID");
        }
        projectRepository.merge(new Project(newName, project.getId(), project.getUserId()));
    }

    public Project getProjectById(String id) {
        return projectRepository.findById(id);
    }
}
