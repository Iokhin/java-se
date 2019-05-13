package ru.iokhin.tm.repository;

import ru.iokhin.tm.entity.Project;
import ru.iokhin.tm.entity.User;

import java.util.*;

public class ProjectRepository {

    public Map<String ,Project> projectLinkedHashMap = new LinkedHashMap<>(0);

    public Project getProjectRepositoryItem(String id) {
        return projectLinkedHashMap.get(id);
    }

    public void  persistProjectRepositoryItem(Project project) {
        projectLinkedHashMap.put(project.getId(), project);
    }

    public void mergeProjectRepositoryItem(Project project) {
        projectLinkedHashMap.merge(project.getId(), project, (oldVal, newVal) ->  new Project(newVal.getName(), oldVal.getId()));
    }

    public void removeProjectRepositoryItem(String id) {
        projectLinkedHashMap.remove(id);
    }

    public void removeAllProjectRepositoryItem() {
        projectLinkedHashMap.clear();
    }

    public void findOneProjectRepositoryItem(String name) {
       for (Project project : projectLinkedHashMap.values()) {
           if (project.getName().contains(name)) {
               System.out.println(project.toString());
               return;
           }
       }
    }

    public void findAllProjectRepositoryItem(String name, String userId) {
       int i = 0;
       for (Project project : projectLinkedHashMap.values()) {
           if (project.getName().contains(name) && project.getUserId().equals(userId)) {
               System.out.println(++i + ". " + project.toString());
           }
       }
    }
}
