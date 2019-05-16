package ru.iokhin.tm.repository;

import ru.iokhin.tm.api.IProjectRepository;
import ru.iokhin.tm.entity.Project;

import java.util.*;

public class ProjectRepository implements IProjectRepository {

    public Map<String, Project> projectLinkedHashMap = new LinkedHashMap<>(0);

    @Override
    public void add(Project project) {
        projectLinkedHashMap.put(project.getId(), project);
    }

    @Override
    public void list(String userId) {
        int i = 0;
        for (Project project : projectLinkedHashMap.values()) {
            if (project.getUserId().equals(userId)) {
                System.out.println(++i + ". " + project.toString());
            }
        }
    }

    @Override
    public void merge(Project project) {
        if (project == null) return;
        projectLinkedHashMap.put(project.getId(), project);
    }

    @Override
    public void delete(String id) {
        projectLinkedHashMap.remove(id);
    }

    @Override
    public void clear() {
        projectLinkedHashMap.clear();
    }

    @Override
    public Project findById(String id) {
        return projectLinkedHashMap.get(id);
    }
}
