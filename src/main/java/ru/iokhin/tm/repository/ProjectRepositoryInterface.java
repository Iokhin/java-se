package ru.iokhin.tm.repository;

import ru.iokhin.tm.entity.Project;

public interface ProjectRepositoryInterface {

    void add(Project project);
    void list(String userId);
    void merge(Project project);
    void delete(String id);
    void clear();

}
