package ru.iokhin.tm.service;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.IProjectService;
import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.User;
import ru.iokhin.tm.repository.ProjectRepository;

import java.util.Map;

@RequiredArgsConstructor
public final class ProjectService implements IProjectService {

    @NotNull
    private final ProjectRepository projectRepository;

    @Override
    public void addProject(@NotNull String name, @NotNull User user) {
        if (name.trim().isEmpty()) {
            System.out.println("Illegal name");
            return;
        }
        projectRepository.add(new Project(name, user.getUserId()));
    }

    @Override
    public void listProject(@NotNull String userId) {
        projectRepository.list(userId);
    }

    @Override
    public void removeProject(@NotNull String id) {
        if (id.trim().isEmpty()) {
            System.out.println("Illegal ID");
            return;
        }

        @NotNull
        Project project = projectRepository.findById(id);

        if (project == null) {
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

    public Map<String, Project> getAllProjects() {
        return projectRepository.getProjectLinkedHashMap();
    }
}
