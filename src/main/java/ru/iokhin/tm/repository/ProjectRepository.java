package ru.iokhin.tm.repository;

import org.jetbrains.annotations.NotNull;
import ru.iokhin.tm.api.IProjectRepository;
import ru.iokhin.tm.entity.Project;

import java.util.*;

public final class ProjectRepository implements IProjectRepository {

    @NotNull
    public Map<String, Project> projectLinkedHashMap = new LinkedHashMap<>(0);

    @Override
    public void add(@NotNull Project project) {
        projectLinkedHashMap.put(project.getId(), project);
    }

    @Override
    public void list(@NotNull String userId) {
        int i = 0;
        for (@NotNull Project project : projectLinkedHashMap.values()) {
            if (project.getUserId().equals(userId)) {
                System.out.println(++i + ". " + project.toString());
            }
        }
    }

    @Override
    public void merge(@NotNull Project project) {
        projectLinkedHashMap.put(project.getId(), project);
    }

    @Override
    public void delete(@NotNull String id) {
        projectLinkedHashMap.remove(id);
    }

    @Override
    public void clear() {
        projectLinkedHashMap.clear();
    }

    @Override
    public Project findById(@NotNull String id) {
        return projectLinkedHashMap.get(id);
    }

    public Map<String, Project> getProjectLinkedHashMap() {
        return projectLinkedHashMap;
    }
}
