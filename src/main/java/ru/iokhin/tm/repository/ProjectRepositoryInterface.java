package ru.iokhin.tm.repository;

import ru.iokhin.tm.entity.Project;

public interface ProjectRepositoryInterface {

    public void add(Project project);
    public void list(String userId);
    public void merge(Project project);
    public void delete(String id);
    public void clear();

}
