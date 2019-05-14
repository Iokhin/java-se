package ru.iokhin.tm.service;

import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.repository.ProjectRepository;

public class ProjectService implements ProjectServiceInterface {

    private ProjectRepository pr;

    public ProjectService(ProjectRepository pr) {
        this.pr = pr;
    }

    @Override
    public void addProject(String name, User user) {
        if (name != null && !name.trim().isEmpty()) {
            pr.add(new Project(name, user.getUserId()));
        }
        else {
            System.out.println("Illegal name");
        }
    }

    @Override
    public void listProject(String userId) {
        pr.list(userId);
    }

    @Override
    public void removeProject(String id) {
        if (id != null && !id.trim().isEmpty()) {
            for (Project project : pr.projectLinkedHashMap.values()) {
                if (project.getId().equals(id)) {
                    pr.delete(id);
                    return;
                }
            }
        }
        else {
            System.out.println("Illegal name");
        }
    }

    @Override
    public void clearProject() {
        pr.clear();
    }

    @Override
    public void editProject(String id, String newName) {
        if (id != null && !id.trim().isEmpty() && newName != null && !newName.trim().isEmpty()) {
            for (Project project : pr.projectLinkedHashMap.values()) {
                if (project.getId().equals(id)) {
                    Project newProject = new Project(newName, project.getId());
                    pr.merge(newProject);
                    return;
                }
            }
            System.out.println("NO PROJECT WITH SUCH ID");
        }
        else {
            System.out.println("Illegal name");
        }
    }
}
