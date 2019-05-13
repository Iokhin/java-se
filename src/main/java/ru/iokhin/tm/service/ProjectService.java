package ru.iokhin.tm.service;

import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.repository.ProjectRepository;

public class ProjectService {

    private ProjectRepository pr;

    public void addProject(String name, User user) {
        if (name != null && !name.trim().isEmpty()) {
            pr.persistProjectRepositoryItem(new Project(name, user.getUserId()));
        }
        else {
            System.out.println("Illegal name");
        }

    }

    public void listProject(String userId) {
        pr.findAllProjectRepositoryItem("", userId);
    }

    public void removeProject(String id) {
        if (id != null && !id.trim().isEmpty()) {
            for (Project project : pr.projectLinkedHashMap.values()) {
                if (project.getId().equals(id)) {
                    pr.removeProjectRepositoryItem(id);
                    return;
                }
            }
        }
        else {
            System.out.println("Illegal name");
        }
    }

    public void clearProject() {
        pr.removeAllProjectRepositoryItem();
    }

    public void editProject(String id, String newName) {
        if (id != null && !id.trim().isEmpty() && newName != null && !newName.trim().isEmpty()) {
            for (Project project : pr.projectLinkedHashMap.values()) {
                if (project.getId().equals(id)) {
                    Project newProject = new Project(newName, project.getId());
                    pr.mergeProjectRepositoryItem(newProject);
                    return;
                }
            }
            System.out.println("NO PROJECT WITH SUCH ID");
        }
        else {
            System.out.println("Illegal name");
        }
    }

    public ProjectService(ProjectRepository pr) {
        this.pr = pr;
    }

}
