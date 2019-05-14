package ru.iokhin.tm.repository;

import ru.iokhin.tm.entity.Project;

import java.util.*;

public class ProjectRepository implements ProjectRepositoryInterface {

    public Map<String ,Project> projectLinkedHashMap = new LinkedHashMap<>(0);

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
        projectLinkedHashMap.merge(project.getId(), project, (oldVal, newVal) ->  new Project(newVal.getName(), oldVal.getId()));
    }

    @Override
    public void delete(String id) {
        projectLinkedHashMap.remove(id);
    }

    @Override
    public void clear() {
        projectLinkedHashMap.clear();
    }
}
