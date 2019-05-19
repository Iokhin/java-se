package ru.iokhin.tm.api;

import ru.iokhin.tm.entity.Project;

public interface IProjectRepository {

    void add(Project project);

    void list(String userId);

    void merge(Project project);

    void delete(String id);

    void clear();

    Project findById(String id);

}
